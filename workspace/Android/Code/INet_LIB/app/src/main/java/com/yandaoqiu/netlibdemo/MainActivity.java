package com.yandaoqiu.netlibdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.yandaoqiu.net.engine.INet;
import com.yandaoqiu.net.inter.INetConfig;
import com.yandaoqiu.net.inter.INetListener;
import com.yandaoqiu.net.projo.INetResponse;
import com.yandaoqiu.net.projo.INetTask;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.FormBody;

public class MainActivity extends AppCompatActivity implements INetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //重要
        INet.getInstatce().registerListener(this);
        //建议放在Application 中
        INet.getInstatce().config(new INetConfig() {
            /**
             * 基础URL，适合单一服务器请求，如果多服务器，不要设置该配置
             *
             * @return
             */
            @Override
            public String baseURL() {
                return "https://api.douban.com/v2/movie/";
            }
        });


        /**********模式一*************/
        //tag 标签建议用自增长0x1001 这样来管理，区分每一次请求，也可以为null,url可以是缩写，也可以全URL，但是config注意去除，requestType默认POST
        // 支持拦截器
        //支付表单 文件上传，在body构建
        {
            INetTask task = new INetTask.Builder().url("top250").tag("Get_Top250").json(MovieSubject.class).requestType("GET").build();
            INet.getInstatce().net(task);
        }

        {
            INetTask task = new INetTask.Builder().url("x3/weather").tag("POST_TASK").body(new FormBody.Builder().add("cityId","1054534").add("key","科幻").build()).build();
            INet.getInstatce().net(task);
        }

        /********模式二**********/
        final TestService service = INet.getInstatce().create(TestService.class);
        service.getTop250(0,10).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieSubject>() {
                    @Override
                    public void accept(MovieSubject movieSubject) throws Exception {
                        Log.e("Service",movieSubject.count+"");
                    }
                });
    }

    @Override
    public void end(INetResponse response) {

        Log.e("INetResponse",response.tag+"  "+response.taskid+" -------- "+response.xml);
        if ("Get_Top250".equals(response.tag)){
            MovieSubject movieSubject = (MovieSubject)response.json;
            Log.e("movieSubject",movieSubject.count+"");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //重要
        INet.getInstatce().unRegisterListner(this);
    }
}

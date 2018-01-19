package com.yandaoqiu.netlibdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;


import com.yandaoqiu.net.engine.INet;
import com.yandaoqiu.net.inter.INetListener;
import com.yandaoqiu.net.projo.INetResponse;
import com.yandaoqiu.net.projo.INetTask;
import com.yandaoqiu.net.ui.image.IImageLoader;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.FormBody;

public class MainActivity extends AppCompatActivity implements INetListener{

    private ImageView imageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //重要
        INet.getInstatce().registerListener(this);

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


        imageview = (ImageView) findViewById(R.id.imageview);
        //图片默认是缓存的，URL一样时默认会忽略当前特效
        //高斯模糊
        //IImageLoader.with(this).url("http://cdn.lizhi.fm/ad_cover/2018/01/19/2648112601926446638.jpg").needBlur(true).blurRadius(15).into(imageview);
        //漩涡
        //IImageLoader.with(this).url("http://cdn.lizhi.fm/ad_cover/2018/01/19/2648112601926446638.jpg").isNeedSwirl(true).into(imageview);
        //马赛克
        IImageLoader.with(this).url("http://cdn.lizhi.fm/ad_cover/2018/01/19/2648112601926446638.jpg").isNeedPixelation(true).pixelationLevel(10).into(imageview);
    }

    @Override
    public void end(INetResponse response) {
        Log.e("INetResponse",response.tag+"  "+response.taskid+" -------- "+response.xml +" "+response.erroMsg);
        if (response.isError){

            return;
        }
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

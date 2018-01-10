package com.yandaoqiu.iwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.yandaoqiu.net.engine.INet;
import com.yandaoqiu.net.inter.INetConfig;
import com.yandaoqiu.net.inter.INetListener;
import com.yandaoqiu.net.projo.INetResponse;
import com.yandaoqiu.net.projo.INetTask;

import java.util.Calendar;
import java.util.UUID;

import okhttp3.FormBody;


public class MainActivity extends AppCompatActivity implements INetListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        INet.getInstatce().registerListener(this);
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


        {
            INetTask task = new INetTask.Builder().url("top250").requestType("GET").build();
            INet.getInstatce().net(task);
        }

        {
            INetTask task = new INetTask.Builder().url("x3/weather").body(new FormBody.Builder().add("cityId","1054534").add("key","科幻").build()).build();
            INet.getInstatce().net(task);
        }

    }

    @Override
    public void end(INetResponse response) {
        Log.e("INetResponse",response.taskid+" -------- "+response.xml);
    }
}

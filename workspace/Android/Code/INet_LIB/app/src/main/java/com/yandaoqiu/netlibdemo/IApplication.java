package com.yandaoqiu.netlibdemo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.yandaoqiu.net.engine.INet;
import com.yandaoqiu.net.inter.INetConfig;
import com.yandaoqiu.net.inter.INetStateChangeListener;
import com.yandaoqiu.net.projo.INET_STATE;
import com.yandaoqiu.net.ui.image.IImageLoader;
import com.yandaoqiu.net.ui.image.IImageLoaderConfig;

/**
 * Created by yandaoqiu on 2018/1/18.
 */

public class IApplication extends Application implements INetStateChangeListener {
    @Override
    public void onCreate() {
        super.onCreate();
        //建议放在Application 中
        INet.getInstatce().init(getApplicationContext(),this);
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

        IImageLoader.init(new IImageLoaderConfig() {
            /**
             * 必须重写
             *
             * @return
             */
            @Override
            public Context context() {
                return getApplicationContext();
            }
        });
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        IImageLoader.onTrimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        IImageLoader.onLowMemory();
    }

    @Override
    public void onNetStateChange(INET_STATE state) {
        Log.e("onNetStateChange",state+"");
    }
}

package com.yandaoqiu.iwork;

import android.content.Context;
import android.util.Log;

import com.didi.virtualapk.PluginManager;
import com.yandaoqiu.iwork.base.BaseApplication;
import com.yandaoqiu.net.projo.INET_STATE;

/**
 * Created by yandaoqiu on 2018/1/26.
 */

public class IApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Log.d("IApplication","attachBaseContext");
        //初始化插件
        PluginManager.getInstance(base).init();
    }

    @Override
    public void onNetStateChange(INET_STATE state) {
        super.onNetStateChange(state);
    }
}

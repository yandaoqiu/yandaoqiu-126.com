package com.yandaoqiu.iwork;

import android.app.Application;
import android.content.Context;

import com.didi.virtualapk.PluginManager;

/**
 * Created by yandaoqiu on 2018/1/26.
 */

public class IApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //初始化插件
        PluginManager.getInstance(base).init();
    }
}

package com.yandaoqiu.iwork.base.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yandaoqiu.iwork.base.BaseApplication;

/**
 * Created by yandaoqiu on 2018/1/26.
 */

public class BaseActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((BaseApplication)getApplicationContext()).addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((BaseApplication)getApplicationContext()).removeActivity(this);
    }
}

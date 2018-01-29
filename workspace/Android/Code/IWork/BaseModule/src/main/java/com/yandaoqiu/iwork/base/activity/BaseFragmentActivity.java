package com.yandaoqiu.iwork.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.yandaoqiu.iwork.base.BaseApplication;

/**
 * Created by yandaoqiu on 2018/1/26.
 */

public class BaseFragmentActivity extends FragmentActivity {
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

package com.yandaoqiu.iwork.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.yandaoqiu.iwork.base.activity.BaseFragmentActivity;
import com.yandaoqiu.iwork.projo.HomePageItem;

import java.util.ArrayList;

/**
 * Created by yandaoqiu on 2018/1/29.
 */

public class HomeActivity extends BaseFragmentActivity {

    private ArrayList<HomePageItem> homePageItems;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.homePageItems = getHomePageItem();

    }

    /**
     * 获取主页配置
     * @return
     */
    private ArrayList<HomePageItem> getHomePageItem(){

        Bundle bundle =  getIntent().getBundleExtra(WelcomeActivity.INTENT_KEY_HOME_PAGE_BUNDLE);
        if (bundle!=null){
            return bundle.getParcelableArrayList(WelcomeActivity.INTENT_KEY_HOME_PAGE_ITEM);
        }
        return null;
    }





    public void leftOnclick(View view){

    }
    public void rightOnclick(View view){

    }


}

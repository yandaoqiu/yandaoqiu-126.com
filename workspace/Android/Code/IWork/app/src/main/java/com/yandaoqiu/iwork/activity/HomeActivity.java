package com.yandaoqiu.iwork.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.yandaoqiu.iwork.R;
import com.yandaoqiu.iwork.adapter.HomePagePageAdapter;
import com.yandaoqiu.iwork.base.activity.BaseFragmentActivity;
import com.yandaoqiu.iwork.base.fragment.BaseFragment;
import com.yandaoqiu.iwork.projo.HomePageItem;
import com.yandaoqiu.iwork.view.HomePageTopBar;

import java.util.ArrayList;

/**
 * Created by yandaoqiu on 2018/1/29.
 */

public class HomeActivity extends BaseFragmentActivity implements HomePageTopBar.OnSelectedListener{

    private ArrayList<HomePageItem> homePageItems;
    private ArrayList<BaseFragment> fragments = new ArrayList<>();

    private ViewPager mViewPager;
    private HomePageTopBar mHomePageTopBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home_page);
        this.homePageItems = getHomePageItem();

        initTopBar();
        try {
            initFragment();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void initTopBar(){
        mHomePageTopBar = (HomePageTopBar) findViewById(R.id.homepage_bar);
        mHomePageTopBar.setHomePageItems(homePageItems);
        mHomePageTopBar.setOnSelectedListener(this);
    }

    private void initFragment() throws Exception {
        this.mViewPager = (ViewPager) findViewById(R.id.homepage_viewPager);

        for (HomePageItem item : homePageItems){
            String className = item.getFragmentName();
            BaseFragment fragment = (BaseFragment)Class.forName(className).newInstance();
            fragments.add(fragment);

        }

        mViewPager.setAdapter(new HomePagePageAdapter(getSupportFragmentManager(),fragments));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mHomePageTopBar.setSelect(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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


    @Override
    public void onSelected(int newItem, int oldItem) {
        mViewPager.setCurrentItem(newItem);
    }
}

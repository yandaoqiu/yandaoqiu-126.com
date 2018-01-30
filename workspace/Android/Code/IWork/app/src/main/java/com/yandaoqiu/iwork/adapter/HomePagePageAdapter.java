package com.yandaoqiu.iwork.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yandaoqiu.iwork.base.fragment.BaseFragment;

import java.util.ArrayList;

/**
 * Created by yandaoqiu on 2018/1/30.
 */

public class HomePagePageAdapter extends FragmentPagerAdapter {

    private ArrayList<BaseFragment> fragments;

    public HomePagePageAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}

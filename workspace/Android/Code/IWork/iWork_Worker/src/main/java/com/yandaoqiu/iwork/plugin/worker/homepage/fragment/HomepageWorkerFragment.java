package com.yandaoqiu.iwork.plugin.worker.homepage.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yandaoqiu.iwork.base.fragment.BaseFragment;
import com.yandaoqiu.iwork.plugin.worker.R;

/**
 * Created by yandaoqiu on 2018/1/30.
 */

public class HomepageWorkerFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_worker_homepage,container,false);
        return view;
    }
}

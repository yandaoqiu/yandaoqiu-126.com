package com.yandaoqiu.iwork.plugin.sale.homepage.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yandaoqiu.iwork.base.fragment.BaseFragment;
import com.yandaoqiu.iwork.plugin.sale.R;
/**
 * Created by yandaoqiu on 2018/1/30.
 */

public class HomepageSaleFragment extends BaseFragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_sale_homepage,container,false);

        return view;
    }
}

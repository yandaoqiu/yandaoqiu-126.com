package com.yandaoqiu.iwork.plugin.job.homeage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.yandaoqiu.iwork.base.adapter.base.ViewHolder;
import com.yandaoqiu.iwork.plugin.job.R;
import com.yandaoqiu.iwork.plugin.job.homeage.view.HomePageJobItemView;

/**
 * Created by 15032065 on 18/2/11.
 */

public class HomepageJobAdapter extends RecyclerView.Adapter {

    private Context mContext;
    public HomepageJobAdapter(Context context){
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        HomePageJobItemView view = new HomePageJobItemView(parent.getContext());

        return new ViewHolder(parent.getContext(),view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 100;
    }
}

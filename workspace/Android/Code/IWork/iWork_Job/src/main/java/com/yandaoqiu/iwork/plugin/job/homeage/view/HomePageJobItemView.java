package com.yandaoqiu.iwork.plugin.job.homeage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.yandaoqiu.iwork.plugin.job.R;

/**
 * Created by yandaoqiu on 18/2/12.
 */

public class HomePageJobItemView extends FrameLayout {
    public HomePageJobItemView(Context context) {
        super(context);
        init(context);
    }

    public HomePageJobItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HomePageJobItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        inflate(context, R.layout.view_home_page_job_item,this);
    }

}

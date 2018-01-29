package com.yandaoqiu.iwork.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.yandaoqiu.iwork.R;
import com.yandaoqiu.iwork.projo.HomePageItem;

import java.util.ArrayList;

/**
 * Created by yandaoqiu on 2018/1/29.
 */


public class HomePageTopBar extends RelativeLayout implements View.OnClickListener{
    public OnSelectedListener listener;
    private ArrayList<HomePageItem> homePageItems;
    private ArrayList<Button> titleItemViews = new ArrayList<>();
    private int currentPostion = 0;
    private int oldPostion = 0;
    public HomePageTopBar(Context context) {
        super(context);
    }

    public HomePageTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HomePageTopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化
     * @param homePageItems
     */
    public void init(ArrayList<HomePageItem> homePageItems){
        this.homePageItems = homePageItems;
    }

    @Override
    public void onClick(View v) {
        if (titleItemViews.contains(v)){
            if (listener == null)return;
            int postion = titleItemViews.indexOf(v);
            if(setSelect(postion)){
                listener.onSelected(homePageItems.get(currentPostion),homePageItems.get(oldPostion));
            }
        }
    }

    /**
     * 选择了哪个标签
     * @param postion
     * @return false 表示当前不用更新
     */
    public boolean setSelect(int postion){
        if (postion >= homePageItems.size() || postion >= titleItemViews.size())return false;
        Button currentView = titleItemViews.get(currentPostion);
        currentView.setTextColor(getResources().getColor(R.color.color_817b74));
        Button nowView = titleItemViews.get(postion);
        nowView.setTextColor(getResources().getColor(R.color.color_fe5353));
        oldPostion = currentPostion;
        currentPostion = postion;
        return true;
    }




    public interface OnSelectedListener{
        void onSelected(HomePageItem newItem,HomePageItem oldItem);
    }
}

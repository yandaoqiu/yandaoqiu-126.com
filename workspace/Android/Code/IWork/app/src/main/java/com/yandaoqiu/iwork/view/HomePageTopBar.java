package com.yandaoqiu.iwork.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.yandaoqiu.iwork.R;
import com.yandaoqiu.iwork.base.utils.PublicUtils;
import com.yandaoqiu.iwork.projo.HomePageItem;

import java.util.ArrayList;

/**
 * Created by yandaoqiu on 2018/1/29.
 */

public class HomePageTopBar extends FrameLayout implements View.OnClickListener{


    private OnSelectedListener listener;
    private ArrayList<HomePageItem> homePageItems;
    private ArrayList<Button> titleItemViews = new ArrayList<>();
    private int currentPostion = 0;
    private int oldPostion = -1;

    private Context mContext;
    private LinearLayout mGropView;

    private HomePageTopBar(Context context) {
        super(context);
    }

    public HomePageTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }


    public HomePageTopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void setOnSelectedListener(OnSelectedListener listener) {
        this.listener = listener;
    }
    private void initView() {
        View view = inflate(mContext,R.layout.view_home_page_bar,this);
        mGropView = (LinearLayout) view.findViewById(R.id.homepage_bar_center);
    }
    /**
     * 初始化
     * @param homePageItems
     */
    public void setHomePageItems(ArrayList<HomePageItem> homePageItems){
        this.homePageItems = homePageItems;
        //按照4个来平分
        int width = PublicUtils.getScreenSize(mContext)[0] - PublicUtils.dip2px(mContext,80);
        int itemWidth = width/4;
        for (int i = 0 ; i < homePageItems.size() ; i++){
            HomePageItem homePageItem = homePageItems.get(i);
            Button titleItem = new Button(mContext);
            titleItem.setBackgroundColor(0);
            titleItem.setText(homePageItem.getTitle());
            titleItem.getPaint().setFakeBoldText(true);
            titleItem.setGravity(Gravity.CENTER);
            titleItem.setOnClickListener(this);
            if (i == currentPostion){
                titleItem.setTextSize(16);
                titleItem.setTextColor(getResources().getColor(R.color.color_fe5353));
            }else {
                titleItem.setTextSize(14);
                titleItem.setTextColor(getResources().getColor(R.color.color_a6a29c));
            }
            titleItem.setLayoutParams(new LinearLayout.LayoutParams(itemWidth, LinearLayout.LayoutParams.MATCH_PARENT));
            mGropView.addView(titleItem);
            titleItemViews.add(titleItem);
        }
    }

    @Override
    public void onClick(View v) {
        if (titleItemViews.contains(v)){
            int postion = titleItemViews.indexOf(v);
            if(setSelect(postion)){
                if (listener == null)return;
                listener.onSelected(currentPostion,oldPostion);
            }
        }
    }

    /**
     * 选择了哪个标签
     * @param postion
     * @return false 表示当前不用更新
     */
    public boolean setSelect(int postion){
        if (postion == currentPostion)return false;
        if (postion >= homePageItems.size() || postion >= titleItemViews.size())return false;
        Button currentView = titleItemViews.get(currentPostion);
        currentView.setTextColor(getResources().getColor(R.color.color_a6a29c));
        currentView.setTextSize(14);
        Button nowView = titleItemViews.get(postion);
        nowView.setTextSize(16);
        nowView.setTextColor(getResources().getColor(R.color.color_fe5353));
        oldPostion = currentPostion;
        currentPostion = postion;
        return true;
    }




    public interface OnSelectedListener{
        void onSelected(int newItem,int oldItem);
    }
}

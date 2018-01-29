package com.yandaoqiu.iwork.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

    private Context mContext;
    private LinearLayout mGropView;
    private LayoutInflater layoutInflater;

    private HomePageTopBar(Context context) {
        super(context);
    }

    public HomePageTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    private void initView() {
        this.layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.view_home_page_bar,null);
        mGropView = (LinearLayout) view.findViewById(R.id.homepage_bar_center);
    }

    public HomePageTopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 初始化
     * @param homePageItems
     */
    public void setHomePageItems(ArrayList<HomePageItem> homePageItems){
        this.homePageItems = homePageItems;
        for (int i = 0 ; i < homePageItems.size() ; i++){
            HomePageItem homePageItem = homePageItems.get(i);
            Button titleItem = new Button(mContext);
            titleItem.setBackgroundColor(0);
            titleItem.setText(homePageItem.getTitle());
            titleItem.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            mGropView.addView(titleItem);
        }
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

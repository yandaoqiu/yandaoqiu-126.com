package com.yandaoqiu.iwork.plugin.job.homeage.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.yandaoqiu.iwork.plugin.job.R;
import com.yandaoqiu.iwork.plugin.job.homeage.projo.SubBannerRichTextItem;
import com.yandaoqiu.net.ui.image.IImageLoader;
import com.yandaoqiu.net.ui.image.projo.ShapeMode;

import java.util.List;

/**
 * Created by yandaoqiu on 2018/2/2.
 */

public class HomePageJobTopView extends LinearLayout{

    private ConvenientBanner mBanner;
    private ConvenientBanner mSubBanner;


    public HomePageJobTopView(Context context) {
        super(context);
        init(context);
    }

    public HomePageJobTopView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HomePageJobTopView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        View view = inflate(context, R.layout.view_job_homepage_job_top,this);
        mBanner = (ConvenientBanner) view.findViewById(R.id.convenientBanner);
        mSubBanner = (ConvenientBanner) view.findViewById(R.id.subConvenientBanner);
        mSubBanner.setManualPageable(true);
    }



    public void initData(List<String> netImages,List<SubBannerRichTextItem> richTextItemList){
        mBanner.setOnItemClickListener(new com.bigkoo.convenientbanner.listener.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (listener != null){
                    listener.onItemClick(mBanner,position);
                }
                Log.d("HomePageJobTopView","onBannerItemClick "+position);
            }
        });

        mSubBanner.setOnItemClickListener(new com.bigkoo.convenientbanner.listener.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (listener != null){
                    listener.onItemClick(mBanner,position);
                }
                Log.d("HomePageJobTopView","onSubBannerItemClick "+position);
            }
        });

        mBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        },netImages).setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused}).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

        mSubBanner.setPages(new CBViewHolderCreator<NetworkRichTextHolderView>(){

            @Override
            public NetworkRichTextHolderView createHolder() {
                return new NetworkRichTextHolderView();
            }
        },richTextItemList);
    }

    public void refushBanner(boolean isVisible){
        if (isVisible) {
            //更新界面数据，如果数据还在下载中，就显示加载框
            mBanner.startTurning(5000);
            mSubBanner.startTurning(8000);
        } else {
            //关闭加载框
            mBanner.stopTurning();
            mSubBanner.stopTurning();
        }
    }

    private OnItemClickListener listener;
    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }


    public class NetworkImageHolderView implements Holder<String> {
        private ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            IImageLoader.with(context).placeHolderResId(R.mipmap.ic_banner_nom).url(data).shapeMode(ShapeMode.RECT_ROUND).rectRoundRadius(8).into(imageView);
        }
    }




    public class NetworkRichTextHolderView implements Holder<SubBannerRichTextItem>{

        private LayoutInflater inflater;
        private View view;
        @Override
        public View createView(Context context) {
            if (inflater == null)inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.view_sub_banner,null);
            return view;
        }

        @Override
        public void UpdateUI(Context context, int position, SubBannerRichTextItem data) {
            TextView textView = (TextView) view.findViewById(R.id.sub_banner_titleView);
            ImageView icon01 = (ImageView) view.findViewById(R.id.sub_banner_icon01);
            ImageView icon02 = (ImageView) view.findViewById(R.id.sub_banner_icon02);
            ImageView icon03 = (ImageView) view.findViewById(R.id.sub_banner_icon03);
            textView.setText(data.getTitle());
            IImageLoader.with(context).url(data.getImages().get(0)).shapeMode(ShapeMode.OVAL).into(icon01);
            IImageLoader.with(context).url(data.getImages().get(1)).shapeMode(ShapeMode.OVAL).into(icon02);
            IImageLoader.with(context).url(data.getImages().get(2)).shapeMode(ShapeMode.OVAL).into(icon03);

        }
    }
}

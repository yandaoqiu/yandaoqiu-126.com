package com.yandaoqiu.iwork.plugin.job.homeage.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.yandaoqiu.iwork.base.fragment.BaseFragment;
import com.yandaoqiu.iwork.plugin.job.R;
import com.yandaoqiu.iwork.plugin.job.homeage.projo.SubBannerRichTextItem;
import com.yandaoqiu.net.ui.image.IImageLoader;
import com.yandaoqiu.net.ui.image.projo.ShapeMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yandaoqiu on 2018/1/30.
 */

public class HomepageJobFragment extends BaseFragment{

    private ConvenientBanner mBanner;
    private ConvenientBanner mSubBanner;
    private View mView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getActivity().getLayoutInflater().inflate(R.layout.layout_job_homepage,null);
        mBanner = (ConvenientBanner) view.findViewById(R.id.convenientBanner);
        mSubBanner = (ConvenientBanner) view.findViewById(R.id.subConvenientBanner);
        mSubBanner.setManualPageable(true);
        mView = view;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ViewGroup mGroup=(ViewGroup) mView.getParent();
        if(mGroup!=null){
           mGroup.removeAllViewsInLayout();
        }
    }

    //状态变化（包含第一次）
    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
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

    //第一次可见
    @Override
    protected void onFragmentFirstVisible() {
        Log.d("HomePageJobFragment","onFragmentFirstVisible");
        mBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("HomepageJobFragment","onBannerItemClick "+position);
            }
        });

        mSubBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("HomepageJobFragment","onSubBannerItemClick "+position);
            }
        });
        loadBanner();
    }


    public void loadBanner(){
        //去服务器下载数据,滚轮展示的内容
        //模拟
        String[] images = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
                "http://img2.3lian.com/2014/f2/37/d/40.jpg",
                "http://d.3987.com/sqmy_131219/001.jpg",
                "http://img2.3lian.com/2014/f2/37/d/39.jpg",
                "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
                "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
                "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
        };
        List<String> netImages = Arrays.asList(images);
        mBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        },netImages).setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused}).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);




        //加载subBanner
        List<SubBannerRichTextItem> richTextItemList = new ArrayList<>();
        {
            SubBannerRichTextItem item = new SubBannerRichTextItem();
            item.setUrl("www.baidu.com");
            item.setTitle("今日最佳");
            String x[] = {"http://cdn.lizhi.fm//user/2018/01/30/2650170574162804738_640x640.jpg","http://cdn.lizhi.fm//studio/2018/02/01/2650491141929982006_640x640.jpg","http://cdn.lizhi.fm//studio/2018/02/01/2650407452344742454_640x640.jpg"};
            List<String> itemImages = Arrays.asList(x);
            item.setImages(itemImages);
            richTextItemList.add(item);
        }
        {
            SubBannerRichTextItem item = new SubBannerRichTextItem();
            item.setUrl("www.baidu.com");
            item.setTitle("本月劳模");
            String x[] = {"http://cdn.lizhi.fm//user/2017/11/28/2638401908506044930_640x640.jpg","http://cdn.lizhi.fm//studio/2018/02/01/2650491141929982006_640x640.jpg","http://cdn.lizhi.fm//studio/2018/02/01/2650407452344742454_640x640.jpg"};
            List<String> itemImages = Arrays.asList(x);
            item.setImages(itemImages);
            richTextItemList.add(item);
        }
        {
            SubBannerRichTextItem item = new SubBannerRichTextItem();
            item.setUrl("www.baidu.com");
            item.setTitle("♥最佳老板♥");
            String x[] = {"http://cdn.lizhi.fm//studio/2018/02/01/2650503670352352310_640x640.jpg","http://cdn.lizhi.fm//studio/2018/01/31/2650386997563251254_640x640.jpg","http://cdn.lizhi.fm//studio/2018/02/01/2650407452344742454_640x640.jpg"};
            List<String> itemImages = Arrays.asList(x);
            item.setImages(itemImages);
            richTextItemList.add(item);
        }

        mSubBanner.setPages(new CBViewHolderCreator<NetworkRichTextHolderView>(){

            @Override
            public NetworkRichTextHolderView createHolder() {
                return new NetworkRichTextHolderView();
            }
        },richTextItemList);

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

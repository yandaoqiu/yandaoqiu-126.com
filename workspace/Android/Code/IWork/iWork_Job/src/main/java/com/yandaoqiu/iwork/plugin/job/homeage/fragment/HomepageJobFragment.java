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

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.yandaoqiu.iwork.base.fragment.BaseFragment;
import com.yandaoqiu.iwork.plugin.job.R;
import com.yandaoqiu.net.ui.image.IImageLoader;
import com.yandaoqiu.net.ui.image.projo.ShapeMode;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yandaoqiu on 2018/1/30.
 */

public class HomepageJobFragment extends BaseFragment{

    private ConvenientBanner mBanner;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_job_homepage,container,false);
        mBanner = (ConvenientBanner) view.findViewById(R.id.convenientBanner);
        return view;
    }

    //状态变化（包含第一次）
    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible) {
            //更新界面数据，如果数据还在下载中，就显示加载框
            mBanner.startTurning(5000);
        } else {
            //关闭加载框
            mBanner.stopTurning();
        }
    }

    //第一次可见
    @Override
    protected void onFragmentFirstVisible() {
        Log.d("HomePageJobFragment","onFragmentFirstVisible");
        mBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("HomepageJobFragment","onItemClick "+position);
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
        List<String> netImages = Arrays.asList(images);;
        mBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        },netImages).setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused}).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

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
}

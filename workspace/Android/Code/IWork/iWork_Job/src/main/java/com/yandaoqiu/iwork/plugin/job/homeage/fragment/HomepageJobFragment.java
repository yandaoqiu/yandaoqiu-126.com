package com.yandaoqiu.iwork.plugin.job.homeage.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yandaoqiu.iwork.base.adapter.wapper.HeaderAndFooterWrapper;
import com.yandaoqiu.iwork.base.fragment.BaseFragment;
import com.yandaoqiu.iwork.plugin.job.R;
import com.yandaoqiu.iwork.plugin.job.homeage.adapter.HomepageJobAdapter;
import com.yandaoqiu.iwork.plugin.job.homeage.projo.JobMenyTypeItem;
import com.yandaoqiu.iwork.plugin.job.homeage.projo.SubBannerRichTextItem;
import com.yandaoqiu.iwork.plugin.job.homeage.view.HomePageJobTopView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yandaoqiu on 2018/1/30.
 */

public class HomepageJobFragment extends BaseFragment implements HomePageJobTopView.OnItemClickListener{

    private RecyclerView mRecyclerView;
    private HomePageJobTopView mHomePageJobTopView;


    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private HomepageJobAdapter mHomepageJobAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerView = (RecyclerView) getActivity().getLayoutInflater().inflate(R.layout.layout_job_homepage,null);
        GridLayoutManager manager = new GridLayoutManager(getContext(),2);
        mRecyclerView.setLayoutManager(manager);
        mHomepageJobAdapter = new HomepageJobAdapter();
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mHomepageJobAdapter);

        mHomePageJobTopView = new HomePageJobTopView(getContext());

        mHeaderAndFooterWrapper.addHeaderView(mHomePageJobTopView);

        mRecyclerView.setAdapter(mHeaderAndFooterWrapper);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return mRecyclerView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ViewGroup mGroup=(ViewGroup) mRecyclerView.getParent();
        if(mGroup!=null){
           mGroup.removeAllViewsInLayout();
        }
    }

    //状态变化（包含第一次）
    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible) {
            //更新界面数据，如果数据还在下载中，就显示加载框

        } else {
            //关闭加载框

        }

        mHomePageJobTopView.refushBanner(isVisible);
    }

    //第一次可见
    @Override
    protected void onFragmentFirstVisible() {
        Log.d("HomePageJobFragment","onFragmentFirstVisible");
        mHomePageJobTopView.setListener(this);
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



        ArrayList<JobMenyTypeItem> meun = new ArrayList<>();
        {
            JobMenyTypeItem item = new JobMenyTypeItem();
            item.setSelected(true);
            item.setName("数码");
            item.setIamge(R.drawable.dianzi+"");
            item.setSelectedImage(R.drawable.dianzhi_selected+"");
            meun.add(item);
        }

        {
            JobMenyTypeItem item = new JobMenyTypeItem();
            item.setName("维修");
            item.setIamge(R.drawable.weixiu+"");
            item.setSelectedImage(R.drawable.weixiu_selected+"");
            meun.add(item);
        }

        {
            JobMenyTypeItem item = new JobMenyTypeItem();
            item.setName("安装");
            item.setIamge(R.drawable.anzhuang+"");
            item.setSelectedImage(R.drawable.anzhuang_selected+"");
            meun.add(item);
        }

        {
            JobMenyTypeItem item = new JobMenyTypeItem();
            item.setName("跑腿");
            item.setIamge(R.drawable.paotui+"");
            item.setSelectedImage(R.drawable.paotui_selected+"");
            meun.add(item);
        }

        {
            JobMenyTypeItem item = new JobMenyTypeItem();
            item.setName("其它");
            item.setIamge(R.drawable.other +"");
            item.setSelectedImage(R.drawable.other_selected +"");
            meun.add(item);
        }

        mHomePageJobTopView.initData(netImages,richTextItemList);
        mHomePageJobTopView.initMenu(meun);
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.d("HomepageJobFragment","onItemClick "+ view.getClass().getName()+" "+ position);
    }
}

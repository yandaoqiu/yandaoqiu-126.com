package com.yandaoqiu.iwork.activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;

import com.didi.virtualapk.PluginManager;
import com.yandaoqiu.iwork.base.activity.BaseActivity;
import com.yandaoqiu.iwork.projo.HomePageItem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yandaoqiu on 2018/1/26.
 */

public class WelcomeActivity extends BaseActivity {
    public static final String INTENT_KEY_HOME_PAGE_BUNDLE = "INTENT_KEY_HOME_PAGE_BUNDLE";
    public static final String INTENT_KEY_HOME_PAGE_ITEM = "INTENT_KEY_HOME_PAGE_ITEM";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //负责启动广告
        //负责加载主页的Item//TODO 加载插件的时候 禁止back按钮

        Observable.create(new ObservableOnSubscribe<ArrayList<HomePageItem>>() {
            @Override
            public void subscribe(ObservableEmitter<ArrayList<HomePageItem>> e) throws Exception {
                ArrayList<HomePageItem> homePageItems = initHomePage();
                loadPlugs(homePageItems);
                e.onNext(homePageItems);
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<HomePageItem>>() {
                    @Override
                    public void accept(ArrayList<HomePageItem> homePageItems) throws Exception {
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList(INTENT_KEY_HOME_PAGE_ITEM,homePageItems);
                        intent.putExtra(INTENT_KEY_HOME_PAGE_BUNDLE,bundle);
                        intent.setClass(WelcomeActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }

    private void loadPlugs(ArrayList<HomePageItem> homePageItems){
        InputStream is = null;
        FileOutputStream out = null;

        String path =  Environment.getExternalStorageDirectory().toString();   //Sdcard
        try {
            AssetManager am = getAssets()  ;
            for (int i = 0;i<homePageItems.size();i++){
                HomePageItem homePageItem = homePageItems.get(i);
                String fileName = homePageItem.getPluginName();
                is = am.open(fileName);

                out = new FileOutputStream(new File(path+"/"+fileName));
                int length = -1;
                byte[] buf = new byte[1024];
                while ((length = is.read(buf)) != -1) {
                    out.write(buf, 0, length);
                }
                out.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (is != null)
                    is.close();
                if (out != null)
                    out.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        try{
            for (int i = 0;i<homePageItems.size();i++) {
                HomePageItem homePageItem = homePageItems.get(i);
                String fileName = homePageItem.getPluginName();
                PluginManager.getInstance(this).loadPlugin(new File(path+"/"+fileName));
                Log.d("LoadPlugin",fileName+" 加载成功！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 初始化HomePageItem
     * @return
     */
    private ArrayList<HomePageItem> initHomePage(){
        ArrayList<HomePageItem> homePageItems = new ArrayList<>();
        //TODO 模拟
        {
            HomePageItem homePageItem = new HomePageItem();
            homePageItem.setTitle("找工作");
            homePageItem.setPluginName("iWork_Job.apk");
            homePageItem.setPackageName("com.yandaoqiu.iwork.plugin.job");
//            homePageItem.setActivityName("HomepageJobActivity");
            homePageItem.setFragmentName("HomepageJobFragment");
            homePageItems.add(homePageItem);
        }
        {
            HomePageItem homePageItem = new HomePageItem();
            homePageItem.setTitle("找贤士");
            homePageItem.setPluginName("iWork_Worker.apk");
            homePageItem.setPackageName("com.yandaoqiu.iwork.plugin.worker");
//            homePageItem.setActivityName("HomepageWorkerActivity");
            homePageItem.setFragmentName("HomepageWorkerFragment");
            homePageItems.add(homePageItem);
        }
        {
            HomePageItem homePageItem = new HomePageItem();
            homePageItem.setTitle("拍卖区");
            homePageItem.setPluginName("iWork_Sale.apk");
            homePageItem.setPackageName("com.yandaoqiu.iwork.plugin.sale");
//            homePageItem.setActivityName("HomepageSaleActivity");
            homePageItem.setFragmentName("HomepageSaleFragment");
            homePageItems.add(homePageItem);
        }

        return homePageItems;
    }

}

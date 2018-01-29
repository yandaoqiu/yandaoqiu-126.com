package com.yandaoqiu.iwork.projo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yandaoqiu on 2018/1/29.
 */

public class HomePageItem implements Parcelable {
    private String title;//标题
    private String pluginName;//插件名
    private String packageName;//包名
    private String activityName;//主页面
    private String fragmentName;//frgement名称

    public HomePageItem(){

    }

    public HomePageItem(Parcel in) {
        title = in.readString();
        pluginName = in.readString();
        packageName = in.readString();
        activityName = in.readString();
        fragmentName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(pluginName);
        dest.writeString(packageName);
        dest.writeString(activityName);
        dest.writeString(fragmentName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HomePageItem> CREATOR = new Creator<HomePageItem>() {
        @Override
        public HomePageItem createFromParcel(Parcel in) {
            return new HomePageItem(in);
        }

        @Override
        public HomePageItem[] newArray(int size) {
            return new HomePageItem[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getFragmentName() {
        return fragmentName;
    }

    public void setFragmentName(String fragmentName) {
        this.fragmentName = fragmentName;
    }

}

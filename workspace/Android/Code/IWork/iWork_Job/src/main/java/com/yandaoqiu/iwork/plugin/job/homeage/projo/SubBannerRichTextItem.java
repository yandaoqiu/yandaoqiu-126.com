package com.yandaoqiu.iwork.plugin.job.homeage.projo;

import java.util.List;

/**
 *  主页第二滚动栏实体
 * Created by yandaoqiu on 2018/2/1.
 */

public class SubBannerRichTextItem {

    private long actionID;
    private String title;
    private List<String> images;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    public long getActionID() {
        return actionID;
    }

    public void setActionID(long actionID) {
        this.actionID = actionID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }



}

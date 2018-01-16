package com.yandaoqiu.net.projo;

import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.RequestBody;


/**
 * Created by yandaoqiu on 2018/1/8.
 */

public final class INetTask {

    private INetTask(Builder builder){
        this.level = builder.level;
        this.taskid = taskid();
        this.url = builder.url;
        this.isBackWithMain = builder.isBackWithMain;
        this.jsonObj = builder.jsonObj;
        this.xml = builder.xml;
        this.state = builder.state;
        this.requestType = builder.requestType;
        this.body = builder.body;
        this.headers = builder.headers;
        this.tag = builder.tag;
        this.ignoreBaseURL = builder.ignoreBaseURL;
    }
    //请求等级，默认高优先
    public enum INET_LEVEL{
        LEVEL_HIGHT,//高优先
        LVEEL_LOW,//低优先
    }

    //请求等级，默认高优先
    public enum INET_PROGRESS{
        PROGRESS_READY,//组装完成
        PROGRESS_WAITING,//等待
        PROGRESS_REQUESTING,//请求中
        PROGRESS_DONE,//完成
    }

    //任务id
    private long taskid ;
    //请求等级
    private INET_LEVEL level = INET_LEVEL.LEVEL_HIGHT;
    //请求地址
    private String url;
    //是否是主线程，默认主线程
    private boolean isBackWithMain = true;
    //json转换对象
    private Class<?> jsonObj;
    //xml结构 暂不支持
    private boolean xml;
    //请求状态
    private INET_PROGRESS state;
    //请求方式
    private String requestType = "POST";
    //请求体
    private RequestBody body;
    //自定义头
    private Headers headers;



    //该Task忽略BaseUrl
    private boolean ignoreBaseURL;






    public Call okHttpCall;
    public Object tag;





    public boolean isIgnoreBaseURL() {
        return ignoreBaseURL;
    }
    public RequestBody getBody() {
        return body;
    }
    public Headers getHeaders() {
        return headers;
    }
    public boolean isBackWithMain() {
        return isBackWithMain;
    }

    public void setState(INET_PROGRESS state) {
        this.state = state;
    }

    public INET_PROGRESS getState() {
        return state;
    }


    public String getRequestType() {
        return requestType;
    }

    public Class<?> getJsonObj() {
        return jsonObj;
    }

    public boolean isXml() {
        return xml;
    }

    public INET_LEVEL getLevel() {
        return level;
    }
    public String getUrl() {
        return url;
    }

    public boolean getIsBackWithMain() {
        return isBackWithMain;
    }

    private void setTaskid(long taskid) {
        this.taskid = taskid;
    }
    public long getTaskid() {
        if (taskid == 0)setTaskid(taskid());
        return taskid;
    }





    private long taskid(){
        return System.nanoTime();
    }

    public static class Builder{

        INET_LEVEL level = INET_LEVEL.LEVEL_HIGHT;
        String url;
        boolean isBackWithMain = true;
        Class<?> jsonObj;
        boolean xml;
        INET_PROGRESS state;
        String requestType = "POST";
        RequestBody body;
        Headers headers;
        Object tag;
        boolean ignoreBaseURL;

        public Builder ignoreBaseURL(boolean ignore){
            this.ignoreBaseURL = ignore;
            return this;
        }

        public Builder tag(Object tag){
            this.tag = tag;
            return this;
        }
        public Builder requestType(Headers headers){
            this.headers = headers;
            return this;
        }

        public Builder requestType(String requestType){
            this.requestType = requestType;
            return this;
        }

        public Builder level(INET_LEVEL level){
            this.level = level;
            return this;
        }

        /**
         * 设置URL
         * 注意：如没在config中配置了BaseURL，此处需要完整URL
         * @param url
         * @return
         */
        public Builder url(String url){
            this.url = url;
            return this;
        }

        public Builder body(RequestBody body){
            this.body = body;
            return this;
        }

        public Builder isBackWithMain(boolean isBackWithMain){
            this.isBackWithMain = isBackWithMain;
            return this;
        }
        public Builder json(Class<?> cz){
            this.jsonObj = cz;
            return this;
        }

        public Builder xml(){
            this.xml = true;
            return this;
        }

        public INetTask build(){
            state = INET_PROGRESS.PROGRESS_READY;
            return new INetTask(this);
        }
    }



}



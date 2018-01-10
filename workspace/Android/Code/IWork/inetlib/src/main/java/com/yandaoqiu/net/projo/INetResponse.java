package com.yandaoqiu.net.projo;

import com.google.gson.Gson;
import com.yandaoqiu.net.engine.INet;

import okhttp3.Response;

/**
 * Created by Administrator on 2018/1/8.
 */

public class INetResponse {
    public Object json;
    public String xml;
    public Response response;
    public long taskid;
    public boolean isError;
    public String erroMsg;
    public int code;
}

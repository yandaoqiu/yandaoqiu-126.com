package com.yandaoqiu.net.inter;

import android.content.Context;

import com.yandaoqiu.net.projo.INetResponse;
import com.yandaoqiu.net.projo.INetTask;

import okhttp3.Interceptor;

/**
 * Created by yandaoqiu on 2018/1/8.
 */

public interface INetInter {

    /**
     *  初始化，建议在Application中调用
     * @param context
     * @param listener 网络状态切换
     */
    void init(Context context,INetStateChangeListener listener);
    /**
     * 异步请求
     * @param task
     * @return taskid,区分请求
     */
    long net(INetTask task);

    /**
     * 监听
     * @param listener
     */
    void registerListener(INetListener listener);

    /**
     * 移除监听
     * @param listener
     */
    void unRegisterListner(INetListener listener);
    /**
     * 取消请求
     * @param task
     */
    void cancel(INetTask task);

    /**
     * 取消请求
     * @param taskid
     */
    void cancel(long taskid);
    /**
     * 设置配置
     * @param config
     */
    void config(INetConfig config);

    /**
     * 添加应用拦截器
     * @param interceptor
     */
    void addInterceptor(Interceptor interceptor);

    /**
     * 添加网络层拦截器
     * @param interceptor
     */
    void addNetworkInterceptor(Interceptor interceptor);

    /**
     * Retrofit 模式的支持
     * @param service
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service);
}

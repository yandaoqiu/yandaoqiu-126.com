package com.yandaoqiu.net.inter;

import com.yandaoqiu.net.projo.INetResponse;
import com.yandaoqiu.net.projo.INetTask;

/**
 * Created by yandaoqiu on 2018/1/8.
 */

public interface INetInter {

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

}

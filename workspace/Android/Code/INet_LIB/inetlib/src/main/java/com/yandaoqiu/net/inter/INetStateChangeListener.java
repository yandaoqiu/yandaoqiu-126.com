package com.yandaoqiu.net.inter;

import com.yandaoqiu.net.projo.INET_STATE;

/**
 * Created by Administrator on 2018/1/10.
 */

public interface INetStateChangeListener {
    void onNetStateChange(INET_STATE state);
}

package com.yandaoqiu.net.ui;

import android.view.View;

/**
 * Created by yandaoqiu on 2018/1/12.
 */

public interface ILoader {

    /**
     * 暂停所有（包括加载缓存）
     */
    void pause();

    /**
     * 恢复所有
     */
    void resume();

    /**
     * 取消加载
     * @param view
     */
    void clear(View view);

    /**
     * 清除磁盘存储
     */
    void clearDiskCache();

    /**
     * 清除缓存
     */
    void clearMemory();

    /**
     * 降级
     * @param level
     */
    void onTrimMemory(int level);

}

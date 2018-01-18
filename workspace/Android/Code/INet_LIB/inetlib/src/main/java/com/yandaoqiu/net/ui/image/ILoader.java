package com.yandaoqiu.net.ui.image;

import android.content.Context;
import android.view.View;

import com.bumptech.glide.MemoryCategory;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.yandaoqiu.net.ui.image.projo.IImageTask;
import com.yandaoqiu.net.ui.image.utils.DownLoadImageTask;

/**
 * Created by yandaoqiu on 2018/1/12.
 */
public interface ILoader {

    /**
     *  初始化
     * @param context 上下文
     * @param factory 磁盘缓存
     * @param memoryCategory 内存等级
     * @param bitmapPool
     * @param memoryCache
     */
    void init(Context context, DiskCache.Factory factory, MemoryCategory memoryCategory, BitmapPool bitmapPool, MemoryCache memoryCache, DecodeFormat decodeFormat);

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

    void onLowMemory();

    /**
     * 执行图片
     * @param task
     */
    void request(IImageTask task);

    /**
     *  保存图片到相册
     * @param downLoadImageTask
     */
    void saveImageIntoGallery(DownLoadImageTask downLoadImageTask);
}

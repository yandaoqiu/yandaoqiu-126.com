package com.yandaoqiu.net.ui.image;


import android.content.Context;
import android.view.View;

import com.yandaoqiu.net.ui.image.projo.IImageTask;
import com.yandaoqiu.net.ui.image.utils.DownLoadImageTask;

/**
 * 对外调用类
 * Created by yandaoqiu on 2018/1/12.
 */

public class IImageLoader {

    public static void init(IImageLoaderConfig config){
        getILoader().init(config.context(),config.diskFactory(),config.memoryCategory(),config.bitmapPool(),config.memoryCache(),config.decodeFormat());
    }

    /**
     * 获取当前的Loader
     * @return
     */
    protected static ILoader getILoader() {
        return IImageLoaderConfig.getLoader();
    }

    /**
     * 加载图片
     *
     * @param
     * @return
     */
    public static IImageTask.Builder with(Context context) {
        return new IImageTask.Builder(context,getILoader());
    }

    /**
     * 降级
     * @param level
     */
    public static void onTrimMemory(int level) {
        getILoader().onTrimMemory(level);
    }

    public static void onLowMemory() {
        getILoader().onLowMemory();
    }
    /**
     * 清除缓存(必须在UI线程中调用)
     */
    public static void clearMemory() {
        getILoader().clearMemory();
    }

    /**
     * 暂停
     */
    public static void pause() {
        getILoader().pause();
    }

    /**
     * 恢复
     */
    public static void resume() {
        getILoader().resume();
    }

    /**
     * The view to cancel loads and free resources for.
     * @param view
     */
    public static void clear(View view) {
        getILoader().clear(view);
    }


    /**
     * 清空磁盘存储 （必须在后台线程中调用）
     */
    public static void clearDiskCache() {
        getILoader().clearDiskCache();
    }


    /**
     * 图片保存到相册
     *
     * @param downLoadImageTask
     */
    public static void saveImageIntoGallery(DownLoadImageTask downLoadImageTask) {
        getILoader().saveImageIntoGallery(downLoadImageTask);
    }
}

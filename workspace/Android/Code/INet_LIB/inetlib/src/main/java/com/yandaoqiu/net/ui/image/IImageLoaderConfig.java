package com.yandaoqiu.net.ui.image;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.bumptech.glide.MemoryCategory;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemoryCache;


/**
 * Created by yandaoqiu on 2018/1/18.
 */

public abstract class IImageLoaderConfig {

    private static ILoader loader;
    public synchronized static ILoader getLoader(){
        if (loader == null){
            loader = new ILoaderImpl();
        }
        return loader;
    }
    private static Handler mainHandler;

    public static Handler getMainHandler() {
        if (mainHandler == null) {
            mainHandler = new Handler(Looper.getMainLooper());
        }
        return mainHandler;
    }
    /**
     * 必须重写
     * @return
     */
    public Context context(){
        return null;
    }
    /**
     * 默认内存 设置图片内存缓存占用八分之一
     * @return
     */
    public int memoryCacheSize(){
        int maxMemory = (int) Runtime.getRuntime().maxMemory();//获取系统分配给应用的总内存大小
        int memoryCacheSize = maxMemory / 8;//设置图片内存缓存占用八分之一
        return memoryCacheSize;
    }

    /**
     * 文件存储 250MB
     * @return
     */
    public int diskCacheSize(){
        return 250;
    }

    /**
     * 内存等级 默认MemoryCategory.NORMAL
     * @return
     */
    public MemoryCategory memoryCategory(){
        return MemoryCategory.NORMAL;
    }


    /**
     * 磁盘缓存文件夹（建议重新设置）
     * @return
     */
    public String diskCacheFileName(){
        return "IImageLoaderCache";
    }

    /**
     * 设置磁盘缓存是否在SD卡(默认在data/data)
     * @return
     */
    public boolean diskCacheisInSDCard(){
        return false;
    }

    /**
     * 存放在data/data/xxxx/cache/
     * new InternalCacheDiskCacheFactory
     * 存放在外置文件浏览器
     * new ExternalCacheDiskCacheFactory
     * @return
     */
    protected DiskCache.Factory diskFactory(){
        Context context = context();
        if (context == null)throw  new RuntimeException("Init DiskFactory Error,You need override IImageLoaderConfig context() first！");
        if (diskCacheisInSDCard()){
            return new InternalCacheDiskCacheFactory(context,diskCacheFileName(),diskCacheSize()*1024*1024);
        }else {
            return new ExternalCacheDiskCacheFactory(context, diskCacheFileName(), diskCacheSize() * 1024 * 1024);
        }
    }

    protected BitmapPool bitmapPool(){
        return new LruBitmapPool(memoryCacheSize());
    }
    protected MemoryCache memoryCache(){
        return new LruResourceCache(memoryCacheSize());
    }
    /**
     * 设置图片解码格式
     * @return
     */
    public DecodeFormat decodeFormat(){
        return DecodeFormat.PREFER_ARGB_8888;
    }

}

package com.yandaoqiu.net.ui.image.utils;

import android.content.Context;
import android.text.TextUtils;

import com.yandaoqiu.net.ui.image.IImageLoaderConfig;
import com.yandaoqiu.net.ui.image.projo.IImageTask;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by yandaoqiu on 2018/1/18.
 */

public class ILoaderUitls {
    /**
     * 判断是否需要加载默认图
     * @param task
     * @return
     */
    public static boolean hasPlaceHolder(IImageTask task) {
        if (task.getPlaceHolderResId() <= 0) {
            return false;
        }
        //只有在图片源为网络图片,并且图片没有缓存到本地时,才给显示placeholder
        if (task.getResId() > 0 || !TextUtils.isEmpty(task.getFilePath()) ||  task.getFile() != null) {
            return false;
        } else {
            return true;
        }
    }


    public static int dip2px(Context context,float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static IImageTask.BitmapListener getBitmapListenerProxy(final IImageTask.BitmapListener listener) {
        return (IImageTask.BitmapListener) Proxy.newProxyInstance(IImageTask.class.getClassLoader(),
                listener.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, final Method method, final Object[] args) throws Throwable {

                        runOnUIThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Object object = method.invoke(listener, args);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        return null;
                    }
                });
    }


    public static void runOnUIThread(Runnable runnable) {
        IImageLoaderConfig.getMainHandler().post(runnable);
    }
}

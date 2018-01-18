package com.yandaoqiu.net.ui.image.projo;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.Animation;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.yandaoqiu.net.ui.image.ILoader;
import com.yandaoqiu.net.ui.image.utils.ILoaderUitls;

import java.io.File;

/**
 * Created by yandaoqiu on 2018/1/16.
 */

public class IImageTask {

    private Context context;
    private ILoader loader;

    private String url;//在线资源
    private String filePath; //文件路径
    private File file; //文件路径
    private int resId;  //资源id
    private String rawPath;  //raw路径
    private String assertspath;  //asserts路径
    private String contentProvider; //内容提供者

    private int placeHolderResId;//占位图
    private int errorResId;//错误显示图

    private float thumbnail; //缩略图缩放倍数
    private boolean isGif; //是否是GIF图
    // CENTER_CROP等比例缩放图片，直到图片的狂高都大于等于ImageView的宽度，然后截取中间的显示 ;
    // FIT_CENTER 等比例缩放图片，宽或者是高等于ImageView的宽或者是高 默认：FIT_CENTER
    private int scaleMode;

    //加载图片时设置分辨率
    private int[]oSize;

    /**
     * 磁盘存储策略
     DiskCacheStrategy.NONE  不缓存文件
     DiskCacheStrategy.SOURCE  只缓存原图
     DiskCacheStrategy.RESULT  只缓存最终加载的图（默认的缓存策略）
     DiskCacheStrategy.ALL  同时缓存原图和结果图
     **/
    private DiskCacheStrategy diskCacheStrategy;

    private View target;

    private int priority;//请求优先级

    //滤镜
    private boolean isNeedVignette; //是否需要晕映

    private boolean isNeedSketch; //是否需要素描

    private boolean isNeedPixelation; //是否需要马赛克
    private float pixelationLevel; //马赛克

    private boolean isNeedInvert; //是否需要胶片

    private boolean isNeedContrast; //是否需要锐化
    public float contrastLevel;  //锐化等级

    private boolean isNeedSepia; //是否需要墨画
    private boolean isNeedToon; //是否需要油画
    private boolean isNeedSwirl;  //是否需要漩涡
    private boolean isNeedGrayscale; //是否需要黑色

    private boolean isNeedBrightness; //是否需要亮度
    private float brightnessLeve; //亮度

    private boolean needFilteColor;//是否需要滤镜
    private int filteColor;//滤镜颜色

    private boolean needBlur;//是否需要模糊
    private int blurRadius;//高斯模糊

    //引入动画
    private int animationId;
    private Animation animation;
    private ViewPropertyAnimation.Animator animator;

    //圆角
    private int shapeMode;//默认矩形,可选直角矩形,圆形/椭圆
    private int rectRoundRadius;//圆角矩形时圆角的半径
    private boolean asBitmap;//只获取bitmap
    private BitmapListener bitmapListener;
    public IImageTask(Builder builder){
        this.url = builder.url;
        this.rawPath = builder.rawPath;
        this.assertspath = builder.assertspath;
        this.contentProvider = builder.contentProvider;
        this.placeHolderResId = builder.placeHolderResId;
        this.errorResId = builder.errorResId;
        this.thumbnail = builder.thumbnail;
        this.isGif = builder.isGif;
        this.scaleMode = builder.scaleMode;
        this.oSize = builder.oSize;
        this.diskCacheStrategy = builder.diskCacheStrategy;
        this.target = builder.target;
        this.priority = builder.priority;
        this.isNeedVignette = builder.isNeedVignette;
        this.isNeedSketch = builder.isNeedSketch;
        this.isNeedPixelation = builder.isNeedPixelation;
        this.pixelationLevel = builder.pixelationLevel;
        this.isNeedInvert = builder.isNeedInvert;
        this.isNeedContrast = builder.isNeedContrast;
        this.contrastLevel = builder.contrastLevel;
        this.isNeedSepia = builder.isNeedSepia;
        this.isNeedToon = builder.isNeedToon;
        this.isNeedSwirl = builder.isNeedSwirl;
        this.isNeedGrayscale = builder.isNeedGrayscale;
        this.isNeedBrightness = builder.isNeedBrightness;
        this.brightnessLeve = builder.brightnessLeve;
        this.needFilteColor = builder.needFilteColor;
        this.filteColor = builder.filteColor;
        this.needBlur = builder.needBlur;
        this.blurRadius = builder.blurRadius;
        this.animationId = builder.animationId;
        this.animation = builder.animation;
        this.animator = builder.animator;
        this.shapeMode = builder.shapeMode;
        this.rectRoundRadius = builder.rectRoundRadius;
        this.context = builder.context;
        this.loader=builder.loader;
        this.file = builder.file;
        this.filePath = builder.filePath;
        this.resId = builder.resId;
        this.asBitmap = builder.asBitmap;
        this.bitmapListener = builder.bitmapListener;
    }
    public interface BitmapListener {
        void onSuccess(Bitmap bitmap);

        void onFail();
    }

    public void setBitmapListener(BitmapListener bitmapListener) {
        this.bitmapListener = ILoaderUitls.getBitmapListenerProxy(bitmapListener);
    }

    public Context getContext() {
        return context;
    }

    public boolean isAsBitmap() {
        return asBitmap;
    }


    public String getFilePath() {
        return filePath;
    }

    public File getFile() {
        return file;
    }

    public String getUrl() {
        return url;
    }

    public int getResId() {
        return resId;
    }

    public String getRawPath() {
        return rawPath;
    }

    public String getAssertspath() {
        return assertspath;
    }

    public String getContentProvider() {
        return contentProvider;
    }

    public int getPlaceHolderResId() {
        return placeHolderResId;
    }

    public int getErrorResId() {
        return errorResId;
    }

    public float getThumbnail() {
        return thumbnail;
    }

    public boolean isGif() {
        return isGif;
    }

    public int getScaleMode() {
        return scaleMode;
    }

    public int[] getoSize() {
        return oSize;
    }

    public DiskCacheStrategy getDiskCacheStrategy() {
        return diskCacheStrategy;
    }

    public View getTarget() {
        return target;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isNeedVignette() {
        return isNeedVignette;
    }

    public boolean isNeedSketch() {
        return isNeedSketch;
    }

    public boolean isNeedPixelation() {
        return isNeedPixelation;
    }

    public float getPixelationLevel() {
        return pixelationLevel;
    }

    public boolean isNeedInvert() {
        return isNeedInvert;
    }

    public boolean isNeedContrast() {
        return isNeedContrast;
    }

    public float getContrastLevel() {
        return contrastLevel;
    }

    public boolean isNeedSepia() {
        return isNeedSepia;
    }

    public boolean isNeedToon() {
        return isNeedToon;
    }

    public boolean isNeedSwirl() {
        return isNeedSwirl;
    }

    public boolean isNeedGrayscale() {
        return isNeedGrayscale;
    }

    public boolean isNeedBrightness() {
        return isNeedBrightness;
    }

    public float getBrightnessLeve() {
        return brightnessLeve;
    }

    public boolean isNeedFilteColor() {
        return needFilteColor;
    }

    public int getFilteColor() {
        return filteColor;
    }

    public boolean isNeedBlur() {
        return needBlur;
    }

    public int getBlurRadius() {
        return blurRadius;
    }

    public int getAnimationId() {
        return animationId;
    }

    public Animation getAnimation() {
        return animation;
    }

    public ViewPropertyAnimation.Animator getAnimator() {
        return animator;
    }

    public int getShapeMode() {
        return shapeMode;
    }

    public int getRectRoundRadius() {
        return rectRoundRadius;
    }
    public BitmapListener getBitmapListener() {

        return bitmapListener;
    }

    private void show() {
        loader.request(this);
    }

    public static class Builder{
         String url;
         int resId;
         String filePath; //文件路径
         File file; //文件路径
         String rawPath;
         String assertspath;
         String contentProvider;
         int placeHolderResId;
         int errorResId;
         float thumbnail;
         boolean isGif;
         int scaleMode;
         int[]oSize;
         DiskCacheStrategy diskCacheStrategy;
         View target;
         int priority;
         boolean isNeedVignette;
         boolean isNeedSketch;
         boolean isNeedPixelation;
         float pixelationLevel;
         boolean isNeedInvert;
         boolean isNeedContrast;
         float contrastLevel;
         boolean isNeedSepia;
         boolean isNeedToon;
         boolean isNeedSwirl;
         boolean isNeedGrayscale;
         boolean isNeedBrightness;
         float brightnessLeve;
         boolean needFilteColor;
         int filteColor;
         boolean needBlur;
         int blurRadius;
         int animationId;
         Animation animation;
         ViewPropertyAnimation.Animator animator;
         int shapeMode;
         int rectRoundRadius;
        boolean asBitmap;
        BitmapListener bitmapListener;
        Context context;
        ILoader loader;


        public Builder(Context contex, ILoader loader){
            this.context = contex;
            this.loader = loader;
        }

        public void into(View targetView) {
            this.target = targetView;
            new IImageTask(this).show();
        }

        public void asBitmap(BitmapListener bitmapListener) {
            this.bitmapListener = ILoaderUitls.getBitmapListenerProxy(bitmapListener);
            this.asBitmap = true;
            new IImageTask(this).show();
        }


        public Builder asBitmap(boolean asBitmap) {
            this.asBitmap = asBitmap;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }
        public Builder resId(int resId) {
            this.resId = resId;
            return this;
        }
        public Builder filePath(String filePath) {
            this.filePath = filePath;
            return this;
        }
        public Builder file(File file) {
            this.file = file;
            return this;
        }
        public Builder rawPath(String rawPath) {
            this.rawPath = rawPath;
            return this;
        }

        public Builder assertspath(String assertspath) {
            this.assertspath = assertspath;
            return this;
        }public Builder contentProvider(String contentProvider) {
            this.contentProvider = contentProvider;
            return this;
        }
        public Builder placeHolderResId(int placeHolderResId) {
            this.placeHolderResId = placeHolderResId;
            return this;
        }
        public Builder errorResId(int errorResId) {
            this.errorResId = errorResId;
            return this;
        }
        public Builder thumbnail(float thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }
        public Builder isGif(boolean isGif) {
            this.isGif = isGif;
            return this;
        }
        public Builder scaleMode(int scaleMode) {
            this.scaleMode = scaleMode;
            return this;
        }
        public Builder diskCacheStrategy(DiskCacheStrategy diskCacheStrategy) {
            this.diskCacheStrategy = diskCacheStrategy;
            return this;
        }
        public Builder target(View target) {
            this.target = target;
            return this;
        }
        public Builder priority(int priority) {
            this.priority = priority;
            return this;
        }
        public Builder isNeedVignette(boolean isNeedVignette) {
            this.isNeedVignette = isNeedVignette;
            return this;
        }
        public Builder isNeedSketch(boolean isNeedSketch) {
            this.isNeedSketch = isNeedSketch;
            return this;
        }
        public Builder isNeedPixelation(boolean isNeedPixelation) {
            this.isNeedPixelation = isNeedPixelation;
            return this;
        }
        public Builder pixelationLevel(float pixelationLevel) {
            this.pixelationLevel = pixelationLevel;
            return this;
        }
        public Builder isNeedInvert(boolean isNeedInvert) {
            this.isNeedInvert = isNeedInvert;
            return this;
        }
        public Builder isNeedContrast(boolean isNeedContrast) {
            this.isNeedContrast = isNeedContrast;
            return this;
        }
        public Builder contrastLevel(float contrastLevel) {
            this.contrastLevel = contrastLevel;
            return this;
        }
        public Builder isNeedSepia(boolean isNeedSepia) {
            this.isNeedSepia = isNeedSepia;
            return this;
        }

        public Builder isNeedToon(boolean isNeedToon) {
            this.isNeedToon = isNeedToon;
            return this;
        }
        public Builder isNeedSwirl(boolean isNeedSwirl) {
            this.isNeedSwirl = isNeedSwirl;
            return this;
        }
        public Builder isNeedGrayscale(boolean isNeedGrayscale) {
            this.isNeedGrayscale = isNeedGrayscale;
            return this;
        }
        public Builder isNeedBrightness(boolean isNeedBrightness) {
            this.isNeedBrightness = isNeedBrightness;
            return this;
        }
        public Builder brightnessLeve(float brightnessLeve) {
            this.brightnessLeve = brightnessLeve;
            return this;
        }
        public Builder needFilteColor(boolean needFilteColor) {
            this.needFilteColor = needFilteColor;
            return this;
        }
        public Builder filteColor(int filteColor) {
            this.filteColor = filteColor;
            return this;
        }
        public Builder needBlur(boolean needBlur) {
            this.needBlur = needBlur;
            return this;
        }
        public Builder blurRadius(int blurRadius) {
            this.blurRadius = blurRadius;
            return this;
        }
        public Builder animationId(int animationId) {
            this.animationId = animationId;
            return this;
        }
        public Builder animation(Animation animation) {
            this.animation = animation;
            return this;
        }
        public Builder animator(ViewPropertyAnimation.Animator animator) {
            this.animator = animator;
            return this;
        }
        public Builder rectRoundRadius(int rectRoundRadius) {
            this.rectRoundRadius = rectRoundRadius;
            return this;
        }
        public Builder shapeMode(int shapeMode) {
            this.shapeMode = shapeMode;
            return this;
        }

        public Builder override(int oWidth, int oHeight) {
            this.oSize = new int[]{ILoaderUitls.dip2px(context,oWidth),ILoaderUitls.dip2px(context,oHeight)};
            return this;
        }

    }
}

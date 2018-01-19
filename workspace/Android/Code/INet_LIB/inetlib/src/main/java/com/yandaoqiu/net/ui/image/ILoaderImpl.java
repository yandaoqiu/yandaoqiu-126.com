package com.yandaoqiu.net.ui.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.MemoryCategory;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.yandaoqiu.net.ui.image.projo.IImageTask;
import com.yandaoqiu.net.ui.image.projo.PriorityMode;
import com.yandaoqiu.net.ui.image.projo.ScaleMode;
import com.yandaoqiu.net.ui.image.projo.ShapeMode;
import com.yandaoqiu.net.ui.image.utils.DownLoadImageTask;
import com.yandaoqiu.net.ui.image.utils.ILoaderUitls;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ContrastFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.InvertFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.PixelationFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SwirlFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ToonFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

/**
 * 加载器具体实现
 * Created by yandaoqiu on 2018/1/18.
 */

public class ILoaderImpl implements ILoader {
    private Context context;
    /**
     * 初始化
     * @param context        上下文
     * @param factory      磁盘缓存
     * @param memoryCategory 缓存等级
     */
    @Override
    public void init(Context context, DiskCache.Factory factory, MemoryCategory memoryCategory, BitmapPool bitmapPool, MemoryCache memoryCache, DecodeFormat decodeFormat) {
        this.context = context;
        Glide.get(context).setMemoryCategory(memoryCategory);
        GlideBuilder builder = new GlideBuilder(context);
        builder.setDiskCache(factory);
        builder.setMemoryCache(memoryCache);
        builder.setBitmapPool(bitmapPool);
        builder.setDecodeFormat(decodeFormat);
    }

    /**
     * 暂停所有（包括加载缓存）
     */
    @Override
    public void pause() {
        Glide.with(context).pauseRequestsRecursive();
    }

    /**
     * 恢复所有
     */
    @Override
    public void resume() {
        Glide.with(context).resumeRequestsRecursive();
    }

    /**
     * 取消加载
     * @param view
     */
    @Override
    public void clear(View view) {
        Glide.clear(view);
    }

    /**
     * 清除磁盘存储
     */
    @Override
    public void clearDiskCache() {
        Glide.get(context).clearDiskCache();
    }

    /**
     * 清除缓存
     */
    @Override
    public void clearMemory() {
        Glide.get(context).clearMemory();
    }

    /**
     * 降级
     *
     * @param level
     */
    @Override
    public void onTrimMemory(int level) {
        Glide.with(context).onTrimMemory(level);
    }

    @Override
    public void onLowMemory() {
        Glide.with(context).onLowMemory();
    }

    private DrawableTypeRequest initDrawableTypeRequest(IImageTask task, RequestManager requestManager) {
        DrawableTypeRequest request = null;
        if (!TextUtils.isEmpty(task.getUrl())) {
            request = requestManager.load(task.getUrl());
            Log.e("ILoader","getUrl : "+task.getUrl());
        } else if (!TextUtils.isEmpty(task.getFilePath())) {
            request = requestManager.load(task.getFilePath());
            Log.e("ILoader","getFilePath : "+task.getFilePath());
        } else if (!TextUtils.isEmpty(task.getContentProvider())) {
            request = requestManager.loadFromMediaStore(Uri.parse(task.getContentProvider()));
            Log.e("ILoader","getContentProvider : "+task.getContentProvider());
        } else if (task.getResId() > 0) {
            request = requestManager.load(task.getResId());
            Log.e("ILoader","getResId : "+task.getResId());
        } else if(task.getFile() != null){
            request = requestManager.load(task.getFile());
            Log.e("ILoader","getFile : "+task.getFile());
        } else if(!TextUtils.isEmpty(task.getAssertspath())){
            request = requestManager.load(task.getAssertspath());
            Log.e("ILoader","getAssertspath : "+task.getAssertspath());
        } else if(!TextUtils.isEmpty(task.getRawPath())){
            request = requestManager.load(task.getRawPath());
            Log.e("ILoader","getRawPath : "+task.getRawPath());
        }
        return request;
    }
    /**
     * 执行图片
     *
     * @param task
     */
    @Override
    public void request(final IImageTask task) {
        RequestManager requestManager = Glide.with(task.getContext());
        DrawableTypeRequest request = initDrawableTypeRequest(task, requestManager);

        if (task.isAsBitmap()){
            SimpleTarget target = new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                    task.getBitmapListener().onSuccess(bitmap);
                }
            };
            setShapeModeAndBlur(task, request);
            if (task.getDiskCacheStrategy() != null) {
                request.diskCacheStrategy(task.getDiskCacheStrategy());
            }
            request.asBitmap().into(target);
        }else {
            if (request == null) {
                Log.e("ILoader","IImageTask request Error,DrawableTypeRequest init is null!");
                return;
            }
            boolean hasPlaceHolder = ILoaderUitls.hasPlaceHolder(task);
            if (hasPlaceHolder){
                request.placeholder(task.getPlaceHolderResId());
            }
            int scaleMode = task.getScaleMode();
            switch (scaleMode) {
                case ScaleMode.CENTER_CROP:
                    request.centerCrop();
                    break;
                case ScaleMode.FIT_CENTER:
                    request.fitCenter();
                    break;
                default:
                    request.fitCenter();
                    break;
            }
            setShapeModeAndBlur(task, request);
            //设置缩略图
            if (task.getThumbnail() != 0) {
                request.thumbnail(task.getThumbnail());
            }
            //设置图片加载的分辨 sp
            if (task.getoSize()!=null) {
                request.override(task.getoSize()[0],task.getoSize()[1]);
            }

            //是否跳过磁盘存储
            if (task.getDiskCacheStrategy() != null) {
                request.diskCacheStrategy(task.getDiskCacheStrategy());
            }
            //设置图片加载动画
            setAnimator(task, request);

            //设置图片加载优先级
            setPriority(task, request);

            if (task.getErrorResId() > 0) {
                request.error(task.getErrorResId());
            }

            if(task.isGif()){
                request.asGif();
            }

            if (task.getTarget() instanceof ImageView) {
                request.into((ImageView) task.getTarget());
            }
        }
    }
    private void setPriority(IImageTask task, DrawableTypeRequest request) {
        switch (task.getPriority()) {
            case PriorityMode.PRIORITY_LOW:
                request.priority(Priority.LOW);
                break;
            case PriorityMode.PRIORITY_NORMAL:
                request.priority(Priority.NORMAL);
                break;
            case PriorityMode.PRIORITY_HIGH:
                request.priority(Priority.HIGH);
                break;
            case PriorityMode.PRIORITY_IMMEDIATE:
                request.priority(Priority.IMMEDIATE);
                break;
            default:
                request.priority(Priority.IMMEDIATE);
                break;
        }
    }
    private void setAnimator(IImageTask task, DrawableTypeRequest request) {
        if (task.getAnimationId() > 0) {
            request.animate(task.getAnimationId());
        } else if (task.getAnimator() != null) {
            request.animate(task.getAnimator());
        } else if (task.getAnimation()!=null) {
            request.animate(task.getAnimation());
        }
    }

    /**
     * 设置图片滤镜和形状
     *
     * @param task
     * @param request
     */
    private void setShapeModeAndBlur(IImageTask task, DrawableTypeRequest request) {

        int count = 0;

        Transformation[] transformation = new Transformation[statisticsCount(task)];

        if (task.isNeedBlur()) {
            transformation[count] = new BlurTransformation(task.getContext(), task.getBlurRadius());
            count++;
        }

        if (task.isNeedBrightness()) {
            transformation[count] = new BrightnessFilterTransformation(task.getContext(), task.getBrightnessLeve()); //亮度
            count++;
        }

        if (task.isNeedGrayscale()) {
            transformation[count] = new GrayscaleTransformation(task.getContext()); //黑白效果
            count++;
        }

        if (task.isNeedFilteColor()) {
            transformation[count] = new ColorFilterTransformation(task.getContext(), task.getFilteColor());
            count++;
        }

        if (task.isNeedSwirl()) {
            transformation[count] = new SwirlFilterTransformation(task.getContext(), 0.5f, 1.0f, new PointF(0.5f, 0.5f)); //漩涡
            count++;
        }

        if (task.isNeedToon()) {
            transformation[count] = new ToonFilterTransformation(task.getContext()); //油画
            count++;
        }

        if (task.isNeedSepia()) {
            transformation[count] = new SepiaFilterTransformation(task.getContext()); //墨画
            count++;
        }

        if (task.isNeedContrast()) {
            transformation[count] = new ContrastFilterTransformation(task.getContext(), task.getContrastLevel()); //锐化
            count++;
        }

        if (task.isNeedInvert()) {
            transformation[count] = new InvertFilterTransformation(task.getContext()); //胶片
            count++;
        }

        if (task.isNeedPixelation()) {
            transformation[count] =new PixelationFilterTransformation(task.getContext(), task.getPixelationLevel()); //马赛克
            count++;
        }

        if (task.isNeedSketch()) {
            transformation[count] =new SketchFilterTransformation(task.getContext()); //素描
            count++;
        }

        if (task.isNeedVignette()) {
            transformation[count] =new VignetteFilterTransformation(task.getContext(), new PointF(0.5f, 0.5f),
                    new float[] { 0.0f, 0.0f, 0.0f }, 0f, 0.75f);//晕映
            count++;
        }

        switch (task.getShapeMode()) {
            case ShapeMode.RECT:

                break;
            case ShapeMode.RECT_ROUND:
                transformation[count] = new RoundedCornersTransformation
                        (task.getContext(), task.getRectRoundRadius(), 0, RoundedCornersTransformation.CornerType.ALL);
                count++;
                break;
            case ShapeMode.OVAL:
                transformation[count] = new CropCircleTransformation(task.getContext());
                count++;
                break;

            case ShapeMode.SQUARE:
                transformation[count] = new CropSquareTransformation(task.getContext());
                count++;
                break;
        }

        if (transformation.length != 0) {
            request.bitmapTransform(transformation);
        }

    }

    private int statisticsCount(IImageTask task) {
        int count = 0;

        if (task.getShapeMode() == ShapeMode.OVAL || task.getShapeMode() == ShapeMode.RECT_ROUND || task.getShapeMode() == ShapeMode.SQUARE) {
            count++;
        }

        if (task.isNeedBlur()) {
            count++;
        }

        if (task.isNeedFilteColor()) {
            count++;
        }

        if (task.isNeedBrightness()) {
            count++;
        }

        if (task.isNeedGrayscale()) {
            count++;
        }

        if (task.isNeedSwirl()) {
            count++;
        }

        if (task.isNeedToon()) {
            count++;
        }

        if (task.isNeedSepia()) {
            count++;
        }

        if (task.isNeedContrast()) {
            count++;
        }

        if (task.isNeedInvert()) {
            count++;
        }

        if (task.isNeedPixelation()) {
            count++;
        }

        if (task.isNeedSketch()) {
            count++;
        }

        if (task.isNeedVignette()) {
            count++;
        }

        return count;
    }

    /**
     * 保存图片到相册
     *
     * @param downLoadImageTask
     */
    @Override
    public void saveImageIntoGallery(DownLoadImageTask downLoadImageTask) {

    }
}

package com.yandaoqiu.iwork.base.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by 15032065 on 18/2/11.
 */

public class UIImageView extends ImageView {

    private Bitmap bitmap;
    private Bitmap selectedBitmp;
    public UIImageView(Context context) {
        super(context);
    }

    public UIImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UIImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setSelectedImage(Bitmap bitmap){

        this.selectedBitmp = bitmap;
        if (isSelected()){
            setImageBitmap(selectedBitmp);
        }
    }

    public void setImage(Bitmap bitmap){

        this.bitmap = bitmap;
        if (!isSelected()){
            setImageBitmap(this.bitmap);
        }
    }

    @Override
    public void setSelected(boolean selected) {
        if (isSelected() == selected)return;
        if (selected){
            setImageBitmap(selectedBitmp);
        }else {
            setImageBitmap(bitmap);
        }
        super.setSelected(selected);
    }
}

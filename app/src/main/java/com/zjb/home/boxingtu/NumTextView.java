package com.zjb.home.boxingtu;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.ViewGroup;


/**
 * des： 宽度不能小于高度
 * author： ZhangJieBo
 * date： 2018/11/1/001 21:24
 */
public class NumTextView extends AppCompatTextView {


    public NumTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NumTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NumTextView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (width<height){
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            layoutParams.width=height;
            setLayoutParams(layoutParams);
        }
    }

}
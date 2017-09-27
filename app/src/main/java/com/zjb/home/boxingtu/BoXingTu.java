package com.zjb.home.boxingtu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/9/27.
 */
public class BoXingTu extends View{

    private int width;
    private int height;
    private int widthJianGe;
    private int heightJianGe;
    private Paint paintHengXian;

    public BoXingTu(Context context) {
        super(context);
    }

    public BoXingTu(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BoXingTu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        widthJianGe=width/8;
        heightJianGe=height/8;
    }

    {
        paintHengXian = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintHengXian.setColor(getResources().getColor(R.color.hengxian));
//        paintHengXian.setStrokeWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}

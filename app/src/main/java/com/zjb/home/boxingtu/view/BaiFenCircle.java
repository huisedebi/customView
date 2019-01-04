package com.zjb.home.boxingtu.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;

import com.zjb.home.boxingtu.R;


/**
 * Created by Administrator on 2017/9/13.
 */
public class BaiFenCircle extends View {

    private float circleStroke;
    private int width;
    private int heigth;
    private Paint paintCircle;
    private int baiFenBiDu = 0;
    private int color01 = Color.RED;
    private int color02 = Color.BLUE;

    public BaiFenCircle(Context context) {
        super(context);
    }

    public BaiFenCircle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaiFenCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //初始化圆圈画笔
        circleStroke = dp_px(4);
        paintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintCircle.setStyle(Paint.Style.STROKE);
        color01 = Color.RED;
        color02 = ContextCompat.getColor(context, R.color.gray_d9d9d9);
    }

    {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        heigth = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画圆圈
        paintCircle.setColor(color02);
        paintCircle.setStrokeWidth(circleStroke);
        paintCircle.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawCircle(getWidth()/2, getHeight() / 2,getWidth()/2-circleStroke / 2,paintCircle);
        RectF oval1 = new RectF(circleStroke / 2, circleStroke / 2, width - circleStroke / 2, heigth - circleStroke / 2);

        paintCircle.setColor(color01);
        canvas.drawArc(oval1, 0-baiFenBiDu-90, baiFenBiDu, false, paintCircle);

    }

    public void setBaiFenBiDu(int baiFenBiDu) {
        this.baiFenBiDu = baiFenBiDu;
        invalidate();
    }

    /**
     * dp转px
     *
     * @param values
     * @return
     */
    public int dp_px(int values) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (values * density + 0.5f);
    }

    public void setColor01(int color01) {
        this.color01 = color01;
        invalidate();
    }

    public void setBaiFenBiDuAnim(int baiFenBiDu){
        ObjectAnimator animator = ObjectAnimator.ofInt(this, "baiFenBiDu",0, baiFenBiDu);
        animator.setDuration(1000);
        animator.setInterpolator(new FastOutSlowInInterpolator());
        animator.start();
    }
}

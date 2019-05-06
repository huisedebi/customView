package com.zjb.home.boxingtu.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by Administrator on 2017/9/13.
 */
public class BaiFenCircle01 extends View {

    private float circleStroke;
    private int width;
    private int heigth;
    private Paint paintCircle;
    private int baiFenBiDu = 0;
    private Paint paintCircle01;
    private int dp3;

    public BaiFenCircle01(Context context) {
        super(context);
    }

    public BaiFenCircle01(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaiFenCircle01(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        dp3 = dp_px(3);
        //初始化圆圈画笔
        circleStroke = dp_px(9);
        paintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setStrokeCap(Paint.Cap.ROUND);
        paintCircle.setStrokeWidth(circleStroke);
        paintCircle.setColor(Color.parseColor("#4F86FF"));
        paintCircle.setShadowLayer(
                dp_px(3),
                0,
                0,
                Color.parseColor("#ff4F86FF"));

        paintCircle01 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintCircle01.setStyle(Paint.Style.STROKE);
        paintCircle01.setStrokeCap(Paint.Cap.ROUND);
        paintCircle01.setStrokeWidth(circleStroke);
        paintCircle01.setColor(Color.parseColor("#FF7800"));
        paintCircle01.setShadowLayer(
                dp_px(3),
                0,
                0,
                Color.parseColor("#ffFF7800"));

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
        RectF oval1 = new RectF(circleStroke / 2+dp3, circleStroke / 2+dp3, width - circleStroke / 2-dp3, heigth - circleStroke / 2-dp3);
        canvas.drawArc(oval1, 0-baiFenBiDu+90, baiFenBiDu, false, paintCircle);
        canvas.drawArc(oval1, 0-baiFenBiDu+90+baiFenBiDu+15, 360-baiFenBiDu-30, false, paintCircle01);

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


    public void setBaiFenBiDuAnim(int baiFenBiDu){
        ObjectAnimator animator = ObjectAnimator.ofInt(this, "baiFenBiDu",0, baiFenBiDu);
        animator.setDuration(1000);
        animator.setInterpolator(new FastOutSlowInInterpolator());
        animator.start();
    }
}

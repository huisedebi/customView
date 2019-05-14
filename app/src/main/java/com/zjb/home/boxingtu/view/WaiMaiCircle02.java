package com.zjb.home.boxingtu.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.zjb.home.boxingtu.util.DpUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */
public class WaiMaiCircle02 extends View {
    /**
     * 初始百分比
     */
    private int baiFenBi01 = 0;
    private int baiFenBi02 = 0;
    private int baiFenBi03 = 0;
    /**
     * 进度初始位置
     */
    private int kaiShiDuShu = 70;
    /**
     * 初始圆圈宽度
     */
    private int circleWidth = 20;
    private float circleStroke;
    private Paint paintCircle;
    private float circleStroke01;
    private int index;

    public WaiMaiCircle02(Context context) {
        super(context);
    }

    public WaiMaiCircle02(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaiMaiCircle02(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //初始化圆圈画笔
        circleStroke = DpUtils.convertDpToPixel(circleWidth, getContext());
        circleStroke01 = DpUtils.convertDpToPixel(circleWidth * 2, getContext());
        paintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF oval1 = new RectF(circleStroke01 / 2, circleStroke01 / 2, getWidth() - circleStroke01 / 2, getHeight() - circleStroke01 / 2);
        paintCircle.setStyle(Paint.Style.STROKE);
        switch (index) {
            case 0:
                paintCircle.setStrokeWidth(circleStroke01);
                paintCircle.setColor(Color.parseColor("#685AFD"));
                canvas.drawArc(oval1, kaiShiDuShu, 360, false, paintCircle);
                paintCircle.setStrokeWidth(circleStroke01 + 1);
                paintCircle.setColor(Color.parseColor("#ffffff"));
                canvas.drawArc(oval1, kaiShiDuShu, 360 - baiFenBi01, false, paintCircle);
                break;
            case 1:
                paintCircle.setStrokeWidth(circleStroke01);
                paintCircle.setColor(Color.parseColor("#4F86FF"));
                canvas.drawArc(oval1, kaiShiDuShu, 360, false, paintCircle);
                paintCircle.setStrokeWidth(circleStroke01 + 1);
                paintCircle.setColor(Color.parseColor("#ffffff"));
                canvas.drawArc(oval1, kaiShiDuShu - baiFenBi01, 360 - baiFenBi02, false, paintCircle);
                break;
            case 2:
                paintCircle.setStrokeWidth(circleStroke01);
                paintCircle.setColor(Color.parseColor("#FF7800"));
                canvas.drawArc(oval1, kaiShiDuShu, 360, false, paintCircle);
                paintCircle.setStrokeWidth(circleStroke01 + 1);
                paintCircle.setColor(Color.parseColor("#ffffff"));
                canvas.drawArc(oval1, kaiShiDuShu - baiFenBi01 - baiFenBi02, 360 - baiFenBi03, false, paintCircle);
                break;
            case 3:
                paintCircle.setStrokeWidth(circleStroke01);
                paintCircle.setColor(Color.parseColor("#35B545"));
                canvas.drawArc(oval1, kaiShiDuShu, 360, false, paintCircle);
                paintCircle.setStrokeWidth(circleStroke01 + 1);
                paintCircle.setColor(Color.parseColor("#ffffff"));
                canvas.drawArc(oval1, kaiShiDuShu - (baiFenBi03 + baiFenBi02 + baiFenBi01), baiFenBi03 + baiFenBi02 + baiFenBi01, false, paintCircle);
                break;
            default:
                paintCircle.setStrokeWidth(circleStroke01);
                paintCircle.setColor(Color.parseColor("#685AFD"));
                canvas.drawArc(oval1, kaiShiDuShu, 360, false, paintCircle);
                paintCircle.setStrokeWidth(circleStroke01 + 1);
                paintCircle.setColor(Color.parseColor("#ffffff"));
                canvas.drawArc(oval1, kaiShiDuShu, 360 - baiFenBi01, false, paintCircle);
                break;
        }

        //画圆圈
        paintCircle.setStrokeWidth(circleStroke);
        paintCircle.setColor(Color.parseColor("#685AFD"));
        canvas.drawArc(oval1, kaiShiDuShu, 360, false, paintCircle);
        if (index == 1) {
            paintCircle.setStrokeWidth(circleStroke + 1);
        }
        paintCircle.setColor(Color.parseColor("#4F86FF"));
        canvas.drawArc(oval1, kaiShiDuShu, 360 - baiFenBi01, false, paintCircle);
        if (index == 2) {
            paintCircle.setStrokeWidth(circleStroke + 1);
        }
        paintCircle.setColor(Color.parseColor("#FF7800"));
        canvas.drawArc(oval1, kaiShiDuShu, 360 - baiFenBi01 - baiFenBi02, false, paintCircle);
        if (index == 3) {
            paintCircle.setStrokeWidth(circleStroke + 1);
        }
        paintCircle.setColor(Color.parseColor("#35B545"));
        canvas.drawArc(oval1, kaiShiDuShu, 360 - baiFenBi01 - baiFenBi02 - baiFenBi03, false, paintCircle);

        RectF oval2 = new RectF(circleStroke01 / 2 + circleStroke01 / 4f, circleStroke01 / 2 + circleStroke01 / 4f, getWidth() - circleStroke01 / 2 - circleStroke01 / 4f, getHeight() - circleStroke01 / 2 - circleStroke01 / 4f);
        paintCircle.setColor(Color.parseColor("#ffffff"));
        paintCircle.setStyle(Paint.Style.FILL);
        canvas.drawArc(oval2, kaiShiDuShu, 360, true, paintCircle);

        Path path = new Path();

    }

    public void setBaiFenBi01(int baiFenBi01) {
        this.baiFenBi01 = baiFenBi01;
        invalidate();
    }

    public void setBaiFenBi02(int baiFenBi02) {
        this.baiFenBi02 = baiFenBi02;
        invalidate();
    }

    public void setBaiFenBi03(int baiFenBi03) {
        this.baiFenBi03 = baiFenBi03;
        invalidate();
    }

    public void setBaiFenBiAnim(int baiFenBi01, int baiFenBi02, int baiFenBi03) {
        List<Integer> baiFenBiList = new ArrayList<>();
        baiFenBiList.add(baiFenBi01);
        baiFenBiList.add(baiFenBi02);
        baiFenBiList.add(baiFenBi03);
        baiFenBiList.add(360 - baiFenBi01 - baiFenBi02 - baiFenBi03);
        Integer max = Collections.max(baiFenBiList);
        index = baiFenBiList.indexOf(max);
        ObjectAnimator animator1 = ObjectAnimator.ofInt(this, "baiFenBi01", this.baiFenBi01, baiFenBi01);
        animator1.setDuration(1000);
        animator1.setInterpolator(new LinearInterpolator());
        animator1.start();
        ObjectAnimator animator2 = ObjectAnimator.ofInt(this, "baiFenBi02", this.baiFenBi02, baiFenBi02);
        animator2.setDuration(1000);
        animator2.setInterpolator(new LinearInterpolator());
        animator2.start();
        ObjectAnimator animator3 = ObjectAnimator.ofInt(this, "baiFenBi03", this.baiFenBi03, baiFenBi03);
        animator3.setDuration(1000);
        animator3.setInterpolator(new LinearInterpolator());
        animator3.start();
    }


}

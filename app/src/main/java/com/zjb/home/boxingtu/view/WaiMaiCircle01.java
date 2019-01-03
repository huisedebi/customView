package com.zjb.home.boxingtu.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017/9/13.
 */
public class WaiMaiCircle01 extends View {
    /**
     * 初始百分比
     */
    private int baiFenBiDu01 = 120;
    /**
     * 进度初始位置
     */
    private int kaiShiDuShu = -90;
    /**
     * 修正dp
     */
    private int xiuZheng = 1;
    /**
     * 颜色1
     */
    private int color01 = Color.RED;
    /**
     * 颜色2
     */
    private int color02 = Color.CYAN;
    /**
     * 初始圆圈宽度
     */
    private int circleWidth = 50;
    private float baiFenBiSize;
    private float circleStroke;
    private Rect rectBaiFenBi01;
    private Rect rectBaiFenBi02;
    private int width;
    private int heigth;
    private Paint paintCircle;
    private Paint paintLitterCircle;
    private Paint paintBaiFenBi;
    private String baiFenBi01;
    private String baiFenBi02;

    public WaiMaiCircle01(Context context) {
        super(context);
    }

    public WaiMaiCircle01(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaiMaiCircle01(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        DecimalFormat df = new DecimalFormat("0.0");
        baiFenBi01 = df.format((double) baiFenBiDu01 / 360 * 100) + "%";
        baiFenBi02 = df.format((double) (360 - baiFenBiDu01) / 360 * 100) + "%";


        //初始化百分数画笔

        baiFenBiSize = dp_px(15);
        paintBaiFenBi = new Paint();
        paintBaiFenBi.setColor(Color.WHITE);
        paintBaiFenBi.setTextSize(baiFenBiSize);
        rectBaiFenBi01 = new Rect();
        rectBaiFenBi02 = new Rect();
        paintBaiFenBi.getTextBounds(baiFenBi01, 0, baiFenBi01.length(), rectBaiFenBi01);
        paintBaiFenBi.getTextBounds(baiFenBi02, 0, baiFenBi02.length(), rectBaiFenBi02);

        //初始化圆圈画笔
        circleStroke = dp_px(circleWidth);
        paintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintCircle.setStyle(Paint.Style.STROKE);
        paintLitterCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintLitterCircle.setStyle(Paint.Style.STROKE);
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
        paintCircle.setStrokeWidth(circleStroke);
        RectF oval1 = new RectF(circleStroke / 2, circleStroke / 2, width - circleStroke / 2, heigth - circleStroke / 2);
        paintCircle.setColor(color02);
        canvas.drawArc(oval1, 0, 360, false, paintCircle);
        paintCircle.setColor(color01);
        canvas.drawArc(oval1, kaiShiDuShu, baiFenBiDu01 + xiuZheng, false, paintCircle);



        //画百分比数
        float x01;
        float y01;
        DecimalFormat df = new DecimalFormat("0.0");
        baiFenBi01 = df.format((double) baiFenBiDu01 / 360 * 100) + "%";
        baiFenBi02 = df.format((double) (360 - baiFenBiDu01) / 360 * 100) + "%";
        int jiaoDu01 = baiFenBiDu01 / 2 + kaiShiDuShu;
        x01 = (float) (Math.cos(Math.toRadians(jiaoDu01)) * (width / 2 - circleStroke / 2) + width / 2);
        y01 = (float) (Math.sin(Math.toRadians(jiaoDu01)) * (heigth / 2 - circleStroke / 2) + heigth / 2);
        canvas.drawText(baiFenBi01, x01 - rectBaiFenBi01.width() / 2, y01, paintBaiFenBi);

        float x02;
        float y02;
        int jiaoDu02 = (360 - baiFenBiDu01) / 2 + baiFenBiDu01 + kaiShiDuShu;
        x02 = (float) (Math.cos(Math.toRadians(jiaoDu02)) * (width / 2 - circleStroke / 2) + width / 2);
        y02 = (float) (Math.sin(Math.toRadians(jiaoDu02)) * (heigth / 2 - circleStroke / 2) + heigth / 2);
        canvas.drawText(baiFenBi02, x02 - rectBaiFenBi02.width() / 2, y02, paintBaiFenBi);

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

    public void setBaiFenBiDu01(int baiFenBiDu01) {
        this.baiFenBiDu01 = baiFenBiDu01;
        invalidate();
    }


    public int getBaiFenBiDu01() {
        return baiFenBiDu01;
    }

    public void setColor01(int color01) {
        this.color01 = color01;
    }

    public void setColor02(int color02) {
        this.color02 = color02;
    }

    public float getCircleStroke() {
        return circleStroke;
    }

    public void setCircleStroke(float circleStroke) {
        this.circleStroke = circleStroke;
        invalidate();
    }

    public int getCircleWidth() {
        return circleWidth;
    }

    public int getWidth11() {
        return width;
    }

    public void setKaiShiDuShu(int kaiShiDuShu) {
        this.kaiShiDuShu = kaiShiDuShu;
        invalidate();
    }

    public int getKaiShiDuShu() {
        return kaiShiDuShu;
    }
}

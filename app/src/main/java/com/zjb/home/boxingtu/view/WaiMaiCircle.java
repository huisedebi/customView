package com.zjb.home.boxingtu.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017/9/13.
 */
public class WaiMaiCircle extends View {

    private String title = "外卖订单总计";
    private String num = "0";
    private float titleSize;
    private float numSize;
    private float baiFenBiSize;
    private float circleStroke;
    private Rect rectTitle;
    private Rect rectNum;
    private Rect rectBaiFenBi01;
    private Rect rectBaiFenBi02;
    private Rect rectBaiFenBi03;
    private int width;
    private int heigth;
    private Paint paintTitle;
    private Paint paintCircle;
    private Paint paintLitterCircle;
    private Paint paintNum;
    private Paint paintBaiFenBi;
    private int colorTitle = Color.BLACK;
    private int colorNum = Color.YELLOW;
    private int baiFenBiDu01 = 120;
    private int baiFenBiDu02 = 120;
    private int baiFenBiDu03 = 120;
    private String baiFenBi01;
    private String baiFenBi02;
    private String baiFenBi03;
    private int color01 = Color.RED;
    private int color02 = Color.CYAN;
    private int color03 = Color.BLUE;
    private int xiuZheng = 1;

    public WaiMaiCircle(Context context) {
        super(context);
    }

    public WaiMaiCircle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaiMaiCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        DecimalFormat df = new DecimalFormat("0.0");
        baiFenBi01 = df.format((double) baiFenBiDu01 / 360 * 100) + "%";
        baiFenBi02 = df.format((double) baiFenBiDu02 / 360 * 100) + "%";
        baiFenBi03 = df.format((double) baiFenBiDu03 / 360 * 100) + "%";


        //初始化title画笔
        titleSize = dp_px(20);
        paintTitle = new Paint();
        paintTitle.setColor(colorTitle);
        paintTitle.setTextSize(titleSize);
        rectTitle = new Rect();
        paintTitle.getTextBounds(title, 0, title.length(), rectTitle);

        //初始化订单数画笔
        numSize = dp_px(35);
        paintNum = new Paint();
        paintNum.setColor(colorNum);
        paintNum.setTextSize(numSize);
        rectNum = new Rect();
        paintNum.getTextBounds(num, 0, num.length(), rectNum);

        //初始化百分数画笔

        baiFenBiSize = dp_px(15);
        paintBaiFenBi = new Paint();
        paintBaiFenBi.setColor(Color.WHITE);
        paintBaiFenBi.setTextSize(baiFenBiSize);
        rectBaiFenBi01 = new Rect();
        rectBaiFenBi02 = new Rect();
        rectBaiFenBi03 = new Rect();
        paintBaiFenBi.getTextBounds(baiFenBi01, 0, baiFenBi01.length(), rectBaiFenBi01);
        paintBaiFenBi.getTextBounds(baiFenBi02, 0, baiFenBi02.length(), rectBaiFenBi02);
        paintBaiFenBi.getTextBounds(baiFenBi03, 0, baiFenBi03.length(), rectBaiFenBi03);

        //初始化圆圈画笔
        circleStroke = dp_px(50);
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
        //画标题
        canvas.drawText(title, width / 2 - rectTitle.width() / 2, heigth / 2 - rectTitle.height(), paintTitle);

        //画订单数
        canvas.drawText(num, width / 2 - rectNum.width() / 2, heigth / 2 + rectNum.height(), paintNum);

        //画圆圈
        paintCircle.setStrokeWidth(circleStroke);
        RectF oval1 = new RectF(circleStroke / 2, circleStroke / 2, width - circleStroke / 2, heigth - circleStroke / 2);

        paintCircle.setColor(color01);
        canvas.drawArc(oval1, 0, baiFenBiDu01+xiuZheng, false, paintCircle);

        paintCircle.setColor(color02);
        canvas.drawArc(oval1, baiFenBiDu01, baiFenBiDu02+xiuZheng, false, paintCircle);

        paintCircle.setColor(color03);
        canvas.drawArc(oval1, baiFenBiDu01 + baiFenBiDu02, baiFenBiDu03+xiuZheng, false, paintCircle);

        paintLitterCircle.setStrokeWidth(circleStroke / 5);
        RectF oval2 = new RectF(circleStroke + circleStroke / 5 / 2, circleStroke + circleStroke / 5 / 2, width - circleStroke - circleStroke / 5 / 2, heigth - circleStroke - circleStroke / 5 / 2);
        ColorFilter lightingColorFilter = new LightingColorFilter(0xffffff, 0x999999);

        paintLitterCircle.setColor(color01);
        paintLitterCircle.setColorFilter(lightingColorFilter);
        canvas.drawArc(oval2, 0, baiFenBiDu01+xiuZheng, false, paintLitterCircle);

        paintLitterCircle.setColor(color02);
        paintLitterCircle.setColorFilter(lightingColorFilter);
        canvas.drawArc(oval2, baiFenBiDu01, baiFenBiDu02+xiuZheng, false, paintLitterCircle);

        paintLitterCircle.setColor(color03);
        paintLitterCircle.setColorFilter(lightingColorFilter);
        canvas.drawArc(oval2, baiFenBiDu01 + baiFenBiDu02, baiFenBiDu03+xiuZheng, false, paintLitterCircle);

        //画百分比数
        float x01;
        float y01;
        DecimalFormat df = new DecimalFormat("0.0");
        baiFenBi01 = df.format((double) baiFenBiDu01 / 360 * 100) + "%";
        baiFenBi02 = df.format((double) baiFenBiDu02 / 360 * 100) + "%";
        baiFenBi03 = df.format((double) baiFenBiDu03 / 360 * 100) + "%";
        int jiaoDu01 = baiFenBiDu01 / 2 + 0;
        x01 = (float) (Math.cos(Math.toRadians(jiaoDu01)) * (width / 2 - circleStroke / 2) + width / 2);
        y01 = (float) (Math.sin(Math.toRadians(jiaoDu01)) * (heigth / 2 - circleStroke / 2) + heigth / 2);
        canvas.drawText(baiFenBi01, x01 - rectBaiFenBi01.width() / 2, y01, paintBaiFenBi);

        float x02;
        float y02;
        int jiaoDu02 = baiFenBiDu02 / 2 + baiFenBiDu01;
        x02 = (float) (Math.cos(Math.toRadians(jiaoDu02)) * (width / 2 - circleStroke / 2) + width / 2);
        y02 = (float) (Math.sin(Math.toRadians(jiaoDu02)) * (heigth / 2 - circleStroke / 2) + heigth / 2);
        canvas.drawText(baiFenBi02, x02 - rectBaiFenBi02.width() / 2, y02, paintBaiFenBi);

        float x03;
        float y03;
        int jiaoDu03 = baiFenBiDu03 / 2 + baiFenBiDu01 + baiFenBiDu02;
        x03 = (float) (Math.cos(Math.toRadians(jiaoDu03)) * (width / 2 - circleStroke / 2) + width / 2);
        y03 = (float) (Math.sin(Math.toRadians(jiaoDu03)) * (heigth / 2 - circleStroke / 2) + heigth / 2);
        canvas.drawText(baiFenBi03, x03 - rectBaiFenBi03.width() / 2, y03, paintBaiFenBi);
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

    public void setBaiFenBiDu02(int baiFenBiDu02) {
        this.baiFenBiDu02 = baiFenBiDu02;
        invalidate();
    }

    public void setBaiFenBiDu03(int baiFenBiDu03) {
        this.baiFenBiDu03 = baiFenBiDu03;
        invalidate();
    }


    public int getBaiFenBiDu01() {
        return baiFenBiDu01;
    }

    public int getBaiFenBiDu02() {
        return baiFenBiDu02;
    }

    public int getBaiFenBiDu03() {
        return baiFenBiDu03;
    }

    public void setColor01(int color01) {
        this.color01 = color01;
    }

    public void setColor02(int color02) {
        this.color02 = color02;
    }

    public void setColor03(int color03) {
        this.color03 = color03;
    }

    public float getCircleStroke() {
        return circleStroke;
    }

    public void setCircleStroke(float circleStroke) {
        this.circleStroke = circleStroke;
        invalidate();
    }

    public int getWidth11() {
        return width;
    }
}

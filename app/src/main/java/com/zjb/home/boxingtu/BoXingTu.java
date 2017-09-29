package com.zjb.home.boxingtu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/9/27.
 */
public class BoXingTu extends View {

    private int numHeng = 7;
    private int numShu = 5;
    private int width;
    private int height;
    private int dianHeight = 3;
    private float xuanZhong = 4;//加亮的位置
    private float widthJianGe;
    private float heightJianGe;
    private float quXian = 4;
    private float bianJu = 30;
    private float radius = 40;
    private float radiusPoint = 10;
    private float ShadowXY = 5;
    private float textSize = 11;
    private Paint paintHengXian;
    private Paint paintQuXian01;
    private Paint paintQuXian02;
    private Paint paintYingYing;
    private float bianJuPx;
    private float[] line01 = new float[]{
            0.21f,
            0.38f,
            0.48f,
            0.43f,
            0.56f,
            0.52f,
            0.63f,
    };
    private float[] line02 = new float[]{
            0.42f,
            0.52f,
            0.65f,
            0.58f,
            0.70f,
            0.67f,
            0.88f,
    };
    Path path01 = new Path();
    Path path02 = new Path();
    private Paint paintText;
    private String[] text = new String[]{
            "08-28",
            "08-29",
            "08-30",
            "08-31",
            "09-01",
            "09-02",
            "09-03",
    };
    private Rect rect;
    private float quXianPx;

    public BoXingTu(Context context) {
        super(context);
    }

    public BoXingTu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BoXingTu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        paintHengXian = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintHengXian.setColor(getResources().getColor(R.color.hengxian));
        paintHengXian.setStrokeWidth(DpUtils.convertDpToPixel(1, getContext()));

        quXianPx = DpUtils.convertDpToPixel(quXian, getContext());

        PathEffect pathEffect = new CornerPathEffect(radius);
        paintQuXian01 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintQuXian01.setPathEffect(pathEffect);
        paintQuXian01.setStyle(Paint.Style.STROKE);
        paintQuXian01.setStrokeCap(Paint.Cap.ROUND);
        paintQuXian01.setShadowLayer(
                DpUtils.convertDpToPixel(quXian, context),
                DpUtils.convertDpToPixel(ShadowXY, context),
                DpUtils.convertDpToPixel(ShadowXY, context),
                getResources().getColor(R.color.quXian0101));
        paintQuXian01.setStrokeWidth(quXianPx);

        paintQuXian02 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintQuXian02.setPathEffect(pathEffect);
        paintQuXian02.setStyle(Paint.Style.STROKE);
        paintQuXian02.setStrokeCap(Paint.Cap.ROUND);
        paintQuXian02.setShadowLayer(
                DpUtils.convertDpToPixel(quXian, context),
                DpUtils.convertDpToPixel(ShadowXY, context),
                DpUtils.convertDpToPixel(ShadowXY, context),
                getResources().getColor(R.color.quXian0201));
        paintQuXian02.setColor(getResources().getColor(R.color.quXian02));
        paintQuXian02.setStrokeWidth(quXianPx);

        paintYingYing = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintYingYing.setStyle(Paint.Style.FILL);
        paintYingYing.setColor(getResources().getColor(R.color.basicLight));

        paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setStyle(Paint.Style.FILL);
        paintText.setTextSize(DpUtils.convertDpToPixel(textSize, context));
        paintText.setColor(getResources().getColor(R.color.textColor));

        bianJuPx = DpUtils.convertDpToPixel(bianJu, getContext());
        rect = new Rect();
        paintText.getTextBounds(text[0], 0, text[0].length(), rect);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        widthJianGe = width / numHeng;
        heightJianGe = (height - bianJuPx) / numShu;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < numShu; i++) {
            canvas.drawLine(0, height - bianJuPx - heightJianGe * i, width, height - bianJuPx - heightJianGe * i, paintHengXian);
        }

        Shader shader = new LinearGradient(widthJianGe * (xuanZhong - 0.5f), 0, widthJianGe * (xuanZhong - 0.5f), height / 2, getResources().getColor(R.color.basicLight),
                getResources().getColor(R.color.basic), Shader.TileMode.MIRROR);
        paintYingYing.setShader(shader);
        RectF rectF = new RectF(widthJianGe * (xuanZhong - 1), 0, widthJianGe * xuanZhong, height);
        canvas.drawRect(rectF, paintYingYing);

        for (int i = 0; i < text.length; i++) {
            if (i == xuanZhong - 1) {
                paintText.setColor(getResources().getColor(R.color.textColorBlack));
            } else {
                paintText.setColor(getResources().getColor(R.color.textColor));
            }
            canvas.drawText(text[i], (widthJianGe - rect.width()) / 2 + widthJianGe * i, height - (bianJuPx - rect.height()) / 2, paintText);
        }

        canvas.save();
        paintQuXian01.setColor(getResources().getColor(R.color.quXian01));
        path01.reset();
        path01.moveTo(widthJianGe / 2 + widthJianGe * 0, height - bianJuPx - heightJianGe * numShu * line01[0]);
        for (int i = 0; i < numHeng - 1; i++) {
            path01.lineTo(widthJianGe / 2 + widthJianGe * (i + 1), height - bianJuPx - heightJianGe * numShu * line01[(i + 1)]);
        }
        canvas.drawPath(path01, paintQuXian01);
        canvas.restore();
        paintQuXian01.setShadowLayer(0, 0, 0, Color.WHITE);
        paintQuXian01.setStrokeWidth(quXianPx * 3);
        if (xuanZhong > 1 && xuanZhong < numHeng) {
            if (line01[(int) xuanZhong - 1] > line01[(int) xuanZhong - 2]) {
                canvas.drawPoint(widthJianGe * (xuanZhong - 0.5f), height - bianJuPx - heightJianGe * numShu * line01[(int) xuanZhong - 1], paintQuXian01);
            } else {
                canvas.drawPoint(widthJianGe * (xuanZhong - 0.5f), height - bianJuPx - heightJianGe * numShu * line01[(int) xuanZhong - 1] - quXianPx / 2, paintQuXian01);
            }
        } else {
            canvas.drawPoint(widthJianGe * (xuanZhong - 0.5f), height - bianJuPx - heightJianGe * numShu * line01[(int) xuanZhong - 1] - quXianPx / 2, paintQuXian01);
        }
        paintQuXian01.setStrokeWidth(quXianPx);
        paintQuXian01.setColor(Color.parseColor("#ffffff"));
        if (xuanZhong > 1 && xuanZhong < numHeng) {
            if (line01[(int) xuanZhong - 1] > line01[(int) xuanZhong - 2]) {
                canvas.drawPoint(widthJianGe * (xuanZhong - 0.5f), height - bianJuPx - heightJianGe * numShu * line01[(int) xuanZhong - 1], paintQuXian01);
            } else {
                canvas.drawPoint(widthJianGe * (xuanZhong - 0.5f), height - bianJuPx - heightJianGe * numShu * line01[(int) xuanZhong - 1] - quXianPx / 2, paintQuXian01);
            }
        } else {
            canvas.drawPoint(widthJianGe * (xuanZhong - 0.5f), height - bianJuPx - heightJianGe * numShu * line01[(int) xuanZhong - 1] - quXianPx / 2, paintQuXian01);
        }

        canvas.save();
        paintQuXian02.setColor(getResources().getColor(R.color.quXian02));
        path02.reset();
        path02.moveTo(widthJianGe / 2 + widthJianGe * 0, height - bianJuPx - heightJianGe * numShu * line02[0]);
        for (int i = 0; i < numHeng - 1; i++) {
            path02.lineTo(widthJianGe / 2 + widthJianGe * (i + 1), height - bianJuPx - heightJianGe * numShu * line02[(i + 1)]);
        }
        canvas.drawPath(path02, paintQuXian02);
        canvas.restore();
        paintQuXian02.setShadowLayer(0, 0, 0, Color.WHITE);
        paintQuXian02.setStrokeWidth(quXianPx * 3);
        if (xuanZhong > 1 && xuanZhong < numHeng) {
            if (line02[(int) xuanZhong - 1] > line02[(int) xuanZhong - 2]) {
                canvas.drawPoint(widthJianGe * (xuanZhong - 0.5f), height - bianJuPx - heightJianGe * numShu * line02[(int) xuanZhong - 1], paintQuXian02);
            } else {
                canvas.drawPoint(widthJianGe * (xuanZhong - 0.5f), height - bianJuPx - heightJianGe * numShu * line02[(int) xuanZhong - 1] - quXianPx / 2, paintQuXian02);
            }
        } else {
            canvas.drawPoint(widthJianGe * (xuanZhong - 0.5f), height - bianJuPx - heightJianGe * numShu * line02[(int) xuanZhong - 1] - quXianPx / 2, paintQuXian02);
        }
        paintQuXian02.setStrokeWidth(quXianPx);
        paintQuXian02.setColor(Color.parseColor("#ffffff"));
        if (xuanZhong > 1 && xuanZhong < numHeng) {
            if (line02[(int) xuanZhong - 1] > line02[(int) xuanZhong - 2]) {
                canvas.drawPoint(widthJianGe * (xuanZhong - 0.5f), height - bianJuPx - heightJianGe * numShu * line02[(int) xuanZhong - 1], paintQuXian02);
            } else {
                canvas.drawPoint(widthJianGe * (xuanZhong - 0.5f), height - bianJuPx - heightJianGe * numShu * line02[(int) xuanZhong - 1] - quXianPx / 2, paintQuXian02);
            }
        } else {
            canvas.drawPoint(widthJianGe * (xuanZhong - 0.5f), height - bianJuPx - heightJianGe * numShu * line02[(int) xuanZhong - 1] - quXianPx / 2, paintQuXian02);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //获取屏幕上点击的坐标
                float x = event.getX();
                Log.e("BoXingTu", "BoXingTu--onTouchEvent--" + x);
                //如果坐标在我们的文字区域内，则将点击的文字改颜色
                if (x > 0 && x < widthJianGe) {
                    //点击后，获取坐标代表的单词的含义
                    xuanZhong = 1;
                    invalidate();//更新视图
                    return true;
                } else if (x > widthJianGe && x < widthJianGe * 2) {
                    //点击后，获取坐标代表的单词的含义
                    xuanZhong = 2;
                    invalidate();//更新视图
                } else if (x > widthJianGe * 2 && x < widthJianGe * 3) {
                    //点击后，获取坐标代表的单词的含义
                    xuanZhong = 3;
                    invalidate();//更新视图
                } else if (x > widthJianGe * 3 && x < widthJianGe * 4) {
                    //点击后，获取坐标代表的单词的含义
                    xuanZhong = 4;
                    invalidate();//更新视图
                } else if (x > widthJianGe * 4 && x < widthJianGe * 5) {
                    //点击后，获取坐标代表的单词的含义
                    xuanZhong = 5;
                    invalidate();//更新视图
                } else if (x > widthJianGe * 5 && x < widthJianGe * 6) {
                    //点击后，获取坐标代表的单词的含义
                    xuanZhong = 6;
                    invalidate();//更新视图
                } else if (x > widthJianGe * 6 && x < widthJianGe * 7) {
                    //点击后，获取坐标代表的单词的含义
                    xuanZhong = 7;
                    invalidate();//更新视图
                }
                break;
        }
        //这句话不要修改
        return super.onTouchEvent(event);
    }

    public void setValue01(int i, float value) {
        line01[i] = value;
        invalidate();
    }

    public void setValue02(int i, float value) {
        line02[i] = value;
        invalidate();
    }
}

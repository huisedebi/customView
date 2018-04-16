package com.zjb.home.boxingtu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/9/27.
 */
public class BoXingTu01 extends View {

    private int numHeng = 12;
    private int numShu = 5;
    private int width;
    private int height;
    private int dianHeight = 3;
    //    private float xuanZhong = 4;//加亮的位置
    private float widthJianGe;
    private float heightJianGe;
    private float quXian = 2;
    private float bianJu = 30;
    private float radius = 40;
    private float radiusPoint = 10;
    private float ShadowXY = 5;
    private float textSize = 11;
    private Paint paintHengXian;
    private Paint paintQuXian01;
    //    private Paint paintQuXian02;
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
            0.21f,
            0.38f,
            0.48f,
            0.43f,
            0.56f,
            0.52f,
    };
    private float[] line02 = new float[]{
            0.42f,
            0.52f,
            0.65f,
            0.58f,
            0.70f,
            0.67f,
            0.88f,
            0.42f,
            0.52f,
            0.65f,
            0.58f,
            0.70f,
            0.67f,
    };
    Path path01 = new Path();
//    Path path02 = new Path();
    private Paint paintText;
    private String[] text = new String[]{
            "1月",
            "2月",
            "3月",
            "4月",
            "5月",
            "6月",
            "7月",
            "8月",
            "9月",
            "10月",
            "11月",
            "12月",
    };
    private Rect rect;
    private float quXianPx;

    public BoXingTu01(Context context) {
        super(context);
    }

    public BoXingTu01(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BoXingTu01(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        paintHengXian = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintHengXian.setColor(getResources().getColor(R.color.hengxian));
        paintHengXian.setStrokeWidth(DpUtils.convertDpToPixel(1, getContext()));

        quXianPx = DpUtils.convertDpToPixel(quXian, getContext());

//        PathEffect pathEffect = new CornerPathEffect(radius);
        paintQuXian01 = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paintQuXian01.setPathEffect(pathEffect);
        paintQuXian01.setStyle(Paint.Style.STROKE);
        paintQuXian01.setStrokeCap(Paint.Cap.ROUND);
        paintQuXian01.setShadowLayer(
                DpUtils.convertDpToPixel(quXian, context),
                DpUtils.convertDpToPixel(ShadowXY, context),
                DpUtils.convertDpToPixel(ShadowXY, context),
                getResources().getColor(R.color.quXian0101));
        paintQuXian01.setStrokeWidth(quXianPx);

//        paintQuXian02 = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paintQuXian02.setPathEffect(pathEffect);
//        paintQuXian02.setStyle(Paint.Style.STROKE);
//        paintQuXian02.setStrokeCap(Paint.Cap.ROUND);
//        paintQuXian02.setShadowLayer(
//                DpUtils.convertDpToPixel(quXian, context),
//                DpUtils.convertDpToPixel(ShadowXY, context),
//                DpUtils.convertDpToPixel(ShadowXY, context),
//                getResources().getColor(R.color.quXian0201));
//        paintQuXian02.setColor(getResources().getColor(R.color.quXian02));
//        paintQuXian02.setStrokeWidth(quXianPx);

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

        for (int i = 0; i < text.length; i++) {
            paintText.setColor(getResources().getColor(R.color.textColor));
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
        paintQuXian01.setColor(Color.BLACK);
        paintQuXian01.setStrokeWidth(quXianPx * 4);
        for (int i = 0; i < numHeng; i++) {
            canvas.drawPoint(widthJianGe * i + widthJianGe / 2, height - bianJuPx - heightJianGe * numShu * line01[i], paintQuXian01);
        }
        paintQuXian01.setStrokeWidth(quXianPx);
        paintQuXian01.setColor(Color.parseColor("#ffffff"));
    }


    public void setValue(float[] value) {
        line01 = value;
        invalidate();
    }

}

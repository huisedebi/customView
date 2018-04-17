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
    /**
     * y轴最大值
     */
    private int maxValue = 10000;
    /**
     * 横线数
     */
    private int numShu = 5;
    /**
     * 曲线宽度dp
     */
    private float quXian = 2;
    /**
     * 左右起始点边距dp
     */
    private float bianJu = 30;
    /**
     * 阴影偏移量dp
     */
    private float ShadowXY = 5;
    /**
     * 文字大小dp
     */
    private float textSize = 11;
    /**
     * 点的大小：为曲线宽度的倍数
     */
    private float pointSize = 4f;
    /**
     * 底部文字颜色
     */
    private String textColor = "#a5a5a6";
    /**
     * 曲线颜色
     */
    private String quXianColor = "#37d726";
    /**
     * 阴影颜色
     */
    private String yinYinColor = "#c9e3c6";
    /**
     * 原点颜色
     */
    private String pointColor = "#000000";
    /**
     * 曲线文字颜色
     */
    private String quxianTextColor = "#000000";
    /**
     * 横线颜色
     */
    private String hengXianColor = "#ededee";
    /**
     * 点阵值0~1.0浮点数
     */
    private float[] line01 = new float[]{
            0.91f,
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
            0.96f,
    };
    /**
     * 底部文字
     */
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
    private int width;
    private int height;
    private float widthJianGe;
    private float heightJianGe;
    private Paint paintHengXian;
    private Paint paintQuXian01;
    private float bianJuPx;
    Path path01 = new Path();
    private Paint paintText;
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
        paintHengXian.setColor(Color.parseColor(hengXianColor));
        paintHengXian.setStrokeWidth(DpUtils.convertDpToPixel(1, getContext()));

        quXianPx = DpUtils.convertDpToPixel(quXian, getContext());

        paintQuXian01 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintQuXian01.setStyle(Paint.Style.STROKE);
        paintQuXian01.setStrokeCap(Paint.Cap.ROUND);
        paintQuXian01.setShadowLayer(
                DpUtils.convertDpToPixel(quXian, context),
                DpUtils.convertDpToPixel(ShadowXY, context),
                DpUtils.convertDpToPixel(ShadowXY, context),
                Color.parseColor(yinYinColor));
        paintQuXian01.setStrokeWidth(quXianPx);

        paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setStyle(Paint.Style.FILL);
        paintText.setTextSize(DpUtils.convertDpToPixel(textSize, context));
        paintText.setColor(Color.parseColor(textColor));

        bianJuPx = DpUtils.convertDpToPixel(bianJu, getContext());
        rect = new Rect();
        paintText.getTextBounds(text[0], 0, text[0].length(), rect);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        widthJianGe = width / line01.length;
        heightJianGe = (height - bianJuPx-getPaddingTop()) / numShu;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < numShu; i++) {
            canvas.drawLine(0, height - bianJuPx - heightJianGe * i, width, height - bianJuPx - heightJianGe * i, paintHengXian);
        }

        paintText.setColor(Color.parseColor(textColor));
        for (int i = 0; i < text.length; i++) {
            canvas.drawText(text[i], (widthJianGe - rect.width()) / 2 + widthJianGe * i-rect.width()/4, height - (bianJuPx - rect.height()) / 2, paintText);
        }


        canvas.save();
        paintQuXian01.setColor(Color.parseColor(quXianColor));
        path01.reset();
        path01.moveTo(widthJianGe / 2 + widthJianGe * 0, height - bianJuPx - heightJianGe * numShu * line01[0]);
        for (int i = 0; i < line01.length - 1; i++) {
            path01.lineTo(widthJianGe / 2 + widthJianGe * (i + 1), height - bianJuPx - heightJianGe * numShu * line01[(i + 1)]);
        }
        canvas.drawPath(path01, paintQuXian01);
        canvas.restore();
        paintQuXian01.setShadowLayer(0, 0, 0, Color.WHITE);
        paintQuXian01.setColor(Color.parseColor(pointColor));
        paintQuXian01.setStrokeWidth(quXianPx * pointSize);
        for (int i = 0; i < line01.length; i++) {
            canvas.drawPoint(widthJianGe * i + widthJianGe / 2, height - bianJuPx - heightJianGe * numShu * line01[i], paintQuXian01);
        }
        paintText.setColor(Color.parseColor(quxianTextColor));
        for (int i = 0; i < text.length; i++) {
            canvas.drawText(String.valueOf((int) (line01[i]*maxValue)), (widthJianGe - rect.width()) / 2 + widthJianGe * i-rect.width()/4, height - bianJuPx - heightJianGe * numShu * line01[i]-heightJianGe/3, paintText);
        }
    }

    /**
     * 设置值
     * @param value
     */
    public void setValue(float[] value) {
        line01 = value;
        invalidate();
    }

}

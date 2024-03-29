package com.zjb.home.boxingtu.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.Shader;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.zjb.home.boxingtu.util.DpUtils;
import com.zjb.home.boxingtu.R;

/**
 * Created by Administrator on 2017/9/27.
 */
public class BoXingTu02 extends RelativeLayout {
    /**
     * y轴最大值
     */
    private int maxValue = 10000;
    /**
     * 横线数
     */
    private int numShu = 4;
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
    private float pointSize = 6f;
    /**
     * 底部文字颜色
     */
    private String textColor = "#6F6F76";
    /**
     * 曲线颜色
     */
    private String quXianColor = "#5556C9";
    /**
     * 阴影颜色
     */
    private String yinYinColor = "#c9e3c6";
    /**
     * 原点颜色
     */
    private String pointColor = "#ffffff";
    /**
     * 曲线文字颜色
     */
    private String quxianTextColor = "#000000";
    /**
     * 横线颜色
     */
    private String hengXianColor = "#40404D";
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
            0.91f,
            0.38f,
            0.78f,
            -1f,
            -1f,
            -1f,
            -1f,
            -1f,
            -1f,
            -1f,
            -1f,
            -1f,
            -1f,
            -1f,
            -1f,
            -1f,
            -1f,
            -1f,
    };
    /**
     * 底部文字
     */
    private String[] text = new String[]{
            "Jun",
            "Jul",
            "Auj",
            "Sep",
            "Oct",
    };
    /**
     * 底部文字
     */
    private String[] textLeft = new String[]{
            "0",
            "2K",
            "4K",
            "6K",
            "8K",
    };
    private int width;
    private int height;
    private float widthJianGePoint;
    private float heightJianGe;
    private Paint paintHengXian;
    private Paint paintQuXian01;
    private Paint paintYinYing;
    private Paint paintPoint;
    private float bianJuPx;
    Path path01 = new Path();
    Path path02 = new Path();
    private Paint paintText;
    private Rect rect;
    private Rect rectLeft;
    private float quXianPx;
    private float radius = 40;
    private float widthJianGeText;
    private int leftTextMargin;
    private int duanDian = 14;
    private float rightPadding;
    private Paint paintZheGai;
    private float hengXiangPx;
    private View view;

    public BoXingTu02(Context context) {
        super(context);
    }

    public BoXingTu02(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BoXingTu02(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        paintHengXian = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintHengXian.setColor(Color.parseColor(hengXianColor));
        hengXiangPx = DpUtils.convertDpToPixel(1, getContext());
        paintHengXian.setStrokeWidth(hengXiangPx);
        PathEffect pathEffect = new CornerPathEffect(radius);
        quXianPx = DpUtils.convertDpToPixel(quXian, getContext());

        paintQuXian01 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintQuXian01.setPathEffect(pathEffect);
        paintQuXian01.setStyle(Paint.Style.STROKE);
        paintQuXian01.setStrokeCap(Paint.Cap.ROUND);
        paintQuXian01.setStrokeWidth(quXianPx);

        paintYinYing = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintYinYing.setPathEffect(pathEffect);
        paintYinYing.setStyle(Paint.Style.FILL_AND_STROKE);
        paintYinYing.setStrokeWidth(quXianPx);

        paintZheGai = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintZheGai.setStyle(Paint.Style.FILL_AND_STROKE);
        paintZheGai.setStrokeWidth(quXianPx);
        paintZheGai.setColor(ContextCompat.getColor(getContext(), R.color.boXing02Bg));

        paintPoint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintPoint.setStyle(Paint.Style.STROKE);
        paintPoint.setStrokeCap(Paint.Cap.ROUND);
        paintPoint.setColor(Color.parseColor(pointColor));
        paintPoint.setStrokeWidth(quXianPx * pointSize);

        paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setStyle(Paint.Style.FILL);
        paintText.setTextSize(DpUtils.convertDpToPixel(textSize, context));
        paintText.setColor(Color.parseColor(textColor));

        bianJuPx = DpUtils.convertDpToPixel(bianJu, getContext());
        rect = new Rect();
        rectLeft = new Rect();
        paintText.getTextBounds(text[0], 0, text[0].length(), rect);
        paintText.getTextBounds(textLeft[textLeft.length - 1], 0, textLeft[textLeft.length - 1].length(), rectLeft);
        view = LayoutInflater.from(getContext()).inflate(R.layout.view_float_boxingtu02, null);
        addView(view);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = (int) (MeasureSpec.getSize(widthMeasureSpec) - rightPadding);
        height = MeasureSpec.getSize(heightMeasureSpec);
        rightPadding = DpUtils.convertDpToPixel(10, getContext());
        widthJianGePoint = width / line01.length;
        widthJianGeText = width / text.length;
        heightJianGe = (height - bianJuPx - getPaddingTop()) / numShu;
        /**
         * 左边文字占用宽度
         */
        leftTextMargin = rectLeft.width() + (int) DpUtils.convertDpToPixel(12, getContext());
        Shader shader = new LinearGradient(0, height / 2, widthJianGePoint * duanDian, height / 2, ContextCompat.getColor(getContext(), R.color.startColor01),
                ContextCompat.getColor(getContext(), R.color.endColor01), Shader.TileMode.CLAMP);
        Shader shaderYinYing = new LinearGradient(0, height / 2, widthJianGePoint * duanDian, height / 2, ContextCompat.getColor(getContext(), R.color.startColor01_tran),
                ContextCompat.getColor(getContext(), R.color.endColor01_tran), Shader.TileMode.CLAMP);
        paintQuXian01.setShader(shader);
        paintYinYing.setShader(shaderYinYing);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < numShu + 1; i++) {
            canvas.drawLine(leftTextMargin, height - bianJuPx - heightJianGe * i, width, height - bianJuPx - heightJianGe * i, paintHengXian);
        }


        canvas.save();
        path01.reset();
        path01.moveTo(widthJianGePoint / 2 + widthJianGePoint * 0 + leftTextMargin, height - bianJuPx - heightJianGe * numShu * line01[0]);
        for (int i = 0; i < line01.length - 1; i++) {
            if (line01[i + 1] >= 0) {
                path01.lineTo(widthJianGePoint / 2 + widthJianGePoint * (i + 1) + leftTextMargin, height - bianJuPx - heightJianGe * numShu * line01[(i + 1)]);
            }
        }
        canvas.drawPath(path01, paintQuXian01);
        canvas.restore();
        canvas.save();
        path02.addPath(path01);
        path02.lineTo(widthJianGePoint / 2 + widthJianGePoint * (duanDian) + leftTextMargin, height);
        path02.lineTo(widthJianGePoint / 2 + widthJianGePoint * 0 + leftTextMargin, height);
        path02.close();
        canvas.drawPath(path02, paintYinYing);
        canvas.restore();

        canvas.drawRect(widthJianGePoint / 2 + widthJianGePoint * 0 + leftTextMargin, height - bianJuPx + hengXiangPx * 2, widthJianGePoint / 2 + widthJianGePoint * (duanDian) + leftTextMargin, height, paintZheGai);

        canvas.drawPoint(widthJianGePoint * (duanDian) + widthJianGePoint / 2 + leftTextMargin, height - bianJuPx - heightJianGe * numShu * line01[duanDian], paintPoint);

        paintText.setColor(Color.parseColor(textColor));
        for (int i = 0; i < textLeft.length; i++) {
            canvas.drawText(textLeft[i], 0, height - bianJuPx - heightJianGe * i + rectLeft.height() / 2 - quXianPx / 2, paintText);
        }
        for (int i = 0; i < text.length; i++) {
            canvas.drawText(text[i], (widthJianGeText - rect.width()) / 2 + widthJianGeText * i - rect.width() / 4 + leftTextMargin, height - (bianJuPx - rect.height()) / 2, paintText);
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        layoutParams.leftMargin = (int) (widthJianGePoint * (duanDian) + widthJianGePoint / 2 + leftTextMargin - view.getMeasuredWidth() / 2);
        layoutParams.topMargin = (int) (height - bianJuPx - heightJianGe * numShu * line01[duanDian] - 2.5f * view.getMeasuredHeight());
        view.setLayoutParams(layoutParams);
    }

    /**
     * 设置值
     *
     * @param value
     */
    public void setValue(float[] value) {
        line01 = value;
        invalidate();
    }

}

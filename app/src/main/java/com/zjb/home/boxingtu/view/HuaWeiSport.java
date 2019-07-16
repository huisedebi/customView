package com.zjb.home.boxingtu.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.zjb.home.boxingtu.util.DpUtils;

/**
 * Created by Administrator on 2017/9/27.
 */
public class HuaWeiSport extends View {

    private int numHeng = 6;
    private int numShu = 7;
    private int width;
    private int height;
    private float xuanZhong = 4;//加亮的位置
    private float widthJianGe;
    private float heightJianGe;
    private float quXian = 2;
    private float bianJu = 30;
    private float radius = 10;
    private float textSize = 11;
    private Paint paintHengXian;
    private Paint paintQuXian01;
    private float bianJuPx;
    private float bianJuLeftPx;
    private float[] line01 = new float[]{
            0.21f,
            0.38f,
            0.48f,
            1.00f,
            0.56f,
            0.52f,
            0.62f,
    };
    private Paint paintText;
    private String[] text = new String[]{
            "周一",
            "周二",
            "周三",
            "周四",
            "周五",
            "周六",
            "周日",
    };
    private Rect rect;
    private float quXianPx;
    private float ZhuZhuangWidth = 10f;//柱状宽度
    private float ZhuZhuangWidthPx;
    private float marginTop = 15f;//距离顶部
    private float marginTopPx;
    private float dp5;

    public HuaWeiSport(Context context) {
        super(context);
    }

    public HuaWeiSport(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HuaWeiSport(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        paintHengXian = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintHengXian.setColor(Color.parseColor("#CBCBCB"));
        paintHengXian.setStrokeWidth(DpUtils.convertDpToPixel(1, getContext()));

        quXianPx = DpUtils.convertDpToPixel(quXian, getContext());

        paintQuXian01 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintQuXian01.setStyle(Paint.Style.FILL);
        paintQuXian01.setStrokeWidth(quXianPx);

        paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setStyle(Paint.Style.FILL);
        paintText.setTextSize(DpUtils.convertDpToPixel(textSize, context));
        paintText.setColor(Color.parseColor("#CBCBCB"));

        bianJuPx = DpUtils.convertDpToPixel(bianJu, getContext());
        rect = new Rect();
        paintText.getTextBounds(text[0], 0, text[0].length(), rect);

        dp5 = DpUtils.convertDpToPixel(5, getContext());

        ZhuZhuangWidthPx = DpUtils.convertDpToPixel(ZhuZhuangWidth, getContext());
        marginTopPx = DpUtils.convertDpToPixel(marginTop,getContext());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        widthJianGe = (width - bianJuLeftPx) / numHeng;
        heightJianGe = (height - bianJuPx - marginTopPx) / numShu;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画灰色横线
        for (int i = 0; i < numShu + 1; i++) {
            paintHengXian.setColor(Color.parseColor("#f1f1f1"));
            canvas.drawLine(bianJuLeftPx - dp5, height - bianJuPx - heightJianGe * i, bianJuLeftPx, height - bianJuPx - heightJianGe * i, paintHengXian);
        }
        //画柱状
        paintQuXian01.setColor(Color.parseColor("#007DFD"));
        for (int i = 0; i < numHeng; i++) {
            canvas.save();
            Path path1 = new Path();
            RectF rectf = new RectF(widthJianGe * i +widthJianGe / 2f + bianJuLeftPx - ZhuZhuangWidthPx / 2f,
                    height - bianJuPx - heightJianGe * numShu * line01[i],
                    widthJianGe * i +widthJianGe / 2f + bianJuLeftPx + ZhuZhuangWidthPx / 2f,
                    height - bianJuPx);
            path1.addRoundRect(rectf, new float[]{ZhuZhuangWidthPx / 2f, ZhuZhuangWidthPx / 2f, ZhuZhuangWidthPx / 2f, ZhuZhuangWidthPx / 2f, 0, 0, 0, 0}, Path.Direction.CCW);
            canvas.drawPath(path1, paintQuXian01);
            canvas.restore();
        }
        //画最底部横线
        paintHengXian.setColor(Color.parseColor("#f1f1f1"));
        canvas.drawLine(0 + bianJuLeftPx, height - bianJuPx - heightJianGe * 0, width, height - bianJuPx - heightJianGe * 0, paintHengXian);
        //画底部文字和刻度
        paintHengXian.setColor(Color.parseColor("#888888"));
        for (int i = 0; i < text.length + 1; i++) {
            if (i < text.length) {
                canvas.drawText(text[i], (widthJianGe - rect.width()) / 2 + widthJianGe * i + bianJuLeftPx, height - (bianJuPx - rect.height()) / 2, paintText);
            }
        }
    }

    public void setValue01(int i, float value) {
        line01[i] = value;
        invalidate();
    }

}

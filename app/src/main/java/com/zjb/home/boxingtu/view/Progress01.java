package com.zjb.home.boxingtu.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.zjb.home.boxingtu.util.Arith;
import com.zjb.home.boxingtu.util.DpUtils;

/**
 * Created by zhangjiebo on 2019/7/7.
 *
 * @author ZhangJieBo
 */
public class Progress01 extends View {

    private Paint paintBg;
    private int conner = 10;
    private int stroke = 1;
    private float convertDpToPixel;
    private float precent = 0.004f;
    private Paint paintText;
    private Rect rect;
    private float strokeWidthPx;
    private int progressColor01 = Color.parseColor("#0095FF");

    public Progress01(Context context) {
        super(context);
        init();
    }

    public Progress01(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Progress01(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paintBg = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBg.setStyle(Paint.Style.FILL);
        paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setTextSize(DpUtils.convertDpToPixel(13, getContext()));
        rect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        convertDpToPixel = DpUtils.convertDpToPixel(conner, getContext());
        strokeWidthPx = DpUtils.convertDpToPixel(stroke, getContext());
        paintBg.setColor(progressColor01);
        RectF oval1 = new RectF(0, 0, getWidth(), getHeight());// 设置个新的长方形
        canvas.drawRoundRect(oval1, convertDpToPixel, convertDpToPixel, paintBg);//第二个参数是x半径，第三个参数是y半径
        paintBg.setColor(Color.parseColor("#F8F8F8"));
        RectF oval2 = new RectF(strokeWidthPx, strokeWidthPx, getWidth() - strokeWidthPx, getHeight() - strokeWidthPx);// 设置个新的长方形
        canvas.drawRoundRect(oval2, convertDpToPixel, convertDpToPixel, paintBg);//第二个参数是x半径，第三个参数是y半径
        paintBg.setColor(progressColor01);
        float progressWidth =( getWidth()-2f*strokeWidthPx )* precent;

        RectF oval4 = new RectF(0, 0, getWidth(), getHeight());// 设置个新的长方形

        Path path1 = new Path();
        path1.addRoundRect(oval4, new float[]{convertDpToPixel, convertDpToPixel, convertDpToPixel, convertDpToPixel,
                convertDpToPixel, convertDpToPixel, convertDpToPixel, convertDpToPixel}, Path.Direction.CCW);
        canvas.clipPath(path1);

        Path path = new Path();
        canvas.save();
        RectF oval3 = new RectF(strokeWidthPx, strokeWidthPx, progressWidth, getHeight()-strokeWidthPx);// 设置个新的长方形
        path.addRoundRect(oval3, new float[]{convertDpToPixel, convertDpToPixel, 0, 0,
                0, 0, convertDpToPixel, convertDpToPixel}, Path.Direction.CCW);
        canvas.drawPath(path, paintBg);
        canvas.restore();

        String prcentText = Arith.formatFloatNumber((double) (precent * 100f)) + "%";
        paintText.getTextBounds(prcentText, 0, prcentText.length(), rect);
        if (rect.width() + 2f * DpUtils.convertDpToPixel(8, getContext()) > progressWidth) {
            paintText.setColor(progressColor01);
            canvas.drawText(prcentText, progressWidth + DpUtils.convertDpToPixel(8, getContext()), getHeight() / 2f + rect.height() / 2f, paintText);
        } else {
            paintText.setColor(Color.parseColor("#ffffff"));
            canvas.drawText(prcentText, progressWidth - DpUtils.convertDpToPixel(8, getContext()) - rect.width(), getHeight() / 2f + rect.height() / 2f, paintText);
        }
    }
    float[] startHsv = new float[3];
    float[] endHsv = new float[3];
    float[] outHsv = new float[3];
    public void setPrecent(float precent) {
        this.precent = precent;
//        if (precent>0.5f){
//            progressColor01 = Color.parseColor("#FF5500");
//        }else {
//            progressColor01 = Color.parseColor("#0095FF");
//        }

        // 把 ARGB 转换成 HSV
        int startValue = Color.parseColor("#0095FF");
        Color.colorToHSV(startValue, startHsv);
        int endValue = Color.parseColor("#FF5500");
        Color.colorToHSV(endValue, endHsv);

        // 计算当前动画完成度（fraction）所对应的颜色值
        if (endHsv[0] - startHsv[0] > 180) {
            endHsv[0] -= 360;
        } else if (endHsv[0] - startHsv[0] < -180) {
            endHsv[0] += 360;
        }
        outHsv[0] = startHsv[0] + (endHsv[0] - startHsv[0]) * precent;
        if (outHsv[0] > 360) {
            outHsv[0] -= 360;
        } else if (outHsv[0] < 0) {
            outHsv[0] += 360;
        }
        outHsv[1] = startHsv[1] + (endHsv[1] - startHsv[1]) * precent;
        outHsv[2] = startHsv[2] + (endHsv[2] - startHsv[2]) * precent;

        // 计算当前动画完成度（fraction）所对应的透明度
        int alpha = startValue >> 24 + (int) ((endValue >> 24 - startValue >> 24) * precent);

        // 把 HSV 转换回 ARGB 返回
        progressColor01 = Color.HSVToColor(alpha, outHsv);

        invalidate();
    }

    public void setPrecenrAnim(float precent) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "precent", this.precent, precent);
        animator.setDuration(1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
    }
}

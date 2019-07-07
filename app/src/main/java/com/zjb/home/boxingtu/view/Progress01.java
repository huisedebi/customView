package com.zjb.home.boxingtu.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
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
    private float precent = 0.1f;
    private Paint paintText;
    private Rect rect;

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
        paintBg.setColor(Color.parseColor("#0095FF"));
        RectF oval1 = new RectF(0, 0, getWidth(), getHeight());// 设置个新的长方形
        canvas.drawRoundRect(oval1, convertDpToPixel, convertDpToPixel, paintBg);//第二个参数是x半径，第三个参数是y半径
        paintBg.setColor(Color.parseColor("#F8F8F8"));
        RectF oval2 = new RectF(DpUtils.convertDpToPixel(1, getContext()), DpUtils.convertDpToPixel(1, getContext()), getWidth() - DpUtils.convertDpToPixel(1, getContext()), getHeight() - DpUtils.convertDpToPixel(1, getContext()));// 设置个新的长方形
        canvas.drawRoundRect(oval2, convertDpToPixel, convertDpToPixel, paintBg);//第二个参数是x半径，第三个参数是y半径
        paintBg.setColor(Color.parseColor("#0095FF"));
        canvas.save();
        Path path = new Path();
        float precent = this.precent;
        float progressWidth = getWidth() * precent;
        RectF oval3 = new RectF(0, 0, progressWidth, getHeight());// 设置个新的长方形
        path.addRoundRect(oval3, new float[]{convertDpToPixel, convertDpToPixel, 0, 0,
                0, 0, convertDpToPixel, convertDpToPixel}, Path.Direction.CW);
        canvas.drawPath(path, paintBg);
        canvas.restore();
        String prcentText = Arith.formatFloatNumber((double) (precent * 100f)) + "%";
        paintText.getTextBounds(prcentText, 0, prcentText.length(), rect);
        if (rect.width() + 2f * DpUtils.convertDpToPixel(8, getContext()) > progressWidth) {
            paintText.setColor(Color.parseColor("#0095FF"));
            canvas.drawText(prcentText, progressWidth + DpUtils.convertDpToPixel(8, getContext()), getHeight() / 2f + rect.height() / 2f, paintText);
        } else {
            paintText.setColor(Color.parseColor("#ffffff"));
            canvas.drawText(prcentText, progressWidth - DpUtils.convertDpToPixel(8, getContext()) - rect.width(), getHeight() / 2f + rect.height() / 2f, paintText);
        }
    }

    public void setPrecent(float precent) {
        this.precent = precent;
        invalidate();
    }

    public void setPrecenrAnim(float precent){
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "precent", this.precent, precent);
        animator.setDuration(1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
    }
}

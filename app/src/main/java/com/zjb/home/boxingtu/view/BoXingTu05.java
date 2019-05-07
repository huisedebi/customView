package com.zjb.home.boxingtu.view;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.zjb.home.boxingtu.util.DpUtils;
import com.zjb.home.boxingtu.util.LogUtil;

/**
 * Created by Administrator on 2017/9/27.
 */
public class BoXingTu05 extends View {

    private int numHeng = 8;
    private int numShu = 5;
    private int width;
    private int height;
    private int dianHeight = 3;
    private float widthJianGe;
    private float heightJianGe;
    private float quXian = 3;
    private float bianJu = 30;
    private float radius = 40;
    private float radiusPoint = 10;
    private float ShadowXY = 5;
    private float textSize = 12;
    private Paint paintHengXian;
    private Paint paintQuXian01;
    private float paddingTop = 0;
    private float bianJuPx;
    private float[] line01 = new float[]{
            0f,
            0f,
            0f,
            0f,
            0f,
            0f,
            0f,
            0f,
    };
    Path path01 = new Path();
    private Paint paintText;
    private String[] text = new String[]{
            "01",
            "02",
            "03",
            "04",
            "05",
            "06",
            "07",
            "08"
    };
    private Rect rect;
    private float quXianPx;
    private Paint paintCircle;
    private Paint paintpoint;

    public BoXingTu05(Context context) {
        super(context);
    }

    public BoXingTu05(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BoXingTu05(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        paintHengXian = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintHengXian.setColor(Color.parseColor("#D4D2D2"));
        paintHengXian.setStrokeWidth(DpUtils.convertDpToPixel(1, getContext()));

        quXianPx = DpUtils.convertDpToPixel(quXian, getContext());

        PathEffect pathEffect = new CornerPathEffect(radius);
        paintQuXian01 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintQuXian01.setPathEffect(pathEffect);
        paintQuXian01.setStyle(Paint.Style.STROKE);
        paintQuXian01.setStrokeCap(Paint.Cap.ROUND);
        paintQuXian01.setStrokeWidth(quXianPx);
        paintQuXian01.setShadowLayer(
                DpUtils.convertDpToPixel(quXian, getContext()),
                DpUtils.convertDpToPixel(ShadowXY, getContext()),
                DpUtils.convertDpToPixel(ShadowXY, getContext()),
                Color.parseColor("#B9B9B9"));
        paintQuXian01.setColor(Color.parseColor("#4F86FF"));

        paintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setStrokeCap(Paint.Cap.ROUND);
        paintCircle.setStrokeWidth(quXianPx * 4);
        paintCircle.setColor(Color.parseColor("#4F81EE"));

        paintpoint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintpoint.setStyle(Paint.Style.STROKE);
        paintpoint.setStrokeCap(Paint.Cap.ROUND);
        paintpoint.setStrokeWidth(quXianPx * 4 - DpUtils.convertDpToPixel(1f, getContext()));
        paintpoint.setColor(Color.parseColor("#ffffff"));

        paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setStyle(Paint.Style.FILL);
        paintText.setTextSize(DpUtils.convertDpToPixel(textSize, context));
        paintText.setColor(Color.parseColor("#D0D0D0"));

        bianJuPx = DpUtils.convertDpToPixel(bianJu, getContext());
        rect = new Rect();
        paintText.getTextBounds(text[0], 0, text[0].length(), rect);

        //顶部边距
        paddingTop = DpUtils.convertDpToPixel(10, getContext());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        widthJianGe = width / numHeng;
        heightJianGe = (height - bianJuPx - paddingTop) / numShu;
        PathEffect pathEffect1 = new DashPathEffect(new float[]{heightJianGe * 2f / 3f, heightJianGe * 1f / 3f}, 0);
        paintHengXian.setPathEffect(pathEffect1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < numHeng; i++) {
            if (i == position) {
                paintHengXian.setColor(Color.parseColor("#4F86FF"));
                canvas.drawLine(widthJianGe / 2 + widthJianGe * i, height - bianJuPx, widthJianGe / 2 + widthJianGe * i, paddingTop, paintHengXian);
            } else {
                paintHengXian.setColor(Color.parseColor("#D4D2D2"));
                canvas.drawLine(widthJianGe / 2 + widthJianGe * i, height - bianJuPx, widthJianGe / 2 + widthJianGe * i, paddingTop, paintHengXian);
            }
        }


        //画底部文字
        for (int i = 0; i < text.length; i++) {
            if (i == position) {
                paintText.setColor(Color.parseColor("#000000"));
            } else {
                paintText.setColor(Color.parseColor("#D0D0D0"));
            }
            canvas.drawText(text[i], (widthJianGe - rect.width()) / 2 + widthJianGe * i, height - (bianJuPx - rect.height()) / 2, paintText);
        }


        canvas.save();
        path01.reset();
        path01.moveTo(widthJianGe / 2 + widthJianGe * 0, height - bianJuPx - heightJianGe * numShu * line01[0]);
        for (int i = 0; i < numHeng - 1; i++) {
            path01.lineTo(widthJianGe / 2 + widthJianGe * (i + 1), height - bianJuPx - heightJianGe * numShu * line01[(i + 1)]);
        }
        canvas.drawPath(path01, paintQuXian01);
        canvas.restore();


        if (position == 0) {
            canvas.drawPoint(widthJianGe * (0.5f), height - bianJuPx - heightJianGe * numShu * line01[position], paintCircle);
            canvas.drawPoint(widthJianGe * (0.5f), height - bianJuPx - heightJianGe * numShu * line01[position], paintpoint);
        } else if (position == numHeng - 1) {
            canvas.drawPoint(widthJianGe * (0.5f) + widthJianGe * position, height - bianJuPx - heightJianGe * numShu * line01[position], paintCircle);
            canvas.drawPoint(widthJianGe * (0.5f) + widthJianGe * position, height - bianJuPx - heightJianGe * numShu * line01[position], paintpoint);
        } else {
            if (line01[position] > line01[position - 1]) {
                if (line01[position] < line01[position + 1]) {
                    canvas.drawPoint(widthJianGe * (0.5f) + widthJianGe * position, height - bianJuPx - heightJianGe * numShu * line01[position], paintCircle);
                    canvas.drawPoint(widthJianGe * (0.5f) + widthJianGe * position, height - bianJuPx - heightJianGe * numShu * line01[position], paintpoint);
                } else {
                    canvas.drawPoint(widthJianGe * (0.5f) + widthJianGe * position, height - bianJuPx - heightJianGe * numShu * line01[position] + quXianPx * 2.5f, paintCircle);
                    canvas.drawPoint(widthJianGe * (0.5f) + widthJianGe * position, height - bianJuPx - heightJianGe * numShu * line01[position] + quXianPx * 2.5f, paintpoint);
                }
            } else {
                if (line01[position] < line01[position + 1]) {
                    canvas.drawPoint(widthJianGe * (0.5f) + widthJianGe * position, height - bianJuPx - heightJianGe * numShu * line01[position] - quXianPx * 2.5f, paintCircle);
                    canvas.drawPoint(widthJianGe * (0.5f) + widthJianGe * position, height - bianJuPx - heightJianGe * numShu * line01[position] - quXianPx * 2.5f, paintpoint);
                } else {
                    canvas.drawPoint(widthJianGe * (0.5f) + widthJianGe * position, height - bianJuPx - heightJianGe * numShu * line01[position], paintCircle);
                    canvas.drawPoint(widthJianGe * (0.5f) + widthJianGe * position, height - bianJuPx - heightJianGe * numShu * line01[position], paintpoint);
                }
            }
        }

    }

    public int getPosition(float x) {
        for (int i = 0; i < text.length; i++) {
            if (x > widthJianGe / 2f + widthJianGe * (i) + widthJianGe / 2 - widthJianGe / 2f && x < widthJianGe / 2f + widthJianGe * (i) + widthJianGe / 2 + widthJianGe / 2f) {
                return i;
            }
        }
        if (x < widthJianGe / 2f + widthJianGe * (0) + widthJianGe / 2 + widthJianGe / 2f) {
            return 0;
        }
        if (x > widthJianGe / 2f + widthJianGe * (text.length - 1) + widthJianGe / 2 - widthJianGe / 2f) {
            return text.length - 1;
        }
        return -1;
    }

    private int position;
    private float xDown;

    public interface OnGetPositionListener {
        void getPosiotion(int position);
    }

    OnGetPositionListener onGetPositionListener;

    public void setOnGetPositionListener(OnGetPositionListener onGetPositionListener) {
        this.onGetPositionListener = onGetPositionListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                position = -1;
                xDown = event.getX();
                int positionX1 = getPosition(xDown);
                if (positionX1 != position) {
                    position = positionX1;
                    if (onGetPositionListener != null) {
                        onGetPositionListener.getPosiotion(position);
                    }
                    invalidate();
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                float xDown = event.getX();
                int positionX2 = getPosition(xDown);
                if (positionX2 != position) {
                    position = positionX2;
                    if (onGetPositionListener != null) {
                        onGetPositionListener.getPosiotion(position);
                    }
                    invalidate();
                }
                return true;
//            case MotionEvent.ACTION_UP:
//                isPress = false;
//                if (position != -1) {
//                    if (onGetPositionListener != null) {
//                        onGetPositionListener.getPosiotion(-1);
//                    }
//                }
//                invalidate();
//                return true;
            default:
                break;
        }
        //这句话不要修改
        return super.onTouchEvent(event);
    }

    public void setValue01(int i, float value) {
        line01[i] = value;
        invalidate();
    }

    public void setBaiFenBiDuAnim(float[] baiFenBiDu) {
        ObjectAnimator animator = ObjectAnimator.ofObject(this, "Line", new HsvEvaluator(), line01, baiFenBiDu);
        animator.setDuration(3000);
        animator.setInterpolator(new FastOutSlowInInterpolator());
        animator.start();
    }

    public void setLine(float[] line01) {
        this.line01 = line01;
        invalidate();
    }

    // 自定义 HslEvaluator
    private class HsvEvaluator implements TypeEvaluator<float[]> {
        float[] outValue = new float[8];
        @Override
        public float[] evaluate(float fraction, float[] startValue, float[] endValue) {
            for (int i = 0; i < startValue.length; i++) {
                outValue[i] = startValue[i] + (endValue[i] - startValue[i]) * fraction;
                LogUtil.LogShitou("HsvEvaluator--evaluate", ""+outValue[i]);
            }
            return outValue;
        }
    }

}

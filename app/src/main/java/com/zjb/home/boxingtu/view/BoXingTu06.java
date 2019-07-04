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
import android.view.View;
import android.view.ViewGroup;

import com.zjb.home.boxingtu.util.DpUtils;
import com.zjb.home.boxingtu.util.ScreenUtils;


/**
 * Created by Administrator on 2017/9/27.
 */
public class BoXingTu06 extends View {

    private int numShu = 5;
    private int width;
    private int height;
    private int dianHeight = 3;
    private float widthJianGe;
    private float heightJianGe;
    private float quXian = 3;
    private float bianJu = 40;
    private float radius = 40;
    private float radiusPoint = 10;
    private float ShadowXY = 5;
    private float textSize = 12;
    private Paint paintHengXian;
    private Paint paintQuXian01;
    private float paddingTop = 0;
    private float bianJuPx;
    private float[] line01 = new float[]{
            0.00f,
            0.00f,
            0.00f,
            0.00f,
            0.00f,
            0.00f,
            0.00f,
            0.00f,
            0.00f,
            0.00f,
            0.00f,
            0.00f,
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
            "08",
            "09",
            "10",
            "11",
            "12"
    };
    private String[] text01 = new String[]{
            "01/01",
            "02/01",
            "03/01",
            "04/01",
            "05/01",
            "06/01",
            "07/01",
            "08/01",
            "09/01",
            "10/01",
            "11/01",
            "12/01"
    };
    private Rect rect;
    private float quXianPx;
    private Paint paintCircle;
    private Paint paintpoint;
    private int type;
    private float paddLeft;
    private float startLeft;
    private Rect rect1;
    private int screenWidth;

    public BoXingTu06(Context context) {
        super(context);
    }

    public BoXingTu06(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BoXingTu06(Context context, AttributeSet attrs, int defStyleAttr) {
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
        paintCircle.setStrokeWidth(quXianPx * 3.5f);
        paintCircle.setColor(Color.parseColor("#4F81EE"));


        paintpoint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintpoint.setStyle(Paint.Style.STROKE);
        paintpoint.setStrokeCap(Paint.Cap.ROUND);
        paintpoint.setStrokeWidth(quXianPx * 3.5f - DpUtils.convertDpToPixel(1f, getContext()));
        paintpoint.setColor(Color.parseColor("#ffffff"));

        paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setStyle(Paint.Style.FILL);
        paintText.setTextSize(DpUtils.convertDpToPixel(textSize, context));
        paintText.setColor(Color.parseColor("#D0D0D0"));

        bianJuPx = DpUtils.convertDpToPixel(bianJu, getContext());
        rect = new Rect();
        paintText.getTextBounds(text[0], 0, text[0].length(), rect);
        rect1 = new Rect();
        paintText.getTextBounds(text01[0], 0, text01[0].length(), rect1);

        screenWidth = ScreenUtils.getScreenWidth(getContext());
        startLeft = screenWidth / 2f;
        paddLeft = DpUtils.convertDpToPixel(30, getContext());
        //顶部边距
        paddingTop = DpUtils.convertDpToPixel(10, getContext());
        widthJianGe = (screenWidth - 2f * paddLeft) / 7f;
        PathEffect pathEffect1 = new DashPathEffect(new float[]{heightJianGe * 2f / 3f, heightJianGe * 1f / 3f}, 0);
        paintHengXian.setPathEffect(pathEffect1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        heightJianGe = (height - bianJuPx - paddingTop) / numShu;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < text.length; i++) {
            if (i == position) {
                paintHengXian.setColor(Color.parseColor("#4F86FF"));
                canvas.drawLine(startLeft + widthJianGe * i, height - bianJuPx, startLeft + widthJianGe * i, paddingTop, paintHengXian);
            } else {
                paintHengXian.setColor(Color.parseColor("#D4D2D2"));
                canvas.drawLine(startLeft + widthJianGe * i, height - bianJuPx, startLeft + widthJianGe * i, paddingTop, paintHengXian);
            }
        }


        //画底部文字
        for (int i = 0; i < text.length; i++) {
            if (i == position) {
                paintText.setColor(Color.parseColor("#000000"));
            } else {
                paintText.setColor(Color.parseColor("#D0D0D0"));
            }
            if (type == 1) {
                canvas.drawText(text[i], startLeft - rect1.width() / 2f + widthJianGe * i, height - bianJuPx + rect1.height() * 2, paintText);
                canvas.drawText(text01[i], startLeft - rect1.width() / 2f + widthJianGe * i, height - bianJuPx + rect1.height() * 3.5f, paintText);
            } else {
                canvas.drawText(text[i], startLeft - rect.width() / 2f + widthJianGe * i, height - bianJuPx + rect.height() * 2, paintText);
            }
        }


        canvas.save();
        path01.reset();
        path01.moveTo(startLeft + widthJianGe * 0, height - bianJuPx - heightJianGe * numShu * line01[0]);
        for (int i = 1; i < line01.length; i++) {
            path01.lineTo(startLeft + widthJianGe * (i), height - bianJuPx - heightJianGe * numShu * line01[(i)]);
        }
        canvas.drawPath(path01, paintQuXian01);
        canvas.restore();


        if (position == 0) {
            canvas.drawPoint(startLeft, height - bianJuPx - heightJianGe * numShu * line01[position], paintCircle);
            canvas.drawPoint(startLeft, height - bianJuPx - heightJianGe * numShu * line01[position], paintpoint);
        } else if (position == line01.length - 1) {
            canvas.drawPoint(startLeft + widthJianGe * position, height - bianJuPx - heightJianGe * numShu * line01[position], paintCircle);
            canvas.drawPoint(startLeft + widthJianGe * position, height - bianJuPx - heightJianGe * numShu * line01[position], paintpoint);
        } else {
            if (line01[position] > line01[position - 1]) {
                if (line01[position] < line01[position + 1]) {
                    canvas.drawPoint(startLeft + widthJianGe * position, height - bianJuPx - heightJianGe * numShu * line01[position], paintCircle);
                    canvas.drawPoint(startLeft + widthJianGe * position, height - bianJuPx - heightJianGe * numShu * line01[position], paintpoint);
                } else {
                    float abs = Math.abs(line01[position - 1] - line01[position]);
                    canvas.drawPoint(startLeft + widthJianGe * position, height - bianJuPx - heightJianGe * numShu * line01[position] + quXianPx * 2.5f*abs, paintCircle);
                    canvas.drawPoint(startLeft + widthJianGe * position, height - bianJuPx - heightJianGe * numShu * line01[position] + quXianPx * 2.5f*abs, paintpoint);
                }
            } else {
                if (line01[position] < line01[position + 1]) {
                    float abs = Math.abs(line01[position + 1] - line01[position]);
                    canvas.drawPoint(startLeft + widthJianGe * position, height - bianJuPx - heightJianGe * numShu * line01[position] - quXianPx * 2.5f*abs, paintCircle);
                    canvas.drawPoint(startLeft + widthJianGe * position, height - bianJuPx - heightJianGe * numShu * line01[position] - quXianPx * 2.5f*abs, paintpoint);
                } else {
                    canvas.drawPoint(startLeft + widthJianGe * position, height - bianJuPx - heightJianGe * numShu * line01[position], paintCircle);
                    canvas.drawPoint(startLeft + widthJianGe * position, height - bianJuPx - heightJianGe * numShu * line01[position], paintpoint);
                }
            }
        }

    }

    public int getPosition(float x) {
        x = x + startLeft;
        for (int i = 0; i < text.length; i++) {
            if (x >= startLeft + widthJianGe * (i) - widthJianGe / 2f && x <= startLeft + widthJianGe * (i) + widthJianGe / 2f) {
                return i;
            }
        }
        if (x < startLeft + widthJianGe * (0) - widthJianGe / 2) {
            return 0;
        }
        if (x > startLeft + widthJianGe * (text.length - 1) + widthJianGe / 2) {
            return text.length - 1;
        }
        return 0;
    }

    private int position = 11;
    private float xDown;

    public interface OnGetPositionListener {
        void getPosiotion(int position);
    }

    OnGetPositionListener onGetPositionListener;

    public void setOnGetPositionListener(OnGetPositionListener onGetPositionListener) {
        this.onGetPositionListener = onGetPositionListener;
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                position = -1;
//                xDown = event.getX();
//                int positionX1 = getPosition(xDown);
//                if (positionX1 != position) {
//                    position = positionX1;
//                    if (onGetPositionListener != null) {
//                        onGetPositionListener.getPosiotion(position);
//                    }
//                    invalidate();
//                }
//                return true;
//            case MotionEvent.ACTION_MOVE:
//                float xDown = event.getX();
//                int positionX2 = getPosition(xDown);
//                if (positionX2 != position) {
//                    position = positionX2;
//                    if (onGetPositionListener != null) {
//                        onGetPositionListener.getPosiotion(position);
//                    }
//                    invalidate();
//                }
//                return true;
////            case MotionEvent.ACTION_UP:
////                isPress = false;
////                if (position != -1) {
////                    if (onGetPositionListener != null) {
////                        onGetPositionListener.getPosiotion(-1);
////                    }
////                }
////                invalidate();
////                return true;
//            default:
//                break;
//        }
//        //这句话不要修改
//        return super.onTouchEvent(event);
//    }


    public float getWidthJianGe() {
        return widthJianGe;
    }

    public int getLength() {
        return text.length;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
        invalidate();
    }

    public void setValue(float[] line01, String[] text) {
        this.line01 = line01;
        this.text = text;
        type = 0;
        invalidate();
    }

    public void setValue(float[] line01, String[] text, String[] text01) {
        this.line01 = line01;
        this.text = text;
        this.text01 = text01;
        type = 1;
        invalidate();
    }


    public void setBaiFenBiDuAnim(float[] baiFenBiDu, String[] text) {
        float[] floats = new float[text.length];
        if (floats.length > line01.length) {
            for (int i = 0; i < line01.length; i++) {
                floats[i] = line01[i];
            }

        } else {
            for (int i = 0; i < floats.length; i++) {
                floats[i] = line01[i];
            }
        }
        this.line01 = floats;
        this.text = text;
        this.position = text.length - 1;
        type = 0;
        ObjectAnimator animator = ObjectAnimator.ofObject(this, "Line", new HsvEvaluator(), line01, baiFenBiDu);
        animator.setDuration(2000);
        animator.setInterpolator(new FastOutSlowInInterpolator());
        animator.start();
    }

    public void setBaiFenBiDuAnim(float[] baiFenBiDu, String[] text, String[] text01) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.width = (int) (widthJianGe * (text.length - 1) + screenWidth);
        setLayoutParams(layoutParams);
        float[] floats = new float[text.length];
        if (floats.length > line01.length) {
            for (int i = 0; i < line01.length; i++) {
                floats[i] = line01[i];
            }
        } else {
            for (int i = 0; i < floats.length; i++) {
                floats[i] = line01[i];
            }
        }
        this.line01 = floats;
        this.text = text;
        this.text01 = text01;
        this.position = text.length - 1;
        type = 1;
        ObjectAnimator animator = ObjectAnimator.ofObject(this, "Line", new HsvEvaluator(), line01, baiFenBiDu);
        animator.setDuration(2000);
        animator.setInterpolator(new FastOutSlowInInterpolator());
        animator.start();
    }

    public void setLine(float[] line01) {
        this.line01 = line01;
        requestLayout();
        invalidate();
    }

    // 自定义 HslEvaluator
    private class HsvEvaluator implements TypeEvaluator<float[]> {
        float[] outValue = new float[text.length];

        @Override
        public float[] evaluate(float fraction, float[] startValue, float[] endValue) {
            for (int i = 0; i < startValue.length; i++) {
                outValue[i] = startValue[i] + (endValue[i] - startValue[i]) * fraction;
            }
            return outValue;
        }
    }
}

package com.zjb.home.boxingtu.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.zjb.home.boxingtu.util.DpUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by Administrator on 2017/9/13.
 */
public class PrcentCircle extends View {
    /**
     * 初始百分比
     */
    private int baiFenBi01 = 0;
    private int baiFenBi02 = 0;
    private int baiFenBi03 = 0;
    private int baiFenBi04 = 0;
    private int baiFenBi05 = 0;
    /**
     * 进度初始位置
     */
    private int kaiShiDuShu = 70;
    /**
     * 初始圆圈宽度
     */
    private int circleWidth = 20;
    private float circleStroke;
    private Paint paintCircle;
    private float circleStroke01;
    private float circleStroke02;
    private float circleStroke03;
    private float circleStroke04;
    private float circleStroke05;
    private int index;
    private int color01 = Color.parseColor("#4F86FF");
    private int color02 = Color.parseColor("#35B545");
    private int color03 = Color.parseColor("#FF7800");
    private int color04 = 0xff685AFD;
    private int color05 = Color.parseColor("#d0d0d0");
    private int color06 = Color.parseColor("#00d0d0d0");
    private float none = 0f;

    public PrcentCircle(Context context) {
        super(context);
    }

    public PrcentCircle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PrcentCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //初始化圆圈画笔
        circleStroke = DpUtils.convertDpToPixel(circleWidth, getContext());
        circleStroke01 = DpUtils.convertDpToPixel(circleWidth, getContext());
        paintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF oval1 = new RectF(circleStroke, circleStroke, getWidth() - circleStroke, getHeight() - circleStroke);
        paintCircle.setStyle(Paint.Style.STROKE);

        paintCircle.setStrokeWidth(circleStroke01);
        paintCircle.setColor(color01);
        canvas.drawArc(oval1, kaiShiDuShu - baiFenBi01, baiFenBi01, false, paintCircle);
        paintCircle.setStrokeWidth(circleStroke02);
        paintCircle.setColor(color02);
        canvas.drawArc(oval1, kaiShiDuShu - baiFenBi01 - baiFenBi02, baiFenBi02, false, paintCircle);
        paintCircle.setStrokeWidth(circleStroke03);
        paintCircle.setColor(color03);
        canvas.drawArc(oval1, kaiShiDuShu - baiFenBi01 - baiFenBi02 - baiFenBi03, baiFenBi03, false, paintCircle);
        paintCircle.setStrokeWidth(circleStroke04);
        paintCircle.setColor(color04);
        canvas.drawArc(oval1, kaiShiDuShu, 360 - (baiFenBi03 + baiFenBi02 + baiFenBi01), false, paintCircle);

        paintCircle.setStrokeWidth(circleStroke05);
        paintCircle.setColor(color05);
        canvas.drawArc(oval1, kaiShiDuShu, baiFenBi05, false, paintCircle);

//        paintCircle.setStrokeWidth(circleStroke04);
//        paintCircle.setColor(index == -1 ? color06 : color04);
//        canvas.drawArc(oval1, kaiShiDuShu, 360 - (baiFenBi03 + baiFenBi02 + baiFenBi01), false, paintCircle);


//        switch (index) {
//            case 0:
////                paintCircle.setStrokeWidth(circleStroke01);
////                paintCircle.setColor(color01);
////                canvas.drawArc(oval1, kaiShiDuShu, 360, false, paintCircle);
//                paintCircle.setStrokeWidth(circleStroke01);
//                paintCircle.setColor(color01);
//                canvas.drawArc(oval1, kaiShiDuShu - baiFenBi01, baiFenBi01, false, paintCircle);
//                break;
//            case 1:
////                paintCircle.setStrokeWidth(circleStroke01);
////                paintCircle.setColor(color02);
////                canvas.drawArc(oval1, kaiShiDuShu, 360, false, paintCircle);
//                paintCircle.setStrokeWidth(circleStroke01);
//                paintCircle.setColor(color02);
//                canvas.drawArc(oval1, kaiShiDuShu - baiFenBi01 - baiFenBi02, baiFenBi02, false, paintCircle);
//                break;
//            case 2:
////                paintCircle.setStrokeWidth(circleStroke01);
////                paintCircle.setColor(color03);
////                canvas.drawArc(oval1, kaiShiDuShu, 360, false, paintCircle);
//                paintCircle.setStrokeWidth(circleStroke01);
//                paintCircle.setColor(color03);
//                canvas.drawArc(oval1, kaiShiDuShu - baiFenBi01 - baiFenBi02 - baiFenBi03, baiFenBi03, false, paintCircle);
//                break;
//            case 3:
////                paintCircle.setStrokeWidth(circleStroke01);
////                paintCircle.setColor(color04);
////                canvas.drawArc(oval1, kaiShiDuShu, 360, false, paintCircle);
//                paintCircle.setStrokeWidth(circleStroke01);
//                paintCircle.setColor(color04);
//                canvas.drawArc(oval1, kaiShiDuShu, 360 - (baiFenBi03 + baiFenBi02 + baiFenBi01), false, paintCircle);
//                break;
//            default:
////                paintCircle.setStrokeWidth(circleStroke01);
////                paintCircle.setColor(color01);
////                canvas.drawArc(oval1, kaiShiDuShu, 360, false, paintCircle);
//                paintCircle.setStrokeWidth(circleStroke01);
//                paintCircle.setColor(color01);
//                canvas.drawArc(oval1, kaiShiDuShu - baiFenBi01, baiFenBi01, false, paintCircle);
//                break;
//        }

        //画圆圈
        paintCircle.setStrokeWidth(circleStroke);
        paintCircle.setColor(color01);
        canvas.drawArc(oval1, kaiShiDuShu, 360, false, paintCircle);
        if (index == 1) {
            paintCircle.setStrokeWidth(circleStroke + 1);
        } else {
            paintCircle.setStrokeWidth(circleStroke);
        }
        paintCircle.setColor(color02);
        canvas.drawArc(oval1, kaiShiDuShu, 360 - baiFenBi01, false, paintCircle);
        if (index == 2) {
            paintCircle.setStrokeWidth(circleStroke + 1);
        } else {
            paintCircle.setStrokeWidth(circleStroke);
        }
        paintCircle.setColor(color03);
        canvas.drawArc(oval1, kaiShiDuShu, 360 - baiFenBi01 - baiFenBi02, false, paintCircle);
        if (index == 3) {
            paintCircle.setStrokeWidth(circleStroke + 1);
        } else {
            paintCircle.setStrokeWidth(circleStroke);
        }
//        paintCircle.setColor(index == -1 ? color05 : color04);
        paintCircle.setColor(color04);
        canvas.drawArc(oval1, kaiShiDuShu, 360 - baiFenBi01 - baiFenBi02 - baiFenBi03, false, paintCircle);
        if (index == -1) {
            paintCircle.setStrokeWidth(circleStroke + 2);
        } else {
            paintCircle.setStrokeWidth(circleStroke);
        }
        paintCircle.setColor(color05);
        canvas.drawArc(oval1, kaiShiDuShu, baiFenBi05, false, paintCircle);


        RectF oval2 = new RectF(circleStroke + circleStroke / 2f, circleStroke + circleStroke / 2f, getWidth() - circleStroke - circleStroke / 2f, getHeight() - circleStroke - circleStroke / 2f);
        paintCircle.setColor(Color.parseColor("#ffffff"));
        paintCircle.setStyle(Paint.Style.FILL);
        canvas.drawArc(oval2, kaiShiDuShu, 360, true, paintCircle);

    }

    public void setBaiFenBi01(int baiFenBi01) {
        this.baiFenBi01 = baiFenBi01;
        invalidate();
    }

    public void setBaiFenBi02(int baiFenBi02) {
        this.baiFenBi02 = baiFenBi02;
        invalidate();
    }

    public void setBaiFenBi03(int baiFenBi03) {
        this.baiFenBi03 = baiFenBi03;
        invalidate();
    }

    public void setBaiFenBi(BaiFenBi baiFenBi) {
        this.baiFenBi01 = baiFenBi.getBaiFenBi01();
        this.baiFenBi02 = baiFenBi.getBaiFenBi02();
        this.baiFenBi03 = baiFenBi.getBaiFenBi03();
        this.baiFenBi05 = baiFenBi.getBaiFenBi05();
//        this.circleStroke01 = baiFenBi.getCircleStroke01();
//        this.circleStroke02 = baiFenBi.getCircleStroke02();
//        this.circleStroke03 = baiFenBi.getCircleStroke03();
//        this.circleStroke04 = baiFenBi.getCircleStroke04();
//        this.circleStroke05 = baiFenBi.getCircleStroke05();
//        this.color04 = baiFenBi.getColor4();
        invalidate();
    }


    public void setCircleStroke01(float circleStroke01) {
        this.circleStroke01 = circleStroke01;
        invalidate();
    }

    public void setCircleStroke(CircleWidth circleWidth) {
        this.circleStroke01 = circleWidth.getCircleStroke01();
        this.circleStroke02 = circleWidth.getCircleStroke02();
        this.circleStroke03 = circleWidth.getCircleStroke03();
        this.circleStroke04 = circleWidth.getCircleStroke04();
        this.circleStroke05 = circleWidth.getCircleStroke05();
//        this.color04 = circleWidth.getColor4();
        invalidate();
    }

    public void setBaiFenBiAnim(int baiFenBi01, int baiFenBi02, int baiFenBi03, int baiFenBi04) {
        List<Integer> baiFenBiList = new ArrayList<>();
        baiFenBiList.add(baiFenBi01);
        baiFenBiList.add(baiFenBi02);
        baiFenBiList.add(baiFenBi03);
        baiFenBiList.add(baiFenBi04);
        this.baiFenBi04 = baiFenBi04;
        Integer max = Collections.max(baiFenBiList);
        int indexX = baiFenBiList.indexOf(max);
        int baiFenBi05 = 0;
        if (max == 0) {
            indexX = -1;
            baiFenBi05 = 360;
        } else {
            baiFenBi05 = 0;
        }
        index = indexX;

        ObjectAnimator animator = ObjectAnimator.ofObject(this,
                "baiFenBi",
                new HsvEvaluator(),
                new BaiFenBi(this.baiFenBi01, this.baiFenBi02, this.baiFenBi03, this.baiFenBi05, this.circleStroke01, this.circleStroke02, this.circleStroke03, this.circleStroke04, this.circleStroke05, this.color04),
                new BaiFenBi(baiFenBi01, baiFenBi02, baiFenBi03, baiFenBi05, index == 0 ? circleStroke * 2 : circleStroke, index == 1 ? circleStroke * 2 : circleStroke, index == 2 ? circleStroke * 2 : circleStroke, index == 3 ? circleStroke * 2 : circleStroke, index == -1 ? circleStroke * 2 : circleStroke, indexX == -1 ? 0xffd0d0d0 : 0xff685AFD)
        );
        animator.setDuration(800);
        animator.setInterpolator(new LinearInterpolator());


        ObjectAnimator animator4 = ObjectAnimator.ofObject(
                this,
                "circleStroke",
                new CircleEvaluator(),
                new CircleWidth(this.circleStroke01, this.circleStroke02, this.circleStroke03, this.circleStroke04, this.circleStroke05, this.color04),
                new CircleWidth(index == 0 ? circleStroke * 2 : circleStroke, index == 1 ? circleStroke * 2 : circleStroke, index == 2 ? circleStroke * 2 : circleStroke, index == 3 ? circleStroke * 2 : circleStroke, index == -1 ? circleStroke * 2 : circleStroke, indexX == -1 ? 0xffd0d0d0 : 0xff685AFD)
        );
        animator4.setDuration(800);
        animator4.setInterpolator(new LinearInterpolator());

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially( animator,animator4);
        animatorSet.start();

    }

    // 自定义 HslEvaluator
    private class HsvEvaluator implements TypeEvaluator<BaiFenBi> {
        BaiFenBi baiFenBi = new BaiFenBi();
        float[] startHsv = new float[3];
        float[] endHsv = new float[3];
        float[] outHsv = new float[3];

        @Override
        public BaiFenBi evaluate(float fraction, BaiFenBi startValue, BaiFenBi endValue) {
            baiFenBi.setBaiFenBi01((int) (startValue.getBaiFenBi01() + (endValue.getBaiFenBi01() - startValue.getBaiFenBi01()) * fraction));
            baiFenBi.setBaiFenBi02((int) (startValue.getBaiFenBi02() + (endValue.getBaiFenBi02() - startValue.getBaiFenBi02()) * fraction));
            baiFenBi.setBaiFenBi03((int) (startValue.getBaiFenBi03() + (endValue.getBaiFenBi03() - startValue.getBaiFenBi03()) * fraction));
            baiFenBi.setBaiFenBi05((int) (startValue.getBaiFenBi05() + (endValue.getBaiFenBi05() - startValue.getBaiFenBi05()) * fraction));
            baiFenBi.setCircleStroke01((startValue.getCircleStroke01() + (endValue.getCircleStroke01() - startValue.getCircleStroke01()) * fraction));
            baiFenBi.setCircleStroke02((startValue.getCircleStroke02() + (endValue.getCircleStroke02() - startValue.getCircleStroke02()) * fraction));
            baiFenBi.setCircleStroke03((startValue.getCircleStroke03() + (endValue.getCircleStroke03() - startValue.getCircleStroke03()) * fraction));
            baiFenBi.setCircleStroke04((startValue.getCircleStroke04() + (endValue.getCircleStroke04() - startValue.getCircleStroke04()) * fraction));
            baiFenBi.setCircleStroke05((startValue.getCircleStroke05() + (endValue.getCircleStroke05() - startValue.getCircleStroke05()) * fraction));

            // 把 ARGB 转换成 HSV
            Color.colorToHSV(startValue.getColor4(), startHsv);
            Color.colorToHSV(endValue.getColor4(), endHsv);

            // 计算当前动画完成度（fraction）所对应的颜色值
            if (endHsv[0] - startHsv[0] > 180) {
                endHsv[0] -= 360;
            } else if (endHsv[0] - startHsv[0] < -180) {
                endHsv[0] += 360;
            }
            outHsv[0] = startHsv[0] + (endHsv[0] - startHsv[0]) * fraction;
            if (outHsv[0] > 360) {
                outHsv[0] -= 360;
            } else if (outHsv[0] < 0) {
                outHsv[0] += 360;
            }
            outHsv[1] = startHsv[1] + (endHsv[1] - startHsv[1]) * fraction;
            outHsv[2] = startHsv[2] + (endHsv[2] - startHsv[2]) * fraction;

            // 计算当前动画完成度（fraction）所对应的透明度
            int alpha = startValue.getColor4() >> 24 + (int) ((endValue.getColor4() >> 24 - startValue.getColor4() >> 24) * fraction);

            baiFenBi.setColor4(Color.HSVToColor(alpha, outHsv));
            return baiFenBi;
        }
    }

    // 自定义 HslEvaluator
    private class CircleEvaluator implements TypeEvaluator<CircleWidth> {
        CircleWidth baiFenBi = new CircleWidth();
        float[] startHsv = new float[3];
        float[] endHsv = new float[3];
        float[] outHsv = new float[3];

        @Override
        public CircleWidth evaluate(float fraction, CircleWidth startValue, CircleWidth endValue) {
            baiFenBi.setCircleStroke01((startValue.getCircleStroke01() + (endValue.getCircleStroke01() - startValue.getCircleStroke01()) * fraction));
            baiFenBi.setCircleStroke02((startValue.getCircleStroke02() + (endValue.getCircleStroke02() - startValue.getCircleStroke02()) * fraction));
            baiFenBi.setCircleStroke03((startValue.getCircleStroke03() + (endValue.getCircleStroke03() - startValue.getCircleStroke03()) * fraction));
            baiFenBi.setCircleStroke04((startValue.getCircleStroke04() + (endValue.getCircleStroke04() - startValue.getCircleStroke04()) * fraction));
            baiFenBi.setCircleStroke05((startValue.getCircleStroke05() + (endValue.getCircleStroke05() - startValue.getCircleStroke05()) * fraction));

            // 把 ARGB 转换成 HSV
            Color.colorToHSV(startValue.getColor4(), startHsv);
            Color.colorToHSV(endValue.getColor4(), endHsv);

            // 计算当前动画完成度（fraction）所对应的颜色值
            if (endHsv[0] - startHsv[0] > 180) {
                endHsv[0] -= 360;
            } else if (endHsv[0] - startHsv[0] < -180) {
                endHsv[0] += 360;
            }
            outHsv[0] = startHsv[0] + (endHsv[0] - startHsv[0]) * fraction;
            if (outHsv[0] > 360) {
                outHsv[0] -= 360;
            } else if (outHsv[0] < 0) {
                outHsv[0] += 360;
            }
            outHsv[1] = startHsv[1] + (endHsv[1] - startHsv[1]) * fraction;
            outHsv[2] = startHsv[2] + (endHsv[2] - startHsv[2]) * fraction;

            // 计算当前动画完成度（fraction）所对应的透明度
            int alpha = startValue.getColor4() >> 24 + (int) ((endValue.getColor4() >> 24 - startValue.getColor4() >> 24) * fraction);

            baiFenBi.setColor4(Color.HSVToColor(alpha, outHsv));
            return baiFenBi;
        }
    }


    class BaiFenBi {
        private int baiFenBi01;
        private int baiFenBi02;
        private int baiFenBi03;
        private int baiFenBi05;
        private float circleStroke01;
        private float circleStroke02;
        private float circleStroke03;
        private float circleStroke04;
        private float circleStroke05;
        private int color4;

        public BaiFenBi() {
        }

        public BaiFenBi(int baiFenBi01, int baiFenBi02, int baiFenBi03, int baiFenBi05, float circleStroke01, float circleStroke02, float circleStroke03, float circleStroke04, float circleStroke05, int color4) {
            this.baiFenBi01 = baiFenBi01;
            this.baiFenBi02 = baiFenBi02;
            this.baiFenBi03 = baiFenBi03;
            this.baiFenBi05 = baiFenBi05;
            this.circleStroke01 = circleStroke01;
            this.circleStroke02 = circleStroke02;
            this.circleStroke03 = circleStroke03;
            this.circleStroke04 = circleStroke04;
            this.circleStroke05 = circleStroke05;
            this.color4 = color4;
        }

        public int getColor4() {
            return color4;
        }

        public void setColor4(int color4) {
            this.color4 = color4;
        }

        public int getBaiFenBi05() {
            return baiFenBi05;
        }

        public void setBaiFenBi05(int baiFenBi05) {
            this.baiFenBi05 = baiFenBi05;
        }

        public float getCircleStroke05() {
            return circleStroke05;
        }

        public void setCircleStroke05(float circleStroke05) {
            this.circleStroke05 = circleStroke05;
        }

        public int getBaiFenBi01() {
            return baiFenBi01;
        }

        public void setBaiFenBi01(int baiFenBi01) {
            this.baiFenBi01 = baiFenBi01;
        }

        public int getBaiFenBi02() {
            return baiFenBi02;
        }

        public void setBaiFenBi02(int baiFenBi02) {
            this.baiFenBi02 = baiFenBi02;
        }

        public int getBaiFenBi03() {
            return baiFenBi03;
        }

        public void setBaiFenBi03(int baiFenBi03) {
            this.baiFenBi03 = baiFenBi03;
        }

        public float getCircleStroke01() {
            return circleStroke01;
        }

        public void setCircleStroke01(float circleStroke01) {
            this.circleStroke01 = circleStroke01;
        }

        public float getCircleStroke02() {
            return circleStroke02;
        }

        public void setCircleStroke02(float circleStroke02) {
            this.circleStroke02 = circleStroke02;
        }

        public float getCircleStroke03() {
            return circleStroke03;
        }

        public void setCircleStroke03(float circleStroke03) {
            this.circleStroke03 = circleStroke03;
        }

        public float getCircleStroke04() {
            return circleStroke04;
        }

        public void setCircleStroke04(float circleStroke04) {
            this.circleStroke04 = circleStroke04;
        }
    }

    class CircleWidth {
        private float circleStroke01;
        private float circleStroke02;
        private float circleStroke03;
        private float circleStroke04;
        private float circleStroke05;
        private int color4;

        public CircleWidth() {
        }

        public CircleWidth(float circleStroke01, float circleStroke02, float circleStroke03, float circleStroke04, float circleStroke05, int color4) {
            this.circleStroke01 = circleStroke01;
            this.circleStroke02 = circleStroke02;
            this.circleStroke03 = circleStroke03;
            this.circleStroke04 = circleStroke04;
            this.circleStroke05 = circleStroke05;
            this.color4 = color4;
        }

        public int getColor4() {
            return color4;
        }

        public void setColor4(int color4) {
            this.color4 = color4;
        }

        public float getCircleStroke05() {
            return circleStroke05;
        }

        public void setCircleStroke05(float circleStroke05) {
            this.circleStroke05 = circleStroke05;
        }

        public float getCircleStroke01() {
            return circleStroke01;
        }

        public void setCircleStroke01(float circleStroke01) {
            this.circleStroke01 = circleStroke01;
        }

        public float getCircleStroke02() {
            return circleStroke02;
        }

        public void setCircleStroke02(float circleStroke02) {
            this.circleStroke02 = circleStroke02;
        }

        public float getCircleStroke03() {
            return circleStroke03;
        }

        public void setCircleStroke03(float circleStroke03) {
            this.circleStroke03 = circleStroke03;
        }

        public float getCircleStroke04() {
            return circleStroke04;
        }

        public void setCircleStroke04(float circleStroke04) {
            this.circleStroke04 = circleStroke04;
        }
    }

}

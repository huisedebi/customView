package com.zjb.home.boxingtu.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.zjb.home.boxingtu.util.DpUtils;

/**
 * Created by Administrator on 2017/9/27.
 */
public class BoXingTu04 extends View {

    private int numHeng = 30;
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
            0.63f,
            0.21f,
            0.38f,
            0.48f,
            0.43f,
            0.56f,
            0.52f,
            0.63f,
            0.21f,
            0.38f,
            0.18f,
            0.03f,
            0.56f,
            0.52f,
            0.63f,
            0.21f,
            0.38f,
            0.98f,
            0.43f,
            0.56f,
            0.52f,
            0.63f,
            0.33f,
            0.83f,
    };
    Path path01 = new Path();
    Path path02 = new Path();
    private Paint paintText;
    private String[] text = new String[]{
            "",
            "",
            "08-30",
            "",
            "",
            "",
            "",
            "",
            "08-29",
            "",
            "",
            "",
            "",
            "",
            "08-28",
            "",
            "",
            "",
            "",
            "",
            "09-03",
            "",
            "",
            "",
            "",
            "",
            "09-02",
            "",
            "",
            "",
    };
    private String[] textLeft = new String[]{
            "0",
            "0.05",
            "0.1",
            "0.15",
            "0.2",
            "0.25",
            "0.3",
            "0.35",
    };
    private Rect rect;
    private Rect[] rectLeft = new Rect[8];
    private float quXianPx;
    private float martop;
    private Paint paintYinYing;
    private Paint paintZheGai;

    public BoXingTu04(Context context) {
        super(context);
    }

    public BoXingTu04(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BoXingTu04(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        paintHengXian = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintHengXian.setColor(Color.parseColor("#CBCBCB"));
        paintHengXian.setStrokeWidth(DpUtils.convertDpToPixel(1, getContext()));

        quXianPx = DpUtils.convertDpToPixel(quXian, getContext());

        PathEffect pathEffect = new CornerPathEffect(DpUtils.convertDpToPixel(radius, context));
        paintQuXian01 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintQuXian01.setPathEffect(pathEffect);
        paintQuXian01.setStyle(Paint.Style.STROKE);
        paintQuXian01.setStrokeCap(Paint.Cap.ROUND);
        paintQuXian01.setStrokeWidth(quXianPx);

        paintYinYing = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintYinYing.setPathEffect(pathEffect);
        paintYinYing.setStyle(Paint.Style.FILL_AND_STROKE);
        paintYinYing.setStrokeWidth(quXianPx);
        paintYinYing.setColor(Color.parseColor("#22508AE4"));

        paintZheGai = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintZheGai.setStyle(Paint.Style.FILL_AND_STROKE);
        paintZheGai.setStrokeWidth(quXianPx);
        paintZheGai.setColor(Color.parseColor("#ffffff"));

        paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setStyle(Paint.Style.FILL);
        paintText.setTextSize(DpUtils.convertDpToPixel(textSize, context));
        paintText.setColor(Color.parseColor("#F19444"));

        bianJuPx = DpUtils.convertDpToPixel(bianJu, getContext());
        rect = new Rect();
        paintText.getTextBounds(text[0], 0, text[0].length(), rect);

        for (int i = 0; i < rectLeft.length; i++) {
            rectLeft[i] = new Rect();
            paintText.getTextBounds(textLeft[i], 0, textLeft[i].length(), rectLeft[i]);
        }
        bianJuLeftPx = rectLeft[7].width() + DpUtils.convertDpToPixel(15, getContext());
        martop = rectLeft[7].height();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        widthJianGe = (width - bianJuLeftPx) / numHeng;
        heightJianGe = (height - bianJuPx - martop) / numShu;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float dp1 = DpUtils.convertDpToPixel(1, getContext()) * 2;
        float dp5 = DpUtils.convertDpToPixel(5, getContext());
        //画灰色横线
        for (int i = 0; i < numShu + 1; i++) {
            if (i == 0) {

            } else {
                paintHengXian.setColor(Color.parseColor("#CBCBCB"));
                canvas.drawLine(0 + bianJuLeftPx-dp5, height - bianJuPx - heightJianGe * i, width, height - bianJuPx - heightJianGe * i, paintHengXian);
            }
        }
        //画竖直线
        canvas.drawLine(bianJuLeftPx,height-bianJuPx,bianJuLeftPx,height - bianJuPx - heightJianGe * numShu,paintHengXian);
        //曲线路径
        paintQuXian01.setColor(Color.parseColor("#508AE4"));
        path01.reset();
        path01.moveTo(widthJianGe / 2 + widthJianGe * 0 + bianJuLeftPx, height - bianJuPx - heightJianGe * numShu * line01[0]);
        for (int i = 0; i < numHeng - 1; i++) {
            path01.lineTo(widthJianGe / 2 + widthJianGe * (i + 1) + bianJuLeftPx, height - bianJuPx - heightJianGe * numShu * line01[(i + 1)]);
        }
        //画曲线和阴影
        canvas.save();
        path02.addPath(path01);
        path02.lineTo(widthJianGe / 2 + widthJianGe * (numHeng-1) + bianJuLeftPx, height);
        path02.lineTo(widthJianGe / 2 + widthJianGe * (0) + bianJuLeftPx, height);
        path02.close();
        canvas.drawPath(path02, paintYinYing);
        canvas.drawPath(path01, paintQuXian01);
        canvas.restore();
        //画最底部横线
        paintHengXian.setColor(Color.parseColor("#F19444"));
        canvas.drawLine(0 + bianJuLeftPx-dp5, height - bianJuPx - heightJianGe * 0, width, height - bianJuPx - heightJianGe * 0, paintHengXian);
        //画遮盖底部
        canvas.drawRect(widthJianGe / 2 + widthJianGe * (0) + bianJuLeftPx, height-bianJuPx + dp1, widthJianGe / 2 + widthJianGe * (numHeng-1) + bianJuLeftPx, height, paintZheGai);
        //画底部文字和刻度
        paintHengXian.setColor(Color.parseColor("#F19444"));
        for (int i = 0; i < text.length; i++) {
            if (!TextUtils.isEmpty(text[i])) {
                canvas.drawText(text[i], (widthJianGe - rect.width()) / 2 + widthJianGe * i + bianJuLeftPx, height - (bianJuPx - rect.height()) / 2, paintText);
                canvas.drawLine(widthJianGe / 2 + widthJianGe * (i + 1) + bianJuLeftPx, height - bianJuPx, widthJianGe / 2 + widthJianGe * (i + 1) + bianJuLeftPx, height - bianJuPx + DpUtils.convertDpToPixel(5, getContext()), paintHengXian);
            }
        }
        //画竖直文字
        paintText.setColor(Color.parseColor("#CBCBCB"));
        for (int i = 0; i < textLeft.length; i++) {
            if (!TextUtils.isEmpty(textLeft[i])) {
                canvas.drawText(textLeft[i], bianJuLeftPx - dp5*2 - rectLeft[i].width(), height - heightJianGe * i - bianJuPx + rectLeft[i].height() / 2f, paintText);
            }
        }
        //画圆
        paintQuXian01.setShadowLayer(0, 0, 0, Color.WHITE);
        paintQuXian01.setStrokeWidth(quXianPx * 3);
        paintQuXian01.setColor(Color.parseColor("#BF3C38"));
        for (int i = 1; i < numHeng + 1; i++) {
            if (i > 1 && i < numHeng) {
                if (line01[(int) i - 1] > line01[(int) i - 2]) {
                    if (line01[(int) i - 1] > line01[(int) i]) {
                        canvas.drawPoint(widthJianGe * (i - 0.5f) + bianJuLeftPx, height - bianJuPx - heightJianGe * numShu * line01[(int) i - 1] + quXianPx, paintQuXian01);
                    } else {
                        canvas.drawPoint(widthJianGe * (i - 0.5f) + bianJuLeftPx, height - bianJuPx - heightJianGe * numShu * line01[(int) i - 1], paintQuXian01);
                    }
                } else {
                    if (line01[(int) i - 1] < line01[(int) i]) {
                        canvas.drawPoint(widthJianGe * (i - 0.5f) + bianJuLeftPx, height - bianJuPx - heightJianGe * numShu * line01[(int) i - 1] - quXianPx, paintQuXian01);
                    } else {
                        canvas.drawPoint(widthJianGe * (i - 0.5f) + bianJuLeftPx, height - bianJuPx - heightJianGe * numShu * line01[(int) i - 1] - quXianPx / 2, paintQuXian01);
                    }
                }
            } else {
                canvas.drawPoint(widthJianGe * (i - 0.5f) + bianJuLeftPx, height - bianJuPx - heightJianGe * numShu * line01[(int) i - 1] - quXianPx / 2, paintQuXian01);
            }
        }
        //画圆心
        paintQuXian01.setStrokeWidth(quXianPx);
        paintQuXian01.setColor(Color.parseColor("#ffffff"));
        for (int i = 1; i < numHeng + 1; i++) {
            if (i > 1 && i < numHeng) {
                if (line01[(int) i - 1] > line01[(int) i - 2]) {
                    if (line01[(int) i - 1] > line01[(int) i]) {
                        canvas.drawPoint(widthJianGe * (i - 0.5f) + bianJuLeftPx, height - bianJuPx - heightJianGe * numShu * line01[(int) i - 1] + quXianPx, paintQuXian01);
                    } else {
                        canvas.drawPoint(widthJianGe * (i - 0.5f) + bianJuLeftPx, height - bianJuPx - heightJianGe * numShu * line01[(int) i - 1], paintQuXian01);
                    }
                } else {
                    if (line01[(int) i - 1] < line01[(int) i]) {
                        canvas.drawPoint(widthJianGe * (i - 0.5f) + bianJuLeftPx, height - bianJuPx - heightJianGe * numShu * line01[(int) i - 1] - quXianPx, paintQuXian01);
                    } else {
                        canvas.drawPoint(widthJianGe * (i - 0.5f) + bianJuLeftPx, height - bianJuPx - heightJianGe * numShu * line01[(int) i - 1] - quXianPx / 2, paintQuXian01);
                    }
                }
            } else {
                canvas.drawPoint(widthJianGe * (i - 0.5f) + bianJuLeftPx, height - bianJuPx - heightJianGe * numShu * line01[(int) i - 1] - quXianPx / 2, paintQuXian01);
            }
        }
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                //获取屏幕上点击的坐标
//                float x = event.getX();
//                Log.e("BoXingTu", "BoXingTu--onTouchEvent--" + x);
//                //如果坐标在我们的文字区域内，则将点击的文字改颜色
//                if (x > 0 && x < widthJianGe) {
//                    //点击后，获取坐标代表的单词的含义
//                    xuanZhong = 1;
//                    invalidate();//更新视图
//                    return true;
//                } else if (x > widthJianGe && x < widthJianGe * 2) {
//                    //点击后，获取坐标代表的单词的含义
//                    xuanZhong = 2;
//                    invalidate();//更新视图
//                } else if (x > widthJianGe * 2 && x < widthJianGe * 3) {
//                    //点击后，获取坐标代表的单词的含义
//                    xuanZhong = 3;
//                    invalidate();//更新视图
//                } else if (x > widthJianGe * 3 && x < widthJianGe * 4) {
//                    //点击后，获取坐标代表的单词的含义
//                    xuanZhong = 4;
//                    invalidate();//更新视图
//                } else if (x > widthJianGe * 4 && x < widthJianGe * 5) {
//                    //点击后，获取坐标代表的单词的含义
//                    xuanZhong = 5;
//                    invalidate();//更新视图
//                } else if (x > widthJianGe * 5 && x < widthJianGe * 6) {
//                    //点击后，获取坐标代表的单词的含义
//                    xuanZhong = 6;
//                    invalidate();//更新视图
//                } else if (x > widthJianGe * 6 && x < widthJianGe * 7) {
//                    //点击后，获取坐标代表的单词的含义
//                    xuanZhong = 7;
//                    invalidate();//更新视图
//                }
//                break;
//            default:
//                break;
//        }
//        //这句话不要修改
//        return super.onTouchEvent(event);
//    }

    public void setValue01(int i, float value) {
        line01[i] = value;
        invalidate();
    }

}

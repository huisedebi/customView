package com.zjb.home.boxingtu.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.zjb.home.boxingtu.util.DpUtils;

/**
 * Created by Administrator on 2017/9/27.
 */
public class ZhuZhuangTu01 extends View {

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
    };
    private Paint paintText;
    private String[] text = new String[]{
            "2018-11",
            "2018-12",
            "2019-01",
            "2019-02",
            "2019-03",
            "2019-04",
    };
    private String[] textLeft = new String[]{
            "0",
            "3",
            "6",
            "9",
            "12",
            "15",
            "18",
            "21",
    };
    private Rect rect;
    private Rect[] rectLeft = new Rect[8];
    private float quXianPx;
    private float martop;

    public ZhuZhuangTu01(Context context) {
        super(context);
    }

    public ZhuZhuangTu01(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZhuZhuangTu01(Context context, AttributeSet attrs, int defStyleAttr) {
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
        float dp5 = DpUtils.convertDpToPixel(5, getContext());
        //画灰色横线
        for (int i = 0; i < numShu + 1; i++) {
            paintHengXian.setColor(Color.parseColor("#CBCBCB"));
            canvas.drawLine( bianJuLeftPx-dp5, height - bianJuPx - heightJianGe * i, bianJuLeftPx, height - bianJuPx - heightJianGe * i, paintHengXian);
        }
        //画竖直线
        canvas.drawLine(bianJuLeftPx,height-bianJuPx,bianJuLeftPx,height - bianJuPx - heightJianGe * numShu,paintHengXian);
        //画柱状
        paintQuXian01.setColor(Color.parseColor("#508AE4"));
        for (int i = 0; i < numHeng; i++) {
            canvas.drawRect(
                    widthJianGe * (i) + bianJuLeftPx + widthJianGe / 8f,
                    height - bianJuPx - heightJianGe * numShu * line01[i],
                    widthJianGe * (i + 1) + bianJuLeftPx - widthJianGe / 8f,
                    height - bianJuPx,
                    paintQuXian01
            );
        }
        //画最底部横线
        paintHengXian.setColor(Color.parseColor("#CBCBCB"));
        canvas.drawLine(0 + bianJuLeftPx, height - bianJuPx - heightJianGe * 0, width, height - bianJuPx - heightJianGe * 0, paintHengXian);
        //画底部文字和刻度
        paintHengXian.setColor(Color.parseColor("#CBCBCB"));
        for (int i = 0; i < text.length + 1; i++) {
            if (i < text.length) {
                canvas.drawText(text[i], (widthJianGe - rect.width()) / 2 + widthJianGe * i + bianJuLeftPx, height - (bianJuPx - rect.height()) / 2, paintText);
            }
            canvas.drawLine(+widthJianGe * (i) + bianJuLeftPx, height - bianJuPx, widthJianGe * (i) + bianJuLeftPx, height - bianJuPx + DpUtils.convertDpToPixel(5, getContext()), paintHengXian);
        }
        //画竖直文字
        paintText.setColor(Color.parseColor("#CBCBCB"));
        for (int i = 0; i < textLeft.length; i++) {
            if (!TextUtils.isEmpty(textLeft[i])) {
                canvas.drawText(textLeft[i], bianJuLeftPx - dp5*2f - rectLeft[i].width(), height - heightJianGe * i - bianJuPx + rectLeft[i].height() / 2f, paintText);
            }
        }
    }

    public void setValue01(int i, float value) {
        line01[i] = value;
        invalidate();
    }

}

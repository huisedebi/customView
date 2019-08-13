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
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.zjb.home.boxingtu.util.DpUtils;

/**
 * Created by Administrator on 2017/9/27.
 */
public class HuaWeiSport extends View {

    private int numHeng = 6;
    private int numShu = 5;
    private int width;
    private int height;
    private float widthJianGe;
    private float heightJianGe;
    private float quXian = 2;
    private float bianJu = 80;
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
    private Paint paintCircle;
    private Paint paintBtm;
    private float radiusPx;
    private int select = 2;
    private float ShadowXY = 2;
    private float pointRadius;
    private float xDown;
    private float distance = 0;//左右移动距离
    private Paint paintShuXian;

    public interface OnGetPositionListener{
        void getPosition(int position);
    }

    OnGetPositionListener onGetPositionListener;

    public void setOnGetPositionListener(OnGetPositionListener onGetPositionListener) {
        this.onGetPositionListener = onGetPositionListener;
    }

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
        paintHengXian.setStrokeWidth(1);

        quXianPx = DpUtils.convertDpToPixel(quXian, getContext());

        paintQuXian01 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintQuXian01.setColor(Color.parseColor("#007DFD"));
        paintQuXian01.setStyle(Paint.Style.FILL);
        paintQuXian01.setStrokeWidth(quXianPx);

        paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setStyle(Paint.Style.FILL);
        paintText.setTextSize(DpUtils.convertDpToPixel(textSize, context));
        paintText.setColor(Color.parseColor("#000000"));

        bianJuPx = DpUtils.convertDpToPixel(bianJu, getContext());
        rect = new Rect();
        paintText.getTextBounds(text[0], 0, text[0].length(), rect);

        dp5 = DpUtils.convertDpToPixel(5, getContext());
        radiusPx = DpUtils.convertDpToPixel(radius, getContext());

        ZhuZhuangWidthPx = DpUtils.convertDpToPixel(ZhuZhuangWidth, getContext());
        marginTopPx = DpUtils.convertDpToPixel(marginTop, getContext());
        pointRadius = DpUtils.convertDpToPixel(13, getContext());

        paintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintCircle.setStyle(Paint.Style.FILL);
        paintCircle.setStrokeWidth(DpUtils.convertDpToPixel(20, getContext()));
        paintCircle.setColor(Color.WHITE);
        paintCircle.setShadowLayer(
                DpUtils.convertDpToPixel(3, getContext()),
                DpUtils.convertDpToPixel(0, getContext()),
                DpUtils.convertDpToPixel(2, getContext()),
                Color.parseColor("#B9B9B9"));

        paintBtm = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBtm.setStyle(Paint.Style.FILL);
        paintBtm.setColor(Color.parseColor("#FFF1F1F1"));
        PathEffect pathEffect = new CornerPathEffect(radiusPx);
        paintBtm.setStrokeWidth(1);
        paintBtm.setPathEffect(pathEffect);

        paintShuXian = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintShuXian.setStyle(Paint.Style.FILL);
        paintShuXian.setColor(Color.parseColor("#007DFD"));

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
            paintHengXian.setColor(Color.parseColor("#f5f5f5"));
            canvas.drawLine(bianJuLeftPx, height - bianJuPx - heightJianGe * i, width, height - bianJuPx - heightJianGe * i, paintHengXian);
        }

        for (int i = 0; i < text.length; i++) {
            //画底部文字
            float textHeightMgr = DpUtils.convertDpToPixel(15, getContext());
            float textDiatance = (widthJianGe * (select) + widthJianGe / 2f + bianJuLeftPx + distance) - (widthJianGe * (i) + widthJianGe / 2f + bianJuLeftPx);
            float textPrecent = 0f;
            if (Math.abs(textDiatance) <= widthJianGe / 2f + dp5 * 3f) {
                textPrecent = (1f - (Math.abs(textDiatance) / (widthJianGe / 2f + dp5 * 3f)));
            }
            textHeightMgr = textHeightMgr * textPrecent;
            paintText.setAlpha((int) ((0.4f + textPrecent * 0.6f) * 255));
            canvas.drawText(text[i], (widthJianGe - rect.width()) / 2 + widthJianGe * i + bianJuLeftPx, height - DpUtils.convertDpToPixel(28, getContext()) - textHeightMgr, paintText);

            //画柱状
            canvas.save();
            Path path1 = new Path();
            RectF rectf = new RectF(widthJianGe * i + widthJianGe / 2f + bianJuLeftPx - ZhuZhuangWidthPx / 2f,
                    height - bianJuPx - heightJianGe * numShu * line01[i],
                    widthJianGe * i + widthJianGe / 2f + bianJuLeftPx + ZhuZhuangWidthPx / 2f,
                    height - bianJuPx);
            paintQuXian01.setAlpha((int) ((0.4f + textPrecent * 0.6f) * 255));
            path1.addRoundRect(rectf, new float[]{ZhuZhuangWidthPx / 2f, ZhuZhuangWidthPx / 2f, ZhuZhuangWidthPx / 2f, ZhuZhuangWidthPx / 2f, 0, 0, 0, 0}, Path.Direction.CCW);
            canvas.drawPath(path1, paintQuXian01);
            canvas.restore();

            if (textPrecent>0.6f){
                if (onGetPositionListener!=null){
                    onGetPositionListener.getPosition(getPosition(widthJianGe * (select) + widthJianGe / 2f + bianJuLeftPx + distance));
                }
            }
        }
        canvas.save();
        float lineHeight = height - bianJuPx + dp5 * 2f;
        Shader shader = new LinearGradient(widthJianGe * (select) - 0.5f + widthJianGe / 2f + distance, 0, widthJianGe * (select) + 0.5f + widthJianGe / 2f + distance, lineHeight / 2, Color.parseColor("#55007DFD"),
                Color.parseColor("#007DFD"), Shader.TileMode.MIRROR);
        paintShuXian.setShader(shader);
        RectF rectF = new RectF(widthJianGe * (select) - 0.5f + widthJianGe / 2f + distance, 0, widthJianGe * select + 0.5f + widthJianGe / 2f + distance, lineHeight);
        Path path2 = new Path();
        path2.addRoundRect(rectF,new float[]{lineHeight/2f,lineHeight/2f,lineHeight/2f,lineHeight/2f,lineHeight/2f,lineHeight/2f,lineHeight/2f,lineHeight/2f}, Path.Direction.CCW);
        canvas.drawPath(path2,paintShuXian);
        canvas.restore();

        //画底部弓形线
        canvas.save();
        Path path = new Path();
        path.moveTo(-radiusPx, height - DpUtils.convertDpToPixel(20, getContext()));
        path.lineTo(widthJianGe * (select) + bianJuLeftPx + distance, height - DpUtils.convertDpToPixel(20, getContext()));
        path.lineTo(widthJianGe * (select) + widthJianGe / 2f + bianJuLeftPx + distance, height - DpUtils.convertDpToPixel(40, getContext()));
        path.lineTo(widthJianGe * (select) + widthJianGe + bianJuLeftPx + distance, height - DpUtils.convertDpToPixel(20, getContext()));
        path.lineTo(radiusPx + getWidth(), height - DpUtils.convertDpToPixel(20, getContext()));
        path.lineTo(radiusPx + getWidth(), height);
        path.lineTo(-radiusPx, height);
        path.close();
        canvas.drawPath(path, paintBtm);
        canvas.restore();
        //画底部圆点
        canvas.drawCircle(widthJianGe * (select) + widthJianGe / 2f + bianJuLeftPx + distance, height - DpUtils.convertDpToPixel(20, getContext()), pointRadius, paintCircle);
    }

    public void setValue01(int i, float value) {
        line01[i] = value;
        invalidate();
    }

    private boolean isMove = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDown = event.getX();
                float yDown = event.getY();
                if (xDown > widthJianGe * (select) + widthJianGe / 2f + bianJuLeftPx - pointRadius
                        && xDown < widthJianGe * (select) + widthJianGe / 2f + bianJuLeftPx + pointRadius
                        && yDown > height - DpUtils.convertDpToPixel(20, getContext()) - pointRadius
                        && yDown < height - DpUtils.convertDpToPixel(20, getContext()) + pointRadius
                ) {
                    isMove = true;
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                float xMove = event.getX();
                if (isMove) {
                    distance = xMove - xDown;
                    invalidate();
                }
                return true;
            case MotionEvent.ACTION_UP:
                if (isMove) {
                    float xUp = event.getX();
                    int position = getPosition(xUp);
                    select = position;
                    distance = 0;
                    invalidate();
                }
                isMove = false;
                return true;
            default:
                break;
        }
        //这句话不要修改
        return super.onTouchEvent(event);
    }

    public int getPosition(float x) {
        for (int i = 0; i < text.length; i++) {
            if (x > widthJianGe * i + bianJuLeftPx && x < widthJianGe * i + widthJianGe + bianJuLeftPx) {
                return i;
            }
        }
        if (x < bianJuLeftPx) {
            return 0;
        }
        if (x > widthJianGe * (text.length - 1) + widthJianGe) {
            return text.length - 1;
        }
        return -1;
    }
}

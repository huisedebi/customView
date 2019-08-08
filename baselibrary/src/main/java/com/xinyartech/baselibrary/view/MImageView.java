package com.xinyartech.baselibrary.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.appcompat.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.xinyartech.baselibrary.R;
import com.xinyartech.baselibrary.utils.DimensUtils;


public class MImageView extends AppCompatImageView {

    public float mScale;

    public MImageView(Context context) {
        this(context, null);
    }

    public MImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public MImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ScaleLayout);
        mScale = typedArray.getFloat(R.styleable.ScaleLayout_scale, -1);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (mScale != -1) {
            super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec((int) (MeasureSpec.getSize(widthMeasureSpec) * mScale), MeasureSpec.getMode(widthMeasureSpec)));
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }


    private boolean mInterceptTouch;

    public boolean isInterceptTouch() {
        return mInterceptTouch;
    }

    public void setInterceptTouch(boolean interceptTouch) {
        mInterceptTouch = interceptTouch;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mInterceptTouch) {
            getParent().requestDisallowInterceptTouchEvent(true);//这句话的作用 告诉父view，我的单击事件我自行处理，不要阻碍我。
        }
        return super.dispatchTouchEvent(ev);
    }

    //要显示的数量数量
    private int num = 0;
    //红色圆圈的半径
    private float radius;
    //圆圈内数字的半径
    private float textSize;
    //右边和上边内边距
    private int paddingRight;
    private int paddingTop;
    private boolean shouldTipVisible = false;

    public void setTipNum(int num) {

        this.num = num > 0 ? num : 0;
        shouldTipVisible = this.num > 0;
        invalidate();
    }

    public void setTipVisible(boolean shouldTipVisible) {
        this.shouldTipVisible = shouldTipVisible;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (shouldTipVisible) {
            //num大于0显示数字
            radius = getWidth() / 6.7f;
            //初始化边距
            paddingRight = getPaddingRight();
            paddingTop = getPaddingTop();
            //初始化画笔
            Paint paint = new Paint();
            //设置抗锯齿
            paint.setAntiAlias(true);

            //设置填充样式为充满
            paint.setStyle(Paint.Style.FILL);



            paint.setColor(Color.WHITE);
            canvas.drawCircle(getWidth() - radius - paddingRight / 2, radius + paddingTop / 2, radius + DimensUtils.dp2px(getContext(), 1), paint);
            //画圆
            //设置颜色为红色
            paint.setColor(0xffff5953);
            canvas.drawCircle(getWidth() - radius - paddingRight / 2, radius + paddingTop / 2, radius, paint);

            if (num > 0) {
                //初始化半径
                //初始化字体大小
                textSize = num < 10 ? radius + 5 : radius;
                //设置颜色为白色
                paint.setColor(0xffffffff);
                //设置字体大小
                paint.setTextSize(textSize);
                //画数字
                canvas.drawText("" + (num < 99 ? num : 99),
                        num < 10 ? getWidth() - radius - textSize / 4 - paddingRight / 2
                                : getWidth() - radius - textSize / 2 - paddingRight / 2,
                        radius + textSize / 3 + paddingTop / 2, paint);
            }
        }
    }

}

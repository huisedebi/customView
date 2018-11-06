package com.zjb.home.boxingtu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;


/**
 * des： 缺口textview
 * author： ZhangJieBo
 * date： 2018/11/6 11:24
 */
public class GapTextView extends AppCompatTextView {
    private int color;
    private Paint paint;
    private boolean leftShow;
    private boolean rightShow;
    private float gap;

    public GapTextView(Context context) {
        super(context);
    }

    public GapTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GapTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GapTextView);
        gap = typedArray.getDimension(R.styleable.GapTextView_gap, 20f);
        color = typedArray.getColor(R.styleable.GapTextView_GapconnerColor, Color.WHITE);
        leftShow = typedArray.getBoolean(R.styleable.GapTextView_GapleftShow, true);
        rightShow = typedArray.getBoolean(R.styleable.GapTextView_GaprightShow, true);
        typedArray.recycle();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (leftShow) {
            Path path = new Path();
            path.lineTo(gap, getHeight() / 2);
            path.lineTo(0, getHeight());
            path.close();
            canvas.drawPath(path,paint);
        }
        if (rightShow) {
            Path path = new Path();
            path.moveTo(getWidth(),0);
            path.lineTo(getWidth()-gap, getHeight() / 2);
            path.lineTo(getWidth(), getHeight());
            path.close();
            canvas.drawPath(path,paint);
        }
    }
}
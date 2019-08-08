package com.zjb.home.boxingtu.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.zjb.home.boxingtu.R;


/**
 * des： 优惠券textview
 * author： ZhangJieBo
 * date： 2018/11/6 11:24
 */
public class ConnerCouponsTextView extends AppCompatTextView {
    private float conner;
    private int color;
    private Paint paint;
    private boolean leftShow;
    private boolean rightShow;

    public ConnerCouponsTextView(Context context) {
        super(context);
    }

    public ConnerCouponsTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ConnerCouponsTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ConnerCouponsTextView);
        conner = typedArray.getDimension(R.styleable.ConnerCouponsTextView_conner, 20f);
        color = typedArray.getColor(R.styleable.ConnerCouponsTextView_connerColor, Color.WHITE);
        leftShow = typedArray.getBoolean(R.styleable.ConnerCouponsTextView_leftShow, true);
        rightShow = typedArray.getBoolean(R.styleable.ConnerCouponsTextView_rightShow, true);
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
            RectF rectFleft = new RectF(-conner, (float) getHeight() / 2 - conner, conner, (float) getHeight() / 2f + conner);
            canvas.drawOval(rectFleft, paint);
        }
        if (rightShow) {
            RectF rectFright = new RectF(getWidth() - conner, (float) getHeight() / 2 - conner, (float) (getWidth() + conner), (float) getHeight() / 2f + conner);
            canvas.drawOval(rectFright, paint);
        }
    }
}
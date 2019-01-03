package com.zjb.home.boxingtu.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.zjb.home.boxingtu.util.DpUtils;
import com.zjb.home.boxingtu.R;


public class ConnerCoupons01View extends View {
    private float lineWidth;
    private float lineJianGE;
    private int conner;
    private Paint paint;
    private int width;
    private int height;
    Path path;

    public ConnerCoupons01View(Context context) {
        super(context);
        paint = new Paint(1);
        path = new Path();
    }

    public ConnerCoupons01View(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ConnerCoupons01View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(1);
        path = new Path();
        setLayerType(1, (Paint)null);
        lineWidth = DpUtils.convertDpToPixel(1.0F, context);
        lineJianGE = DpUtils.convertDpToPixel(3.0F, context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.basic));
        paint.setStyle(Paint.Style.FILL);
        conner = height / 2;
        paint.setPathEffect((PathEffect)null);
        RectF rectFleft = new RectF((float)(-conner), 0.0F + lineWidth / 2.0F, (float)conner, (float)height - lineWidth / 2.0F);
        canvas.drawOval(rectFleft, paint);
        RectF rectFright = new RectF((float)(width - conner), 0.0F + lineWidth / 2.0F, (float)(getWidth() + conner), (float)height - lineWidth / 2.0F);
        canvas.drawOval(rectFright, paint);
        canvas.save();
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(lineWidth);
        PathEffect pathEffect = new DashPathEffect(new float[]{lineJianGE, lineJianGE}, 0.0F);
        paint.setPathEffect(pathEffect);
        path.moveTo((float)(0 + conner) + DpUtils.convertDpToPixel(10.0F, getContext()), (float)conner);
        path.lineTo((float)(width - conner) - DpUtils.convertDpToPixel(10.0F, getContext()), (float)conner);
        canvas.drawPath(path, paint);
        canvas.restore();
    }
}
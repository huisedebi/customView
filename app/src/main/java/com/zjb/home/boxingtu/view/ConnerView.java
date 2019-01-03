package com.zjb.home.boxingtu.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.zjb.home.boxingtu.util.DpUtils;


public class ConnerView extends View {
    private float lineWidth;
    private float lineJianGE;
    private int conner;
    private Paint paint;
    private int width;
    private int height;
    Path path;

    public ConnerView(Context context) {
        super(context);
        this.paint = new Paint(1);
        this.path = new Path();
    }

    public ConnerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ConnerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.paint = new Paint(1);
        this.path = new Path();
        this.setLayerType(1, (Paint)null);
        this.lineWidth = DpUtils.convertDpToPixel(1.0F, context);
        this.lineJianGE = DpUtils.convertDpToPixel(3.0F, context);
        this.paint.setColor(Color.BLACK);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(this.lineWidth);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.width = MeasureSpec.getSize(widthMeasureSpec);
        this.height = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.conner = this.height / 2;
        this.paint.setPathEffect((PathEffect)null);
        RectF rectFleft = new RectF((float)(-this.conner), 0.0F + this.lineWidth / 2.0F, (float)this.conner, (float)this.height - this.lineWidth / 2.0F);
        canvas.drawOval(rectFleft, this.paint);
        RectF rectFright = new RectF((float)(this.width - this.conner), 0.0F + this.lineWidth / 2.0F, (float)(this.getWidth() + this.conner), (float)this.height - this.lineWidth / 2.0F);
        canvas.drawOval(rectFright, this.paint);
        canvas.save();
        PathEffect pathEffect = new DashPathEffect(new float[]{this.lineJianGE, this.lineJianGE}, 0.0F);
        this.paint.setPathEffect(pathEffect);
        this.path.moveTo((float)(0 + this.conner) + DpUtils.convertDpToPixel(10.0F, this.getContext()), (float)this.conner);
        this.path.lineTo((float)(this.width - this.conner) - DpUtils.convertDpToPixel(10.0F, this.getContext()), (float)this.conner);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }
}
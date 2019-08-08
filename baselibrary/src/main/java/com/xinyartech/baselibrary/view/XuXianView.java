package com.xinyartech.baselibrary.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.xinyartech.baselibrary.R;
import com.xinyartech.baselibrary.utils.DpUtils;


public class XuXianView extends View {
    private float lineWidth;
    private float lineJianGE;
    private int conner;
    private Paint paint;
    private int width;
    private int height;
    Path path;

    public XuXianView(Context context) {
        super(context);
        this.paint = new Paint(1);
        this.path = new Path();
    }

    public XuXianView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XuXianView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.paint = new Paint(1);
        this.path = new Path();
        this.setLayerType(1, (Paint)null);
        this.lineWidth = DpUtils.convertDpToPixel(1.0F, context);
        this.lineJianGE = DpUtils.convertDpToPixel(3.0F, context);
        this.paint.setColor(ContextCompat.getColor(context,R.color.gray_f999));
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
        PathEffect pathEffect = new DashPathEffect(new float[]{this.lineJianGE, this.lineJianGE}, 0.0F);
        this.paint.setPathEffect(pathEffect);
        this.path.moveTo(0, height/2);
        this.path.lineTo(width, height/2);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }
}
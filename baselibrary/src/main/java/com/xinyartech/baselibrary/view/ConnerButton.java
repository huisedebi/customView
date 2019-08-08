package com.xinyartech.baselibrary.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by zhangjiebo on 2018/11/3/003.
 *
 * @author ZhangJieBo
 */
public class ConnerButton extends AppCompatTextView {
    String color = "#00000000";
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float conner = 10;

    public ConnerButton(Context context) {
        super(context);
        init();
    }

    public ConnerButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ConnerButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint.setColor(Color.parseColor(color));
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        RectF rect = new RectF(0, 0, getWidth(), getHeight());
        paint.setColor(Color.parseColor(color));
        canvas.drawRoundRect(rect, conner, conner, paint);
        super.onDraw(canvas);
    }

    public void setColorConner(String color, float conner) {
        this.color = color;
        this.conner = conner;
        invalidate();
    }
}

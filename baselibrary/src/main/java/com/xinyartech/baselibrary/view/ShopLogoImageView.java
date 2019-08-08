package com.xinyartech.baselibrary.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.appcompat.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.xinyartech.baselibrary.utils.DpUtils;


/**
 * Created by zhangjiebo on 2018/11/10/010.
 *
 * @author ZhangJieBo
 */
public class ShopLogoImageView extends AppCompatImageView {

    private Paint paint;

    public ShopLogoImageView(Context context) {
        super(context);
        init();
    }

    public ShopLogoImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShopLogoImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(DpUtils.convertDpToPixel(3, getContext()));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect = new Rect(0, 0, getWidth(), getHeight());
        canvas.drawRect(rect, paint);
    }
}

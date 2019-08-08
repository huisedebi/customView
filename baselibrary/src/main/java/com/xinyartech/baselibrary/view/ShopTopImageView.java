package com.xinyartech.baselibrary.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.core.content.ContextCompat;
import androidx.appcompat.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.xinyartech.baselibrary.R;


/**
 * Created by zhangjiebo on 2018/11/10/010.
 *
 * @author ZhangJieBo
 */
public class ShopTopImageView extends AppCompatImageView {

    private Paint paint;

    public ShopTopImageView(Context context) {
        super(context);
        init();
    }

    public ShopTopImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShopTopImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(ContextCompat.getColor(getContext(),R.color.tranblack99));
        paint.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect = new Rect(0, 0, getWidth(), getHeight());
        canvas.drawRect(rect, paint);
    }
}

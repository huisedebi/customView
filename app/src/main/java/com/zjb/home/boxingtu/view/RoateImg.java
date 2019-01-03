package com.zjb.home.boxingtu.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.zjb.home.boxingtu.R;


/**
 * Created by zhangjiebo on 2017/10/27 0027.
 *
 * @author ZhangJieBo
 */
public class RoateImg extends View{
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Camera camera = new Camera();
    Matrix matrix = new Matrix();
    int degree;
    ObjectAnimator animator = ObjectAnimator.ofInt(this, "degree", 0, 360);
    private static final int COLORDRAWABLE_DIMENSION = 2;

    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;

    public RoateImg(Context context) {
        super(context);
    }

    public RoateImg(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoateImg(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.huojian);

        animator.setDuration(1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float newZ = - displayMetrics.density * 6;
        camera.setLocation(0, 0, newZ);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        animator.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animator.end();
    }

    @SuppressWarnings("unused")
    public void setDegree(int degree) {
        this.degree = degree;
        invalidate();
    }

    public void stopAnim(){
        animator.end();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, getWidth(), getHeight(), true);
        bitmap.recycle();
        bitmap = scaledBitmap;
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        int centerX = bitmapWidth / 2;
        int centerY = bitmapHeight / 2;

        camera.save();
        matrix.reset();
        camera.rotateY(degree);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);
        canvas.save();
        canvas.concat(matrix);
        Path path = new Path();
        RectF rectF = new RectF(0,0,getWidth(),getHeight());
        path.addOval(rectF, Path.Direction.CW);
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap,0, 0, paint);
        canvas.restore();
    }

    public void setImageBitmap(Bitmap bm) {
        bitmap = bm;
    }

    public void setImageDrawable(Drawable drawable) {
        bitmap = getBitmapFromDrawable(drawable);
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        try {
            Bitmap bitmap;

            if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(COLORDRAWABLE_DIMENSION, COLORDRAWABLE_DIMENSION, BITMAP_CONFIG);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), BITMAP_CONFIG);
            }

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

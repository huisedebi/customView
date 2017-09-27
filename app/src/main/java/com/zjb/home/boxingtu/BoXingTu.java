package com.zjb.home.boxingtu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/9/27.
 */
public class BoXingTu extends View {

    private int num = 8;
    private int width;
    private int height;
    private int dianHeight = 3;
    private float widthJianGe;
    private float heightJianGe;
    private float quXian = 3;
    private float bianJu = 10;
    private float radius = 40;
    private float radiusPoint = 5;
    private Paint paintHengXianBase;
    private Paint paintHengXian;
    private Paint paintQuXian01;
    private Paint paintQuXian02;
    private Paint paintYingYing;
    private float bianJuPx;
    private float[] line01 = new float[]{
            0.15f,
            0.25f,
            0.21f,
            0.38f,
            0.48f,
            0.43f,
            0.56f,
            0.52f,
            0.63f,
    };
    private float[] line02 = new float[]{
            0.20f,
            0.28f,
            0.32f,
            0.42f,
            0.55f,
            0.48f,
            0.60f,
            0.57f,
            0.68f,
    };
    Path path01 = new Path();
    Path path02 = new Path();
    Path path03 = new Path();
    private float dianHeightPx;
    private float radiusPointPx;

    public BoXingTu(Context context) {
        super(context);
    }

    public BoXingTu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BoXingTu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paintHengXianBase = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintHengXianBase.setColor(getResources().getColor(R.color.hengxian));
        paintHengXianBase.setStrokeWidth(DpUtils.convertDpToPixel(2, getContext()));

        paintHengXian = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintHengXian.setColor(getResources().getColor(R.color.hengxian));
        paintHengXian.setStrokeWidth(DpUtils.convertDpToPixel(1, getContext()));

        PathEffect pathEffect = new CornerPathEffect(radius);
        paintQuXian01 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintQuXian01.setPathEffect(pathEffect);
        paintQuXian01.setStyle(Paint.Style.STROKE);
        paintQuXian01.setColor(getResources().getColor(R.color.quXian01));
        paintQuXian01.setStrokeWidth(DpUtils.convertDpToPixel(quXian, getContext()));

        paintQuXian02 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintQuXian02.setPathEffect(pathEffect);
        paintQuXian02.setStyle(Paint.Style.STROKE);
        paintQuXian02.setColor(getResources().getColor(R.color.quXian02));
        paintQuXian02.setStrokeWidth(DpUtils.convertDpToPixel(quXian, getContext()));

        paintYingYing = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintYingYing.setStyle(Paint.Style.FILL);
        paintYingYing.setColor(getResources().getColor(R.color.basicLight));

        bianJuPx = DpUtils.convertDpToPixel(bianJu, getContext());
        dianHeightPx = DpUtils.convertDpToPixel(dianHeight, getContext());
        radiusPointPx = DpUtils.convertDpToPixel(radiusPoint, getContext());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        widthJianGe = (width - bianJuPx * 2) / num;
        heightJianGe = (height - bianJuPx * 2) / num;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawLine(bianJuPx, height - bianJuPx, width - bianJuPx, height - bianJuPx, paintHengXianBase);
        for (int i = 0; i < num + 1; i++) {
            canvas.drawLine(bianJuPx + widthJianGe * i, height - bianJuPx, bianJuPx + widthJianGe * i, height - bianJuPx - dianHeightPx, paintHengXianBase);
        }

        for (int i = 0; i < num - 1; i++) {
            canvas.drawLine(bianJuPx, height - bianJuPx - heightJianGe * (i + 1), width - bianJuPx, height - bianJuPx - heightJianGe * (i + 1), paintHengXian);
        }

        Shader shader = new LinearGradient(bianJuPx + widthJianGe * 3.5f, 0, bianJuPx + widthJianGe * 3.5f, (height - bianJuPx) / 2, getResources().getColor(R.color.basicLight),
                getResources().getColor(R.color.basic), Shader.TileMode.MIRROR);
        paintYingYing.setShader(shader);
        path03.addRect(bianJuPx + widthJianGe * 3, 0, bianJuPx + widthJianGe * 4, height - bianJuPx, Path.Direction.CCW);
        canvas.drawPath(path03, paintYingYing);

        canvas.save();
        path01.moveTo(bianJuPx + widthJianGe * 0, height - bianJuPx - heightJianGe * num * line01[0]);
        for (int i = 0; i < num; i++) {
            path01.lineTo(bianJuPx + widthJianGe * (i + 1), height - bianJuPx - heightJianGe * num * line01[(i + 1)]);
        }
        canvas.drawPath(path01, paintQuXian01);
        canvas.restore();

        canvas.save();
        path02.moveTo(bianJuPx + widthJianGe * 0, height - bianJuPx - heightJianGe * num * line02[0]);
        for (int i = 0; i < num; i++) {
            path02.lineTo(bianJuPx + widthJianGe * (i + 1), height - bianJuPx - heightJianGe * num * line02[(i + 1)]);
        }
        canvas.drawPath(path02, paintQuXian02);
        canvas.restore();

    }
}

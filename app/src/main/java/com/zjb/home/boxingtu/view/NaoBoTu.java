package com.zjb.home.boxingtu.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.zjb.home.boxingtu.util.DpUtils;


/**
 * Created by zhangjiebo on 2018/1/11/011.
 *
 * @author ZhangJieBo
 */

public class NaoBoTu extends View {
    /**
     * 横线画笔
     */
    private Paint paintHengXian;
    /**
     * 底部文字画笔
     */
    private Paint paintWenZiDiBu;
    /**
     * 右边文字画笔
     */
    private Paint paintWenZiYouBian;
    /**
     * 横线数量
     */
    int hengXianNum = 7;
    /**
     * 底部字体占用高度
     */
    float diBuZiHeght;
    /**
     * 右边字体占用宽度
     */
    float youBianZiWidth;
    /**
     * 右边文字margin
     */
    private float youBianTextMargin;

    /**
     * 右边文字
     */
    String[] strYouBian = new String[]{
            "128",
            "256",
            "384",
            "512",
            "640",
            "768",
            "896",
    };
    /**
     * 底部文字
     */
    String[] strDiBu = new String[]{
            "10",
            "20",
            "30",
            "40",
//            "50",
//            "60",
//            "70",
//            "80",
    };
    /**
     * 底部横线宽度
     */
    float diBuHengXianWidth;
    /**
     * 其他横线宽度
     */
    float qiTaHengXianWidth;
    /**
     * 底部文字大小
     */
    float diBuTextSize;
    private Rect rectDiBu;

    /**
     * 右边文字大小
     */
    float youBianTextSize;
    private Rect rectYouBian;
    /**
     * 脑波数据
     */
    int[] naoBoShuJuArr = new int[]{
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
            512, 512, 512, 512, 512, 512, 512, 512,
    };
    /**
     * 脑波画笔
     */
    private Paint paintNaoBo;
    Path path = new Path();
    private Paint paintBaiDian;
    private float naoBoLineWidth;

    public NaoBoTu(Context context) {
        super(context);
    }

    public NaoBoTu(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NaoBoTu(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 初始化距离
         */
        diBuZiHeght = DpUtils.convertDpToPixel(30f, context);
        youBianZiWidth = DpUtils.convertDpToPixel(50f, context);
        diBuHengXianWidth = DpUtils.convertDpToPixel(1.2f, context);
        qiTaHengXianWidth = DpUtils.convertDpToPixel(0.8f, context);
        diBuTextSize = DpUtils.convertDpToPixel(12f, context);
        youBianTextSize = DpUtils.convertDpToPixel(8f, context);
        youBianTextMargin = DpUtils.convertDpToPixel(24f, context);

        setLayerType(LAYER_TYPE_SOFTWARE, null);
        /**
         * 初始化横线画笔
         */
        paintHengXian = new Paint(Paint.ANTI_ALIAS_FLAG);
        /**
         * 初始化文字画笔
         */
        paintWenZiDiBu = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintWenZiDiBu.setColor(Color.parseColor("#b3d7fa"));
        paintWenZiDiBu.setStyle(Paint.Style.FILL);
        paintWenZiDiBu.setTextSize(diBuTextSize);
        rectDiBu = new Rect();
        paintWenZiDiBu.getTextBounds(strDiBu[0], 0, strDiBu[0].length(), rectDiBu);

        paintWenZiYouBian = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintWenZiYouBian.setColor(Color.parseColor("#62adf5"));
        paintWenZiYouBian.setStyle(Paint.Style.FILL);
        paintWenZiYouBian.setTextSize(diBuTextSize);
        rectYouBian = new Rect();
        paintWenZiYouBian.getTextBounds(strYouBian[0], 0, strDiBu[0].length(), rectYouBian);

        /**
         * 初始化脑波曲线画笔
         */
        PathEffect pathEffect = new CornerPathEffect(DpUtils.convertDpToPixel(5f,context));
        paintNaoBo = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintNaoBo.setPathEffect(pathEffect);
        paintNaoBo.setStyle(Paint.Style.STROKE);
        naoBoLineWidth = DpUtils.convertDpToPixel(1f, context);
        paintNaoBo.setStrokeWidth(naoBoLineWidth);
//        paintNaoBo.setColor(Color.parseColor("#ffb662"));
        Shader shader = new LinearGradient(getWidth(), 0, 0, getHeight(), Color.parseColor("#ffb06d"),
                Color.parseColor("#ffff54"), Shader.TileMode.MIRROR);
        paintNaoBo.setShader(shader);
        /**
         * 初始化白点画笔
         */
        paintBaiDian = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBaiDian.setStyle(Paint.Style.STROKE);
        paintBaiDian.setStrokeWidth(DpUtils.convertDpToPixel(5, context));
        paintBaiDian.setColor(Color.WHITE);
        paintBaiDian.setStrokeCap(Paint.Cap.ROUND);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 画底部线条
         */
        paintHengXian.setColor(Color.parseColor("#88b3d7fa"));
        paintHengXian.setStrokeWidth(diBuHengXianWidth);
        canvas.drawLine(0, getHeight() - diBuZiHeght, getWidth(), getHeight() - diBuZiHeght + naoBoLineWidth, paintHengXian);
        /**
         * 画其他横线
         */
        paintHengXian.setColor(Color.parseColor("#55b3d7fa"));
        paintHengXian.setStrokeWidth(qiTaHengXianWidth);
        //高度间隔
        float gaoDuJianGe = (getHeight() - diBuZiHeght) / (hengXianNum + 1);
        for (int i = 0; i < hengXianNum; i++) {
            canvas.drawLine(0, getHeight() - diBuZiHeght - gaoDuJianGe * (i + 1), getWidth() - youBianZiWidth, getHeight() - diBuZiHeght - gaoDuJianGe * (i + 1) + naoBoLineWidth, paintHengXian);
        }
        /**
         * 画底部文字
         */
        //宽度间隔
        float kuanDuJianGe = (getWidth() - youBianZiWidth) / strDiBu.length;
        for (int i = 0; i < strDiBu.length; i++) {
            canvas.drawText(strDiBu[i], i * kuanDuJianGe + 0.5f * kuanDuJianGe - 0.5f * rectDiBu.width(), getHeight() - diBuZiHeght / 2 + rectDiBu.height() / 2 + naoBoLineWidth, paintWenZiDiBu);
        }

        /**
         * 画右边文字
         */
        for (int i = 0; i < strYouBian.length; i++) {
            canvas.drawText(strYouBian[i], getWidth() - rectYouBian.width() - youBianTextMargin, getHeight() - diBuZiHeght - gaoDuJianGe * (i + 1) + rectYouBian.height() / 2 + naoBoLineWidth, paintWenZiYouBian);
        }

        /**
         * 画脑波
         */
        canvas.save();
        path.reset();
        path.moveTo(getWidth() - youBianZiWidth, (getHeight() - diBuZiHeght) - (getHeight() - diBuZiHeght) * ((float) naoBoShuJuArr[0] / 1024f) + naoBoLineWidth * 2);
        for (int i = 0; i < naoBoShuJuArr.length; i++) {
            path.lineTo((getWidth() - youBianZiWidth) - ((float) i / 32f) * ((getWidth() - youBianZiWidth)), (getHeight() - diBuZiHeght) - (getHeight() - diBuZiHeght) * ((float) naoBoShuJuArr[i] / 1024f) + naoBoLineWidth * 2);
        }
        canvas.drawPath(path, paintNaoBo);
        canvas.restore();

        /**
         * 画开始的白点
         */
        canvas.drawPoint(getWidth() - youBianZiWidth, (getHeight() - diBuZiHeght) - (getHeight() - diBuZiHeght) * ((float) naoBoShuJuArr[0] / 1024f) + naoBoLineWidth * 2, paintBaiDian);
    }

    public void setNaoBoPoint(int value) {
        int[] naoBoShuJuArrX = new int[256];
        naoBoShuJuArrX[0] = value;
        for (int i = 0; i < naoBoShuJuArr.length - 1; i++) {
            naoBoShuJuArrX[i + 1] = naoBoShuJuArr[i];
        }
        naoBoShuJuArr = naoBoShuJuArrX;
        invalidate();
    }

    public void chuShiHua(){
        for (int i = 0; i < naoBoShuJuArr.length; i++) {
            naoBoShuJuArr[i] =512;
        }
    }
}

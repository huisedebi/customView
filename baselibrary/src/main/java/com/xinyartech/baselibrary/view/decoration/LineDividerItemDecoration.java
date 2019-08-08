package com.xinyartech.baselibrary.view.decoration;

import android.content.Context;
import android.graphics.Color;

import com.yanyusong.y_divideritemdecoration.Y_Divider;
import com.yanyusong.y_divideritemdecoration.Y_DividerBuilder;
import com.yanyusong.y_divideritemdecoration.Y_DividerItemDecoration;

public class LineDividerItemDecoration extends Y_DividerItemDecoration {
    float lineWidth;
    int headNum;
    int max = Integer.MAX_VALUE;
    int color = Color.TRANSPARENT;
    public static float small = 0.5f;
    public static float big = 10f;
    public float left = 0f;
    public float right = 0f;

    public LineDividerItemDecoration(Context context, float lineWidth, int headNum) {
        super(context);
        this.lineWidth = lineWidth;
        this.headNum = headNum;
    }

    public LineDividerItemDecoration(Context context, float lineWidth, int headNum, int color) {
        super(context);
        this.lineWidth = lineWidth;
        this.headNum = headNum;
        this.color = color;
    }

    public LineDividerItemDecoration(Context context, float lineWidth, float left, float right, int headNum, int color) {
        super(context);
        this.lineWidth = lineWidth;
        this.left = left;
        this.right = right;
        this.headNum = headNum;
        this.color = color;
    }

    public LineDividerItemDecoration(Context context, float lineWidth, int headNum, int max, int color) {
        super(context);
        this.lineWidth = lineWidth;
        this.headNum = headNum;
        this.max = max;
        this.color = color;
    }

    @Override
    public Y_Divider getDivider(int itemPosition) {
        Y_Divider divider = null;
        if (itemPosition >= headNum && itemPosition <= max) {
            divider = new Y_DividerBuilder()
                    .setTopSideLine(true, color, lineWidth, left, right)
                    .create();
        } else {
            divider = new Y_DividerBuilder()
                    .setTopSideLine(false, color, 0, 0, 0)
                    .create();
        }
        return divider;
    }
}
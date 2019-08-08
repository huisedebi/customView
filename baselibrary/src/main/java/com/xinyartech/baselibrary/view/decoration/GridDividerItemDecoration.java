package com.xinyartech.baselibrary.view.decoration;

import android.content.Context;
import android.graphics.Color;

import com.yanyusong.y_divideritemdecoration.Y_Divider;
import com.yanyusong.y_divideritemdecoration.Y_DividerBuilder;
import com.yanyusong.y_divideritemdecoration.Y_DividerItemDecoration;

public class GridDividerItemDecoration extends Y_DividerItemDecoration {
    float side;
    float middle;
    int num;

    public GridDividerItemDecoration(Context context, float side, float middle, int num) {
        super(context);
        this.side = side;
        this.middle = middle;
        this.num = num;
    }

    @Override
    public Y_Divider getDivider(int itemPosition) {
        Y_Divider divider = null;
        int i = itemPosition % num;
        if (i == 0) {
            divider = new Y_DividerBuilder()
                    .setLeftSideLine(true, Color.TRANSPARENT, side, 0, 0)
                    .setBottomSideLine(true, Color.TRANSPARENT, middle, 0, 0)
                    .setRightSideLine(true, Color.TRANSPARENT, side / 2, 0, 0)
                    .create();
        } else if (i == (num - 1)) {
            divider = new Y_DividerBuilder()
                    .setRightSideLine(true, Color.TRANSPARENT, side, 0, 0)
                    .setBottomSideLine(true, Color.TRANSPARENT, middle, 0, 0)
                    .setLeftSideLine(true, Color.TRANSPARENT, side / 2, 0, 0)
                    .create();
        } else {
            divider = new Y_DividerBuilder()
                    .setLeftSideLine(true, Color.TRANSPARENT, side/2, 0, 0)
                    .setBottomSideLine(true, Color.TRANSPARENT, middle, 0, 0)
                    .setRightSideLine(true, Color.TRANSPARENT, side/2, 0, 0)
                    .create();
        }
        return divider;
    }
}
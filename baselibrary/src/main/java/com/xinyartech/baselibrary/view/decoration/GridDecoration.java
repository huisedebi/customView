package com.xinyartech.baselibrary.view.decoration;

import android.content.Context;
import android.graphics.Color;

import com.yanyusong.y_divideritemdecoration.Y_Divider;
import com.yanyusong.y_divideritemdecoration.Y_DividerBuilder;
import com.yanyusong.y_divideritemdecoration.Y_DividerItemDecoration;
import com.yanyusong.y_divideritemdecoration.Y_SideLine;

public class GridDecoration extends Y_DividerItemDecoration {
    float width;
    int num;
    int heads;
    int color = Color.TRANSPARENT;

    public GridDecoration(Context context, int color, float width, int num) {
        super(context);
        this.width = width;
        this.num = num;
        this.color = color;
    }

    public GridDecoration(Context context, int color, float width, int num, int heads) {
        super(context);
        this.width = width;
        this.num = num;
        this.color = color;
        this.heads = heads;
    }

    @Override
    public Y_Divider getDivider(int itemPosition) {
        itemPosition = itemPosition-heads;
        Y_Divider divider = null;
        if (itemPosition>=0){
            int i = itemPosition % num;
            if (i == 0) {
                divider = new Y_DividerBuilder()
                        .setLeftSideLine(true, color, width/2, 0, 0)
                        .setBottomSideLine(true, color, width, 0, 0)
                        .setRightSideLine(true, color, width / 2, 0, 0)
                        .create();
            } else if (i == (num - 1)) {
                divider = new Y_DividerBuilder()
                        .setRightSideLine(true, color, width/2, 0, 0)
                        .setBottomSideLine(true, color, width, 0, 0)
                        .setLeftSideLine(true, color, width / 2, 0, 0)
                        .create();
            } else {
                divider = new Y_DividerBuilder()
                        .setLeftSideLine(true, color, width / 2, 0, 0)
                        .setBottomSideLine(true, color, width, 0, 0)
                        .setRightSideLine(true, color, width / 2, 0, 0)
                        .create();
            }
            if (itemPosition < num) {
                divider.setTopSideLine(new Y_SideLine(true, color, width, 0, 0));
            }
        }else {
            divider = new Y_DividerBuilder()
                    .setLeftSideLine(false, color, 0, 0, 0)
                    .setBottomSideLine(false, color, 0, 0, 0)
                    .setRightSideLine(false, color, 0, 0, 0)
                    .setTopSideLine(false,0,0,0,0)
                    .create();
        }
        return divider;
    }
}
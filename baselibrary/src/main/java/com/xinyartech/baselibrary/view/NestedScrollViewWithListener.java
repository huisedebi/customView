package com.xinyartech.baselibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * des： 带滑动监听的NestedScrollView
 * author： ZhangJieBo
 * date： 2018/11/3/003 16:33
 */
public class NestedScrollViewWithListener extends ScrollView {
    private NestedScrollViewWithListener.OnScrollChangedListener listener;

    public void setOnScrollChangedListener(NestedScrollViewWithListener.OnScrollChangedListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (this.listener!=null){
            this.listener.onScrollChanged(t);
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public NestedScrollViewWithListener(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public interface OnScrollChangedListener {
        void onScrollChanged(int var1);
    }
}
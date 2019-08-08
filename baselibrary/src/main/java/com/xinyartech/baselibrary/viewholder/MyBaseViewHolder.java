package com.xinyartech.baselibrary.viewholder;

import androidx.annotation.LayoutRes;
import android.view.ViewGroup;

import com.xinyartech.baselibrary.easyrecyclerview.adapter.BaseViewHolder;


/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class MyBaseViewHolder extends BaseViewHolder<Integer> {

    public MyBaseViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
    }

    @Override
    public void setData(Integer data) {
        super.setData(data);
    }
    
}

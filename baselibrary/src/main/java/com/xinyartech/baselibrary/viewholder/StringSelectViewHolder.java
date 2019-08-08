package com.xinyartech.baselibrary.viewholder;

import androidx.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xinyartech.baselibrary.R;
import com.xinyartech.baselibrary.easyrecyclerview.adapter.BaseViewHolder;


/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class StringSelectViewHolder extends BaseViewHolder<String> {

    private final TextView textName;

    public StringSelectViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textName = $(R.id.textName);
    }

    @Override
    public void setData(String data) {
        super.setData(data);
        textName.setText(data);
    }
    
}

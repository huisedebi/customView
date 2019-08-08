package com.zjb.home.boxingtu.view.datepicker;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.core.content.ContextCompat;

import com.xinyartech.baselibrary.easyrecyclerview.adapter.BaseViewHolder;
import com.zjb.home.boxingtu.R;


/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class DateItemViewHolder extends BaseViewHolder<DateEntity> {

    private final TextView textDate;
    private int nowMonth;

    public DateItemViewHolder(ViewGroup parent, @LayoutRes int res,int nowMonth) {
        super(parent, res);
        this.nowMonth=nowMonth;
        textDate = $(R.id.textDate);
    }

    @Override
    public void setData(DateEntity data) {
        super.setData(data);
        if (data.select){
            textDate.setBackgroundResource(R.drawable.shape_basic_18dp);
            textDate.setTextColor(ContextCompat.getColor(getContext(),R.color.white));
        }else {
            textDate.setTextColor(ContextCompat.getColor(getContext(),R.color.color_4D4D4D));
            textDate.setBackground(null);
        }
        textDate.setText(String.valueOf(data.day));
        if (data.month==nowMonth+1){
            textDate.setVisibility(View.VISIBLE);
        }else {
            textDate.setVisibility(View.INVISIBLE);
        }
    }

}

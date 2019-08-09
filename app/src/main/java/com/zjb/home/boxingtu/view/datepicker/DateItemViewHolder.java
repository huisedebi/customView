package com.zjb.home.boxingtu.view.datepicker;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.LayoutRes;

import com.xinyartech.baselibrary.easyrecyclerview.adapter.BaseViewHolder;
import com.xinyartech.baselibrary.utils.DateTransforam;
import com.zjb.home.boxingtu.R;

import java.text.ParseException;


/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class DateItemViewHolder extends BaseViewHolder<DateEntity> {

    private final TextView textDate;
    private int nowMonth;
    private long min;
    private long max;
    DateEntity data;
    public interface OnSelectListener {
        void select(DateEntity dateEntity);
    }

    OnSelectListener onSelectListener;

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.onSelectListener = onSelectListener;
    }

    public DateItemViewHolder(ViewGroup parent, @LayoutRes int res, int nowMonth,long min,long max) {
        super(parent, res);
        this.nowMonth = nowMonth;
        this.min = min;
        this.max = max;
        textDate = $(R.id.textDate);
        textDate.setOnClickListener(v -> {
            long dateToStamp = 0;
            try {
                dateToStamp = DateTransforam.dateToStamp(data.year + "-" + data.month + "-" + data.day);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if ((min>0&&dateToStamp <min)||(max>0&&dateToStamp>max)){
                return;
            }
            if (onSelectListener != null) {
                onSelectListener.select(data);
            }
        });
    }

    @Override
    public void setData(DateEntity data) {
        super.setData(data);
        this.data=data;
        textDate.setText(String.valueOf(data.day));
        if (data.month == nowMonth + 1) {
            textDate.setVisibility(View.VISIBLE);
            if (data.select) {
                textDate.setBackgroundResource(R.drawable.shape_basic_18dp);
                textDate.setAlpha(1f);
            } else {
                try {
                    long dateToStamp = DateTransforam.dateToStamp(data.year + "-" + data.month + "-" + data.day);
                    if ((min>0&&dateToStamp <min)||(max>0&&dateToStamp>max)){
                        textDate.setBackgroundResource(R.drawable.shape_gray_18dp);
                        textDate.setAlpha(1f);
                    }else {
                        textDate.setBackgroundResource(R.drawable.shape_basic_18dp);
                        textDate.setAlpha(0.3f);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } else {
            textDate.setVisibility(View.INVISIBLE);
        }
    }

}

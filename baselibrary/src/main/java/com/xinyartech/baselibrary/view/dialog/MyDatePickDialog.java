package com.xinyartech.baselibrary.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.xinyartech.baselibrary.R;
import com.xinyartech.baselibrary.R2;


public class MyDatePickDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private ClickListenerInterface clickListenerInterface;
    private ViewHolder viewHolder;
    long end;
    long start;
    String date;
    boolean isMax = true;

    public interface ClickListenerInterface {
        void sure(String date);
    }

    public MyDatePickDialog(Context context, long start, long end, ClickListenerInterface clickListenerInterface) {
        super(context, R.style.dialog);
        this.context = context;
        this.start = start;
        this.end = end;
        this.clickListenerInterface = clickListenerInterface;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_date_time_pick, null);
        setContentView(view);
        viewHolder = new ViewHolder(view);

        Calendar c = Calendar.getInstance();
        date = c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.DAY_OF_MONTH);
        viewHolder.dataPickek.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String monthStr;
                if (monthOfYear < 9) {
                    monthStr = "0" + (monthOfYear + 1);
                } else {
                    monthStr = "" + (monthOfYear + 1);
                }
                String dayStr;
                if (dayOfMonth < 10) {
                    dayStr = "0" + dayOfMonth;
                } else {
                    dayStr = "" + dayOfMonth;
                }
                date = year + "-" + monthStr + "-" + dayStr;
            }
        });
        if (end > 0) {
            viewHolder.dataPickek.setMaxDate(end);
        }
        if (start > 0) {
            viewHolder.dataPickek.setMaxDate(start);
        }
        viewHolder.textSure.setOnClickListener(this);
        viewHolder.textCancle.setOnClickListener(this);
        viewHolder.textClear.setOnClickListener(this);

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.textSure) {
            clickListenerInterface.sure(date);
            dismiss();
        } else if (id == R.id.textCancle) {
            dismiss();
        } else if (id == R.id.textClear) {
            clickListenerInterface.sure("");
            dismiss();
        }
    }

    static class ViewHolder {
        @BindView(R2.id.dataPickek)
        DatePicker dataPickek;
        @BindView(R2.id.textCancle)
        TextView textCancle;
        @BindView(R2.id.textClear)
        TextView textClear;
        @BindView(R2.id.textSure)
        TextView textSure;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
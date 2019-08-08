package com.zjb.home.boxingtu.view.datepicker;

import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.xinyartech.baselibrary.constant.Constant;
import com.zjb.home.boxingtu.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyDatePickerDialog extends Dialog {

    private AppCompatActivity context;
    private String auto;
    private ViewHolder viewHolder;

    public interface OnCheckListener {
        void check();
    }

    OnCheckListener onCheckListener;

    public void setOnCheckListener(OnCheckListener onCheckListener) {
        this.onCheckListener = onCheckListener;
    }

    public MyDatePickerDialog(AppCompatActivity context) {
        super(context, R.style.dialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_date_picker, null);
        setContentView(view);
        viewHolder = new ViewHolder(view);
        CalendarTool calendarTool = new CalendarTool(getContext());
        Calendar c = Calendar.getInstance();
        int nowYear = c.get(Calendar.YEAR);
        int nowMonth = c.get(Calendar.MONTH);

        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < 200*12; i++) {
            DateFragment rankFragment = new DateFragment();
            fragments.add(rankFragment);
            Bundle bundle = new Bundle();
            bundle.putInt(Constant.IntentKey.TYPE, i+1);
            rankFragment.setArguments(bundle);
        }
        viewHolder.viewPager.setAdapter(new DatePageAdapter(context.getSupportFragmentManager(),fragments));
        List<DateEntity> dateEntityList = calendarTool.getDateEntityList(2019, 9);
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.dialogFenXiang);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 1); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    static
    class ViewHolder {
        @BindView(R.id.viewPager)
        ViewPager viewPager;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
package com.zjb.home.boxingtu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xinyartech.baselibrary.baseactivity.ZjbBaseActivity;
import com.xinyartech.baselibrary.router.RouterUrl;
import com.xinyartech.baselibrary.utils.DateTransforam;
import com.zjb.home.boxingtu.view.datepicker.MyDatePickerDialog;

import java.text.ParseException;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = RouterUrl.Main.main_datePicker)
public class DatePickerActivity extends ZjbBaseActivity {

    @BindView(R.id.btn0001)
    Button btn0001;
    private int currentYear;
    private int currentMonth;
    private int currentDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initViews() {
        Calendar c = Calendar.getInstance();
        currentYear = c.get(Calendar.YEAR);
        currentMonth = c.get(Calendar.MONTH)+1;
        currentDay = c.get(Calendar.DAY_OF_MONTH);
        btn0001.setText(currentYear+"-"+currentMonth+"-"+currentDay);
    }

    @Override
    protected void initRecyclerview() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn0001})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn0001:
                MyDatePickerDialog myDatePickerDialog = new MyDatePickerDialog(mContext, currentYear, currentMonth, currentDay);
                try {
                    myDatePickerDialog.setMin(DateTransforam.dateToStamp("2019-06-20"));
                    myDatePickerDialog.setMax(DateTransforam.dateToStamp("2019-09-20"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                myDatePickerDialog.show();
                myDatePickerDialog.setOnCheckListener((currentYear, currentMonth, currentDay) -> {
                    DatePickerActivity.this.currentYear=currentYear;
                    DatePickerActivity.this.currentMonth=currentMonth;
                    DatePickerActivity.this.currentDay=currentDay;
                    btn0001.setText(currentYear+"-"+currentMonth+"-"+currentDay);
                });
                break;
        }
    }
}

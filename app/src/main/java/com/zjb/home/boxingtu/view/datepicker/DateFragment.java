package com.zjb.home.boxingtu.view.datepicker;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.xinyartech.baselibrary.baseactivity.ZjbBaseFragment;
import com.zjb.home.boxingtu.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class DateFragment extends ZjbBaseFragment {


    private View inflate;
    private Unbinder unbinder;
    private int position;

    public DateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (inflate == null) {
            inflate = inflater.inflate(R.layout.fragment_date, container, false);
            unbinder = ButterKnife.bind(this, inflate);
            init();
        }
        unbinder = ButterKnife.bind(this, inflate);
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) inflate.getParent();
        if (parent != null) {
            parent.removeView(inflate);
        }
        return inflate;
    }

    @Override
    protected void initIntent() {
//        Bundle arguments = getArguments();
//        position = arguments.getInt(Constant.IntentKey.POSITION, 0);
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initViews() {
//        CalendarTool calendarTool = new CalendarTool(getContext());
//        Calendar c = Calendar.getInstance();
//        int nowYear = c.get(Calendar.YEAR);
//        int nowMonth = c.get(Calendar.MONTH);
//        int year = position / 12;
//        int month = position % 12;
//        LogUtil.LogShitou("DateFragment--initViews", "now  " + nowYear+ "   " + nowMonth);
//        LogUtil.LogShitou("DateFragment--initViews", "current  " + year + "   " + month);
//        List<DateEntity> dateEntityList = calendarTool.getDateEntityList(nowYear, nowMonth);
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
}

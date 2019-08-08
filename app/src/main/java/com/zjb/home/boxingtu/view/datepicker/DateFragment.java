package com.zjb.home.boxingtu.view.datepicker;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initViews() {

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

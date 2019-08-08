package com.zjb.home.boxingtu.view.datepicker;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * des： 店铺-申请分销
 * author： ZhangJieBo
 * date： 2018/11/8/008 19:09
 */
public class DatePageAdapter extends FragmentPagerAdapter {

    List<DateFragment> fragments = new ArrayList<>();

    public DatePageAdapter(FragmentManager fm, List<DateFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return new DateFragment();
    }

    @Override
    public int getCount() {
        return 200*12;
    }
}
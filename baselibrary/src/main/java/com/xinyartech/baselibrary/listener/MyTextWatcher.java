package com.xinyartech.baselibrary.listener;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by zhangjiebo on 2019/1/19.
 *
 * @author ZhangJieBo
 */
public abstract class MyTextWatcher implements TextWatcher {

    public abstract void afterTextChangedX(Editable s);

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        afterTextChangedX(s);
    }
}

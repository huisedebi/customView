package com.xinyartech.baselibrary.interfacebag;


import com.xinyartech.baselibrary.bean.Login;

/**
 * Created by zhangjiebo on 2018/3/26.
 *
 * @author ZhangJieBo
 */

public interface OnOkListener {
    Login getToken();
    boolean isLogin();
    void showLoading();
    void hideLoading();
    void ok(int position);
}

package com.xinyartech.baselibrary.listener;


import com.xinyartech.baselibrary.bean.Login;
import com.xinyartech.baselibrary.interfacebag.OnOkListener;

/**
 * Created by zhangjiebo on 2019/1/27.
 *
 * @author ZhangJieBo
 */
public abstract class OnSimpleOkListener implements OnOkListener {


    OnOkListener onOkListener;

    public OnSimpleOkListener(OnOkListener onOkListener) {
        this.onOkListener = onOkListener;
    }

    @Override
    public Login getToken() {
        return onOkListener.getToken();
    }

    @Override
    public boolean isLogin() {
        return onOkListener.isLogin();
    }

    @Override
    public void showLoading() {
        onOkListener.showLoading();
    }

    @Override
    public void hideLoading() {
        onOkListener.hideLoading();
    }

    @Override
    public void ok(int position) {
        onOkListener.ok(position);
    }
}

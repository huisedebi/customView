package com.xinyartech.baselibrary.baseactivity;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import com.xinyartech.baselibrary.R;
import com.xinyartech.baselibrary.bean.Login;
import com.xinyartech.baselibrary.constant.Constant;
import com.xinyartech.baselibrary.easyrecyclerview.EasyRecyclerView;
import com.xinyartech.baselibrary.interfacebag.FragmentBackHandler;
import com.xinyartech.baselibrary.interfacebag.OnOkListener;
import com.xinyartech.baselibrary.utils.ACacheX;
import com.xinyartech.baselibrary.utils.BackHandlerHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class ZjbBaseFragment extends Fragment implements FragmentBackHandler {
    public boolean isLogin = false;
    public int changeControl = 2016;
    public Login login;
    public Activity mContext;
    private AlertDialog mAlertDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //禁止横屏
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.mContext = getActivity();
//        StatusBarUtil.setTranslucentForImageViewInFragment(mContext, null);
    }

    public void init() {
        //添加当前界面到容器中
        changeControl = Constant.changeControl - 1;
        initSP();
        initIntent();
        initViews();
        initRecyclerview();
        initListener();
    }

    protected abstract void initIntent();

    protected abstract void initSP();

    protected abstract void initViews();

    protected abstract void initRecyclerview();

    protected abstract void initListener();

    protected abstract void initData();

    private void initLogin() {
        login = (Login) ACacheX.getAsObject(mContext, Constant.Acache.APP, Constant.Acache.USER_INFO, Login.class);
        if (login != null) {
            isLogin = true;
        } else {
            isLogin = false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (changeControl != Constant.changeControl) {
            initLogin();
            initData();
            changeControl++;
        }
    }

    public void showLoadingDialog() {
        if (mAlertDialog == null) {
            View dialog_progress = LayoutInflater.from(mContext).inflate(R.layout.view_progress, null);
            mAlertDialog = new AlertDialog.Builder(mContext, R.style.dialog)
                    .setView(dialog_progress)
                    .setCancelable(false)
                    .create();
            mAlertDialog.show();
            mAlertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                        cancelLoadingDialog();
                        mContext.finish();
                    }
                    return false;
                }
            });
        } else {
            if (!mAlertDialog.isShowing()) {
                mAlertDialog.show();
            }
        }
    }

    public void cancelLoadingDialog() {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
            mAlertDialog = null;
        }
    }

    public OnOkListener onOkListener = new OnOkListener() {
        @Override
        public Login getToken() {
            return login;
        }

        @Override
        public boolean isLogin() {
            return isLogin;
        }

        @Override
        public void showLoading() {
            showLoadingDialog();
        }

        @Override
        public void hideLoading() {
            cancelLoadingDialog();
        }

        @Override
        public void ok(int position) {

        }
    };

    /**
     * 错误显示
     *
     * @param msg
     */
    public void showError(final EasyRecyclerView recyclerView, String msg) {
        try {
            View viewLoader = LayoutInflater.from(mContext).inflate(R.layout.view_loaderror, null);
            TextView textMsg = viewLoader.findViewById(R.id.textMsg);
            textMsg.setText(msg);
            viewLoader.findViewById(R.id.buttonReLoad).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerView.showProgress();
                    initData();
                }
            });
            recyclerView.setErrorView(viewLoader);
            recyclerView.showError();
        } catch (Exception e) {
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        if (shouldReceiveEvent && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (shouldReceiveEvent && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    //是否需要接收eventBus事件，默认为false，如果为true则继承该基类的类必须自定义公有带@Subscrib注解的方法
    private boolean shouldReceiveEvent = true;

    public void setShouldReceiveEvent(boolean shouldReceiveEvent) {
        this.shouldReceiveEvent = shouldReceiveEvent;
    }

    /**
     * 处理注册了观察者确没有subscribe注解的方法
     *
     * @param e
     */
    @Subscribe
    public void handleEvent(Exception e) {
    }

    @Override
    public boolean onBackPressed() {
        return BackHandlerHelper.handleBackPress(this);
    }

}

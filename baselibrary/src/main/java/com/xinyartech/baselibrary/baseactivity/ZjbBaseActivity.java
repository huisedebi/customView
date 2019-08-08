package com.xinyartech.baselibrary.baseactivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import com.xinyartech.baselibrary.R;
import com.xinyartech.baselibrary.application.MyApplication;
import com.xinyartech.baselibrary.bean.Login;
import com.xinyartech.baselibrary.constant.Constant;
import com.xinyartech.baselibrary.easyrecyclerview.EasyRecyclerView;
import com.xinyartech.baselibrary.interfacebag.OnOkListener;
import com.xinyartech.baselibrary.utils.ACacheX;
import com.xinyartech.baselibrary.utils.StatusBarHelper;

public abstract class ZjbBaseActivity extends AppCompatActivity {

    public int changeControl = 2016;
    public Login login;
    public boolean isLogin = false;
    public Context mContext;
    private AlertDialog mAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //禁止横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mContext = ZjbBaseActivity.this;
        StatusBarHelper.setFullScreenAndBlackTextBase(this);
        ARouter.getInstance().inject(this);//添加在onCreate（）
    }

    public void init() {
        MyApplication.getInstance().addActivity(this);
        changeControl = Constant.changeControl - 1;
        initSP();
        initIntent();
        initViews();
        initRecyclerview();
        initListener();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (shouldReceiveEvent && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (shouldReceiveEvent && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    protected abstract void initSP();

    protected abstract void initIntent();

    protected abstract void initViews();

    protected abstract void initRecyclerview();

    protected abstract void initListener();

    protected abstract void initData();

    @Override
    protected void onResume() {
        super.onResume();
        initLogin();
        if (changeControl != Constant.changeControl) {
            initData();
            changeControl++;
        }
    }

    private void initLogin() {
        login = (Login) ACacheX.getAsObject(this, Constant.Acache.APP, Constant.Acache.USER_INFO, Login.class);
        if (login != null) {
            isLogin = true;
        } else {
            isLogin = false;
        }
    }

    public void showLoadingDialog() {
        if (mAlertDialog == null) {
            View dialog_progress = getLayoutInflater().inflate(R.layout.view_progress, null);
            mAlertDialog = new AlertDialog.Builder(this, R.style.dialog)
                    .setView(dialog_progress)
                    .setCancelable(false)
                    .create();
            mAlertDialog.show();
            mAlertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

                    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                        cancelLoadingDialog();
                        finish();
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
            try {
                mAlertDialog.dismiss();
                mAlertDialog = null;
            } catch (Exception e) {
            }
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
}

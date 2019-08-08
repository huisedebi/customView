package com.xinyartech.baselibrary.baseactivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Process;
import android.view.KeyEvent;

import com.xinyartech.baselibrary.constant.Constant;
import com.xinyartech.baselibrary.router.RouterUrl;
import com.xinyartech.baselibrary.router.RouterUtil;
import com.xinyartech.baselibrary.utils.ACacheX;
import com.xinyartech.baselibrary.view.dialog.SingleBtnDialog;


/**
 * 去登陆
 * Created by Administrator on 2017/8/27.
 */
public class ToLoginActivity {

    /**
     * des： 登录
     * author： ZhangJieBo
     * date： 2017/7/6 0006 下午 2:14
     */
    public static void toLoginActivity(final Context context) {
        Constant.changeControl++;
        ACacheX.clear(context, Constant.Acache.APP);
        SingleBtnDialog singleBtnDialog = new SingleBtnDialog(context, "登录已过期", "重新登录", new SingleBtnDialog.ClickListenerInterface() {
            @SuppressLint("WrongConstant")
            @Override
            public void doWhat() {
                toLoginActivityX(context);
            }
        });
        singleBtnDialog.setCancelable(false);
        singleBtnDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    toLoginActivityX(context);
                }
                return false;
            }
        });
        singleBtnDialog.show();
    }

    /**
     * des： 登录
     * author： ZhangJieBo
     * date： 2017/7/6 0006 下午 2:14
     */
    @SuppressLint("WrongConstant")
    public static void toLoginActivityX(final Context context) {
        Constant.changeControl++;
//        MyApplication.getInstance().exit();
        ACacheX.clear(context, Constant.Acache.APP);
        RouterUtil.getPostcardWithTransition(context, RouterUrl.LoginRegister.login_login)
                .withFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .navigation();
//        // 杀掉进程
        Process.killProcess(Process.myPid());
        System.exit(0);
    }
}

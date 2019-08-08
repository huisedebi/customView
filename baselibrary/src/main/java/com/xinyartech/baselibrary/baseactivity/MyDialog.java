package com.xinyartech.baselibrary.baseactivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;

import com.xinyartech.baselibrary.view.dialog.SingleBtnDialog;


/**
 * Created by Administrator on 2017/8/27.
 */
public class MyDialog {


    /**
     * 单个按钮无操作
     *
     * @param msg
     */
    public static void showTipCaoZuoDialog(Context context, String msg, final OnCaoZuoListener onCaoZuoListener) {
        SingleBtnDialog singleBtnDialog = new SingleBtnDialog(context, msg, "确认",new SingleBtnDialog.ClickListenerInterface() {
            @Override
            public void doWhat() {
                onCaoZuoListener.caoZuo();
            }
        });
        singleBtnDialog.show();
        singleBtnDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    dialog.dismiss();
                    onCaoZuoListener.caoZuo();
                }
                return false;
            }
        });
        singleBtnDialog.setCancelable(false);
    }

    /**
     * 单个按钮无操作
     *
     * @param msg
     */
    public static void showTipDialog(Context context, String msg) {
        SingleBtnDialog singleBtnDialog = new SingleBtnDialog(context, msg, "确认",new SingleBtnDialog.ClickListenerInterface() {
            @Override
            public void doWhat() {
            }
        });
        singleBtnDialog.show();
    }

    public static void dialogFinish(final Activity activity, String msg) {
        SingleBtnDialog singleBtnDialog = new SingleBtnDialog(activity, msg, "确认",new SingleBtnDialog.ClickListenerInterface() {
            @Override
            public void doWhat() {
                activity.finish();
            }
        });
        singleBtnDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    dialog.dismiss();
                    activity.finish();
                }
                return false;
            }
        });
        singleBtnDialog.setCancelable(false);
        singleBtnDialog.show();
    }

    public interface OnCaoZuoListener {
        void caoZuo();
    }

    public static OnChoosePicListener onChoosePicListener;

    public interface OnChoosePicListener {
        void chaKan();

        void shangChuan();
    }

    public static void setOnChoosePicListener(OnChoosePicListener onChoosePicListener) {
        MyDialog.onChoosePicListener = onChoosePicListener;
    }

//    /**
//     * des： 选择图片
//     * author： ZhangJieBo
//     * date： 2017/11/6 0006 上午 9:36
//     */
//    public static void choosePic(Context context) {
//        View dialog_tu_pian = LayoutInflater.from(context).inflate(R.layout.dialog_tu_pian, null);
//        final AlertDialog alertDialog = new AlertDialog.Builder(context, R.style.dialog)
//                .setView(dialog_tu_pian)
//                .create();
//        dialog_tu_pian.findViewById(R.id.textChaKan).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//                onChoosePicListener.chaKan();
//            }
//        });
//        dialog_tu_pian.findViewById(R.id.textShangChuan).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//                onChoosePicListener.shangChuan();
//            }
//        });
//        dialog_tu_pian.findViewById(R.id.textQuXiao).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//            }
//        });
//        alertDialog.show();
//        Window dialogWindow = alertDialog.getWindow();
//        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialogWindow.setWindowAnimations(R.style.dialogFenXiang);
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
//        lp.width = (int) (d.widthPixels * 1); // 高度设置为屏幕的0.6
//        dialogWindow.setAttributes(lp);
//    }
//
//    /**
//     * des： 预览图片
//     * author： ZhangJieBo
//     * date： 2017/11/6 0006 上午 9:34
//     */
//    public static void showPicDialog(Context context, String img, int placeholder) {
//        View dialog_img_show = LayoutInflater.from(context).inflate(R.layout.dialog_img_show, null);
//        ImageView imageView = (ImageView) dialog_img_show.findViewById(R.id.imageView);
//        GlideApp.with(context)
//                .asBitmap()
//                .load(img)
//                .placeholder(placeholder)
//                .into(imageView);
//        final AlertDialog alertDialog = new AlertDialog.Builder(context, R.style.dialog)
//                .setView(dialog_img_show)
//                .create();
//        alertDialog.show();
//        Window dialogWindow = alertDialog.getWindow();
//        dialogWindow.setGravity(Gravity.CENTER);
//        dialogWindow.setWindowAnimations(R.style.AnimFromTopToButtom);
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
//        lp.width = (int) (d.widthPixels * 0.6); // 高度设置为屏幕的0.6
//        dialogWindow.setAttributes(lp);
//    }
//
//    /**
//     * des： 预览图片
//     * author： ZhangJieBo
//     * date： 2017/11/6 0006 上午 9:34
//     */
//    public static void showPicDialog(Context context, Bitmap img, int placeholder) {
//        View dialog_img_show = LayoutInflater.from(context).inflate(R.layout.dialog_img_show, null);
//        ImageView imageView = (ImageView) dialog_img_show.findViewById(R.id.imageView);
//        GlideApp.with(context)
//                .asBitmap()
//                .load(img)
//                .placeholder(placeholder)
//                .into(imageView);
//        final AlertDialog alertDialog = new AlertDialog.Builder(context, R.style.dialog)
//                .setView(dialog_img_show)
//                .create();
//        alertDialog.show();
//        Window dialogWindow = alertDialog.getWindow();
//        dialogWindow.setGravity(Gravity.CENTER);
//        dialogWindow.setWindowAnimations(R.style.AnimFromTopToButtom);
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
//        lp.width = (int) (d.widthPixels * 0.6); // 高度设置为屏幕的0.6
//        dialogWindow.setAttributes(lp);
//    }
//
//    /**
//     * des： 预览图片
//     * author： ZhangJieBo
//     * date： 2017/11/6 0006 上午 9:34
//     */
//    public static void showPicDialog(Context context, int img, int placeholder) {
//        View dialog_img_show = LayoutInflater.from(context).inflate(R.layout.dialog_img_show, null);
//        ImageView imageView = (ImageView) dialog_img_show.findViewById(R.id.imageView);
//        GlideApp.with(context)
//                .asBitmap()
//                .load(img)
//                .placeholder(placeholder)
//                .into(imageView);
//        final AlertDialog alertDialog = new AlertDialog.Builder(context, R.style.dialog)
//                .setView(dialog_img_show)
//                .create();
//        alertDialog.show();
//        Window dialogWindow = alertDialog.getWindow();
//        dialogWindow.setGravity(Gravity.CENTER);
//        dialogWindow.setWindowAnimations(R.style.AnimFromTopToButtom);
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
//        lp.width = (int) (d.widthPixels * 0.6); // 高度设置为屏幕的0.6
//        dialogWindow.setAttributes(lp);
//    }

}



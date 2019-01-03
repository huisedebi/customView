package com.zjb.home.boxingtu.dialog;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zjb.home.boxingtu.R;
import com.zjb.home.boxingtu.util.DpUtils;
import com.zjb.home.boxingtu.util.ScreenUtils;
import com.zjb.home.boxingtu.util.ToastUtils;

import java.util.Random;

/**
 * Created by zhangjiebo on 2019/1/3.
 *
 * @author ZhangJieBo
 */
public class RedbagDialog {
    Activity activity;
    private Dialog mDialog;
    public RedbagDialog(Activity activity) {
        this.activity = activity;
    }

    /**
     * 红包弹窗
     */
    @SuppressLint("WrongConstant")
    public void hongBaoDialog() {
        if (mDialog == null) {
            int screenWidth = ScreenUtils.getScreenWidth(activity);
            int screenHeight = ScreenUtils.getScreenHeight(activity);


            View inflate = LayoutInflater.from(activity).inflate(R.layout.dialog_hongbao, null);
            RelativeLayout relaHongBao = (RelativeLayout) inflate.findViewById(R.id.relaHongBao);


            for (int i = 0; i < 10; i++) {
                ImageView imageView = new ImageView(activity);
                imageView.setImageResource(R.mipmap.hongbao002);
                int anInt = new Random().nextInt(40);
                float hongBaoSize = DpUtils.convertDpToPixel((anInt + 60), activity);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) hongBaoSize, (int) hongBaoSize);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                layoutParams.rightMargin = new Random().nextInt(screenWidth) - (int) hongBaoSize;
                layoutParams.topMargin = -(int) hongBaoSize;
                relaHongBao.addView(imageView, layoutParams);
                PropertyValuesHolder holder02 = PropertyValuesHolder.ofFloat("translationY", screenHeight + hongBaoSize);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageView, holder02);
                int duration = new Random().nextInt(3000) + 2000;
                int delay = new Random().nextInt(2500);
                animator.setDuration(duration);
                animator.setStartDelay(delay);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.setRepeatMode(ValueAnimator.INFINITE);
                animator.start();
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        qiangHongBao();
                    }
                });
            }
            for (int i = 0; i < 10; i++) {
                ImageView imageView = new ImageView(activity);
                imageView.setImageResource(R.mipmap.hongbao002);
                int anInt = new Random().nextInt(40);
                float hongBaoSize = DpUtils.convertDpToPixel((anInt + 60), activity);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) hongBaoSize, (int) hongBaoSize);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                layoutParams.leftMargin = new Random().nextInt(screenWidth) - (int) hongBaoSize;
                layoutParams.topMargin = -(int) hongBaoSize;
                relaHongBao.addView(imageView, layoutParams);
                PropertyValuesHolder holder02 = PropertyValuesHolder.ofFloat("translationY", screenHeight + hongBaoSize);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageView, holder02);
                int duration = new Random().nextInt(3000) + 2000;
                int delay = new Random().nextInt(2500);
                animator.setDuration(duration);
                animator.setStartDelay(delay);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.setRepeatMode(ValueAnimator.INFINITE);
                animator.start();
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        qiangHongBao();
                    }
                });
            }

            for (int i = 0; i < 10; i++) {
                ImageView imageView = new ImageView(activity);
                imageView.setImageResource(R.mipmap.hongbao001);
                int anInt = new Random().nextInt(40);
                float hongBaoSize = DpUtils.convertDpToPixel((anInt + 80), activity);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) hongBaoSize, (int) hongBaoSize);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                layoutParams.rightMargin = new Random().nextInt(screenWidth) - (int) hongBaoSize;
                layoutParams.topMargin = -(int) hongBaoSize;
                relaHongBao.addView(imageView, layoutParams);
                PropertyValuesHolder holder02 = PropertyValuesHolder.ofFloat("translationY", screenHeight + hongBaoSize);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageView, holder02);
                int duration = new Random().nextInt(5000) + 3000;
                int delay = new Random().nextInt(4000);
                animator.setDuration(duration);
                animator.setStartDelay(delay);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.setRepeatMode(ValueAnimator.INFINITE);
                animator.start();
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        qiangHongBao();
                    }
                });
            }
            for (int i = 0; i < 10; i++) {
                ImageView imageView = new ImageView(activity);
                imageView.setImageResource(R.mipmap.hongbao001);
                int anInt = new Random().nextInt(40);
                float hongBaoSize = DpUtils.convertDpToPixel((anInt + 80),activity);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) hongBaoSize, (int) hongBaoSize);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                layoutParams.leftMargin = new Random().nextInt(screenWidth) - (int) hongBaoSize;
                layoutParams.topMargin = -(int) hongBaoSize;
                relaHongBao.addView(imageView, layoutParams);
                PropertyValuesHolder holder02 = PropertyValuesHolder.ofFloat("translationY", screenHeight + hongBaoSize);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageView, holder02);
                int duration = new Random().nextInt(5000) + 3000;
                int delay = new Random().nextInt(4000);
                animator.setDuration(duration);
                animator.setStartDelay(delay);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.setRepeatMode(ValueAnimator.INFINITE);
                animator.start();
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        qiangHongBao();
                    }
                });
            }
            for (int i = 0; i < 10; i++) {
                ImageView imageView = new ImageView(activity);
                imageView.setImageResource(R.mipmap.hongbao003);
                int anInt = new Random().nextInt(40);
                float hongBaoSize = DpUtils.convertDpToPixel((anInt + 80), activity);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) hongBaoSize, (int) hongBaoSize);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                layoutParams.rightMargin = new Random().nextInt(screenWidth) - (int) hongBaoSize;
                layoutParams.topMargin = -(int) hongBaoSize;
                relaHongBao.addView(imageView, layoutParams);
                PropertyValuesHolder holder02 = PropertyValuesHolder.ofFloat("translationY", screenHeight + hongBaoSize);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageView, holder02);
                int duration = new Random().nextInt(5000) + 3000;
                int delay = new Random().nextInt(4000);
                animator.setDuration(duration);
                animator.setStartDelay(delay);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.setRepeatMode(ValueAnimator.INFINITE);
                animator.start();
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        qiangHongBao();
                    }
                });
            }

            for (int i = 0; i < 10; i++) {
                ImageView imageView = new ImageView(activity);
                imageView.setImageResource(R.mipmap.hongbao003);
                int anInt = new Random().nextInt(40);
                float hongBaoSize = DpUtils.convertDpToPixel((anInt + 80), activity);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) hongBaoSize, (int) hongBaoSize);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                layoutParams.leftMargin = new Random().nextInt(screenWidth) - (int) hongBaoSize;
                layoutParams.topMargin = -(int) hongBaoSize;
                relaHongBao.addView(imageView, layoutParams);
                PropertyValuesHolder holder02 = PropertyValuesHolder.ofFloat("translationY", screenHeight + hongBaoSize);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageView, holder02);
                int duration = new Random().nextInt(5000) + 3000;
                int delay = new Random().nextInt(4000);
                animator.setDuration(duration);
                animator.setStartDelay(delay);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.setRepeatMode(ValueAnimator.INFINITE);
                animator.start();
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        qiangHongBao();
                    }
                });
            }


            mDialog = new Dialog(activity, R.style.mydialog);
            mDialog.setContentView(inflate);
            mDialog.show();
        } else {
            mDialog.show();
        }
    }

    private void qiangHongBao() {
        ToastUtils.showToast(activity, "抢到了");
    }
}

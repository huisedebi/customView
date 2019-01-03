package com.zjb.home.boxingtu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zjb.home.boxingtu.util.ScreenUtils;
import com.zjb.home.boxingtu.util.ToastUtils;

public class EndTimeActivity extends AppCompatActivity {

    private int screenWidth;
    private RelativeLayout viewDaoJiShi;
    ObjectAnimator animator01 = new ObjectAnimator();
    ObjectAnimator animator1 = new ObjectAnimator();
    private ImageView imageDaoJiShi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_time);
        screenWidth = ScreenUtils.getScreenWidth(this);
        imageDaoJiShi = (ImageView) findViewById(R.id.imageDaoJiShi);
        viewDaoJiShi = (RelativeLayout) findViewById(R.id.viewDaoJiShi);
        daoJiShi();
    }

    /**
     * 倒计时
     */
    @SuppressLint("WrongConstant")
    public void daoJiShi() {
        screenWidth = ScreenUtils.getScreenWidth(EndTimeActivity.this);
        final ImageView imageView = new ImageView(EndTimeActivity.this);
        imageView.setImageResource(R.mipmap.jianbianquan);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) ((float) screenWidth * 0.6f), (int) ((float) screenWidth * 0.6f));
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        viewDaoJiShi.addView(imageView, layoutParams);
        PropertyValuesHolder holder01 = PropertyValuesHolder.ofFloat("scaleX", 1f, 3f);
        PropertyValuesHolder holder02 = PropertyValuesHolder.ofFloat("scaleY", 1f, 3f);
        PropertyValuesHolder holder03 = PropertyValuesHolder.ofFloat("alpha", 1f, 0f);
        animator01 = ObjectAnimator.ofPropertyValuesHolder(imageView, holder01, holder02, holder03);
        animator01.setInterpolator(new LinearInterpolator());
        animator01.setDuration(1000);
        animator01.setRepeatCount(2);
        animator01.setRepeatMode(ValueAnimator.INFINITE);
        animator01.start();
        animator01.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });

        final int[] count = {1};
        PropertyValuesHolder holder0101 = PropertyValuesHolder.ofFloat("scaleX", 1f, 2f);
        PropertyValuesHolder holder0201 = PropertyValuesHolder.ofFloat("scaleY", 1f, 2f);
        PropertyValuesHolder holder0301 = PropertyValuesHolder.ofFloat("alpha", 1f, 0.5f);
        animator1 = ObjectAnimator.ofPropertyValuesHolder(imageDaoJiShi, holder0101, holder0201, holder0301);
        animator1.setInterpolator(new LinearInterpolator());
        animator1.setDuration(1000);
        animator1.setRepeatCount(2);
        animator1.setRepeatMode(ValueAnimator.INFINITE);
        animator1.start();
        animator1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ToastUtils.showToast(EndTimeActivity.this, "倒计时结束");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
                count[0]++;
                if (count[0] == 2) {
                    imageDaoJiShi.setImageResource(R.mipmap.daojishi2);
                } else if (count[0] == 3) {
                    imageDaoJiShi.setImageResource(R.mipmap.daojishi1);
                }
            }
        });
    }
}

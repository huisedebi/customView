package com.zjb.home.boxingtu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zjb.home.boxingtu.util.ScreenUtils;
import com.zjb.home.boxingtu.view.RoateImg;

public class UpLoadAnimActivity extends AppCompatActivity {

    private RoateImg roateImg;
    private TextView textShangChuanStatue;
    ObjectAnimator[] animator = new ObjectAnimator[5];
    private RelativeLayout viewShangChuan;
    private RelativeLayout viewDuQuBG;
    private int screenWidth;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_load_anim);
        roateImg = (RoateImg) findViewById(R.id.roateImg);
        textShangChuanStatue = (TextView) findViewById(R.id.textShangChuanStatue);
        viewShangChuan = findViewById(R.id.viewShangChuan);
        viewDuQuBG = findViewById(R.id.viewDuQuBG);
        screenWidth = ScreenUtils.getScreenWidth(UpLoadAnimActivity.this);
        ImageView imageView1 = new ImageView(UpLoadAnimActivity.this);
        imageView1.setImageResource(R.mipmap.jianbianquan);
        RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams((int) ((float) screenWidth * 0.6f), (int) ((float) screenWidth * 0.6f));
        layoutParams1.addRule(RelativeLayout.CENTER_IN_PARENT);
        viewShangChuan.addView(imageView1, layoutParams1);
        for (int i = 0; i < 5; i++) {
            final ImageView imageView = new ImageView(UpLoadAnimActivity.this);
            imageView.setImageResource(R.mipmap.jianbianquan);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) ((float) screenWidth * 0.6f), (int) ((float) screenWidth * 0.6f));
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            viewShangChuan.addView(imageView, layoutParams);
            if (i > 0) {
                imageView.setVisibility(View.GONE);
            }
            PropertyValuesHolder holder01 = PropertyValuesHolder.ofFloat("scaleX", 1f, 3f);
            PropertyValuesHolder holder02 = PropertyValuesHolder.ofFloat("scaleY", 1f, 3f);
            PropertyValuesHolder holder03 = PropertyValuesHolder.ofFloat("alpha", 1f, 0f);
            animator[i] = ObjectAnimator.ofPropertyValuesHolder(imageView, holder01, holder02, holder03);
            animator[i].setInterpolator(new LinearInterpolator());
            animator[i].setDuration(4000);
            animator[i].setStartDelay(0 + 1000 * i);
            animator[i].setRepeatCount(ValueAnimator.INFINITE);
            animator[i].setRepeatMode(ValueAnimator.INFINITE);
            animator[i].start();
            animator[i].addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    imageView.setVisibility(View.VISIBLE);
                }
            });
        }

        ImageView imageView3 = new ImageView(UpLoadAnimActivity.this);
        imageView3.setImageResource(R.mipmap.jianbianquan);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams((int) ((float) screenWidth * 0.8f), (int) ((float) screenWidth * 0.8f));
        layoutParams3.addRule(RelativeLayout.CENTER_IN_PARENT);
        viewDuQuBG.addView(imageView3, layoutParams3);
        imageView3.setAlpha(0.4f);

        ImageView imageView2 = new ImageView(UpLoadAnimActivity.this);
        imageView2.setImageResource(R.mipmap.jianbianquan);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams((int) ((float) screenWidth * 1f), (int) ((float) screenWidth * 1f));
        layoutParams2.addRule(RelativeLayout.CENTER_IN_PARENT);
        viewDuQuBG.addView(imageView2, layoutParams2);
        imageView2.setAlpha(0.2f);
        textShangChuanStatue.postDelayed(new Runnable() {
            @Override
            public void run() {
                shangChuangWanCheng();
            }
        },3000);
        viewShangChuan.setVisibility(View.VISIBLE);
        viewDuQuBG.setVisibility(View.GONE);
    }

    /**
     * 上传完成
     */
    private void shangChuangWanCheng() {
        textShangChuanStatue.setText("上传完成");
        for (int i = 0; i < animator.length; i++) {
            animator[i].end();
        }
        roateImg.stopAnim();
        PropertyValuesHolder holder01 = PropertyValuesHolder.ofFloat("scaleY", 1f, 0.6f);
        PropertyValuesHolder holder03 = PropertyValuesHolder.ofFloat("scaleX", 1f, 1.4f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(roateImg, holder01, holder03);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(500);
        PropertyValuesHolder holder04 = PropertyValuesHolder.ofFloat("scaleY", 0.6f, 1f);
        PropertyValuesHolder holder05 = PropertyValuesHolder.ofFloat("scaleX", 1.4f, 1f);
        ObjectAnimator animator2 = ObjectAnimator.ofPropertyValuesHolder(roateImg, holder04, holder05);
        animator2.setInterpolator(new LinearInterpolator());
        animator2.setDuration(200);
        PropertyValuesHolder holder02 = PropertyValuesHolder.ofFloat("translationY", -1500);
        ObjectAnimator animator1 = ObjectAnimator.ofPropertyValuesHolder(roateImg, holder02);
        animator1.setInterpolator(new LinearInterpolator());
        animator1.setDuration(1000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animator, animator2, animator1);
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                viewShangChuan.setVisibility(View.GONE);
                viewDuQuBG.setVisibility(View.VISIBLE);
            }
        });
    }
}

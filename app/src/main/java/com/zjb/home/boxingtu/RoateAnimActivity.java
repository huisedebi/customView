package com.zjb.home.boxingtu;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class RoateAnimActivity extends AppCompatActivity {
    private ImageView imageNeiQuan;
    private ImageView imageWaiQuan;
    ObjectAnimator animator = new ObjectAnimator();
    ObjectAnimator animator1 = new ObjectAnimator();
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roate_anim);
        imageNeiQuan = findViewById(R.id.imageNeiQuan);
        imageWaiQuan = findViewById(R.id.imageWaiQuan);
        PropertyValuesHolder holder02 = PropertyValuesHolder.ofFloat("rotation", 360, 0);

        animator1 = ObjectAnimator.ofPropertyValuesHolder(imageNeiQuan, holder02);
        animator1.setInterpolator(new LinearInterpolator());
        animator1.setDuration(6000);
        animator1.setRepeatCount(ValueAnimator.INFINITE);
        animator1.setRepeatMode(ValueAnimator.INFINITE);
        animator1.start();
        PropertyValuesHolder holder01 = PropertyValuesHolder.ofFloat("rotation", 0, 360);

        animator = ObjectAnimator.ofPropertyValuesHolder(imageWaiQuan, holder01);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(8000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.INFINITE);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (animator != null) {
            animator.start();
        }
        if (animator1 != null) {
            animator1.start();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (animator != null) {
            animator.end();
        }
        if (animator1 != null) {
            animator1.end();
        }
    }
}

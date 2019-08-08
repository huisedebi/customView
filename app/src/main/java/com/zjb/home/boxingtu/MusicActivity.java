package com.zjb.home.boxingtu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zjb.home.boxingtu.util.ScreenUtils;

public class MusicActivity extends AppCompatActivity {
    private RelativeLayout viewMusic;
    private ImageView imageMusic;
    private MediaPlayer mMediaPlayer;
    private int screenWidth;
    ObjectAnimator[] animatorMusic = new ObjectAnimator[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        mMediaPlayer = MediaPlayer.create(this, R.raw.zhunbei);
        viewMusic = (RelativeLayout) findViewById(R.id.viewMusic);
        imageMusic = (ImageView) findViewById(R.id.imageMusic);
        screenWidth = ScreenUtils.getScreenWidth(this);
        ViewGroup.LayoutParams layoutParams2 = imageMusic.getLayoutParams();
        layoutParams2.width = (int) ((float) screenWidth * 0.6f);
        layoutParams2.height = (int) ((float) screenWidth * 0.6f);
        imageMusic.setLayoutParams(layoutParams2);
        setMusicView();
        findViewById(R.id.btnFangSongDN).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer != null) {
                    if (mMediaPlayer.isPlaying()) {
                        mMediaPlayer.pause();
                        for (int i = 0; i < animatorMusic.length; i++) {
                            animatorMusic[i].end();
                        }
                    } else {
                        for (int i = 0; i < animatorMusic.length; i++) {
                            animatorMusic[i].start();
                        }
                        mMediaPlayer.start();
                    }
                }
            }
        });
    }

    @SuppressLint("WrongConstant")
    public void setMusicView() {
        for (int i = 0; i < 5; i++) {
            final ImageView imageView = new ImageView(MusicActivity.this);
            imageView.setImageResource(R.mipmap.jianbianquan);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) ((float) screenWidth * 0.58f), (int) ((float) screenWidth * 0.58f));
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            viewMusic.addView(imageView, layoutParams);
//            if (i > 0) {
                imageView.setVisibility(View.GONE);
//            }
            PropertyValuesHolder holder01 = PropertyValuesHolder.ofFloat("scaleX", 1f, 3f);
            PropertyValuesHolder holder02 = PropertyValuesHolder.ofFloat("scaleY", 1f, 3f);
            PropertyValuesHolder holder03 = PropertyValuesHolder.ofFloat("alpha", 1f, 0f);
            animatorMusic[i] = ObjectAnimator.ofPropertyValuesHolder(imageView, holder01, holder02, holder03);
            animatorMusic[i].setInterpolator(new LinearInterpolator());
            animatorMusic[i].setDuration(4000);
            animatorMusic[i].setStartDelay(0 + 1000 * i);
            animatorMusic[i].setRepeatCount(ValueAnimator.INFINITE);
            animatorMusic[i].setRepeatMode(ValueAnimator.INFINITE);
//            animatorMusic[i].start();
            animatorMusic[i].addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    imageView.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}

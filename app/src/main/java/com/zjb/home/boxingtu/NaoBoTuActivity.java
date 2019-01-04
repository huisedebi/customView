package com.zjb.home.boxingtu;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zjb.home.boxingtu.view.NaoBoTu;

import java.util.Random;

public class NaoBoTuActivity extends AppCompatActivity {
    private NaoBoTu naoBo01;
    private NaoBoTu naoBo02;
    private TextView textLeftTime;
    private TextView textZuoNaoDis;
    private TextView textYouNaoDis;
    private MediaPlayer mMediaPlayer;
    boolean isBreak = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nao_bo_tu);
        naoBo01 = (NaoBoTu) findViewById(R.id.naoBo01);
        naoBo02 = (NaoBoTu) findViewById(R.id.naoBo02);
        textLeftTime = (TextView) findViewById(R.id.textLeftTime);
        textZuoNaoDis = (TextView) findViewById(R.id.textZuoNaoDis);
        textYouNaoDis = (TextView) findViewById(R.id.textYouNaoDis);
        initNaoBo();
        final int[] leftTime = {120000};
        final int cut = 100;
        final int[] left = {512};
        final int[] right = {512};
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (isBreak) {
                    try {
                        Thread.sleep(cut);
                        leftTime[0] = leftTime[0] - cut;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (leftTime[0] % 1000 == 0) {
                                    leftTime(leftTime[0] / 1000);
                                }
                                int leftSmall = new Random().nextInt(100);
                                if ((left[0] + leftSmall) > 1000) {
                                    left[0] = left[0] - leftSmall;
                                } else if ((left[0] - leftSmall) < 0) {
                                    left[0] = left[0] + leftSmall;
                                } else {
                                    left[0] = left[0] + ((new Random().nextBoolean()) ? (-1 * leftSmall) : (leftSmall));
                                }
                                int rightSmall = new Random().nextInt(100);
                                if ((right[0] + rightSmall) > 1000) {
                                    right[0] = right[0] - rightSmall;
                                } else if ((right[0] - rightSmall) < 0) {
                                    right[0] = right[0] + rightSmall;
                                } else {
                                    right[0] = right[0] + ((new Random().nextBoolean()) ? (-1 * rightSmall) : (rightSmall));
                                }
                                setNaoBo(left[0], right[0]);
                            }
                        });
                        if (leftTime[0] == 0) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    initNaoBo();
                                }
                            });
                            isBreak = false;
                            break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(runnable).start();

    }


    /**
     * 初始化脑波图
     */
    public void initNaoBo() {
        naoBo01.chuShiHua();
        naoBo02.chuShiHua();
        textZuoNaoDis.setText("512");
        textYouNaoDis.setText("512");
        textLeftTime.setText("120");
    }

    /**
     * 刷新剩余时间
     *
     * @param leftTime
     */
    public void leftTime(int leftTime) {
        textLeftTime.setText(String.valueOf(leftTime));


        if (leftTime == 80) {
//            soundPool.play(load40, 1, 1, 0, 0, 1);
            mMediaPlayer = MediaPlayer.create(this, R.raw.naobo40);
            mMediaPlayer.start();

        }
        if (leftTime == 40) {
            mMediaPlayer = MediaPlayer.create(this, R.raw.naobo80);
            mMediaPlayer.start();
//            soundPool.play(load80, 1, 1, 0, 0, 1);
        }
    }

    /**
     * 刷新脑波图
     *
     * @param value01
     * @param value02
     */
    public void setNaoBo(int value01, int value02) {
        naoBo01.setNaoBoPoint(value01);
        naoBo02.setNaoBoPoint(value02);
        textZuoNaoDis.setText(String.valueOf(value01));
        textYouNaoDis.setText(String.valueOf(value02));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        isBreak = false;
    }
}

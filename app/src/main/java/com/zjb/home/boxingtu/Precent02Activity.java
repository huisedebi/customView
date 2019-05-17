package com.zjb.home.boxingtu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zjb.home.boxingtu.util.LogUtil;
import com.zjb.home.boxingtu.view.PrcentCircle;

import java.util.Random;

public class Precent02Activity extends AppCompatActivity {

    private PrcentCircle precentCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precent02);
        precentCircle = findViewById(R.id.precentCircle);
        precentCircle.setBaiFenBiAnim(10, 20, 30, 300);
    }

    public void suiJi(View v) {
        int pre01 = new Random().nextInt(360) + 1;
        int pre02;
        int pre03;
        int pre04;
        if (360-pre01 == 0) {
            pre02 = 0;
            pre03 = 0;
            pre04 = 0;
        }else {
            pre02 = new Random().nextInt(360 - pre01) + 1;
            if (360-pre01-pre02==0){
                pre03=0;
                pre04=0;
            }else {
                pre03 = new Random().nextInt(360 - pre01-pre02) + 1;
                if (360-pre01-pre02-pre03==0){
                    pre04=0;
                }else {
                    pre04 = 360 - pre01-pre02-pre03;
                }
            }
        }

        LogUtil.LogShitou("Precent02Activity--suiJi", ""+pre01+" "+pre02+" "+pre03+" "+pre04);
        precentCircle.setBaiFenBiAnim(pre01,pre02,pre03,pre04);
    }
}

package com.zjb.home.boxingtu;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
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
        float pre01 = new Random().nextFloat();
        float pre02 = new Random().nextFloat();
        float pre03 = new Random().nextFloat();
        float pre04 = new Random().nextFloat();

        float sum = pre01+pre02+pre03+pre04;


        LogUtil.LogShitou("Precent02Activity--suiJi", "" + pre01/sum*360f + " " + pre02/sum*360f + " " + pre03/sum*360f + " " + pre04/sum*360f);
        precentCircle.setBaiFenBiAnim(pre01/sum*360f, pre02/sum*360f, pre03/sum*360f, pre04/sum*360f);
    }

    public void ling(View v) {
        precentCircle.setBaiFenBiAnim(0, 0, 0, 0);
    }
}

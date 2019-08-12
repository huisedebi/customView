package com.zjb.home.boxingtu;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.zjb.home.boxingtu.view.BoXingTu05;

import java.util.Random;

public class BoXingTu05Activity extends AppCompatActivity {
    private float[] line01 = new float[]{
            0.61f,
            0.38f,
            0.88f,
            0.33f,
            0.56f,
            0.22f,
            1.00f,
            0.13f,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo_xing_tu05);
        BoXingTu05 boxingtu = findViewById(R.id.boxingtu);
        boxingtu.setBaiFenBiDuAnim(line01);
        findViewById(R.id.btnRandom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < line01.length; i++) {
                    float pre01 = new Random().nextFloat();
                    line01[i] = pre01;
                }
                boxingtu.setBaiFenBiDuAnim(line01);
            }
        });
    }
}

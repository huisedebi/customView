package com.zjb.home.boxingtu;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.zjb.home.boxingtu.view.BoXingTu05;

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
    }
}

package com.zjb.home.boxingtu;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class BoXingTuActivity extends AppCompatActivity {
    private float[] line01 = new float[]{
            0.21f,
            0.38f,
            0.48f,
            0.43f,
            0.56f,
            0.52f,
            0.63f,
    };
    private float[] line02 = new float[]{
            0.42f,
            0.52f,
            0.65f,
            0.58f,
            0.70f,
            0.67f,
            0.88f,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo_xing_tu);
    }
}

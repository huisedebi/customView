package com.zjb.home.boxingtu;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.zjb.home.boxingtu.view.BaiFenCircle01;

public class Precent01Activity extends AppCompatActivity {

    private BaiFenCircle01 baiFenCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_circle);
        baiFenCircle = findViewById(R.id.baifenbi01);
        baiFenCircle.setBaiFenBiDuAnim(270);
    }
}

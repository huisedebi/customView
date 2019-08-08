package com.zjb.home.boxingtu;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.zjb.home.boxingtu.view.BoXingTu02;

public class BoXingTu02Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo_xing_tu02);
        BoXingTu02 boXingTu02 = findViewById(R.id.boXingTu02);
    }
}

package com.zjb.home.boxingtu;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.zjb.home.boxingtu.view.BoXingTu04;

public class BoXingTu04Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo_xing_tu04);
        BoXingTu04 boxingtu = findViewById(R.id.boxingtu);
    }
}

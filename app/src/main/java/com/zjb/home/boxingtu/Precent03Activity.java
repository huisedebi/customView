package com.zjb.home.boxingtu;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.zjb.home.boxingtu.view.WaiMaiCircle02;

public class Precent03Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precent03);
        WaiMaiCircle02 waiMaiCircle = findViewById(R.id.waiMaiCircle);
        waiMaiCircle.setBaiFenBiAnim(80,70,110);
    }
}

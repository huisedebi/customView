package com.zjb.home.boxingtu;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.zjb.home.boxingtu.view.BaiFenCircle;

public class PrecentActivity extends AppCompatActivity {

    private BaiFenCircle baiFenCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precent);
        baiFenCircle = findViewById(R.id.baiFenCircle);
        baiFenCircle.setBaiFenBiDuAnim(270);
    }
}

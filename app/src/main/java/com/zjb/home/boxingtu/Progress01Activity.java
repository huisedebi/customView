package com.zjb.home.boxingtu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zjb.home.boxingtu.view.Progress01;

import java.util.Random;

public class Progress01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        final Progress01 progress01 = findViewById(R.id.progress01);
        findViewById(R.id.btnRandom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float pre01 = new Random().nextFloat();
                progress01.setPrecenrAnim(pre01);
            }
        });
    }
}

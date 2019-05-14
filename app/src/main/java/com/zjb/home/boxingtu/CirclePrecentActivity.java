package com.zjb.home.boxingtu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.SeekBar;

import com.zjb.home.boxingtu.view.WaiMaiCircle01;

public class CirclePrecentActivity extends AppCompatActivity {
    private boolean isChange01 =false;
    private boolean isChange02 =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_precent);
        final WaiMaiCircle01 waiMaiCircle = (WaiMaiCircle01) findViewById(R.id.waiMaiCircle);

        waiMaiCircle.setBaiFenBiDu01(120);

        final SeekBar seekBarBaiFenBi01 = (SeekBar) findViewById(R.id.seekBarBaiFenBi01);
        final SeekBar seekBarBaiFenBi02 = (SeekBar) findViewById(R.id.seekBarBaiFenBi02);
        final SeekBar seekBarStroke = (SeekBar) findViewById(R.id.seekBarStroke);
        waiMaiCircle.post(new Runnable() {
            @Override
            public void run() {
                Log.e("MainActivity", "MainActivity--onCreate--"+waiMaiCircle.getWidth11());
                seekBarStroke.setMax(waiMaiCircle.getWidth11()/2);
                seekBarStroke.setProgress(waiMaiCircle.getWidth11()/2/2);
            }
        });
        seekBarBaiFenBi01.setProgress(waiMaiCircle.getBaiFenBiDu01());
        seekBarBaiFenBi02.setProgress(waiMaiCircle.getKaiShiDuShu()+90);
        seekBarBaiFenBi02.setMax(360);
        seekBarBaiFenBi01.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (!isChange02){
                    isChange01 =true;
                    waiMaiCircle.setBaiFenBiDu01(i);
                    isChange01=false;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBarBaiFenBi02.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (!isChange01){
                    isChange02=true;
                    waiMaiCircle.setKaiShiDuShu(i-90);
                    isChange02=false;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBarStroke.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                waiMaiCircle.setCircleStroke(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}

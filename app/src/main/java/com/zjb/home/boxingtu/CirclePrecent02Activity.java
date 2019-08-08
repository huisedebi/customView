package com.zjb.home.boxingtu;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.SeekBar;

import com.zjb.home.boxingtu.view.WaiMaiCircle;

public class CirclePrecent02Activity extends AppCompatActivity {
    private boolean isChange01 =false;
    private boolean isChange02 =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_precent02);
        final WaiMaiCircle waiMaiCircle = (WaiMaiCircle) findViewById(R.id.waiMaiCircle);






        waiMaiCircle.setBaiFenBiDu01(120);
        waiMaiCircle.setBaiFenBiDu02(120);
        waiMaiCircle.setBaiFenBiDu03(120);
        waiMaiCircle.setCircleStroke(50);
//        waiMaiCircle.setColor01(getResources().getColor(R.color.color01));
//        waiMaiCircle.setColor02(getResources().getColor(R.color.color02));
//        waiMaiCircle.setColor03(getResources().getColor(R.color.color03));






        final SeekBar seekBarBaiFenBi01 = (SeekBar) findViewById(R.id.seekBarBaiFenBi01);
        final SeekBar seekBarBaiFenBi02 = (SeekBar) findViewById(R.id.seekBarBaiFenBi02);
        final SeekBar seekBarStroke = (SeekBar) findViewById(R.id.seekBarStroke);
        seekBarStroke.setProgress(150);
        waiMaiCircle.post(new Runnable() {
            @Override
            public void run() {
                Log.e("MainActivity", "MainActivity--onCreate--"+waiMaiCircle.getWidth11());
                seekBarStroke.setMax(waiMaiCircle.getWidth11()/2);
            }
        });
        seekBarBaiFenBi01.setProgress(waiMaiCircle.getBaiFenBiDu01());
        seekBarBaiFenBi02.setProgress(waiMaiCircle.getBaiFenBiDu02());
        seekBarBaiFenBi02.setMax(360-waiMaiCircle.getBaiFenBiDu02());
        seekBarBaiFenBi01.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (!isChange02){
                    isChange01 =true;
                    waiMaiCircle.setBaiFenBiDu01(i);
                    if ((360-i)>waiMaiCircle.getBaiFenBiDu03()){
                        waiMaiCircle.setBaiFenBiDu02(360-i-waiMaiCircle.getBaiFenBiDu03());
                    }else {
                        waiMaiCircle.setBaiFenBiDu02(0);
                        waiMaiCircle.setBaiFenBiDu03(360-i);
                    }
                    seekBarBaiFenBi02.setProgress(waiMaiCircle.getBaiFenBiDu02());
                    seekBarBaiFenBi02.setMax(360-i);
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
                    waiMaiCircle.setBaiFenBiDu02(i);
                    waiMaiCircle.setBaiFenBiDu03(360-i-waiMaiCircle.getBaiFenBiDu01());
                    seekBarBaiFenBi01.setProgress(waiMaiCircle.getBaiFenBiDu01());
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

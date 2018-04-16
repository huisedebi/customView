package com.zjb.home.boxingtu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private BoXingTu01 boxingtu;
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
        setContentView(R.layout.activity_main);
        boxingtu = (BoXingTu01) findViewById(R.id.boxingtu);
        SeekBar seekBar0101 = (SeekBar) findViewById(R.id.seekBar0101);
        SeekBar seekBar0102 = (SeekBar) findViewById(R.id.seekBar0102);
        SeekBar seekBar0103 = (SeekBar) findViewById(R.id.seekBar0103);
        SeekBar seekBar0104 = (SeekBar) findViewById(R.id.seekBar0104);
        SeekBar seekBar0105 = (SeekBar) findViewById(R.id.seekBar0105);
        SeekBar seekBar0106 = (SeekBar) findViewById(R.id.seekBar0106);
        SeekBar seekBar0107 = (SeekBar) findViewById(R.id.seekBar0107);
        SeekBar seekBar0201 = (SeekBar) findViewById(R.id.seekBar0201);
        SeekBar seekBar0202 = (SeekBar) findViewById(R.id.seekBar0202);
        SeekBar seekBar0203 = (SeekBar) findViewById(R.id.seekBar0203);
        SeekBar seekBar0204 = (SeekBar) findViewById(R.id.seekBar0204);
        SeekBar seekBar0205 = (SeekBar) findViewById(R.id.seekBar0205);
        SeekBar seekBar0206 = (SeekBar) findViewById(R.id.seekBar0206);
        SeekBar seekBar0207 = (SeekBar) findViewById(R.id.seekBar0207);
        seekBar0101.setProgress((int) (100f * line01[0]));
        seekBar0102.setProgress((int) (100f * line01[1]));
        seekBar0103.setProgress((int) (100f * line01[2]));
        seekBar0104.setProgress((int) (100f * line01[3]));
        seekBar0105.setProgress((int) (100f * line01[4]));
        seekBar0106.setProgress((int) (100f * line01[5]));
        seekBar0107.setProgress((int) (100f * line01[6]));
        seekBar0201.setProgress((int) (100f * line02[0]));
        seekBar0202.setProgress((int) (100f * line02[1]));
        seekBar0203.setProgress((int) (100f * line02[2]));
        seekBar0204.setProgress((int) (100f * line02[3]));
        seekBar0205.setProgress((int) (100f * line02[4]));
        seekBar0206.setProgress((int) (100f * line02[5]));
        seekBar0207.setProgress((int) (100f * line02[6]));
        seekBar0101.setOnSeekBarChangeListener(this);
        seekBar0102.setOnSeekBarChangeListener(this);
        seekBar0103.setOnSeekBarChangeListener(this);
        seekBar0104.setOnSeekBarChangeListener(this);
        seekBar0105.setOnSeekBarChangeListener(this);
        seekBar0106.setOnSeekBarChangeListener(this);
        seekBar0107.setOnSeekBarChangeListener(this);
        seekBar0201.setOnSeekBarChangeListener(this);
        seekBar0202.setOnSeekBarChangeListener(this);
        seekBar0203.setOnSeekBarChangeListener(this);
        seekBar0204.setOnSeekBarChangeListener(this);
        seekBar0205.setOnSeekBarChangeListener(this);
        seekBar0206.setOnSeekBarChangeListener(this);
        seekBar0207.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//        switch (seekBar.getId()) {
//            case R.id.seekBar0101:
//                boxingtu.setValue01(0, (float) i / 100f);
//                break;
//            case R.id.seekBar0102:
//                boxingtu.setValue01(1, (float) i / 100f);
//                break;
//            case R.id.seekBar0103:
//                boxingtu.setValue01(2, (float) i / 100f);
//                break;
//            case R.id.seekBar0104:
//                boxingtu.setValue01(3, (float) i / 100f);
//                break;
//            case R.id.seekBar0105:
//                boxingtu.setValue01(4, (float) i / 100f);
//                break;
//            case R.id.seekBar0106:
//                boxingtu.setValue01(5, (float) i / 100f);
//                break;
//            case R.id.seekBar0107:
//                boxingtu.setValue01(6, (float) i / 100f);
//                break;
//            case R.id.seekBar0201:
//                boxingtu.setValue02(0, (float) i / 100f);
//                break;
//            case R.id.seekBar0202:
//                boxingtu.setValue02(1, (float) i / 100f);
//                break;
//            case R.id.seekBar0203:
//                boxingtu.setValue02(2, (float) i / 100f);
//                break;
//            case R.id.seekBar0204:
//                boxingtu.setValue02(3, (float) i / 100f);
//                break;
//            case R.id.seekBar0205:
//                boxingtu.setValue02(4, (float) i / 100f);
//                break;
//            case R.id.seekBar0206:
//                boxingtu.setValue02(5, (float) i / 100f);
//                break;
//            case R.id.seekBar0207:
//                boxingtu.setValue02(6, (float) i / 100f);
//                break;
//        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}

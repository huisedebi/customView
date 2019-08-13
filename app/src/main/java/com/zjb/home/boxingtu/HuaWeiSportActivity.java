package com.zjb.home.boxingtu;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zjb.home.boxingtu.view.HuaWeiSport;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HuaWeiSportActivity extends AppCompatActivity {

    @BindView(R.id.textNum)
    TextView textNum;
    @BindView(R.id.huaWeiSport)
    HuaWeiSport huaWeiSport;

    private float[] line01 = new float[]{
            0.21f,
            0.38f,
            0.48f,
            1.00f,
            0.56f,
            0.52f,
            0.62f,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hua_wei_sport);
        ButterKnife.bind(this);
        huaWeiSport.setBaiFenBiDuAnim(line01);
        huaWeiSport.setOnGetPositionListener(new HuaWeiSport.OnGetPositionListener() {
            @Override
            public void getPosition(int position) {
                textNum.setText((int)(10000*line01[position])+"");
            }
        });
    }
}

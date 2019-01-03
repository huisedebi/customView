package com.zjb.home.boxingtu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zjb.home.boxingtu.dialog.RedbagDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn0001).setOnClickListener(this);
        findViewById(R.id.btn0002).setOnClickListener(this);
        findViewById(R.id.btn0003).setOnClickListener(this);
        findViewById(R.id.btn0004).setOnClickListener(this);
        findViewById(R.id.btn0005).setOnClickListener(this);
        findViewById(R.id.btn0006).setOnClickListener(this);
        findViewById(R.id.btn0007).setOnClickListener(this);
        findViewById(R.id.btn0008).setOnClickListener(this);
        findViewById(R.id.btn0009).setOnClickListener(this);
        findViewById(R.id.btn0010).setOnClickListener(this);
        findViewById(R.id.btn0011).setOnClickListener(this);
        findViewById(R.id.btn0012).setOnClickListener(this);
        findViewById(R.id.btn0013).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn0001:
                intent.setClass(this, BoXingTuActivity.class);
                startActivity(intent);
                break;
            case R.id.btn0002:
                intent.setClass(this, BoXingTu01Activity.class);
                startActivity(intent);
                break;
            case R.id.btn0003:
                intent.setClass(this, BoXingTu02Activity.class);
                startActivity(intent);
                break;
            case R.id.btn0004:
                intent.setClass(this, BoXingTu03Activity.class);
                startActivity(intent);
                break;
            case R.id.btn0005:
                intent.setClass(this, YuanJiaoViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn0006:
                intent.setClass(this, QueKouActivity.class);
                startActivity(intent);
                break;
            case R.id.btn0007:
                intent.setClass(this, LineActivity.class);
                startActivity(intent);
                break;
            case R.id.btn0008:
                intent.setClass(this, CirclePrecentActivity.class);
                startActivity(intent);
                break;
            case R.id.btn0009:
                intent.setClass(this, CirclePrecent02Activity.class);
                startActivity(intent);
                break;
            case R.id.btn0010:
                new RedbagDialog(MainActivity.this).hongBaoDialog();
                break;
            case R.id.btn0011:
                intent.setClass(this, RoateAnimActivity.class);
                startActivity(intent);
                break;
            case R.id.btn0012:
                intent.setClass(this, MusicActivity.class);
                startActivity(intent);
                break;
            case R.id.btn0013:
                intent.setClass(this, EndTimeActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}

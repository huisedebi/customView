package com.zjb.home.boxingtu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.pgyersdk.update.PgyUpdateManager;
import com.zjb.home.boxingtu.dialog.RedbagDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new PgyUpdateManager.Builder()
                .register();
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
        findViewById(R.id.btn0014).setOnClickListener(this);
        findViewById(R.id.btn0015).setOnClickListener(this);
        findViewById(R.id.btn0016).setOnClickListener(this);
        findViewById(R.id.btn0017).setOnClickListener(this);
        findViewById(R.id.btn0018).setOnClickListener(this);
        findViewById(R.id.btn0019).setOnClickListener(this);
        findViewById(R.id.btn0020).setOnClickListener(this);
        findViewById(R.id.btn0021).setOnClickListener(this);
        findViewById(R.id.btn0022).setOnClickListener(this);
        findViewById(R.id.btn0023).setOnClickListener(this);
        findViewById(R.id.btn0024).setOnClickListener(this);
        findViewById(R.id.btn0025).setOnClickListener(this);
        findViewById(R.id.btn0026).setOnClickListener(this);
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
            case R.id.btn0023:
                intent.setClass(this, BoXingTu06Activity.class);
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
            case R.id.btn0014:
                intent.setClass(this, NaoBoTuActivity.class);
                startActivity(intent);
                break;
            case R.id.btn0015:
                intent.setClass(this, UpLoadAnimActivity.class);
                startActivity(intent);
                break;
            case R.id.btn0016:
                intent.setClass(this, PrecentActivity.class);
                startActivity(intent);
                break;
            case R.id.btn0017:
                intent.setClass(this, BoXingTu04Activity.class);
                startActivity(intent);
                break;
            case R.id.btn0018:
                intent.setClass(this, ZhuZhuangTu01Activity.class);
                startActivity(intent);
                break;
            case R.id.btn0019:
                intent.setClass(this, BoXingTu05Activity.class);
                startActivity(intent);
                break;
            case R.id.btn0020:
                intent.setClass(this, Precent01Activity.class);
                startActivity(intent);
                break;
            case R.id.btn0021:
                intent.setClass(this, Precent03Activity.class);
                startActivity(intent);
                break;
            case R.id.btn0022:
                intent.setClass(this, Precent02Activity.class);
                startActivity(intent);
                break;
            case R.id.btn0024:
                intent.setClass(this, Progress01Activity.class);
                startActivity(intent);
                break;
            case R.id.btn0025:
                intent.setClass(this, HuaWeiSportActivity.class);
                startActivity(intent);
                break;
            case R.id.btn0026:
                intent.setClass(this, DatePickerActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}

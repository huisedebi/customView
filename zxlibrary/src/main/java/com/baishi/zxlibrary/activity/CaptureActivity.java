package com.baishi.zxlibrary.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.baishi.zxlibrary.R;
import com.baishi.zxlibrary.view.EditDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;


/**
 * Initial the camera
 * <p>
 * 默认的二维码扫描Activity
 */
public class CaptureActivity extends AppCompatActivity implements View.OnClickListener {


    private double money;
    private EditDialog editDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        CaptureFragment captureFragment = new CaptureFragment();
        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_zxing_container, captureFragment).commit();
        captureFragment.setCameraInitCallBack(new CaptureFragment.CameraInitCallBack() {
            @Override
            public void callBack(Exception e) {
                if (e == null) {

                } else {
                    Log.e("TAG", "callBack: ", e);
                }
            }
        });
        Intent intent = getIntent();
        money = intent.getDoubleExtra("value", 0);
        int type = intent.getIntExtra("type", 0);

        ((TextView) findViewById(R.id.textViewRight)).setText(R.string.xiangCe);
        TextView textDes= findViewById(R.id.textDes);
        findViewById(R.id.imageBack).setOnClickListener(this);
        Button btnQuXiao = findViewById(R.id.btnQuXiao);
        btnQuXiao.setOnClickListener(this);
        findViewById(R.id.textViewRight).setOnClickListener(this);
        TextView textMoney = (TextView) findViewById(R.id.textMoney);
        if (type== 1){
            ((TextView) findViewById(R.id.textViewTitle)).setText("扫一扫查订单");
            textDes.setVisibility(View.GONE);
            textMoney.setVisibility(View.GONE);
            btnQuXiao.setVisibility(View.GONE);
        }else if (type== 2){
            ((TextView) findViewById(R.id.textViewTitle)).setText("绑定台卡");
            textDes.setVisibility(View.GONE);
            textMoney.setVisibility(View.GONE);
            btnQuXiao.setVisibility(View.GONE);
        }else {
            ((TextView) findViewById(R.id.textViewTitle)).setText("扫一扫收款");
            textDes.setVisibility(View.VISIBLE);
            textMoney.setVisibility(View.VISIBLE);
            btnQuXiao.setVisibility(View.VISIBLE);
            textMoney.setText("¥"+money);
        }
    }

    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String type, String result) {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
            bundle.putString(CodeUtils.RESULT_STRING, result);
            bundle.putString(CodeUtils.RESULT_CODE_TYPE, type);
            resultIntent.putExtras(bundle);
            CaptureActivity.this.setResult(RESULT_OK, resultIntent);
            CaptureActivity.this.finish();
        }

        @Override
        public void onAnalyzeFailed() {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
            bundle.putString(CodeUtils.RESULT_STRING, "");
            resultIntent.putExtras(bundle);
            CaptureActivity.this.setResult(RESULT_OK, resultIntent);
            CaptureActivity.this.finish();
        }
    };

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.imageBack) {
            finish();
        } else if (view.getId() == R.id.btnQuXiao) {
            if(editDialog!=null){
                editDialog.dismiss();
                editDialog=null;
            }
            editDialog = new EditDialog(this, "请输入收款码", "输入收款码", "确认", "取消", new EditDialog.ClickListenerInterface() {
                @Override
                public void doConfirm(String intro) {
                    Intent resultIntent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
                    bundle.putString(CodeUtils.RESULT_STRING, intro);
                    resultIntent.putExtras(bundle);
                    CaptureActivity.this.setResult(RESULT_OK, resultIntent);
                    CaptureActivity.this.finish();
                }

                @Override
                public void doCancel() {
                }
            });
            editDialog.show();

        } else if (view.getId() == R.id.textViewRight) {
            chooseHead();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PictureConfig.CHOOSE_REQUEST) {
            // 图片选择结果回调
            List<LocalMedia> selectList1 = PictureSelector.obtainMultipleResult(data);
            // 例如 LocalMedia 里面返回三种path
            // 1.media.getPath(); 为原图path
            // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
            // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
            // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
            String cutPath = selectList1.get(0).getCompressPath();
            CodeUtils.analyzeBitmap(cutPath, analyzeCallback);
        }
    }


    /**
     * des： 选择图片
     * author： ZhangJieBo
     * date： 2017/7/6 0006 下午 2:31
     */
    public void chooseHead() {
        PictureSelector.create(this)
                /*全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()*/
                .openGallery(PictureMimeType.ofImage())
                /* 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE*/
                .selectionMode(PictureConfig.SINGLE)
                /*是否显示拍照按钮 true or false*/
                .isCamera(false)
                /*拍照保存图片格式后缀,默认jpeg*/
                .imageFormat(PictureMimeType.JPEG)
                /*glide 加载图片大小 0~1之间 如设置 .glideOverride()无效*/
                .sizeMultiplier(0.5f)
                /*是否允许裁剪*/
//                .enableCrop(true)
                /*是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false*/
//                .showCropFrame(true)
                /*是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false*/
//                .showCropGrid(true)
//                .withAspectRatio(1, 1)
                /*圆形裁剪*/
//                .circleDimmedLayer(true)
                /*是否压缩 true or false*/
                .compress(true)
                /*裁剪压缩质量 默认90 int*/
                .cropCompressQuality(100)
                /*小于100kb的图片不压缩*/
                .minimumCompressSize(100)
                /*同步true或异步false 压缩 默认同步*/
                .synOrAsy(true)
                /*裁剪是否可旋转图片 true or false*/
//                .rotateEnabled(true)
                /*裁剪是否可放大缩小图片 true or false*/
//                .scaleEnabled(true)
//                .isDragFrame(true)
                /*结果回调onActivityResult code*/
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }
}
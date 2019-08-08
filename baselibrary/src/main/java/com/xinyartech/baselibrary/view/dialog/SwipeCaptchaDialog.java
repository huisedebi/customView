package com.xinyartech.baselibrary.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.Toast;

import com.mcxtzhang.captchalib.SwipeCaptchaView;

import com.xinyartech.baselibrary.R;
import com.xinyartech.baselibrary.utils.DpUtils;


public class SwipeCaptchaDialog extends Dialog {

    private Context context;
    private SwipeCaptchaView swipeCaptchaView;

    public interface OnPassListener{
        void pass();
    }

    OnPassListener onPassListener;

    public void setOnPassListener(OnPassListener onPassListener) {
        this.onPassListener = onPassListener;
    }

    public SwipeCaptchaDialog(Context context ) {
        super(context, R.style.dialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_swipesaptcha, null);
        setContentView(view);
        swipeCaptchaView = view.findViewById(R.id.swipeCaptchaView);
        SeekBar drag_sb = view.findViewById(R.id.drag_sb);

        drag_sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        drag_sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                swipeCaptchaView.setCurrentSwipeValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //随便放这里是因为控件
                drag_sb.setMax(swipeCaptchaView.getMaxSwipeValue());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("zxt", "onStopTrackingTouch() called with: seekBar = [" + seekBar + "]");
                swipeCaptchaView.matchCaptcha();
            }
        });

        swipeCaptchaView.setOnCaptchaMatchCallback(new SwipeCaptchaView.OnCaptchaMatchCallback() {
            @Override
            public void matchSuccess(SwipeCaptchaView swipeCaptchaView) {
                Toast.makeText(context, "验证成功", Toast.LENGTH_SHORT).show();
                if (onPassListener!=null){
                    onPassListener.pass();
                }
                dismiss();
                //swipeCaptcha.createCaptcha();
                drag_sb.setEnabled(false);
            }

            @Override
            public void matchFailed(SwipeCaptchaView swipeCaptchaView) {
                Log.d("zxt", "matchFailed() called with: swipeCaptchaView = [" + swipeCaptchaView + "]");
                Toast.makeText(context, "验证失败", Toast.LENGTH_SHORT).show();
                swipeCaptchaView.resetCaptcha();
                drag_sb.setProgress(0);
            }
        });
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        ViewGroup.LayoutParams layoutParams = swipeCaptchaView.getLayoutParams();
        layoutParams.height = (int) ((d.widthPixels * 0.8f- DpUtils.convertDpToPixel(10,context))*(116f/260f));
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    public void setImg(Drawable imgId){
        swipeCaptchaView.post(new Runnable() {
            @Override
            public void run() {
                swipeCaptchaView.setImageDrawable(imgId);
                swipeCaptchaView.createCaptcha();
            }
        });
    }

}
package com.xinyartech.baselibrary.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.xinyartech.baselibrary.R;


public class SingleBtnDialog extends Dialog {

    private Context context;
    private String title;
    private String confirmButtonText;
    private ClickListenerInterface clickListenerInterface;

    public interface ClickListenerInterface {

        void doWhat();

    }

    public SingleBtnDialog(Context context, String title, String confirmButtonText, ClickListenerInterface clickListenerInterface) {
        super(context, R.style.dialog);
        this.context = context;
        this.title = title;
        this.confirmButtonText = confirmButtonText;
        this.clickListenerInterface = clickListenerInterface;
    }

    public SingleBtnDialog(Context context, String title, String confirmButtonText) {
        super(context, R.style.dialog);
        this.context = context;
        this.title = title;
        this.confirmButtonText = confirmButtonText;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        init();
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_one_btn, null);
        setContentView(view);

        TextView tvTitle = (TextView) view.findViewById(R.id.textTitle);
        TextView tvConfirm = (TextView) view.findViewById(R.id.textQueDing);

        tvTitle.setText(title);
        tvConfirm.setText(confirmButtonText);

        tvConfirm.setOnClickListener(new clickListener());

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    public void setClicklistener(ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }

    private class clickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            int id = v.getId();
            if (id == R.id.textQueDing) {
                clickListenerInterface.doWhat();
                dismiss();
            }
        }

    }

}
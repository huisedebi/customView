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


public class TwoBtnDialog extends Dialog {

    private Context context;
    private String title;
    private String name = "提示";
    private String confirmButtonText;
    private String cacelButtonText;
    private ClickListenerInterface clickListenerInterface;
    boolean canClacle = true;

    public interface ClickListenerInterface {

        public void doConfirm();

        public void doCancel();
    }

    public TwoBtnDialog(Context context, String title, String confirmButtonText, String cacelButtonText, ClickListenerInterface clickListenerInterface) {
        super(context, R.style.dialog);
        this.context = context;
        this.title = title;
        this.confirmButtonText = confirmButtonText;
        this.cacelButtonText = cacelButtonText;
        this.clickListenerInterface=clickListenerInterface;
    }

    public TwoBtnDialog(Context context,boolean canClacle, String title, String confirmButtonText, String cacelButtonText) {
        super(context, R.style.dialog);
        this.context = context;
        this.canClacle = canClacle;
        this.title = title;
        this.confirmButtonText = confirmButtonText;
        this.cacelButtonText = cacelButtonText;
    }

    public TwoBtnDialog(Context context, String name , String title, String confirmButtonText, String cacelButtonText, ClickListenerInterface clickListenerInterface) {
        super(context, R.style.dialog);
        this.context = context;
        this.title = title;
        this.name = name;
        this.confirmButtonText = confirmButtonText;
        this.cacelButtonText = cacelButtonText;
        this.clickListenerInterface=clickListenerInterface;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void setClickListenerInterface(ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_two_btn, null);
        setContentView(view);

        TextView tvTitle = (TextView) view.findViewById(R.id.textTitle);
        TextView tvConfirm = (TextView) view.findViewById(R.id.textQueDing);
        TextView tvCancel = (TextView) view.findViewById(R.id.textQuXiao);
        TextView textName = (TextView) view.findViewById(R.id.textName);

        textName.setText(name);

        tvTitle.setText(title);
        tvConfirm.setText(confirmButtonText);
        tvCancel.setText(cacelButtonText);

        tvConfirm.setOnClickListener(new clickListener());
        tvCancel.setOnClickListener(new clickListener());

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    private class clickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            int id = v.getId();
            if (id==R.id.textQueDing){
                clickListenerInterface.doConfirm();
                if (canClacle){
                    dismiss();
                }
            }else if (id==R.id.textQuXiao){
                clickListenerInterface.doCancel();
                if (canClacle){
                    dismiss();
                }
            }
        }

    };

}
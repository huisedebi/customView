package com.baishi.zxlibrary.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baishi.zxlibrary.R;


public class EditDialog extends Dialog {

    private Context context;
    private String title;
    private String confirmButtonText;
    private String cacelButtonText;
    private String hint;
    private ClickListenerInterface clickListenerInterface;
    private EditText editIntro;

    public interface ClickListenerInterface {

        void doConfirm(String intro);

        void doCancel();
    }

    public EditDialog(Context context, String title, String hint, String confirmButtonText, String cacelButtonText, ClickListenerInterface clickListenerInterface) {
        super(context, R.style.dialog);
        this.clickListenerInterface = clickListenerInterface;
        this.context = context;
        this.title = title;
        this.confirmButtonText = confirmButtonText;
        this.cacelButtonText = cacelButtonText;
        this.hint = hint;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_edit, null);
        setContentView(view);

        TextView tvTitle = (TextView) view.findViewById(R.id.textTitle);
        editIntro = view.findViewById(R.id.editIntro);
        TextView tvConfirm = (TextView) view.findViewById(R.id.textQueDing);
        TextView tvCancel = (TextView) view.findViewById(R.id.textQuXiao);
        editIntro.setHint(hint);
        tvTitle.setText(title);
        tvConfirm.setText(confirmButtonText);
        tvCancel.setText(cacelButtonText);

        tvConfirm.setOnClickListener(new clickListener());
        tvCancel.setOnClickListener(new clickListener());

        Window dialogWindow = getWindow();
//        dialogWindow.setGravity(Gravity.BOTTOM);
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
                if (TextUtils.isEmpty(editIntro.getText().toString().trim())) {
                    Toast.makeText(context, "请输入内容", Toast.LENGTH_SHORT).show();
                    return;
                }
                dismiss();
                clickListenerInterface.doConfirm(editIntro.getText().toString().trim());
            } else if (id == R.id.textQuXiao) {
                clickListenerInterface.doCancel();
                dismiss();
            }
        }

    }

}
package com.jinq.widgetsx.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jinq.widgetsx.R;
import com.jinq.widgetsx.util.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlertDialog extends Dialog {

    @BindView(R.id.btnCancel)
    TextView mCancel;
    @BindView(R.id.btnConfirm)
    TextView mConfirm;
    @BindView(R.id.dialogTitle)
    TextView mTitle;
    @BindView(R.id.dialogContent)
    TextView mContent;

    private ButtonClickListener mListener;

    public AlertDialog(@NonNull Context context, ButtonClickListener listener) {
        this(context, R.style.DialogStyle);
        this.mListener = listener;
    }

    public AlertDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_alert, null);
        setContentView(view);
        ButterKnife.bind(this, view);

    }

    @OnClick({R.id.btnCancel, R.id.btnConfirm})
    public void handleClick(View view) {
        switch (view.getId()) {
            case R.id.btnCancel:
                this.dismiss();
                //TODO
//                mListener.onCancel();
                break;
            case R.id.btnConfirm:
                mListener.onConfirm();
                this.dismiss();
                break;
        }
    }

    public interface ButtonClickListener {
        void onCancel();

        void onConfirm();
    }

    public void setConfimText(String text){
        if(!StringUtil.isEmpty(text)){
            mConfirm.setText(text);
        }
    }
    public void setCancelText(String text){
        if(!StringUtil.isEmpty(text)){
            mCancel.setText(text);
        }
    }
    public void setTitleText(String text){
        if(!StringUtil.isEmpty(text)){
            mTitle.setText(text);
        }
    }
    public void setContentText(String text){
        if(!StringUtil.isEmpty(text)){
            mContent.setText(text);
        }
    }
}

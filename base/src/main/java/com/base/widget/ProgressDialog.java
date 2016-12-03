package com.base.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bm.base.R;

/**
 * Create by 俞智威
 * on 2015-09-09
 * 10:37
 * Procedure Explain:进度对话框
 */
public class ProgressDialog extends Dialog {

    public static final String TAG = "ProgressDialog";

    /**
     * 是否结束当前 activity  如果设置为true
     * 对话框弹出时  点击其他区域对话框不消失，点击返回键直接结束当前activity
     */
    private boolean isFinishActivity;
    private Context context;

    public ProgressDialog(Context context) {
        this(context, false);
    }

    public ProgressDialog(Context context, boolean isFinishActivity) {
        super(context, R.style.AlertDialogStyle);
        this.context = context;
        this.isFinishActivity = isFinishActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_layout);

        setCanceledOnTouchOutside(isFinishActivity);

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && isFinishActivity) {

            ((Activity) context).finish();

        }

        return super.onKeyDown(keyCode, event);
    }
}


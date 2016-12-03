package com.base.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.bm.base.R;

/**
 * Created by 俞智威
 * on 2015-12-24.
 * 17:49
 * Procedure Explain:仿ios dialog
 */
public class IOSDialog {
    private Context context;
    public Dialog dialog;
    private LinearLayout lLayout_bg;
    private TextView txt_title;
    private TextView txt_msg;
    public EditText edit_msg;
    private Button btn_left;
    private Button btn_right;
    private ImageView img_line;
    private Display display;
    private boolean showTitle = false;
    private boolean showMsg = false;
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;
    private boolean showEdit = false;

    public IOSDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public IOSDialog builder() {
        // 仿ios Dialog的布局文件
        View view = LayoutInflater.from(context).inflate(
                R.layout.view_alertdialog, null);

        // 仿ios Dialog的控件
        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_title.setVisibility(View.GONE);
        txt_msg = (TextView) view.findViewById(R.id.txt_msg);
        txt_msg.setVisibility(View.GONE);
        edit_msg = (EditText) view.findViewById(R.id.edit_msg);
        edit_msg.setVisibility(View.GONE);
        btn_left = (Button) view.findViewById(R.id.btn_left);
        btn_left.setTextColor(context.getResources().getColor(R.color.text_color));
        btn_left.setVisibility(View.GONE);
        btn_right = (Button) view.findViewById(R.id.btn_right);
        btn_right.setTextColor(context.getResources().getColor(R.color.text_color));
        btn_right.setVisibility(View.GONE);
        img_line = (ImageView) view.findViewById(R.id.img_line);
        img_line.setVisibility(View.GONE);

        // 仿ios Dialog的初始化，设置style
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);

        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.85), LayoutParams.WRAP_CONTENT));

        return this;
    }

    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public IOSDialog setTitle(String title) {
        showTitle = true;
        if ("".equals(title)) {
            txt_title.setText("提示");
        } else {
            txt_title.setText(title);
        }
        return this;
    }

    /**
     * 设置标题风格
     *
     * @param style
     * @return
     */
    public IOSDialog setTitleStyle(int style) {
        if (txt_title != null) {
            txt_title.setTextAppearance(context, style);
        }
        return this;
    }

    /**
     * 设置文字内容
     *
     * @param msg
     * @return
     */
    public IOSDialog setMsg(String msg) {
        showMsg = true;
        if ("".equals(msg)) {
            txt_msg.setText("确认");
        } else {
            txt_msg.setText(msg);
        }
        return this;
    }

    /**
     * 设置内容风格
     *
     * @param style
     * @return
     */
    public IOSDialog setMsgStyle(int style) {
        if (txt_msg != null) {
            txt_msg.setTextAppearance(context, style);
        }
        return this;
    }

    /**
     * 设置文本框
     * @param msg
     * @return
     */
    public IOSDialog setEdit(String msg) {
        showEdit = true;
        edit_msg.setHint(msg);
        return this;
    }

    /**
     * 设置能否取消
     * @param cancel
     * @return
     */
    public IOSDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    /**
     * 设置右边的按钮
     *
     * @param text
     * @param listener
     * @return
     */
    public IOSDialog setRightButton(String text,
                                    final OnClickListener listener) {
        showPosBtn = true;
        if ("".equals(text)) {
            btn_right.setText("确认");
        } else {
            btn_right.setText(text);
        }
        btn_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(v);
                } else
                    dialog.dismiss();

            }
        });
        return this;
    }

    /**
     * 设置左边的按钮
     *
     * @param text //     * @param listener
     * @return
     */
    public IOSDialog setLeftButton(String text, final OnClickListener listener) {
        showNegBtn = true;
        if ("".equals(text)) {
            btn_left.setText("确认");
        } else {
            btn_left.setText(text);
        }
        btn_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(v);
                }
                dialog.dismiss();
            }
        });
        return this;
    }

    /**
     * 设置右边按钮的文字风格
     * @param style
     * @return
     */
    public IOSDialog setRightButtonStyle(int style) {
        if (btn_right != null) {
            btn_right.setTextAppearance(context, style);
        }
        return this;
    }
    /**
     * 设置左边按钮的文字风格
     * @param style
     * @return
     */
    public IOSDialog setLeftButtonStyle(int style) {
        if (btn_left != null) {
            btn_left.setTextAppearance(context, style);
        }
        return this;
    }

    private void setLayout() {
        if (!showTitle && !showMsg) {
            txt_title.setText("提示");
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showTitle) {
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showMsg) {
            txt_msg.setVisibility(View.VISIBLE);
        }

        if (showEdit) {
            edit_msg.setVisibility(View.VISIBLE);
        }

        if (!showPosBtn && !showNegBtn) {
            btn_right.setText("确认");
            btn_right.setVisibility(View.VISIBLE);
            btn_right.setBackgroundResource(R.drawable.alertdialog_single_selector);
            btn_right.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        if (showPosBtn && showNegBtn) {
            btn_right.setVisibility(View.VISIBLE);
            btn_right.setBackgroundResource(R.drawable.alertdialog_right_selector);
            btn_left.setVisibility(View.VISIBLE);
            btn_left.setBackgroundResource(R.drawable.alertdialog_left_selector);
            img_line.setVisibility(View.VISIBLE);
        }

        if (showPosBtn && !showNegBtn) {
            btn_right.setVisibility(View.VISIBLE);
            btn_right.setBackgroundResource(R.drawable.alertdialog_single_selector);
        }

        if (!showPosBtn && showNegBtn) {
            btn_left.setVisibility(View.VISIBLE);
            btn_left.setBackgroundResource(R.drawable.alertdialog_single_selector);
        }
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public void show() {
        setLayout();
        dialog.show();
    }
}

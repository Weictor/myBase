package com.base.util;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by 俞智威
 * on 2015-12-04.
 * 15:52
 * Procedure Explain:
 */
public class ToastTools {

    private static Toast toast;

    /**
     * 多次点击只显示一次的toast
     *
     * @param context
     * @param message
     */
    public static void showOnce(Context context, String message) {
        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 显示长时间
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 显示短时间
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


    /**
     * 自定义只有字的toast，可以设置显示的位置
     *
     * @param activity
     * @param msg
     * @param gravity
     */
    public static void MyToast(Activity activity, String msg, int gravity) {
        toast = Toast.makeText(activity,
                msg, Toast.LENGTH_SHORT);
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }

    /**
     * 自定义有字和图片的toast，可以设置显示的位置
     *
     * @param activity
     * @param msg
     * @param drawable
     * @param bgDrawable
     * @param gravity
     */
    public static void MyToastWithPic(Activity activity, String msg, int drawable, int bgDrawable, int gravity) {
        toast = Toast.makeText(activity, msg, Toast.LENGTH_SHORT);
        toast.setGravity(gravity, 0, 0);
        LinearLayout toastView = (LinearLayout) toast.getView();
        ImageView imageCodeProject = new ImageView(activity);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(DensityUtils.dip2px(activity, 60), DensityUtils.dip2px(activity, 20), DensityUtils.dip2px(activity, 60), DensityUtils.dip2px(activity, 20));
        imageCodeProject.setLayoutParams(layoutParams);
        if (drawable != 0) {
            imageCodeProject.setImageResource(drawable);
        }
        if (bgDrawable != 0) {
            toastView.setBackgroundResource(bgDrawable);
        }
        toastView.addView(imageCodeProject, 0);
        toast.show();
    }

}

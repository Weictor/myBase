package com.base.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Create by 俞智威
 * on 2016-11-18
 * 15:47
 * Procedure Explain: intent跳转帮助类
 */

public class IntentUtil {

    /**
     * 跳转一个新界面的Intent
     *
     * @param context
     * @param cls
     * @return Intent
     */
    public static void StartIntent(Context context, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        context.startActivity(intent);
    }

    /**
     * 跳转一个新界面的Intent
     *
     * @param context
     * @param cls
     * @return Intent
     */
    public static void StartIntent(Context context, Class<?> cls, int flag) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        intent.setFlags(flag);
        context.startActivity(intent);
    }

    /**
     * 跳转相册
     *
     * @return Intent
     */
    public Intent getImageIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        return intent;
    }

    /**
     * 裁剪图片方法实现
     * @param uri
     * @param cutWidth
     * @param cutHigh
     * @return
     */
    public Intent startPhotoZoom(Uri uri,int cutWidth,int cutHigh) {
        if(cutWidth<=0){
            cutWidth=150;
        }
        if(cutHigh<=0){
            cutHigh=150;
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", cutWidth);
        intent.putExtra("outputY", cutHigh);
        intent.putExtra("return-data", true);
        // cls.startActivityForResult(intent, 3);
        return intent;
    }
}

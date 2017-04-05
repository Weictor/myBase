package com.base.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

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
     * 跳转一个新界面的Intent,带参数
     *
     * @param context
     * @param clazz
     * @param extras
     */
    public static void gotoActivityWithExtras(Context context, Class<?> clazz, Bundle extras) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(extras);
        context.startActivity(intent);

    }

}

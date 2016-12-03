package com.base.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by 俞智威
 * on 2016-4-22.
 * 12:18
 * Procedure Explain:调用系统的工具类
 */
public class SystemUsedUtils {

    /**
     * 分享文本
     *
     * @param ctx
     * @param text
     */
    public static void shareText(Context ctx, String text) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        sendIntent.setType("text/plain");
        ctx.startActivity(Intent.createChooser(sendIntent, "分享至"));
    }

    /**
     * 分享图片
     *
     * @param ctx
     * @param uri
     */
    public static void shareImage(Context ctx, Uri uri) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_STREAM, uri);
        sendIntent.setType("image/jpeg");
        ctx.startActivity(Intent.createChooser(sendIntent, "分享至"));
    }

    /**
     * 分享图片集合
     *
     * @param ctx
     * @param uris
     */
    public static void shareImageList(Context ctx, ArrayList<Uri> uris) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
        sendIntent.putExtra(Intent.EXTRA_STREAM, uris);
        sendIntent.setType("image/*");
        ctx.startActivity(Intent.createChooser(sendIntent, "分享至"));
    }

    /**
     * 调拨号界面
     *
     * @param ctx
     * @param tel 电话号
     */
    public static void phoneUI(Context ctx, String tel) {

        Intent intent = new Intent(Intent.ACTION_DIAL,
                Uri.parse(String.format("tel:%s", tel)));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);
    }
}

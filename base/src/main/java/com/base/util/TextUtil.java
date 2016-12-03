package com.base.util;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;

/**
 * Created by 俞智威
 * on 2015-12-26.
 * 17:18
 * Procedure Explain:字体设置类
 */

public class TextUtil {

    /**
     * 截取string
     *
     * @param content 文本内容
     * @param begin   开始位置
     * @param end     结束位置
     * @return
     */
    public static String getString(String content, int begin, int end) {
        return content.substring(begin, end);
    }

    /**
     * 获取不一样的style
     *
     * @param context
     * @param style
     * @param content
     * @param begin
     * @param end
     * @return
     */
    public static CharSequence getDifferentSize(Context context, int style, String content, int begin, int end) {
        SpannableString sp = new SpannableString(content);
        sp.setSpan(new TextAppearanceSpan(context, style), begin, end, Spanned.SPAN_COMPOSING);
        return sp;
    }

    /**
     * 设置超链接
     *
     * @param content 文本内容
     * @param url     url内容
     * @param begin   开始位置
     * @param end     结束位置
     * @return
     */
    public static CharSequence getUrlType(String content, String url, int begin, int end) {
        SpannableString sp = new SpannableString(content);
        sp.setSpan(new URLSpan(url), begin, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sp;
    }

    /**
     * 设置高亮样式一
     *
     * @param content 文本内容
     * @param color   字体颜色
     * @param begin   开始位置
     * @param end     结束位置
     * @return
     */
    public static CharSequence getHighLightType(String content, int color, int begin, int end) {
        SpannableString sp = new SpannableString(content);
        sp.setSpan(new BackgroundColorSpan(color), begin, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sp;
    }

    /**
     * 设置高亮样式二
     *
     * @param content 文本内容
     * @param color   字体颜色
     * @param begin   开始位置
     * @param end     结束位置
     * @return
     */
    public static CharSequence getHighLightsType(String content, int color, int begin, int end) {
        SpannableString sp = new SpannableString(content);
        sp.setSpan(new ForegroundColorSpan(color), begin, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return sp;
    }

    /**
     * 设置斜体
     *
     * @param content 文本内容
     * @param begin   开始位置
     * @param end     结束位置
     * @return
     */
    public static CharSequence getItalicType(String content, int begin, int end) {
        SpannableString sp = new SpannableString(content);
        sp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC), begin, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return sp;
    }

    /**
     * 设置下划线
     *
     * @param content 文本内容
     * @param begin   开始位置
     * @param end     结束位置
     * @return
     */
    public static CharSequence getUnderlineType(String content, int begin, int end) {
        SpannableString sp = new SpannableString(content);
        sp.setSpan(new UnderlineSpan(), begin, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sp;
    }

    /**
     * 半角转全角
     *
     * @param input String.
     * @return 全角字符串.
     */
    public static String ToSBC(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') {
                c[i] = '\u3000';
            } else if (c[i] < '\177') {
                c[i] = (char) (c[i] + 65248);

            }
        }
        return new String(c);
    }

    /**
     * 全角转半角
     *
     * @param input String.
     * @return 半角字符串
     */
    public static String ToDBC(String input) {


        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '\u3000') {
                c[i] = ' ';
            } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                c[i] = (char) (c[i] - 65248);

            }
        }
        String returnString = new String(c);

        return returnString;
    }


    /**
     * 加密算法
     *
     * @param content   加密前的内容
     * @param pwdString 密码
     * @return 加密后的内容
     */
    public static String encrypt(String content, String pwdString) {
        char[] p = pwdString.toCharArray();
        int n = p.length;
        char[] c = content.toCharArray();
        int m = c.length;
        for (int i = 0; i < m; i++) {
            int pwd = c[i] + p[i % n];
            c[i] = (char) pwd;
        }
        return new String(c);
    }

    /**
     * 解密算法
     *
     * @param content   加密后的内容
     * @param pwdString 密码
     * @return 加密前的内容
     */
    public static String decrypt(String content, String pwdString) {
        char[] p = pwdString.toCharArray();
        int n = p.length;
        char[] c = content.toCharArray();
        int m = c.length;
        for (int i = 0; i < m; i++) {
            int pwd = c[i] - p[i % n];
            c[i] = (char) pwd;
        }
        return new String(c);
    }
}

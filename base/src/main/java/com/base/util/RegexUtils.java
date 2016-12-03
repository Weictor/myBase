package com.base.util;

import android.text.Editable;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 俞智威
 * on 2015-12-04.
 * 13:27
 * Procedure Explain:判断输入内容格式的工具类
 */
public class RegexUtils {

    public static final String TAG = "RegexUtils";

    public static final int PASSWORD_MIN_LENGTH = 6;//密码的最小长度
    public static final int PASSWORD_MAX_LENGTH = 16;//密码的最大长度

    /**
     * 验证身份证号
     */
    public static boolean testIdCard(String s) {

        return new IdCard().isValidate18Idcard(s);

    }

    /**
     * 验证密码格式是否错误
     *
     * @param password
     * @return
     */
    public static boolean passwordIsLegal(EditText password) {
        int length = password.getText().toString().length();
        if (length < PASSWORD_MIN_LENGTH) {
            ToastTools.showShort(password.getContext(), "密码小于" + PASSWORD_MIN_LENGTH + "位");
            return false;
        }
        if (length > PASSWORD_MAX_LENGTH) {
            ToastTools.showShort(password.getContext(), "密码大于" + PASSWORD_MAX_LENGTH + "位");
            return false;
        }
        return true;
    }


    /**
     * 判断 email地址是否合法
     *
     * @param input 输入内容
     * @return true, 合法;false,不合法.
     */
    public static boolean emailsLegal(Editable input) {
        char[] arrChar = input.toString().toCharArray();
        int first = getIndexFromArrays(arrChar, '@');
        if (first == -1 || first == 0) {
            return false;
        }
        int second = getIndexFromArrays(arrChar, '.');
        if (second == -1 || second == arrChar.length - 1) {
            return false;
        }
        if (first + 1 >= second) {
            return false;
        }
        return true;
    }

    /**
     * 获取 字符在  字符数组中的坐标
     *
     * @param arr 字符数组
     * @param c   字符
     * @return 坐标
     */
    public static int getIndexFromArrays(char[] arr, char c) {
        if (arr.length == 0) {
            return -1;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == c) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 验证手机号码
     *
     * @param phone 要验证的手机号
     * @return 验证结果
     */
    public static boolean isPhoneNumber(String phone) {
        Pattern p = Pattern.compile("^[1][34578][0-9]{9}$"); // 验证手机号
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    /**
     * 判断输入的字符串是否为纯汉字
     *
     * @param Chinese 传入的字符窜
     * @return 如果是纯汉字返回true, 否则返回false
     */
    public static boolean isChinese(String Chinese) {
        Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
        return pattern.matcher(Chinese).matches();
    }

    /**
     * 判断是否为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断是否为浮点数，包括double和float
     *
     * @param str 传入的字符串
     * @return 是浮点数返回true, 否则返回false
     */
    public static boolean isDouble(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 验证密码
     *
     * @param pass 密码
     * @return 结果
     */
    public static boolean isPassword(String pass) {
        Pattern p = Pattern.compile("^[a-zA-Z0-9]{6,16}+$"); // 验证密码
        Matcher m = p.matcher(pass);
        return m.matches();
    }

    /**
     * 判断是否是数字
     *
     * @return 结果
     */
    public static boolean isNumber(String str) {
        return str.matches("[0-9]*");
    }

    /**
     * 判断string 是否包含非空格字符
     *
     * @param text string
     * @return true，有非空格字符；false，全是空格
     */
    public static boolean isTextNotAllSpace(String text) {
        if (text == null) {
            return false;
        }
        if (text.length() == 0) {
            return false;
        }
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != ' ') {
                return true;
            }
        }
        return false;
    }

}

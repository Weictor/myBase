package com.base.util;

import android.util.Log;

/**
 * Created by 俞智威
 * on 2016-02-03.
 * 16:48
 * Procedure Explain:打印工具类
 */
public class LogCat {

    /**
     * 是否为开发者模式(开发模式打印LOG,非开发模式不打印LOG)
     */
    private static boolean isLog = true;

    public static final String TAG = "LogCat";

    private static final String EXCEPTION_MESSAGE = "发现问题了：";

    public static void i(String message) {

        i(TAG, message);

    }

    public static void i(String tag, String message) {

        if (isLog) {

            if (message == null) {
                Log.i(tag, "null");
            } else {
                Log.i(tag, message);
            }
        }
    }

    public static void i(int message) {

        i(TAG, message);

    }

    public static void i(String tag, int message) {

        if (isLog) {
            Log.i(tag, Integer.toString(message));
        }

    }

    public static void i(short message) {

        i(TAG, message);

    }

    public static void i(String tag, short message) {

        if (isLog) {
            Log.i(tag, Short.toString(message));
        }

    }

    public static void i(long message) {

        i(TAG, message);

    }

    public static void i(String tag, Long message) {

        if (isLog) {
            Log.i(tag, Long.toString(message));
        }

    }


    public static void i(byte message) {

        i(TAG, message);

    }

    public static void i(String tag, byte message) {

        if (isLog) {
            Log.i(tag, Byte.toString(message));
        }

    }

    public static void i(char message) {

        i(TAG, message);

    }

    public static void i(String tag, char message) {

        if (isLog) {
            Log.i(tag, Character.toString(message));
        }

    }


    public static void i(float message) {

        i(TAG, message);

    }

    public static void i(String tag, float message) {

        if (isLog) {
            Log.i(tag, Float.toString(message));
        }

    }

    public static void i(double message) {

        i(TAG, message);

    }

    public static void i(String tag, double message) {

        if (isLog) {
            Log.i(tag, Double.toString(message));
        }

    }

    public static void i(boolean message) {

        i(TAG, message);

    }

    public static void i(String tag, boolean message) {

        if (isLog) {
            Log.i(tag, Boolean.toString(message));
        }

    }

    public static void i(Object message) {

        i(TAG, message);


    }

    public static void i(String tag, Object message) {


        if (isLog) {
            Log.i(tag, String.valueOf(message));
        }

    }


    public static void e(String tag, String message, Exception e) {

        if (isLog) {
            Log.e(tag, message, e.getCause());
        }

    }

    public static void e(String message, Exception e) {

        e(TAG, message, e);

    }

    public static void e(Exception e) {

        e(EXCEPTION_MESSAGE, e);

    }

}

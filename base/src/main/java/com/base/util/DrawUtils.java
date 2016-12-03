package com.base.util;

import android.graphics.Paint;

/**
 * 创建者: 李政
 * 创建日期: 2014-12-01
 * 创建时间: 09:45
 * DrawUtils: view 手动画图辅助工具
 *
 * @author lizheng
 * @version 1.0
 */
public class DrawUtils {

    public static final String TAG = "DrawUtils";

    private DrawUtils() {
    }

    /**
     * 算出文字真实基准线
     */
    public static int getTextBaseline(int top, int bottom, Paint.FontMetricsInt fontMetrics) {

        return top + (bottom - top - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;

    }

}

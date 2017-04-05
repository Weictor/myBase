package com.base.util;

import android.graphics.Paint;

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

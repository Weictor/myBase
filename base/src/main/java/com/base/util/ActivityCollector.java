package com.base.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by 俞智威
 * on 2015-11-08
 * 16:10
 * Procedure Explain: Activity堆栈管理工具，提供一键清除所有activity
 */

public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<Activity>();

    /**
     * 添加activity到集合
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        if (!activities.contains(activity)) {
            activities.add(activity);
        }
    }

    /**
     * 到集合中删除activity
     *
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        if (activities.contains(activity)) {
            activities.remove(activity);
        }
    }

    /**
     * 清空集合
     */
    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}

package com.base.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * Created by 俞智威
 * on 2016-4-26.
 * 17:18
 * Procedure Explain:与系统有关的工具类
 */
public class SystemUtils {

    /**
     * 获取设备的制造商
     *
     * @return 设备制造商
     */
    public static String getDeviceManufacture() {
        return android.os.Build.MANUFACTURER;
    }

    /**
     * 获取设备名称
     *
     * @return 设备名称
     */
    public static String getDeviceName() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取设备号
     *
     * @param context
     * @return
     */
    public static String getDeviceIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager == null || TextUtils.isEmpty(telephonyManager.getDeviceId())) {
            return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        } else {
            return telephonyManager.getDeviceId();
        }
    }

    /**
     * 封装的打开应用单击事件
     */
    public static class StartAppOnClickListener implements View.OnClickListener {

        private Activity activity;
        private String packageName;



        public StartAppOnClickListener(Activity activity, String packageName) {
            this.activity = activity;
            this.packageName = packageName;
        }

        @Override
        public void onClick(View v) {
            startApp(activity, packageName);
        }

    }

    /**
     * @param packageName 要打开应用的包名
     */
    public static void startApp(Activity activity , String packageName){
        activity.startActivity(activity.getPackageManager().getLaunchIntentForPackage(packageName));
    }

    /**
     * 获取应用的版本号
     *
     * @param context
     * @return
     */
    public static String getAppVersion(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取其他应用版本号
     *
     * @param context     上下文
     * @param packageName 包名
     * @return 版本号
     */
    public static String getAppVersion(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return "";
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
    }

    /**
     * 判断权限是否已获取
     *
     * @param context    当前
     * @param permission 权限名称 manifest文件中的引号内容
     * @return true, 已获取;false,未获取
     */
    public static boolean isPermissionAccess(Context context, String permission) {
        return (context.checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
    }

    /**
     * 判断app 是否已经安装到设备上
     * 一般用户在分享模块判断提供分享接口的app是否安装
     *
     * @param context     this
     * @param packageName app的包名
     * @return true, installed;false,not yet.
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> listInfo = packageManager.getInstalledPackages(0);
        Log.i("", "list.size = " + listInfo.size());
        if (listInfo != null) {
            for (int i = 0; i < listInfo.size(); i++) {
                Log.i("", "packageName = " + listInfo.get(i).packageName);
                if (listInfo.get(i).packageName.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

}

package com.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.TextView;

import com.base.volley.HttpConnect;

import org.litepal.LitePalApplication;


/**
 * Created by 俞智威
 * on 2016-02-01.
 * 09:03
 * Procedure Explain:封装的Application，继承于LitePalApplication
 */
public class BaseApplication extends LitePalApplication {

    public static final String TAG = "BaseApplication";
    public static final String BASE_SETTING_KEY = TAG;

    public static final String KEY_VERSION = "version";
    private boolean isFirstStart;


    @Override
    public void onCreate() {
        super.onCreate();
        HttpConnect.init(getApplicationContext());
    }

    /**
     * 判断此版本是否第一次登录
     */
    public boolean isFirstStart() {

        SharedPreferences sharedPreferences = getSharedPreferences(BASE_SETTING_KEY, Context.MODE_PRIVATE);
        int oldVersion = sharedPreferences.getInt(KEY_VERSION, -1);

        PackageInfo packInfo = null;
        PackageManager packageManager = getPackageManager();
        try {
            packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        int version = packInfo.versionCode;

        if (version != oldVersion) {

            isFirstStart = false;
            sharedPreferences.edit().putInt(KEY_VERSION, version).commit();


        } else {
            isFirstStart = true;
        }

        return isFirstStart;

    }

    public void setFirstStart(boolean isFirstStart) {
        this.isFirstStart = isFirstStart;
    }
}

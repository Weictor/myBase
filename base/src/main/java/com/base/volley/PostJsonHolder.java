package com.base.volley;

import android.app.Activity;
import android.app.Dialog;


import com.base.interfaces.ErrorCallBack;
import com.base.interfaces.ShowData;
import com.base.util.LogCat;

import java.lang.reflect.Field;

/**
 * Create by 俞智威
 * on 2015-11-05
 * 15:27
 * Procedure Explain:网络请求配置
 */
public class PostJsonHolder<T> {

    private Activity activity;
    private String method;
    private ShowData<T> showData;
    private Dialog dialog;
    private String errorMessage;
    private ErrorCallBack errorCallBack;
    private Object object;

    public Activity getActivity() {
        return activity;
    }

    public PostJsonHolder<T> setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public PostJsonHolder<T> setMethod(String method) {
        this.method = method;
        return this;
    }

    public ShowData<T> getShowData() {
        return showData;
    }

    public PostJsonHolder<T> setShowData(ShowData<T> showData) {
        this.showData = showData;
        return this;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public PostJsonHolder<T> setDialog(Dialog dialog) {
        this.dialog = dialog;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public PostJsonHolder<T> setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public ErrorCallBack getErrorCallBack() {
        return errorCallBack;
    }

    public PostJsonHolder<T> setErrorCallBack(ErrorCallBack errorCallBack) {
        this.errorCallBack = errorCallBack;
        return this;
    }

    public PostJsonHolder<T> setObject(Object object) {
        this.object = object;
        return this;
    }


    /**
     * 获取请求地址
     *
     * @return 地址
     */
    public String getUrl(String ip) {
        return ip + getMethod();
    }

    public String getRequestBody() {
        String res = "";
        Field fields[] = object.getClass().getDeclaredFields();// 获得对象所有属性
        if (fields.length != 0) {
            for (int i = 0; i < fields.length; i++) {
                try {
                    fields[i].setAccessible(true);
                    res += fields[i].getName();
                    res += "=";
                    res += fields[i].get(object);
                    res += "&";
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return res.substring(0, res.length() - 1);
        } else
            return res;
    }

}

package com.base.volley;

import android.app.Activity;
import android.app.Dialog;

import com.android.volley.Request;
import com.base.interfaces.ErrorCallBack;
import com.base.interfaces.GetData;
import com.base.interfaces.Parser;
import com.base.util.LogCat;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by 俞智威
 * on 2016-02-13.
 * 09:10
 * Procedure Explain: volley的参数持有类
 */

public class Holder<T> {

    Activity activity;

    Parser<T> parser;

    Map<String, String> paramMap = new HashMap<String, String>();

    String url;

    int httpType = Request.Method.POST;

    GetData<T> getData;

    Dialog dialog;

    String errorMessage;

    int tag;

    ErrorCallBack errorCallBack;

    Object object;

    public int getTag() {
        return tag;
    }

    public Holder<T> setTag(int tag) {
        this.tag = tag;
        return this;
    }

    public Holder<T> setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public Holder<T> setParser(Parser<T> parser) {
        this.parser = parser;
        return this;
    }

    public Holder setParams(Map<String, String> paramMap) {
        this.paramMap = paramMap;
        return this;
    }

    public Holder<T> setParam(String key, String value) {
        paramMap.put(key, value);
        return this;
    }

    public Holder<T> setErrorCallBack(ErrorCallBack errorCallBack) {
        this.errorCallBack = errorCallBack;
        return this;
    }

    public Holder<T> setUrl(String url) {
        this.url = url;
        return this;
    }

    public Holder<T> setHttpType(int httpType) {
        this.httpType = httpType;
        return this;
    }

    public Holder<T> setDialog(Dialog dialog) {
        this.dialog = dialog;
        return this;
    }

    public Holder<T> setGetData(GetData<T> getData) {
        this.getData = getData;
        return this;
    }

    public Holder<T> setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }


    /**
     * 获取请求地址
     *
     * @return 地址
     */
    public String getUrl() {
        return url;
    }

    public String getRequestBody() {
        StringBuffer stringBuffer = new StringBuffer();
        Field fields[] = object.getClass().getDeclaredFields();// 获得对象所有属性
        if (fields.length != 0) {
            for (int i = 0; i < fields.length; i++) {
                try {
                    fields[i].setAccessible(true);
                    stringBuffer.append(fields[i].getName());
                    stringBuffer.append("=");
                    stringBuffer.append(fields[i].get(object));
                    stringBuffer.append("&");
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return stringBuffer.substring(0, stringBuffer.length() - 1);
        } else
            return stringBuffer.toString();
    }

    /**
     * 输出参数值
     *
     * @return
     */
    public Holder<T> logParamsValues() {
        StringBuffer sb = new StringBuffer();
        Iterator it = paramMap.entrySet().iterator();//返回set的迭代器 装的key值
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            sb.append(entry.getKey() + "=" + entry.getValue() + ",");
        }
        LogCat.i("post参数:{" + sb.toString() + "}");
        return this;
    }

    /**
     * 一键传参
     *
     * @param object postBean 的对象
     * @return
     */
    public Holder<T> setPostValues(Object object) {
        this.object = object;
        Field fields[] = object.getClass().getDeclaredFields();// 获得对象所有属性
        if (fields.length != 0) {
            for (int i = 0; i < fields.length; i++) {
                try {
                    fields[i].setAccessible(true);
                    if (fields[i].get(object) != null) {
                        setParam(fields[i].getName(), fields[i].get(object) + "");
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return this;
        } else
            return this;
    }

}

package com.base.volley;

import android.app.Activity;
import android.app.Dialog;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.base.interfaces.Parser;
import com.base.interfaces.ShowData;
import com.base.util.LogCat;
import com.base.util.ToastTools;
import com.bm.base.R;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by 俞智威
 * on 2016-02-12.
 * 10:31
 * Procedure Explain:封装的BaseRequest，继承与Request
 */
public abstract class BaseRequest<T> extends Request<T> {

    public static final String TAG = "BaseRequest";

    Holder<T> holder;

    public BaseRequest(Holder<T> holder) {
        super(holder.httpType, holder.url, new Error(holder));
        this.holder = holder;
    }

    @Override
    protected void deliverResponse(T response) {
        holder.showData.showData(response);
        if (holder.dialog != null && holder.dialog.isShowing()) {
            holder.dialog.dismiss();
        }
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return holder.paramMap;
    }

    public static class Holder<T> {

        Activity activity;

        Parser<T> parser;

        Map<String, String> paramMap = new HashMap<String, String>();

        String url;

        int httpType = Request.Method.POST;

        ShowData<T> showData;

        Dialog dialog;

        String errorMessage;

        public Holder<T> setActivity(Activity activity) {
            this.activity = activity;
            return this;
        }

        public Holder<T> setParser(Parser<T> parser) {
            this.parser = parser;
            return this;
        }

        public Holder<T> setParam(String key, String value) {
            paramMap.put(key, value);
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

        public Holder<T> setShowData(ShowData<T> showData) {
            this.showData = showData;
            return this;
        }

        public Holder<T> setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
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
            Field fields[] = object.getClass().getDeclaredFields();// 获得对象所有属性
            if (fields.length != 0) {
                for (int i = 0; i < fields.length; i++) {
                    try {
                        fields[i].setAccessible(true);
                        if (fields[i].get(object) != null) {
                            this.setParam(fields[i].getName(), fields[i].get(object) + "");
                        }
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                return this;
            } else
                return this;
        }

    }

    public static class Error implements Response.ErrorListener {

        Holder holder;

        public Error(Holder holder) {
            this.holder = holder;
        }

        @Override
        public void onErrorResponse(VolleyError error) {

            if (holder != null && holder.activity != null) {

                if (!"".equals(holder.errorMessage)) {
                    ToastTools.showShort(holder.activity.getApplicationContext(), holder.errorMessage == null
                            ? holder.activity.getString(R.string.data_fail) : holder.errorMessage);
                }

                if (holder.dialog != null && holder.dialog.isShowing()) {
                    holder.dialog.dismiss();
                }
            }
        }
    }

}

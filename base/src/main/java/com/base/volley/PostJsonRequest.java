package com.base.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.base.util.LogCat;
import com.base.util.ToastTools;
import com.google.gson.Gson;

/**
 * Create by 俞智威
 * on 2015-11-05
 * 15:28
 * Procedure Explain:json请求
 */
public class PostJsonRequest<T> extends JsonRequest<T> {

    private Class<T> tClass;
    private Holder<T> holder;

    public PostJsonRequest(int method, String url, String requestBody, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);
    }

    public PostJsonRequest(Holder<T> holder, Class<T> tClass) {
        super(Method.POST, holder.getUrl(), holder.getRequestBody(), null, new Error(holder));
        this.holder = holder;
        this.tClass = tClass;
        LogCat.i("post参数", "{" + holder.getRequestBody() + "}");
    }


    @Override
    protected void deliverResponse(T response) {
        if (holder.dialog != null && holder.dialog.isShowing()) {
            holder.dialog.dismiss();
        }
        holder.getData.getData(response, 0);// 0 的作用查看于Holder<T> 中的tag
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            T t = null;
            LogCat.i(new String(response.data));
            t = new Gson().fromJson(new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers, "utf-8")).trim(), tClass);

            return Response.success(t, HttpHeaderParser.parseCacheHeaders(response));

        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }

    public static class Error implements Response.ErrorListener {
        Holder holder;

        public Error(Holder holder) {
            this.holder = holder;
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
            if (holder != null && holder.activity != null) {
                if (!"".equals(holder.errorMessage)) {
                    ToastTools.showShort(holder.activity.getApplicationContext()
                            , holder.errorMessage == null
                                    ? holder.activity.getString(com.bm.base.R.string.data_fail) : holder.errorMessage);
                }

                if (holder.dialog != null && holder.dialog.isShowing()) {
                    holder.dialog.dismiss();
                }

                if (holder.errorCallBack != null) {
                    holder.errorCallBack.onError(0);
                }
            }
        }
    }
}

        /*
        Volley的异常列表：
        AuthFailureError：如果在做一个HTTP的身份验证，可能会发生这个错误。
        NetworkError：Socket关闭，服务器宕机，DNS错误都会产生这个错误。
        NoConnectionError：和NetworkError类似，这个是客户端没有网络连接。
        ParseError：在使用JsonObjectRequest或JsonArrayRequest时，如果接收到的JSON是畸形，会产生异常。
        SERVERERROR：服务器的响应的一个错误，最有可能的4xx或5xx HTTP状态代码。
        TimeoutError：Socket超时，服务器太忙或网络延迟会产生这个异常。默认情况下，Volley的超时时间为2.5秒。如果得到这个错误可以使用RetryPolicy。
        */
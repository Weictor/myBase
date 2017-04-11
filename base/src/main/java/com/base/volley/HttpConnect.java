package com.base.volley;

import android.content.Context;
import android.view.ContextThemeWrapper;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by 俞智威
 * on 2016-02-13.
 * 09:23
 * Procedure Explain:
 */
public class HttpConnect {

    public static final String TAG = "HttpConnect";

    private static HttpConnect httpConnect;

    private RequestQueue requestQueue;

    public static void init(Context context) {
        httpConnect = new HttpConnect(context);
    }

    private HttpConnect(Context context) {

        requestQueue = Volley.newRequestQueue(context.getApplicationContext());

    }


    public void add(Request request) {
//        request.setRetryPolicy(new DefaultRetryPolicy());  //用于修改默认的超时时间与重试策略
        requestQueue.add(request);
    }


    public void cancelAll(Object TAG) {
        requestQueue.cancelAll(TAG);
    }

    public static synchronized HttpConnect getInstance() {
        return httpConnect;
    }


}

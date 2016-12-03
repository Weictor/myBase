package com.base.volley;

import com.alibaba.fastjson.JSON;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.base.util.LogCat;

/**
 * Created by 俞智威
 * on 2016-02-13.
 * 09:10
 * Procedure Explain:
 */
public class FastJsonRequest<T> extends BaseRequest<T> {

    public static final String TAG = "FastJsonRequest";
    private Class<T> tClass;

    public FastJsonRequest(Holder<T> holder, Class<T> tClass) {
        super(holder);
        this.tClass = tClass;
    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {

        try {

            T t = null;

            LogCat.i(new String(response.data));

            if(holder.parser == null){

                t = JSON.parseObject(new String(response.data,
                        HttpHeaderParser.parseCharset(response.headers, "utf-8")),tClass);

            }else {

                t = holder.parser.parse(new String(response.data
                        , HttpHeaderParser.parseCharset(response.headers, "utf-8")));

            }

            return Response.success(t, HttpHeaderParser.parseCacheHeaders(response));

        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }

    }

}

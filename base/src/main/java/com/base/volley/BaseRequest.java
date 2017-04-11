package com.base.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.base.util.ToastTools;
import com.bm.base.R;

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
        holder.getData.getData(response, holder.getTag());
        if (holder.dialog != null && holder.dialog.isShowing()) {
            holder.dialog.dismiss();
        }
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return holder.paramMap;
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

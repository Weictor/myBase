package com.mybase;


import android.app.Activity;
import android.app.Dialog;

import com.base.interfaces.ShowData;
import com.base.util.LogCat;
import com.base.volley.BaseRequest;
import com.base.volley.FastJsonRequest;
import com.base.volley.HttpConnect;
import com.base.volley.PostJsonHolder;
import com.base.volley.PostJsonRequest;
import com.base.widget.ProgressDialog;

/**
 * Create by 俞智威
 * on 2015-11-27
 * 10:25
 * Procedure Explain:网络请求配置
 */
public class HttpService {

    public static final String IP = "http://139.196.233.169:8080/gate/";
    public static final String METHOD_FIND_TRANSLATION_LIST = "app/make/findTranslationList";//p4-2预约详情(翻译列表)
    public static final String METHOD_GET_CODE_FOR_USER = "app/user/getCodeForUser";//获取验证码

    public static void findTranslationList(ShowData<FindTranslationListBean> showData, FindTranslationListPostBean post) {
        BaseRequest.Holder<FindTranslationListBean> holder = new BaseRequest.Holder();
        holder.setShowData(showData)
                .setUrl(IP + METHOD_FIND_TRANSLATION_LIST)
                .setPostValues(post);
//        holder.setParam("token", post.getToken());
//        holder.setParam("sex", post.getSex());
//        holder.setParam("provinceid", post.getProvinceid());
//        holder.setParam("countryid", post.getCountryid());
////        holder.setParam("cityid", post.getCityid());
//        holder.setParam("languageid", post.getLanguageid());
//        holder.setParam("level", post.getLevel());
//        if (post.getSpecialtyid() != null) {
//            holder.setParam("specialtyid", post.getSpecialtyid());
//        }
        HttpConnect.getInstance().add(new FastJsonRequest<FindTranslationListBean>(holder, FindTranslationListBean.class));
    }

    /**
     * 获取验证码
     *
     * @param showData
     * @param post
     */
    public static void getCodeForUser(ShowData<GetCodeForUserBean> showData, GetCodeForUserPostBean post) {
        BaseRequest.Holder<GetCodeForUserBean> holder = new BaseRequest.Holder();
        holder.setShowData(showData)
                .setUrl(IP + METHOD_GET_CODE_FOR_USER)
                .setPostValues(post);
        HttpConnect.getInstance().add(new FastJsonRequest(holder, GetCodeForUserBean.class));
    }
}
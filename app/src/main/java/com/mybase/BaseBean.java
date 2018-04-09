package com.mybase;

/**
 * Created by 俞智威
 * on 2015-12-03.
 * 10:19
 * Procedure Explain:获取数据的基类bean   xiugai cesi
 */

public class BaseBean {

    private String state;//状态 0为成功
    private String msg; //状态说明

    /**
     * 判断是否成功，true为成功
     *
     * @return
     */
    public boolean isSuccess() {
        return state.equals("0") ? true : false;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

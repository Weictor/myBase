package com.mybase;

/**
 * 创建者：张路
 * 创建日期：2015/12/3
 * 创建时间：10:35
 * 描述：获取验证码
 */
public class GetCodeForUserPostBean {

    private String phone;//手机号码
    private int type;//1注册2找回密码

    public GetCodeForUserPostBean(String phone, int type) {
        this.phone = phone;
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

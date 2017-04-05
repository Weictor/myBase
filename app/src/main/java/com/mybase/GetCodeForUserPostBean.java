package com.mybase;

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

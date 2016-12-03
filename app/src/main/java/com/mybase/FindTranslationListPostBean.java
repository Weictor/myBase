package com.mybase;

/**
 * Created by 俞智威
 * on 2015-12-07.
 * 16:22
 * Procedure Explain:p4-2预约详情(翻译列表)
 */

public class FindTranslationListPostBean {

    private String token;//用户唯一标识
    private String sex;//性别 1男2女
    private String provinceid;//省
    private String countryid;//	国家
    private String cityid;//市
    private String level;//	等级
    private String languageid;//语种
    private String specialtyid;//专业

    public FindTranslationListPostBean(String token, String sex, String provinceid, String countryid, String level, String languageid, String specialtyid) {//, String cityid  在countryid的后面
        this.token = token;
        this.sex = sex;
        this.provinceid = provinceid;
        this.countryid = countryid;
//        this.cityid = cityid;
        this.level = level;
        this.languageid = languageid;
        this.specialtyid = specialtyid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid;
    }

    public String getCountryid() {
        return countryid;
    }

    public void setCountryid(String countryid) {
        this.countryid = countryid;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLanguageid() {
        return languageid;
    }

    public void setLanguageid(String languageid) {
        this.languageid = languageid;
    }

    public String getSpecialtyid() {
        return specialtyid;
    }

    public void setSpecialtyid(String specialtyid) {
        this.specialtyid = specialtyid;
    }
}

package com.mybase;

/**
 * Created by 俞智威
 * on 2015-12-07.
 * 16:50
 * Procedure Explain:
 */

public class FindTranslationListDataBean {

    private String specialtyname;//	专业
    private String starlevel;//	评分(星星)
    private String school;//	学校
    private String languagename;//	语种
    private String userid;//	翻译ID
    private String headurl;//	头像
    private String name;//姓名
    private String sex;//性别  1男2女

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSpecialtyname() {
        return specialtyname;
    }

    public void setSpecialtyname(String specialtyname) {
        this.specialtyname = specialtyname;
    }

    public String getStarlevel() {
        return starlevel;
    }

    public void setStarlevel(String starlevel) {
        this.starlevel = starlevel;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getLanguagename() {
        return languagename;
    }

    public void setLanguagename(String languagename) {
        this.languagename = languagename;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

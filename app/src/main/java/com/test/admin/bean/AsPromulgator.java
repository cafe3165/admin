package com.test.admin.bean;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2016/11/18 0018.
 */

public class AsPromulgator extends BmobUser{

    private String proQQNumber;//QQ号
    private String proImPermission_2;//通知权限_年级
    private String proImPermission_1;//通知权限_学院
    private String proIdentity;//身份
    private String proAcPermission_2;//活动权限_年级
    private String proAcPermission_1;//活动权限_学院
    private String parStuNumber;//学号--该字段是为了避免唯一键冲突

    public String getProQQNumber() {
        return proQQNumber;
    }
    public void setProQQNumber(String proQQNumber) {
        this.proQQNumber = proQQNumber;
    }

    public String getProImPermission_2() {
        return proImPermission_2;
    }
    public void setProImPermission_2(String proAcPermission_2) {
        this.proImPermission_2 = proAcPermission_2;
    }

    public String getProImPermission_1() {
        return proImPermission_1;
    }
    public void setProImPermission_1(String proImPermission_1) {
        this.proImPermission_1 = proImPermission_1;
    }

    public String getProIdentity() {
        return proIdentity;
    }
    public void setProIdentity(String proIdentity) {
        this.proIdentity = proIdentity;
    }

    public String getProAcPermission_2() {
        return proAcPermission_2;
    }
    public void setProAcPermission_2(String proAcPermission_2) {
        this.proAcPermission_2 = proAcPermission_2;
    }

    public String getProAcPermission_1() {
        return proAcPermission_1;
    }
    public void setProAcPermission_1(String proAcPermission_1) {
        this.proAcPermission_1 = proAcPermission_1;
    }

    public void setParStuNumber(String parStuNumber) {
        this.parStuNumber = parStuNumber;
    }
    public String getParStuNumber() {
        return parStuNumber;
    }
}

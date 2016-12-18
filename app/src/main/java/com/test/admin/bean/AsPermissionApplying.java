package com.test.admin.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/11/17 0017.
 */

public class AsPermissionApplying extends BmobObject {
    private String perTelNumber;//手机号
    private String perSupplement;//权限申请补充
    private String perQQNumber;//QQ号
    private String perImPermission_2;//活动权限_年级
    private String perImPermission_1;//活动权限_学院
    private String perIdentity;//身份
    private String perEmail;//邮箱
    private String perAcPermission_2;//活动权限_年级
    private String perAcPermission_1;//活动权限_学院
    private String perUserName;//用户名
    private String perPassword;//密码

    public String getPerPassword() {
        return perPassword;
    }

    public void setPerPassword(String perPassword) {
        this.perPassword = perPassword;
    }

    public String getPerTelNumber() {
        return perTelNumber;
    }
    public void setPerTelNumber(String perTelNumber) {
        this.perTelNumber = perTelNumber;
    }

    public String getPerSupplement() {
        return perSupplement;
    }
    public void setPerSupplement(String perSupplement) {
        this.perSupplement = perSupplement;
    }

    public String getPerQQNumber() {
        return perQQNumber;
    }
    public void setPerQQNumber(String perQQNumber) {
        this.perQQNumber = perQQNumber;
    }

    public String getPerImPermission_2() {
        return perImPermission_2;
    }
    public void setPerImPermission_2(String perImPermission_2) {
        this.perImPermission_2 = perImPermission_2;
    }

    public String getPerImPermission_1() {
        return perImPermission_1;
    }
    public void setPerImPermission_1(String perImPermission_1) {
        this.perImPermission_1 = perImPermission_1;
    }

    public String getPerIdentity() {
        return perIdentity;
    }
    public void setPerIdentity(String perIdentity) {
        this.perIdentity = perIdentity;
    }

    public String getPerEmail() {
        return perEmail;
    }
    public void setPerEmail(String perEmail) {
        this.perEmail = perEmail;
    }

    public String getPerAcPermission_2() {
        return perAcPermission_2;
    }
    public void setPerAcPermission_2(String perAcPermission_2) {
        this.perAcPermission_2 = perAcPermission_2;
    }

    public String getPerAcPermission_1() {
        return perAcPermission_1;
    }
    public void setPerAcPermission_1(String perAcPermission_1) {
        this.perAcPermission_1 = perAcPermission_1;
    }

    public String getPerUserName() {
        return perUserName;
    }
    public void setPerUserName(String perUserName) {
        this.perUserName = perUserName;
    }
}
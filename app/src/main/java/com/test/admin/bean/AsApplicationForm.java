package com.test.admin.bean;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/11/17 0017.
 */

public class AsApplicationForm extends BmobObject {
    private String apAcId;//活动编号
    private List<String> apParId;//报名参与者编号
    private List<Boolean> apParStatus;//参与者签到状态

    public String getApAcId() {
        return apAcId;
    }
    public void setApAcId(String apAcId) {
        this.apAcId = apAcId;
    }

    public List<String> getApParId() {
        return apParId;
    }
    public void setApParId(List<String> apParId) {
        this.apParId = apParId;
    }

    public List<Boolean> getApParStatus() {
        return apParStatus;
    }
    public void setApParStatus(List<Boolean> apParStatus) {
        this.apParStatus = apParStatus;
    }
}


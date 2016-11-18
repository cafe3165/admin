package com.test.admin.bean;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/11/17 0017.
 */

public class AsAcApplying extends BmobObject {
    private String acApplyAudiences;//活动受众
    private String acApplyContent;//活动内容
    private String acApplyDeadline;//结束时间
    private List<String> acApplyLabel;//活动标签
    private String acApplyOrganizer;//举办方
    private String acApplyPlace;//活动地点
    private String acApplyStartTime;//开始时间
    private String acApplyStatus;//活动状态
    private String acApplyTitle;//活动标题
    private String acApplyPushScope_1;//推送范围_学院
    private String acApplyPushScope_2;//推送范围_年级
    private String acApplyProId;//发布者Id

    public String getAcApplyAudiences() {
        return acApplyAudiences;
    }
    public void setAcApplyAudiences(String acApplyAudiences) {
        this.acApplyAudiences = acApplyAudiences;
    }

    public String getAcApplyContent() {
        return acApplyContent;
    }
    public void setAcApplyContent(String acApplyContent) {
        this.acApplyContent = acApplyContent;
    }

    public String getAcApplyDeadline() {
        return acApplyDeadline;
    }
    public void setAcApplyDeadline(String acApplyDeadline) {
        this.acApplyDeadline = acApplyDeadline;
    }

    public List<String> getAcApplyLabel() {
        return acApplyLabel;
    }
    public void setAcApplyLabel(List<String> acApplyLabel) {
        this.acApplyLabel = acApplyLabel;
    }

    public String getAcApplyOrganizer() {
        return acApplyOrganizer;
    }
    public void setAcApplyOrganizer(String acApplyOtganizer) {
        this.acApplyOrganizer = acApplyOtganizer;
    }

    public String getAcApplyPlace() {
        return acApplyPlace;
    }
    public void setAcApplyPlace(String acApplyPlace) {
        this.acApplyPlace = acApplyPlace;
    }

    public String getAcApplyStartTime() {
        return acApplyStartTime;
    }
    public void setAcApplyStartTime(String acApplyStartTime) {
        this.acApplyStartTime = acApplyStartTime;
    }

    public String getAcApplyStatus() {
        return acApplyStatus;
    }
    public void setAcApplyStatus(String acApplyStatus) {
        this.acApplyStatus = acApplyStatus;
    }

    public String getAcApplyTitle() {
        return acApplyTitle;
    }
    public void setAcApplyTitle(String acApplyTitle) {
        this.acApplyTitle = acApplyTitle;
    }

    public String getAcApplyPushScope_1() {
        return acApplyPushScope_1;
    }
    public void setAcApplyPushScope_1(String acApplyPushScope_1) {
        this.acApplyPushScope_1 = acApplyPushScope_1;
    }

    public String getAcApplyPushScope_2() {
        return acApplyPushScope_2;
    }
    public void setAcApplyPushScope_2(String acApplyPushScope_2) {
        this.acApplyPushScope_2 = acApplyPushScope_2;
    }

    public String getAcApplyProId() {
        return acApplyProId;
    }
    public void setAcApplyProId(String acApplyProId) {
        this.acApplyProId = acApplyProId;
    }
}

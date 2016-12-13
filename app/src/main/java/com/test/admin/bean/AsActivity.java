package com.test.admin.bean;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/11/17 0017.
 */

public class AsActivity extends BmobObject {
    private String acAudiences;//活动受众
    private String acStartTime;//开始时间
    private String acPushScope_2;//推送范围_年级
    private String acPushScope_1;//推送范围_学院
    private String acPlace;//活动地点
    private String acOrganizer;//举办方
    private List<String> acLabel;//活动标签
    private String acDeadline;//结束时间
    private String acContent;//活动内容
    private String acTitle;//活动标题
    private String acPromulgator;//发布者ID

    public String getAcAudiences() {
        return acAudiences;
    }
    public void setAcAudiences(String acAudiences) {
        this.acAudiences = acAudiences;
    }

    public String getAcStartTime() {
        return acStartTime;
    }
    public void setAcStartTime(String acStartTime) {
        this.acStartTime = acStartTime;
    }

    public String getAcPushScope_2() {
        return acPushScope_2;
    }
    public void setAcPushScope_2(String acPushScope_2) {
        this.acPushScope_2 = acPushScope_2;
    }

    public String getAcPushScope_1() {
        return acPushScope_1;
    }
    public void setAcPushScope_1(String acPushScope_1) {
        this.acPushScope_1 = acPushScope_1;
    }

    public String getAcPlace() {
        return acPlace;
    }
    public void setAcPlace(String acPlace) {
        this.acPlace = acPlace;
    }

    public String getAcOtganizer() {
        return acOrganizer;
    }
    public void setAcOtganizer(String acOtganizer) {
        this.acOrganizer = acOtganizer;
    }

    public List<String> getAcLabel() {
        return acLabel;
    }
    public void setAcLabel(List<String> acLabel) {
        this.acLabel = acLabel;
    }

    public String getAcDeadline() {
        return acDeadline;
    }
    public void setAcDeadline(String acDeadline) {
        this.acDeadline = acDeadline;
    }

    public String getAcContent() {
        return acContent;
    }
    public void setAcContent(String acContent) {
        this.acContent = acContent;
    }

    public String getAcTitle() {
        return acTitle;
    }
    public void setAcTitle(String acTitle) {
        this.acTitle = acTitle;
    }

    public String getAcPromulgator() {
        return acPromulgator;
    }
    public void setAcPromulgator(String acPromulgator) {
        this.acPromulgator = acPromulgator;
    }
}

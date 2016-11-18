package com.test.admin.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/11/18 0018.
 */

public class AsImformation extends BmobObject {
    private String imAudiences;//通知受众
    private String imContent;//通知内容
    private String imOrganizer;//通知方
    private String imPushScope_1;//推送范围_学院
    private String imPushScope_2;//推送范围_年级
    private String imTitle;//通知标题

    public String getImAudiences() {
        return imAudiences;
    }
    public void setImAudiences(String mAudiences) {
        this.imAudiences = mAudiences;
    }

    public String getImContent() {
        return imContent;
    }
    public void setImContent(String imContent) {
        this.imContent = imContent;
    }

    public String getImOrganizer() {
        return imOrganizer;
    }
    public void setImOrganizer(String imOrganizer) {
        this.imOrganizer = imOrganizer;
    }

    public String getImPushScope_1() {
        return imPushScope_1;
    }
    public void setImPushScope_1(String imPushScope_1) {
        this.imPushScope_1 = imPushScope_1;
    }

    public String getImPushScope_2() {
        return imPushScope_2;
    }
    public void setImPushScope_2(String imPushScope_2) {
        this.imPushScope_2 = imPushScope_2;
    }

    public String getImTitle() {
        return imTitle;
    }
    public void setImTitle(String imTitle) {
        this.imTitle = imTitle;
    }
}

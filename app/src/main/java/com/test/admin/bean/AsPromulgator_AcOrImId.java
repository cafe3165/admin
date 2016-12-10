package com.test.admin.bean;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/12/10 0010.
 */

public class AsPromulgator_AcOrImId extends BmobObject {

    private List<String> proAcId;//已发布活动编号
    private List<String> proImId;//已发布通知编号
    private List<String> proAcIdEnd;//已结束活动编号
    private String proId;//发布者Id

    public void setProAcId(List<String> proAcId) {
        this.proAcId = proAcId;
    }

    public void setProImId(List<String> proImId) {
        this.proImId = proImId;
    }

    public void setProAcIdEnd(List<String> proAcIdEnd) {
        this.proAcIdEnd = proAcIdEnd;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public List<String> getProAcId() {
        return proAcId;
    }

    public List<String> getProImId() {
        return proImId;
    }

    public List<String> getProAcIdEnd() {
        return proAcIdEnd;
    }

    public String getProId() {
        return proId;
    }
}

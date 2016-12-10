package com.test.admin.bean;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/12/10 0010.
 */

public class AsParticipant_AcId extends BmobObject{

    private List<String> parAcId;//已报名活动编号
    private List<String> parAcIdEnd;//历史参与活动编号
    private String parId;//参与者Id

    public void setParAcId(List<String> parAcId) {
        this.parAcId = parAcId;
    }

    public void setParAcIdEnd(List<String> parAcIdEnd) {
        this.parAcIdEnd = parAcIdEnd;
    }

    public void setParId(String parId) {
        this.parId = parId;
    }

    public List<String> getParAcId() {
        return parAcId;
    }

    public List<String> getParAcIdEnd() {
        return parAcIdEnd;
    }

    public String getParId() {
        return parId;
    }
}

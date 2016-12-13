package com.test.admin.bean;

import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Administrator on 2016/11/17 0017.
 */

public class AsParticipant extends BmobUser {

    //由于BmobUSer自带username,password,mobliephonenumber,故省略个别属性，另外parCollegeCode显得多余,已删除
    private BmobFile parHeadPortrait;//头像
    private List<String> parAcId;//已报名活动编号
    private String parStuNumber;//学号
    private String parName;//姓名
    private String parGrade;//年级
    private String parGender;//性别
    private String parCollege;//学院
    private String parAge;//年龄
    private List<String> parAcIdEnd;//历史参与活动编号
//    private String password;

//    public String getPassword() {
//        return password;
//    }

    public BmobFile getParHeadPortrait(){
        return parHeadPortrait;
    }
    public void setParHeadPortrait(BmobFile parHeadPortrait) {
        this.parHeadPortrait = parHeadPortrait;
    }

    public List<String> getParAcId() {
        return parAcId;
    }
    public void setParAcId(List<String> parAcId) {
        this.parAcId = parAcId;
    }

    public String getParStuNumber() {
        return parStuNumber;
    }
    public void setParStuNumber(String parStuNumber) {
        this.parStuNumber = parStuNumber;
    }

    public String getParName() {
        return parName;
    }
    public void setParName(String parName) {
        this.parName = parName;
    }

    public String getParGrade() {
        return parGrade;
    }
    public void setParGrade(String parGrade) {
        this.parGrade = parGrade;
    }

    public String getParGender() {
        return parGender;
    }
    public void setParGender(String parGender) {
        this.parGender = parGender;
    }

    public String getParCollege() {
        return parCollege;
    }
    public void setParCollege(String parCollege) {
        this.parCollege = parCollege;
    }

    public String getParAge() {
        return parAge;
    }
    public void setParAge(String parAge) {
        this.parAge = parAge;
    }

    public List<String> getParAcIdEnd() {
        return parAcIdEnd;
    }
    public void setParAcIdEnd(List<String> parAcIdEnd) {
        this.parAcIdEnd = parAcIdEnd;
    }

}

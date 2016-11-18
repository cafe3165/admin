package com.test.admin.model;



import com.test.admin.bean.AsParticipant;

import java.io.File;
import java.util.Arrays;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * 参与者
 * Created by X on 2016/11/12.
 */
public class Aspar {

//返回值
private BmobException sendsms,verifysms,signup,signin,change,uploadhpic,pwsms,pwsmsverify,chnpw,actiadd,actidelete,actiedadd;
    //发送验证码
    ///@Override
    public BmobException AsParSendSms(String username) {
        String template="verify";
        BmobSMS.requestSMSCode(username, template, new QueryListener<Integer>() {
            @Override
            public void done(Integer integer, BmobException e) {
                sendsms=e;
            }
        });
        return sendsms;
    }

    //短信验证码验证
    //@Override
    public BmobException AsparSmsSignup(String username, String yzcode) {
        BmobSMS.verifySmsCode(username, yzcode, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                verifysms=e;
            }
        });
        return verifysms;
    }

    //注册
    //@Override
    public BmobException AsParSignup(String username, String pw, String parStuNumber, String parName, String parCollege, String parGrade) {
        AsParticipant par=new AsParticipant();
        par.setUsername(username);
        par.setPassword(pw);
        par.setParStuNumber(parStuNumber);
        par.setParName(parName);
        par.setParCollege(parCollege);
        par.setParGrade(parGrade);
        par.setMobilePhoneNumber(username);
        par.signUp(new SaveListener<AsParticipant>() {
            @Override
            public void done(AsParticipant asParticipant, BmobException e) {
                signup=e;
            }
        });
        return signup;
    }

    //登录
    //@Override
    public BmobException AsParSignin(String username, String password) {
        AsParticipant par=new AsParticipant();
        par.setUsername(username);
        par.setPassword(password);
        par.login(new SaveListener<AsParticipant>() {
            @Override
            public void done(AsParticipant asParticipant, BmobException e) {
                signin=e;
            }
        });
        return signin;
    }

    //修改信息
    //@Override
    public BmobException AsParChange(String pargender, String parage, String parcol, String pargrade, String pnum) {
        AsParticipant par=new AsParticipant();
        par.setParGender(pargender);
        par.setParAge(parage);
        par.setParCollege(parcol);
        par.setParGrade(pargrade);
        par.setMobilePhoneNumber(pnum);
        par.update(par.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                change=e;
            }
        });
        return change;
    }

    //上传头像并设置头像
    //@Override
    public BmobException AsParUploadHpic(String picpath) {
        final BmobFile headpic=new BmobFile(new File(picpath));
        headpic.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    //上传成功，开始设置头像
                    AsParticipant par=new AsParticipant();
                    par.setParHeadPortrait(headpic);
                    par.update(par.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException a) {
                            uploadhpic=a;
                        }
                    });
                }else{
                    uploadhpic=e;
                }
            }
        });
        return uploadhpic;
    }

    //发送修改密码短信验证码
    //@Override
    public BmobException AsParPwSms(String username) {
        String template="verify";
        BmobSMS.requestSMSCode(username, template, new QueryListener<Integer>() {
            @Override
            public void done(Integer integer, BmobException e) {
                pwsms=e;
            }
        });
        return pwsms;
    }

    //验证重置密码短信验证码并重置密码
    //@Override
    public BmobException AsParPwSmsVerify(String yzcode, String newpw) {
        AsParticipant.resetPasswordBySMSCode(yzcode, newpw, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                pwsmsverify=e;
            }
        });
        return pwsmsverify;
    }

    //已报名活动增加
    //@Override
    public BmobException AsParAcAdd(String actiid) {
        AsParticipant par=new AsParticipant();
        par.addUnique("parAcId",actiid);
        par.update(par.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                actiadd=e;
            }
        });
        return actiadd;
    }

    //已报名活动删除
    //@Override
    public BmobException AsParAcDelete(String actiid) {
        AsParticipant par=new AsParticipant();
        par.removeAll("parAcId", Arrays.asList(actiid));
        par.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                actidelete=e;
            }
        });
        return actidelete;
    }

    //历史参加活动增加
    //@Override
    public BmobException AsParAcEdAdd(String actiid) {
        AsParticipant par=new AsParticipant();
        par.addUnique("parAcIdEnd",actiid);
        par.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                actiedadd=e;
            }
        });
        return actiedadd;
    }

    //修改学号、姓名（管理员用）
    //@Override
    public BmobException AsParChnpw(String userid, String newstunum, String newname) {
        AsParticipant par=new AsParticipant();
        par.setParStuNumber(newstunum);
        par.setParName(newname);
        par.update(userid, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                chnpw=e;
            }
        });
        return chnpw;
    }
}

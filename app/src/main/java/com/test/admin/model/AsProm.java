package com.test.admin.model;



import com.test.admin.bean.AsPromulgator;

import java.util.Arrays;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 发布者
 * Created by X on 2016/11/14.
 */

public class AsProm{
    private BmobException proacadd,proacdelete,proimadd,proimdelete,signin,signup;

    //发布者注册
    public BmobException ProSignUp(String username, String password, String QQnumber, String proimpermission1, String proimpermission2, String proacpermission1, String proacpermission2, String proidentity){
        AsPromulgator asp=new AsPromulgator();
        asp.setUsername(username);
        asp.setPassword(password);
        asp.setProQQNumber(QQnumber);
        asp.setProImPermission_1(proimpermission1);
        asp.setProImPermission_2(proimpermission2);
        asp.setProAcPermission_1(proacpermission1);
        asp.setProAcPermission_2(proacpermission2);
        asp.setProAcPermission_2(proidentity);
        asp.signUp(new SaveListener<AsPromulgator>() {
            @Override
            public void done(AsPromulgator asPromulgator, BmobException e) {
                signup=e;
            }
        });
        return signup;
    }

    //发布者登录
    public BmobException ProSignIn(String username, String password){
        AsPromulgator asp=new AsPromulgator();
        asp.loginByAccount(username, password, new LogInListener<AsPromulgator>() {
            @Override
            public void done(AsPromulgator asPromulgator, BmobException e) {
                signin=e;
            }
        });
        return signin;
    }



    //已发布活动增加
    //@Override
    public BmobException ProAcAdd(String actiid) {
        AsPromulgator asp=new AsPromulgator();
        asp.addUnique("proAcId",actiid);
        asp.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                proacadd=e;
            }
        });
        return proacadd;
    }

    //已发布活动删除
    //@Override
    public BmobException ProAcDelete(String actiid) {
        AsPromulgator asp=new AsPromulgator();
        asp.removeAll("proAcId", Arrays.asList(actiid));
        asp.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                proacdelete=e;
            }
        });
        return proacdelete;
    }

    //已发布通知增加
    //@Override
    public BmobException ProImAdd(String imid) {
        AsPromulgator asp=new AsPromulgator();
        asp.addUnique("proImId",imid);
        asp.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                proimadd=e;
            }
        });
        return proimadd;
    }



}

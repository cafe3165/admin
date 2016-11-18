package com.test.admin.model;

import com.test.admin.bean.AsAdministrator;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 管理员
 * Created by X on 2016/11/13.
 */

public class Asadmin{
    //返回值
    private BmobException login;

    //登录
    //@Override
    public BmobException Asadminsignin(String username, String pw) {
        AsAdministrator admin=new AsAdministrator();
        admin.setUsername(username);
        admin.setPassword(pw);
        admin.login(new SaveListener<AsAdministrator>() {
            @Override
            public void done(AsAdministrator asAdministrator, BmobException e) {
                login=e;
            }
        });
        return login;
    }
}

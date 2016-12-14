package com.test.admin.model;

import android.widget.Toast;

import com.test.admin.bean.AsPromulgator;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

import static cn.bmob.v3.Bmob.getApplicationContext;

/**
 * Created by Administrator on 2016/11/17 0017.
 */

public class Function {

    public static void showToast(String msg){

        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();
    }
    //判断输入是否为空
    public static boolean judge(List<String> list){

        boolean flag = true;

        for(int i = 0;i < list.size(); i++){

            if(list.get(i).equals("") == true){

                flag = false;
                break;
            }
        }
        return  flag;
    }
    //发送邮件
    public void sendEmial(AsPromulgator asPromulgator){

        final String proEmail = asPromulgator.getEmail();
        BmobUser.requestEmailVerify(proEmail, new UpdateListener() {
            @Override
            public void done(BmobException e) {

                if(e == null){

                }else {

                    showToast("发送邮件操作失败" + "\t" + e.getErrorCode() + ":" + e.getMessage());
                }
            }
        });

    }
}

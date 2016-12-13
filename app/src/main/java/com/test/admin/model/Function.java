package com.test.admin.model;

import android.widget.Toast;

import java.util.List;

import static cn.bmob.v3.Bmob.getApplicationContext;

/**
 * Created by Administrator on 2016/11/17 0017.
 */

public class Function {

    public static final int UPDATE_TEXT = 1;

    public static void showToast(String msg){

        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();
    }

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
}

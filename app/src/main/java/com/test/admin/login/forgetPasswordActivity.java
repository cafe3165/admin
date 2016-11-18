package com.test.admin.login;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.test.admin.R;
import com.test.admin.model.Aspar;

import cn.bmob.v3.exception.BmobException;

/**
 * Created by wuzhenge on 2016/11/13.
 */

public class forgetPasswordActivity extends AppCompatActivity {

    private EditText phonenum;
    private EditText verifycode;
    private EditText newpassword;
    private EditText passwordcon;
    private Aspar par;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password_activity);
        Button btn1 = (Button)findViewById(R.id.btn1);//确认
        Button btn2 = (Button)findViewById(R.id.btn2);
        Button send=(Button)findViewById(R.id.send);//发送按钮

        phonenum=(EditText)findViewById(R.id.et1);
        verifycode=(EditText)findViewById(R.id.et2);
        newpassword=(EditText)findViewById(R.id.et3);
        passwordcon=(EditText)findViewById(R.id.et4);
        par=new Aspar();


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgetPassword_intent = new Intent(forgetPasswordActivity.this,MainActivity.class);
                startActivity(forgetPassword_intent);
            }
        });

        //点击确认
        btn1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String pw=newpassword.getText().toString();
                String pwc=passwordcon.getText().toString();
                if(pw.equals(pwc)){
                    smsverify();
                }else {
                    toast("确认密码错误");
                }
                //showDialog(1);
            }
        });

        //点击发送验证码
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pnum=phonenum.getText().toString();
                BmobException a=par.AsParSendSms(pnum);
                if (a==null){
                    //发送成功
                    toast("验证码发送成功");
                }else {
                    toast("验证码发送失败");
                }
            }
        });
    }

    public void toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //验证码验证并修改密码
    private void smsverify() {
        String vcode=verifycode.getText().toString();
        String newpw=newpassword.getText().toString();
        BmobException a=par.AsParPwSmsVerify(vcode,newpw);
        if (a==null){
            toast("修改成功");
        }else{
            toast("修改失败");
        }


    }

    protected Dialog onCreateDialog(int id)
    {
        Dialog dialog = null;
        switch(id)
        {
            case 1:
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setMessage("修改成功！");
                b.setPositiveButton(
                        "确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                dialog = b.create();
                break;
            default:break;
        }
        return dialog;
    }
}

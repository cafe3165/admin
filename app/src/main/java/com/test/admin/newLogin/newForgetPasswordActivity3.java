package com.test.admin.newLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.test.admin.R;
import com.test.admin.bean.AsParticipant;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class newForgetPasswordActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_step5_1);

        ImageButton imgBtn1 = (ImageButton)findViewById(R.id.imgBtn1);
        Button btn1 = (Button)findViewById(R.id.btn1);
        final EditText newpw = (EditText) findViewById(R.id.newpw);
        final EditText newpwcon = (EditText) findViewById(R.id.newpwcon);
        Bundle bundle1 = this.getIntent().getExtras();
        //String phonenum = bundle1.getString("phonenumber");
        final String verifycode = bundle1.getString("verifycode");

        //返回
        imgBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(newForgetPasswordActivity3.this,newForgetPasswordActivity2.class);
                startActivity(intent);
            }
        });

        //确认
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(newpw.getText().toString())||TextUtils.isEmpty(newpwcon.getText().toString())){
                    Toast.makeText(newForgetPasswordActivity3.this,"输入不能为空",Toast.LENGTH_SHORT).show();
                }
                else{
                    //检验密码是否一致
                    if (newpw.getText().toString().equals(newpwcon.getText().toString())){
                        //验证短信验证码并重置密码
                        AsParticipant.resetPasswordBySMSCode(verifycode, newpw.getText().toString(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e==null){
                                    //重置成功,跳转
                                    Intent intent = new Intent(newForgetPasswordActivity3.this,loginActivity.class);
                                    startActivity(intent);
                                }
                                else{
                                    //重置失败
                                    Toast.makeText(newForgetPasswordActivity3.this,"重置失败",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    else{
                        Toast.makeText(newForgetPasswordActivity3.this,"密码不一致，请重新输入",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            Intent intent = new Intent(newForgetPasswordActivity3.this, newForgetPasswordActivity2.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

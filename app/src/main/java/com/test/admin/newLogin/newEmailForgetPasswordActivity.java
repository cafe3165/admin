package com.test.admin.newLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.test.admin.R;
import com.test.admin.bean.AsPromulgator;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class newEmailForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_emial_forget_password);

        Button send = (Button)findViewById(R.id.btn1);
        ImageButton back = (ImageButton)findViewById(R.id.imgBtn1);
        final EditText emailadress = (EditText)findViewById(R.id.emailadress);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(newEmailForgetPasswordActivity.this,loginActivity.class);
                startActivity(intent);
            }
        });
        //发送邮件
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailadress.getText().toString().matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")){
                    //检测邮箱是否已注册
                    BmobQuery<AsPromulgator> query = new BmobQuery<AsPromulgator>();
                    query.addWhereEqualTo("username",emailadress.getText().toString());
                    query.findObjects(new FindListener<AsPromulgator>() {
                        @Override
                        public void done(List<AsPromulgator> list, BmobException e) {
                            if (e==null&&list.size()!=0){
                                //邮箱已注册，发送邮件
                                BmobUser.resetPasswordByEmail(emailadress.getText().toString(), new UpdateListener() {
                                    @Override
                                    public void done(BmobException e) {
                                        if (e==null){
                                            Toast.makeText(newEmailForgetPasswordActivity.this, "邮件已经发送到邮箱，请注意查收", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            Toast.makeText(newEmailForgetPasswordActivity.this,"邮件发送失败，请点击按钮重新发送",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                            else if (e==null&&list.isEmpty()){
                                Toast.makeText(newEmailForgetPasswordActivity.this,"该邮箱尚未注册",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(newEmailForgetPasswordActivity.this,"查询邮箱是否已注册失败",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
                else{
                    Toast.makeText(newEmailForgetPasswordActivity.this,"请输入正确的邮箱",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            Intent intent = new Intent(newEmailForgetPasswordActivity.this, loginActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

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
import com.test.admin.model.Aspar;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

public class newForgetPasswordActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_forget_password1);

        ImageButton imgBtn1 = (ImageButton)findViewById(R.id.imgBtn1);//返回
        final Button btn1 = (Button)findViewById(R.id.btn1);//下一步
        final EditText phonenumber = (EditText) findViewById(R.id.phonenumber);

        imgBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(newForgetPasswordActivity1.this,loginActivity.class);
                startActivity(intent);
            }
        });

        //下一步
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //若输入为手机号则继续，否则提示输入手机号
                if (!TextUtils.isEmpty(phonenumber.getText().toString())&&phonenumber.getText().toString().matches("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(17[0-8])|(147))\\d{8}$")){
                    //检查手机号是否已注册，否则提示手机号尚未注册
                    btn1.setEnabled(false);
                    BmobQuery<AsParticipant> query = new BmobQuery<AsParticipant>();
                    query.addWhereEqualTo("username",phonenumber.getText().toString());
                    query.findObjects(new FindListener<AsParticipant>() {
                        @Override
                        public void done(List<AsParticipant> list, BmobException e) {
                            //手机号已注册
                            if (list.size()!=0){
                                //发送验证码
                                String template = "verify";
                                BmobSMS.requestSMSCode(phonenumber.getText().toString(), template, new QueryListener<Integer>() {
                                    @Override
                                    public void done(Integer integer, BmobException e) {
                                        if (e==null){
                                            String phone = phonenumber.getText().toString();
                                            Toast.makeText(newForgetPasswordActivity1.this,"验证码发送成功",Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(newForgetPasswordActivity1.this,newForgetPasswordActivity2.class);
                                            //Bundle bundle = new Bundle();
                                            //bundle.putString("phonenumber",phonenumber.getText().toString());
                                            //intent.putExtras(bundle);
                                            intent.putExtra("phonenumber",phone);
                                            startActivity(intent);
                                            btn1.setEnabled(true);
                                        }
                                        else{
                                            Toast.makeText(newForgetPasswordActivity1.this,"验证码发送失败，请点击按钮重新发送",Toast.LENGTH_SHORT).show();
                                            btn1.setEnabled(true);
                                        }
                                    }
                                });
                            }
                            //手机号尚未注册
                            else{
                                Toast.makeText(newForgetPasswordActivity1.this,"该手机号尚未注册",Toast.LENGTH_SHORT).show();
                                btn1.setEnabled(true);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(newForgetPasswordActivity1.this,"请输入手机号",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            Intent intent = new Intent(newForgetPasswordActivity1.this, loginActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

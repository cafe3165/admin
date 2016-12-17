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
import com.test.admin.bean.AsPermissionApplying;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import static com.test.admin.R.id.btn1;

public class register3_2xActivity extends AppCompatActivity {
    Bundle bundle2 = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_step3_2);

        ImageButton imgBtn1 = (ImageButton)findViewById(R.id.imgBtn1);
        Button btn1 = (Button)findViewById(R.id.btn1);
        final Bundle bundle1 = this.getIntent().getExtras();
        bundle2.putString("identity",bundle1.getString("identity"));
        final EditText pw = (EditText)findViewById(R.id.pw);
        final EditText pwcon = (EditText)findViewById(R.id.pwcon);

        imgBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register3_2xActivity.this,register2_2xActivity.class);
                intent.putExtras(bundle2);
                startActivity(intent);
            }
        });

        //提交审核
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(pw.getText().toString())&&!TextUtils.isEmpty(pwcon.getText().toString())){
                    if (pw.getText().toString().equals(pwcon.getText().toString())){
                        //提交后跳转到初始界面
                        AsPermissionApplying asper = new AsPermissionApplying();
                        asper.setPerUserName(bundle1.getString("email"));
                        asper.setPerIdentity(bundle1.getString("identity"));
                        asper.setPerEmail(bundle1.getString("email"));
                        asper.setPerQQNumber(bundle1.getString("qqnum"));
                        asper.setPerPassword(pw.getText().toString());
                        asper.setPerTelNumber(bundle1.getString("phnum"));
                        asper.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e==null){
                                    Toast.makeText(register3_2xActivity.this,"申请提交成功，等待管理员审核",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(register3_2xActivity.this,loginRegisterActivity.class);
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(register3_2xActivity.this,"申请提交失败",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    else{
                        Toast.makeText(register3_2xActivity.this,"密码不一致",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(register3_2xActivity.this,"输入不能为空",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            Intent intent = new Intent(register3_2xActivity.this, register2_2xActivity.class);
            intent.putExtras(bundle2);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

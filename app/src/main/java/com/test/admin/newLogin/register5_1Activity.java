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
import cn.bmob.v3.listener.SaveListener;

import static com.test.admin.R.id.btn1;

public class register5_1Activity extends AppCompatActivity {
    Bundle bundle2 = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_step5_1);

        ImageButton imgBtn1 = (ImageButton)findViewById(R.id.imgBtn1);
        Button btn1 = (Button)findViewById(R.id.btn1);
        final EditText newpw = (EditText) findViewById(R.id.newpw);
        final EditText newpwcon = (EditText) findViewById(R.id.newpwcon);
        final Bundle bundle1 = this.getIntent().getExtras();
        bundle2=this.getIntent().getExtras();

        //返回
        imgBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register5_1Activity.this,register4_1Activity.class);
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });
        //确认
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(newpw.getText().toString())||TextUtils.isEmpty(newpwcon.getText().toString())){
                    Toast.makeText(register5_1Activity.this,"输入不能为空",Toast.LENGTH_SHORT).show();
                }
                else{
                    if (newpw.getText().toString().equals(newpwcon.getText().toString())){
                        //注册并跳转到登录界面
                        AsParticipant par = new AsParticipant();
                        par.setUsername(bundle1.getString("pnum"));
                        par.setMobilePhoneNumber(bundle1.getString("pnum"));
                        par.setParGrade(bundle1.getString("grade"));
                        par.setParCollege(bundle1.getString("college"));
                        par.setParStuNumber(bundle1.getString("stuno"));
                        par.setParName(bundle1.getString("name"));
                        par.setPassword(newpw.getText().toString());
                        par.signUp(new SaveListener<AsParticipant>() {
                            @Override
                            public void done(AsParticipant asParticipant, BmobException e) {
                                if (e==null){
                                    Toast.makeText(register5_1Activity.this,"注册成功",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(register5_1Activity.this,loginRegisterActivity.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(register5_1Activity.this,"注册失败"+e.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }else{
                        Toast.makeText(register5_1Activity.this,"密码不一致",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            Intent intent = new Intent(register5_1Activity.this, register4_1Activity.class);
            intent.putExtras(bundle2);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}

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

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

import static com.test.admin.R.id.btn1;
import static com.test.admin.R.id.start;

public class register4_1Activity extends AppCompatActivity {
    Bundle bundle2 = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_step4_1);

        ImageButton imgBtn1 = (ImageButton)findViewById(R.id.imgBtn1);
        Button btn1 = (Button)findViewById(R.id.btn1);
        Button btn2 = (Button)findViewById(R.id.btn2);
        final Bundle bundle1 = this.getIntent().getExtras();
        final EditText verifycode = (EditText)findViewById(R.id.verifycode);
        bundle2 = this.getIntent().getExtras();


        //返回
        imgBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register4_1Activity.this,register3_1Activity.class);
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });

        //重新发送验证码
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobSMS.requestSMSCode(bundle1.getString("pnum"), "verify", new QueryListener<Integer>() {
                    @Override
                    public void done(Integer integer, BmobException e) {
                        if (e==null){
                            Toast.makeText(register4_1Activity.this,"验证短信重发成功",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(register4_1Activity.this,"验证短信重发失败,请点击重新发送",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        //下一步
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobSMS.verifySmsCode(bundle1.getString("pnum"), verifycode.getText().toString(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            //验证成功，跳转
                            Intent intent= new Intent(register4_1Activity.this,register5_1Activity.class);
//                            Bundle bundle = new Bundle();
//                            bundle.putString("pnum",bundle1.getString("pnum"));
//                            bundle.putString("stuno",bundle1.getString("stuno"));
//                            bundle.putString("college",bundle1.getString("college"));
//                            bundle.putString("grade",bundle1.getString("grade"));
//                            bundle.putString("name",bundle1.getString("name"));
                            intent.putExtras(bundle1);
                            startActivity(intent);
                        }else{
                            Toast.makeText(register4_1Activity.this,"验证失败，请重新发送验证码",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            Intent intent = new Intent(register4_1Activity.this, register3_1Activity.class);
            intent.putExtras(bundle2);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

package com.test.admin.newLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.test.admin.R;
import com.test.admin.bean.AsParticipant;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class newForgetPasswordActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_step4_1);

        final EditText verifycode = (EditText) findViewById(R.id.verifycode);
        ImageButton imgBtn1 = (ImageButton)findViewById(R.id.imgBtn1);
        Button btn2 = (Button)findViewById(R.id.btn2);
        TextView phone = (TextView)findViewById(R.id.tv1);
        //Bundle bundle1 = this.getIntent().getExtras();
        //final String phonenumber=bundle1.getString("phonenumber");

        //返回
        Intent intent = getIntent();
        String phonenumber = intent.getStringExtra("phonenumber");
        phone.setText(phonenumber);
        phone.setTextColor(0xFF2BDCDC);
        phone.setTextSize(15);
        imgBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(newForgetPasswordActivity2.this,newForgetPasswordActivity1.class);
                startActivity(intent);
            }
        });

        //下一步
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接传递验证码跳转
                Intent intent = new Intent(newForgetPasswordActivity2.this,newForgetPasswordActivity3.class);
                Bundle bundle = new Bundle();
                //bundle.putString("phonenumber",phonenumber);
                bundle.putString("verifycode",verifycode.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            Intent intent = new Intent(newForgetPasswordActivity2.this, newForgetPasswordActivity1.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

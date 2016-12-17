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

public class register2_2xActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_step2_2);

        ImageButton imgBtn1 = (ImageButton)findViewById(R.id.imgBtn1);
        Button btn1 = (Button)findViewById(R.id.btn1);
        final EditText qqnum = (EditText)findViewById(R.id.qqnum);
        final EditText phnum = (EditText)findViewById(R.id.phnum);
        final EditText email = (EditText)findViewById(R.id.email);
        final Bundle bundle1 = this.getIntent().getExtras();

        imgBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register2_2xActivity.this,register1_2Activity.class);
                startActivity(intent);
            }
        });
        //下一步
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qqnum.getText().toString().matches("[1-9][0-9]{4,}")&&phnum.getText().toString().matches("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(17[0-8])|(147))\\d{8}$")&&email.getText().toString().matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")){
                    Intent intent = new Intent(register2_2xActivity.this,register3_2xActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("identity",bundle1.getString("identity"));
                    bundle.putString("qqunm",qqnum.getText().toString());
                    bundle.putString("phnum",phnum.getText().toString());
                    bundle.putString("email",email.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(register2_2xActivity.this,"请输入完整、正确的信息",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            Intent intent = new Intent(register2_2xActivity.this, register1_1Activity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

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

public class register2_2Activity extends AppCompatActivity {
    Bundle bundle2 = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_step2_2);

        ImageButton imgBtn1 = (ImageButton)findViewById(R.id.imgBtn1);
        Button btn1 = (Button)findViewById(R.id.btn1);
        final Bundle bundle1 = this.getIntent().getExtras();
        bundle2 = this.getIntent().getExtras();
        final EditText qqunm = (EditText)findViewById(R.id.qqnum);
        final EditText phnum = (EditText)findViewById(R.id.phnum);
        final EditText email = (EditText)findViewById(R.id.email);


        imgBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register2_2Activity.this,register1_2_2Activity.class);
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });
        //下一步
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qqunm.getText().toString().matches("[1-9][0-9]{4,}")&&phnum.getText().toString().matches("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(17[0-8])|(147))\\d{8}$")&&email.getText().toString().matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")){
                    Intent intent = new Intent(register2_2Activity.this,register3_2Activity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("qqnum",qqunm.getText().toString());
                    bundle.putString("phnum",phnum.getText().toString());
                    bundle.putString("email",email.getText().toString());
                    bundle.putString("college",bundle1.getString("college"));
                    bundle.putString("grade",bundle1.getString("grade"));
                    bundle.putString("identity",bundle1.getString("identity"));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(register2_2Activity.this,"请输入完整、正确的信息",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            Intent intent = new Intent(register2_2Activity.this, register1_2_2Activity.class);
            intent.putExtras(bundle2);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

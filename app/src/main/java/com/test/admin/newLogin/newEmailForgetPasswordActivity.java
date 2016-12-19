package com.test.admin.newLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.test.admin.R;

public class newEmailForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_emial_forget_password);

        Button send = (Button)findViewById(R.id.btn1);
        ImageButton back = (ImageButton)findViewById(R.id.imgBtn1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(newEmailForgetPasswordActivity.this,loginActivity.class);
                startActivity(intent);
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(newEmailForgetPasswordActivity.this, "邮件已经发送到邮箱，请注意查收", Toast.LENGTH_SHORT).show();
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

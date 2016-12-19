package com.test.admin.newLogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.test.admin.R;

public class newEmailForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_emial_forget_password);

        Button send = (Button)findViewById(R.id.btn1);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(newEmailForgetPasswordActivity.this, "邮件已经发送到邮箱，请注意查收", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.test.admin.newLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.test.admin.R;

public class newForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_forget_password1);

        ImageButton imgBtn1 = (ImageButton)findViewById(R.id.imgBtn1);
        Button btn1 = (Button)findViewById(R.id.btn1);

        imgBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(newForgetPasswordActivity.this,newForgetPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}

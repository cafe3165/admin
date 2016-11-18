package com.test.admin.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.test.admin.R;

/**
 * Created by wuzhenge on 2016/11/14.
 */

public class Registered  extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registered);
        Button btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registered_intent = new Intent(Registered.this,MainActivity.class);
                startActivity(registered_intent);
            }
        });
    }
}

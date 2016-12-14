package com.test.admin.newLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.test.admin.R;

public class identityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identity);

        ImageButton btn0 = (ImageButton) findViewById(R.id.imgBtn0);
        ImageButton btn1 = (ImageButton) findViewById(R.id.imgBtn1);
        ImageButton btn2 = (ImageButton) findViewById(R.id.imgBtn2);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(identityActivity.this,loginRegisterActivity.class);
                startActivity(intent);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(identityActivity.this,register1_1Activity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(identityActivity.this,register1_2Activity.class);
                startActivity(intent);

            }
        });
    }
}

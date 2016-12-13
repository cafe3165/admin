package com.test.admin.newLogin;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.test.admin.R;
import com.test.admin.login.applyPromulgator;
import com.test.admin.login.registerActivity;

import static android.R.attr.onClick;

public class identityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identity);
        ImageButton btn1 = (ImageButton) findViewById(R.id.imaBtn1);
        ImageButton btn2 = (ImageButton) findViewById(R.id.imaBtn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(identityActivity.this,registerActivity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(identityActivity.this,applyPromulgator.class);
                startActivity(intent);
            }
        });
    }
}

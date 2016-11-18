package com.test.admin.promulgator;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.test.admin.R;

public class headAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head);

        Button bNewNotice=(Button) findViewById(R.id.widget40) ;
        bNewNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNotice=new Intent(headAct.this,newNotice.class);
                startActivity(intentNotice);
            }
        });

        Button bNewAct=(Button) findViewById(R.id.widget39);

        bNewAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAct=new Intent(headAct.this,newActivity.class);
                startActivity(intentAct);
            }
        });
    }
}

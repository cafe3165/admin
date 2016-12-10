package com.test.admin.Participant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.test.admin.R;

public class userInfo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_activity_user_info);


        Button button =(Button) findViewById(R.id.ge_edit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(userInfo.this,InfoEdit.class);
                startActivity(intent);
            }
        });

        ImageButton Ibutton1=(ImageButton) findViewById(R.id.my_apply);
        Ibutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(userInfo.this,MyAct.class);
                startActivity(intent);
            }
        });

        ImageButton Ibutton2=(ImageButton) findViewById(R.id.my_applied);
        Ibutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(userInfo.this,MyAct.class);
                startActivity(intent);
            }
        });
    }
}

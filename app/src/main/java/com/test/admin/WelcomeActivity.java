package com.test.admin;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.view.Window;

import static com.test.admin.R.id.login;


public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);


        new Handler().postDelayed(new Runnable(){
    @Override
    public void run() {
        Intent intent = new Intent(WelcomeActivity.this,WelcomeActivity.class);
        startActivity(intent);
        WelcomeActivity.this.finish();

        Intent intent2 = new Intent(WelcomeActivity.this,com.test.admin.login.MainActivity.class);
        startActivity(intent2);
    }

}, 2500);

}



}

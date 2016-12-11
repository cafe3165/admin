package com.test.admin;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.view.Window;
import android.widget.Toast;
import android.widget.ImageView;

import com.test.admin.login.MainActivity;
import com.test.admin.login.registerActivity;

import static com.test.admin.R.id.activity_login_register;
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

        Intent intent2 = new Intent(WelcomeActivity.this,com.test.admin.newLogin.loginRegisterActivity.class);
        startActivity(intent2);
    }

}, 2500);

/*
        final Handler handler=new Handler();
        Runnable runnable=new Runnable(){
            @Override
            public void run() {
                // TODO Auto-generated method stub
                //要做的事情，这里再次调用此Runnable对象，以实现每两秒实现一次的定时器操作
                handler.postDelayed(this, 2000);
            }
        };
        handler.postDelayed(runnable, 2000);
        handler.removeCallbacks(runnable);

*/


//        super.onCreate(savedInstanceState);
//        ///setContentView(R.layout.activity_welcome);
//        //定义一个Toast对象
//        Toast imageToast=new Toast(WelcomeActivity.this);
//        //定义一个InageView对象
//        ImageView imageView=new ImageView(WelcomeActivity.this);
//        //为ImageView对象设置上去一张图片
//        imageView.setImageResource(R.drawable.w3);
//        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//        //将ImageView对象绑定到Toast对象imageToasr上面去
//        imageToast.setView(imageView);
//        //设置Toast对象显示的时间长短
//        imageToast.setDuration(Toast.LENGTH_LONG);
//        //显示Toast
//        imageToast.show();
//
//        Intent intent2 = new Intent(WelcomeActivity.this,com.test.admin.login.MainActivity.class);
//       startActivity(intent2);
}
//


}

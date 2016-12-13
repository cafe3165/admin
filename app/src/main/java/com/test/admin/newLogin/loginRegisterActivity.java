package com.test.admin.newLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.test.admin.R;
import com.test.admin.login.MainActivity;
import com.test.admin.login.applyPromulgator;
import com.test.admin.login.registerActivity;

public class loginRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_new_login);
        Button btn1 = (Button)findViewById(R.id.login);
        Button btn2 = (Button)findViewById(R.id.register);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginRegisterActivity.this, loginActivity.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginRegisterActivity.this, identityActivity.class);
                startActivity(intent);
            }
        });

    }
    long exitTime = 0;
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if(System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(this,"再点击一次返回键退出",Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }
            else{
                finish();
                Intent home = new Intent(Intent.ACTION_MAIN);
                home.addCategory(Intent.CATEGORY_HOME);
                startActivity(home);
                //System.exit(0);
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

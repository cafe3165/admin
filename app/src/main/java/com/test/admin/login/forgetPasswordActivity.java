package com.test.admin.login;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.test.admin.R;

/**
 * Created by wuzhenge on 2016/11/13.
 */

public class forgetPasswordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password_activity);
        Button btn1 = (Button)findViewById(R.id.btn1);
        Button btn2 = (Button)findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgetPassword_intent = new Intent(forgetPasswordActivity.this,MainActivity.class);
                startActivity(forgetPassword_intent);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                showDialog(1);
            }

        });
    }
    protected Dialog onCreateDialog(int id)
    {
        Dialog dialog = null;
        switch(id)
        {
            case 1:
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setMessage("修改成功！");
                b.setPositiveButton(
                        "确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                dialog = b.create();
                break;
            default:break;
        }
        return dialog;
    }
}

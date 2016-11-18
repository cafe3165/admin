package com.test.admin.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import cn.bmob.v3.Bmob;
import com.test.admin.R;

import static com.test.admin.R.id.btn4;


public class MainActivity extends AppCompatActivity {
    private static final String[] items = new String[]{"管理员", "发布者", "参与者"};
    private String str1 = "忘记密码?";
    private String str2 = "还未注册?";
    Button btn1,btn2,btn3,btn4;
    TextView tv1,tv2;
    String str;
    String s1 = "管理员";
    String s2 = "发布者";
    String s3 = "参与者";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        Bmob.initialize(this,"b00c3fa99b902d98cfed5e24a278166d");

        ArrayAdapter<String> source = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);

        Spinner Spinner1 = (Spinner) findViewById(R.id.Spinner1);
        Spinner1.setAdapter(source);
        btn1=(Button)findViewById(R.id.login);
        btn2=(Button)findViewById(R.id.btn2);
        btn3=(Button)findViewById(R.id.btn3);
        btn4=(Button)findViewById(R.id.btn4);

        //tv1=(TextView)findViewById(tv5);
        //tv2=(TextView)findViewById(tv6);


        //SpannableString ss1 = new SpannableString(str1);

        Spinner1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3)
            {
                str = items[arg2];
                arg0.setVisibility(View.VISIBLE);

                if(s1.equals(str))
                {
                    btn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent login_intent=new Intent(MainActivity.this, com.test.admin.activity.MainActivity.class);
                            startActivity(login_intent);
                        }
                    });
                }
                else if(s2.equals(str))
                {
                    btn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent login_intent=new Intent(MainActivity.this,com.test.admin.promulgator.MainActivity.class);
                            startActivity(login_intent);
                        }
                    });
                }
                else if(s3.equals(str))
                {
                    btn1.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(MainActivity.this,com.test.admin.activitypromulgator.MainActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
            public void onNothingSelected(AdapterView<?> arg0) {}

        });



        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login_intent=new Intent(MainActivity.this,forgetPasswordActivity.class);
                startActivity(login_intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,registerActivity.class);
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,applyPromulgator.class);
                startActivity(intent);
            }
        });

/*
        ss1.setSpan(new ClickableSpan()
        {
            public void onClick(View view)
            {
                Intent forgetPasswordIntent = new Intent(MainActivity.this,forgetPasswordActivity.class);
                startActivity(forgetPasswordIntent);
            }
        },0,str1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //tv5.setText(str1);
        //tv5.setMovementMethod(LinkMovementMethod.getInstance());
        */

    }
}

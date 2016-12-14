package com.test.admin.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.test.admin.R;
import com.test.admin.bean.AsAdministrator;
import com.test.admin.bean.AsParticipant;
import com.test.admin.bean.AsPromulgator;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import static com.test.admin.bean.Parameters.pObjectdId;


public class MainActivity extends AppCompatActivity {
    private static final String[] items = new String[]{"管理员", "发布者", "参与者"};
    private String str1 = "忘记密码?";
    private String str2 = "还未注册?";
    Button btn1, btn2, btn3, btn4;
    TextView tv1, tv2;
    String str;//选择的身份
    String s1 = "管理员";
    String s2 = "发布者";
    String s3 = "参与者";


    private EditText Username;//用户名
    private EditText Password;//密码


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bmob.initialize(this, "b00c3fa99b902d98cfed5e24a278166d");//初始化
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        ArrayAdapter<String> source = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);

        final Spinner Spinner1 = (Spinner) findViewById(R.id.Spinner1);
        Spinner1.setAdapter(source);
        btn1 = (Button) findViewById(R.id.login);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        //tv1=(TextView)findViewById(tv5);
        //tv2=(TextView)findViewById(tv6);

        //par=new AsParticipant();
        //prom=new AsProm();
        //admin=new Asadmin();

        Username = (EditText) findViewById(R.id.Username);
        Password = (EditText) findViewById(R.id.Password);


        //SpannableString ss1 = new SpannableString(str1);

        Spinner1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                str = items[arg2];
                arg0.setVisibility(View.VISIBLE);

                //管理员
                if (s1.equals(str)) {
                    Username.setHint("管理员账号");
                    btn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            adminverify();
                        }
                    });
                }
                //发布者
                else if (s2.equals(str)) {
                    Username.setHint("邮箱");
                    btn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            promverify();
                        }
                    });
                }
                //参与者
                else if (s3.equals(str)) {
                    Username.setHint("手机号");
                    btn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            parverify();
                        }
                    });
                }
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }

        });


        //忘记密码跳转
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login_intent = new Intent(MainActivity.this, forgetPasswordActivity.class);
                startActivity(login_intent);
            }
        });

        //还未注册跳转
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, registerActivity.class);
                startActivity(intent);
            }
        });

        //申请成为发布者跳转
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, applyPromulgator.class);
                startActivity(intent);
            }
        });

        TextView textView1=(TextView)findViewById(R.id.text3);



        String text1="注册";
        SpannableString spannableString1=new SpannableString(text1);

        spannableString1.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,registerActivity.class);
                startActivity(intent);
            }
        }, 0, text1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView1.setText(spannableString1);
        textView1.setMovementMethod(LinkMovementMethod.getInstance());

        noLine mNoUnderlineSpan = new noLine();
        if (textView1.getText() instanceof Spannable) {
            Spannable s = (Spannable) textView1.getText();
            s.setSpan(mNoUnderlineSpan, 0, s.length(), Spanned.SPAN_MARK_MARK);
        }


    }


    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //参与者验证（限定用手机号登录）
    private void parverify() {
        String username = Username.getText().toString();
        final String moblienum = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(17[0-8])|(147))\\d{8}$";
        if (username.matches(moblienum)) {
            parsignin();
        } else {
            toast("请使用手机号登录");
        }
    }


    //参与者登录
    private void parsignin() {
        btn1.setEnabled(false);
        final AsParticipant par = new AsParticipant();
        par.setUsername(Username.getText().toString());
        par.setPassword(Password.getText().toString());
        par.login(new SaveListener<AsParticipant>() {
            @Override
            public void done(AsParticipant asParticipant, BmobException e) {
                if (e == null) {
                    toast("登录成功");
                    Intent intent = new Intent(MainActivity.this, com.test.admin.Participant.newMain.class);
                    startActivity(intent);
                    Password.setText("");
                    btn1.setEnabled(true);
                    pObjectdId = (String) BmobUser.getObjectByKey("objectdId");
                } else {
                    toast("登录失败"+e.getMessage());
                    btn1.setEnabled(true);
                }
            }
        });

    }

    //发布者验证（限定用邮箱登录）
    private void promverify() {
        String username = Username.getText().toString();
        final String email = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        if (username.matches(email)) {
            promsignin();
        } else {
            toast("请使用邮箱登录");
        }
    }

    //发布者登录
    private void promsignin() {
        btn1.setEnabled(false);
        final AsPromulgator asp = new AsPromulgator();
        asp.setUsername(Username.getText().toString());
        asp.setPassword(Password.getText().toString());
        asp.login(new SaveListener<AsPromulgator>() {
            @Override
            public void done(AsPromulgator asPromulgator, BmobException e) {
                if (e == null) {
                    toast("登录成功");
                    Intent intent = new Intent(MainActivity.this, com.test.admin.promulgator.newMain.class);
                    startActivity(intent);
                    btn1.setEnabled(true);
                    Password.setText("");
                } else {
                    toast("登录失败");
                    btn1.setEnabled(true);
                }
            }
        });
    }

    //管理员验证（限定用4位数登录）
    private void adminverify() {
        String username = Username.getText().toString();
        final String admincode = "^[0-9]{4}$";
        if (username.matches(admincode)) {
            adminsignin();
        } else {
            toast("请使用管理员账号登录");
        }
    }

    //管理员登录
    private void adminsignin() {
        btn1.setEnabled(false);
        final AsAdministrator admin = new AsAdministrator();
        admin.setUsername(Username.getText().toString());
        admin.setPassword(Password.getText().toString());
        admin.login(new SaveListener<AsAdministrator>() {
            @Override
            public void done(AsAdministrator asAdministrator, BmobException e) {
                if (e == null) {
                    toast("登录成功");
                    Intent login_intent = new Intent(MainActivity.this, com.test.admin.activity.MainActivity.class);
                    startActivity(login_intent);
                    btn1.setEnabled(true);
                    Password.setText("");
                } else {
                    toast("登录失败");
                    btn1.setEnabled(true);
                }
            }
        });
    }
}



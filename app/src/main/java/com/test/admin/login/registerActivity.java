package com.test.admin.login;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.test.admin.R;
import com.test.admin.bean.AsParticipant;
import com.test.admin.model.Aspar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

public class registerActivity extends AppCompatActivity {

    private String[] items = new String[]{"数计学院", "经管学院", "物信学院", "生工学院", "环资学院", "紫金学院", "海洋学院"};
    private String[] items2 = new String[]{"2013", "2014", "2015", "2016"};

    private EditText StuNum;//学号
    private EditText StuName;//姓名
    private EditText PhoneNum;//手机号
    private EditText VerifyCode;//验证码
    private EditText Password;//设置密码
    private EditText PasswordCon;//确认密码
    private Aspar par;//参与者
    private String college;//学院
    private String grade;//年级

    //private Animation myAnimation=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        //Button btn1,btn2;

        ArrayAdapter<String> source=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        ArrayAdapter<String> source2=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items2);

        final Spinner Spinner1=(Spinner)findViewById(R.id.Spinner1);
        Spinner Spinner2=(Spinner)findViewById(R.id.Spinner2);
        Button btn1 = (Button)findViewById(R.id.btn2);//注册
        Button btn2 = (Button)findViewById(R.id.btn3);//返回
        Button btn3 = (Button)findViewById(R.id.btn4);//账号被注册
        Button btn4 =(Button)findViewById(R.id.SmsSend);//发送验证码

        //EditText
        StuNum=(EditText)findViewById(R.id.StuNum);
        StuName=(EditText)findViewById(R.id.StuName);
        PhoneNum=(EditText)findViewById(R.id.PhoneNum);
        VerifyCode=(EditText)findViewById(R.id.VerifyCode);
        Password=(EditText)findViewById(R.id.et3);
        PasswordCon=(EditText)findViewById(R.id.pwconfigure);

        par=new Aspar();


        Spinner1.setAdapter((SpinnerAdapter) source);//学院
        Spinner2.setAdapter((SpinnerAdapter)source2);//年级



        //学院选择
        Spinner1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                college=items[arg2];
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {}

        });
        //年级选择
        Spinner2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                grade=items2[arg2];
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {}
        });

        //注册
        btn1.setOnClickListener(new View.OnClickListener()
                                {
                                    public void onClick(View v)
                                    {
                                        //检测信息是否完全输入
                                        if(TextUtils.isEmpty(StuNum.getText())||TextUtils.isEmpty(StuName.getText())||TextUtils.isEmpty(PhoneNum.getText())||TextUtils.isEmpty(VerifyCode.getText())||TextUtils.isEmpty(Password.getText())||TextUtils.isEmpty(PasswordCon.getText())){
                                            toast("请输入完整信息");
                                        }
                                        else{
                                            smsvetify();
                                        }
                                        //showDialog(1);
                                    }
                                }
        );

        //返回
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register_intent = new Intent(registerActivity.this,MainActivity.class);
                startActivity(register_intent);
            }
        });

        //账号被注册
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registered_intent = new Intent(registerActivity.this,Registered.class);
                startActivity(registered_intent);
            }
        });

        //发送验证码按钮
        btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //当输入为手机号时检测手机号是否已被注册
                if (PhoneNum.getText().toString().matches("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(17[0-8])|(147))\\d{8}$")){
                    //检测手机号是否已被注册
                    BmobQuery<AsParticipant> query = new BmobQuery<AsParticipant>();
                    query.addWhereEqualTo("username",PhoneNum.getText().toString());
                    query.findObjects(new FindListener<AsParticipant>() {
                        @Override
                        public void done(List<AsParticipant> list, BmobException e) {
                            if (e==null&&list.size()!=0){
                                toast("手机号已被注册,请使用其它号码注册");//手机号已被注册
                            }else if(e==null&&list.isEmpty()){
                                //手机号未被注册，发送验证码
                               sendsms();
                            }else{
                                toast("查询失败:"+e.getErrorCode()+" "+e.getMessage());
                            }
                        }
                    });
                }else{
                    toast("请输入手机号");
                }
            }
        });

    }
    //发送验证码
    private void sendsms(){
        BmobException a = par.AsParSendSms(PhoneNum.getText().toString());
        if(a==null){
            toast("验证码发送成功");
        }else{
            toast("验证码发送失败");
        }
    }

    //短信验证码验证
    private void smsvetify() {
        String pnum=PhoneNum.getText().toString();
        String code=VerifyCode.getText().toString();
        if (!TextUtils.isEmpty(pnum)&&!TextUtils.isEmpty(code)){
            BmobException a=par.AsparSmsSignup(pnum,code);
            if (a==null){
                //开始注册
                signup();
                toast("验证成功");
            }else{
                toast("验证失败");
            }
        }else{
            toast("请输入手机号和验证码");
        }
    }

    public void toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //检验密码一致性并注册
    private void signup(){
        String pw=Password.getText().toString();
        String pwc=PasswordCon.getText().toString();
        if(!TextUtils.equals(pw,pwc)){
            //密码不一致
            toast("确认密码不一致，请重新输入");
        }else {
            //密码一致，进行注册
            String pnum=PhoneNum.getText().toString();
            String stunum=StuNum.getText().toString();
            String stuname=StuName.getText().toString();
            BmobException a= par.AsParSignup(pnum,pw,stunum,stuname,college,grade);
            if (a==null){
                toast("注册成功");
                StuNum.setText("");
                StuName.setText("");
                VerifyCode.setText("");
                PhoneNum.setText("");
                Password.setText("");
                PasswordCon.setText("");
            }else{
                toast("注册失败");
            }
        }
    }



//    protected Dialog onCreateDialog(int id)
//    {
//        Dialog dialog = null;
//        switch(id)
//        {
//            case 1:
//                AlertDialog.Builder b = new AlertDialog.Builder(this);
//                b.setMessage("注册成功！");
//                b.setPositiveButton(
//                        "确定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {}
//                        });
//                dialog = b.create();
//                break;
//            default:break;
//        }
//        return dialog;
//    }
}
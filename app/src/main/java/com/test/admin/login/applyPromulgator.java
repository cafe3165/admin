package com.test.admin.login;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.test.admin.R;
import com.test.admin.bean.AsPermissionApplying;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import static android.R.attr.button;
import static android.R.attr.tabStripEnabled;

/**
 * Created by wuzhenge on 2016/11/17.
 */

public class applyPromulgator extends AppCompatActivity {
    String []identify = new String[]{"社团负责人","部门负责人","校级组织","学院负责人","班级负责人"};
    String []range = new String[]{"数计学院", "经管学院", "物信学院", "生工学院", "环资学院", "紫金学院", "海洋学院"};
    String []grade = new String[]{"2013", "2014", "2015", "2016"};

    private EditText email;
    private EditText pnum;
    private EditText QQnum;
    private EditText addition;

    private String peridentity;
    private String pimpermission1;//通知权限学院
    private String pimpermission2;//通知权限年级
    private String pacpermission1;//活动权限学院
    private String pacpermission2;//活动权限年级

    private AsPermissionApplying aspa;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promulgator);
        Spinner spinner1 = (Spinner)findViewById(R.id.spinner1);//身份
        Spinner spinner2 = (Spinner)findViewById(R.id.spinner2);//通知权限学院
        Spinner spinner3 = (Spinner)findViewById(R.id.spinner3);//通知权限年级
        Spinner spinner4 = (Spinner)findViewById(R.id.spinner4);//活动权限学院
        Spinner spinner5 = (Spinner)findViewById(R.id.spinner5);//活动权限年级
        Button btn = (Button)findViewById(R.id.button);

        ArrayAdapter<String> source1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, identify);
        ArrayAdapter<String> source2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, range);
        ArrayAdapter<String> source3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, grade);

        spinner1.setAdapter(source1);
        spinner2.setAdapter(source2);
        spinner3.setAdapter(source3);
        spinner4.setAdapter(source2);
        spinner5.setAdapter(source3);

        email=(EditText)findViewById(R.id.email);
        pnum=(EditText)findViewById(R.id.pnum);
        QQnum=(EditText)findViewById(R.id.QQnum);
        addition=(EditText)findViewById(R.id.addition);

        aspa=new AsPermissionApplying();

        //身份
        spinner1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
                peridentity=identify[arg2];
            }
            public void onNothingSelected(AdapterView<?> arg0) {}

        });

        //通知权限学院
        spinner2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
                pimpermission1=range[arg2];
            }
            public void onNothingSelected(AdapterView<?> arg0) {}

        });

        //通知权限年级
        spinner3.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
                pimpermission2=grade[arg2];
            }
            public void onNothingSelected(AdapterView<?> arg0) {}

        });

        //活动权限学院
        spinner4.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
                pacpermission1=range[arg2];
            }
            public void onNothingSelected(AdapterView<?> arg0) {}

        });

        //活动权限年级
        spinner5.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
                pacpermission2=grade[arg2];
            }
            public void onNothingSelected(AdapterView<?> arg0) {}

        });

        btn.setOnClickListener(new View.OnClickListener()
        {
            //点击申请
            public void onClick(View v)
            {
                sendmessage();
                //showDialog(1);
            }

        });
    }

    public void toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //发送申请消息
    private void sendmessage() {
        final String qqnumverify="[1-9][0-9]{4,}";
        final String pnumverify="^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(17[0-8])|(147))\\d{8}$";
        final String emailverify="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        if (email.getText().toString().matches(emailverify)&&QQnum.getText().toString().matches(qqnumverify)&&pnum.getText().toString().matches(pnumverify)){
            aspa.setPerEmail(email.getText().toString());
            aspa.setPerQQNumber(QQnum.getText().toString());
            aspa.setPerTelNumber(pnum.getText().toString());
            aspa.setPerIdentity(peridentity);
            aspa.setPerImPermission_1(pimpermission1);
            aspa.setPerImPermission_2(pimpermission2);
            aspa.setPerAcPermission_1(pacpermission1);
            aspa.setPerAcPermission_2(pacpermission2);
            aspa.setPerSupplement(addition.getText().toString());
            aspa.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e==null){
                        toast("申请发送成功，等待管理员审核");
                    }else{
                        toast("申请发送失败"+e.getErrorCode());
                    }
                }
            });
        }else {
            toast("请输入正确的信息");
        }
    }

    protected Dialog onCreateDialog(int id)
    {
        Dialog dialog = null;
        switch(id)
        {
            case 1:
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setMessage("已发出申请！");
                b.setPositiveButton(
                        "确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {}
                        });
                dialog = b.create();
                break;
            default:break;
        }
        return dialog;
    }
}
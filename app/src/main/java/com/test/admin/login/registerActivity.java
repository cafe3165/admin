package com.test.admin.login;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.test.admin.R;

public class registerActivity extends AppCompatActivity {

    private String[] items = new String[]{"数计学院", "经管学院", "物信学院", "生工学院", "环资学院", "紫金学院", "海洋学院"};
    private String[] items2 = new String[]{"2013", "2014", "2015", "2016"};

    //private Animation myAnimation=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        //Button btn1,btn2;

        ArrayAdapter<String> source=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        ArrayAdapter<String> source2=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items2);

        Spinner Spinner1=(Spinner)findViewById(R.id.Spinner1);
        Spinner Spinner2=(Spinner)findViewById(R.id.Spinner2);
        Button btn1 = (Button)findViewById(R.id.btn2);
        Button btn2 = (Button)findViewById(R.id.btn3);
        Button btn3 = (Button)findViewById(R.id.btn4);

        Spinner1.setAdapter((SpinnerAdapter) source);
        Spinner2.setAdapter((SpinnerAdapter)source2);

        Spinner1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {}
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {}

        });
        Spinner2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {}
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {}
        });

        btn1.setOnClickListener(new View.OnClickListener()
                                {
                                    public void onClick(View v)
                                    {
                                        showDialog(1);
                                    }

                                });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register_intent = new Intent(registerActivity.this,MainActivity.class);
                startActivity(register_intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registered_intent = new Intent(registerActivity.this,Registered.class);
                startActivity(registered_intent);
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
                b.setMessage("注册成功！");
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
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
import android.widget.Spinner;

import com.test.admin.R;

import static android.R.attr.button;

/**
 * Created by wuzhenge on 2016/11/17.
 */

public class applyPromulgator extends AppCompatActivity {
    String []identify = new String[]{"社团负责人","部门负责人","校级组织","学院负责人","班级负责人"};
    String []range = new String[]{"数计学院", "经管学院", "物信学院", "生工学院", "环资学院", "紫金学院", "海洋学院"};
    String []grade = new String[]{"2013", "2014", "2015", "2016"};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promulgator);
        Spinner spinner1 = (Spinner)findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner)findViewById(R.id.spinner2);
        Spinner spinner3 = (Spinner)findViewById(R.id.spinner3);
        Spinner spinner4 = (Spinner)findViewById(R.id.spinner4);
        Spinner spinner5 = (Spinner)findViewById(R.id.spinner5);
        Button btn = (Button)findViewById(R.id.button);

        ArrayAdapter<String> source1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, identify);
        ArrayAdapter<String> source2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, range);
        ArrayAdapter<String> source3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, grade);

        spinner1.setAdapter(source1);
        spinner2.setAdapter(source2);
        spinner3.setAdapter(source3);
        spinner4.setAdapter(source2);
        spinner5.setAdapter(source3);

        spinner1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {}
            public void onNothingSelected(AdapterView<?> arg0) {}

        });

        spinner2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {}
            public void onNothingSelected(AdapterView<?> arg0) {}

        });

        spinner3.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {}
            public void onNothingSelected(AdapterView<?> arg0) {}

        });

        spinner4.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {}
            public void onNothingSelected(AdapterView<?> arg0) {}

        });

        spinner5.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {}
            public void onNothingSelected(AdapterView<?> arg0) {}

        });

        btn.setOnClickListener(new View.OnClickListener()
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

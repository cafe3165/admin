package com.test.admin.promulgator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.test.admin.R;
import com.test.admin.bean.AsActivity;
import com.test.admin.bean.AsImformation;
import com.test.admin.bean.AsPromulgator;
import com.test.admin.model.AsImformationMethod;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

import static com.test.admin.bean.Parameters.pObjectdId;
import static com.test.admin.bean.Parameters.staticObjectdId;

public class PromulgatorImformationModify extends BaseActivity {

    private EditText imTitle;
    private EditText imOrganizer;
    private EditText imContent;
    private EditText imAudiences;
    private Spinner imPushScope_1;
    private Spinner imPushScope_2;
    private Button modify;

    String mImPushScope_1 = null;
    String mImPushScope_2 = null;
    String[] mCollege;
    String[] mGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promulgator_imformation_modify);

        setCustomTitle("通知编辑", false);

        String[] college = this.getResources().getStringArray(R.array.xueyuan);
        String[] grade1 = this.getResources().getStringArray(R.array.grade);
        ArrayAdapter<String> collegeAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,college);
        ArrayAdapter<String> gradeAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,grade1);

        mCollege = college;
        mGrade = grade1;

        imTitle = (EditText)findViewById(R.id.imTitle);
        imOrganizer = (EditText)findViewById(R.id.imOrganizer);
        imContent = (EditText)findViewById(R.id.imContent);
        imAudiences = (EditText)findViewById(R.id.imAudiences);
        imPushScope_1 = (Spinner)findViewById(R.id.imPushScope_1);
        imPushScope_2 = (Spinner)findViewById(R.id.imPushScope_2);
        modify = (Button)findViewById(R.id.modify);

        imPushScope_1.setAdapter(collegeAdapter);
        imPushScope_2.setAdapter(gradeAdapter);
        imPushScope_1.setOnItemSelectedListener(new onItemClickListener());
        imPushScope_2.setOnItemSelectedListener(new onItemClickListener());

        BmobQuery<AsImformation> query = new BmobQuery<AsImformation>();
        query.getObject(staticObjectdId, new QueryListener<AsImformation>() {
            @Override
            public void done(AsImformation asImformation, BmobException e) {

                if(e == null){

                    imTitle.setText(asImformation.getImTitle());
                    imOrganizer.setText(asImformation.getImOrganizer());
                    imContent.setText(asImformation.getImContent());
                    imAudiences.setText(asImformation.getImAudiences());
                    //初始化下拉框
                    initSpinner1(asImformation.getImPushScope_1());
                    initSpinner2(asImformation.getImPushScope_2());
                }
            }
        });

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //改变按钮状态为不可按
                modify.setEnabled(false);
                //获取EditText信息
                String mImTitle = imTitle.getText().toString();
                String mImOrganizer = imOrganizer.getText().toString();
                String mImContent = imContent.getText().toString();
                String mImAudiences = imAudiences.getText().toString();
                pObjectdId = (String) AsPromulgator.getObjectByKey("objectId");
                //发布通知
                AsImformationMethod asImformation = new AsImformationMethod();
                asImformation.asImModify(modify,staticObjectdId,mImTitle,mImOrganizer,mImContent,
                        mImAudiences,mImPushScope_1,mImPushScope_2);
            }
        });
    }
    //下拉框监听
    class onItemClickListener implements Spinner.OnItemSelectedListener{

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3){

            switch (arg0.getId()){
                case R.id.imPushScope_1:
                    mImPushScope_1 = mCollege[arg2];
                    break;
                case R.id.imPushScope_2:
                    mImPushScope_2 = mGrade[arg2];
                    break;
            }
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    //匹配下拉框
    void initSpinner1(String college){

        for (int i = 0; i < mCollege.length; i++){
            if(college.equals(mCollege[i])){
                imPushScope_1.setSelection(i,true);
                break;
            }
        }
    }
    void initSpinner2(String grade){

        for (int i = 0; i < mGrade.length; i ++){
            if(grade.equals(mGrade[i])){
                imPushScope_2.setSelection(i,true);
                break;
            }
        }
    }
}

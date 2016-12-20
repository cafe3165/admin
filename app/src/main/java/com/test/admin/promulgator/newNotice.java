package com.test.admin.promulgator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.test.admin.R;
import com.test.admin.bean.AsImformation;
import com.test.admin.bean.AsPromulgator;
import com.test.admin.model.AsImformationMethod;

import static com.test.admin.bean.Parameters.pObjectdId;

public class newNotice extends BaseActivity {

    private EditText imTitle;
    private EditText imOrganizer;
    private EditText imContent;
    private EditText imAudiences;
    private Spinner imPushScope_1;
    private Spinner imPushScope_2;
    private Button publish;

    String mImPushScope_1 = null;
    String mImPushScope_2 = null;
    String[] mCollege;
    String[] mGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notice);

        setCustomTitle("发布通知", false);

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
        publish = (Button)findViewById(R.id.publish);

        imPushScope_1.setAdapter(collegeAdapter);
        imPushScope_2.setAdapter(gradeAdapter);
        imPushScope_1.setOnItemSelectedListener(new onItemClickListener());
        imPushScope_2.setOnItemSelectedListener(new onItemClickListener());

        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //改变按钮状态为不可按
                publish.setEnabled(false);
                //获取EditText信息
                String mImTitle = imTitle.getText().toString();
                String mImOrganizer = imOrganizer.getText().toString();
                String mImContent = imContent.getText().toString();
                String mImAudiences = imAudiences.getText().toString();
                pObjectdId = (String) AsPromulgator.getObjectByKey("objectId");
                //发布通知
                AsImformationMethod asImformation = new AsImformationMethod();
                asImformation.asImAdd(publish,mImTitle,mImOrganizer,mImContent,mImAudiences,mImPushScope_1,mImPushScope_2,pObjectdId);
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
}

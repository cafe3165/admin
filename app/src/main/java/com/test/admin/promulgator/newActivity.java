package com.test.admin.promulgator;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

import com.test.admin.R;
import com.test.admin.bean.AsPromulgator;
import com.test.admin.model.AsAcApply;

import java.util.ArrayList;
import java.util.List;

import static com.test.admin.bean.Parameters.pObjectdId;

public class newActivity extends AppCompatActivity{

    private EditText acTitle;
    private EditText acOrganizer;
    private EditText acStartTime;
    private EditText acDeadLine;
    private EditText acPlace;
    private EditText acContent;
    private EditText acAudiences;
    private Spinner acPushScope_1;
    private Spinner acPushScope_2;
    private Button acIssue;
    private CheckBox present;
    private CheckBox outdoors;
    private CheckBox official;
    private CheckBox celebrity;
    private CheckBox grade;
    private String myPresent;
    private String myOutdoors;
    private String myOfficial;
    private String myCelebrity;
    private String myGrade;

    String myAcPushScope_1 = null;
    String myAcPushScope_2 = null;
    String[] mCollege;
    String[] mGrade;

    //TypedArray typedArray = getResources().obtainTypedArray(R.array.xueyuan);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        String[] college = this.getResources().getStringArray(R.array.xueyuan);
        String[] grade1 = this.getResources().getStringArray(R.array.grade);
        ArrayAdapter<String> collegeAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,college);
        ArrayAdapter<String> gradeAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,grade1);

        mCollege = college;
        mGrade = grade1;

        acTitle = (EditText)findViewById(R.id.acTitle);
        acOrganizer = (EditText)findViewById(R.id.acOrganizer);
        acStartTime = (EditText)findViewById(R.id.acStartTime);
        acDeadLine = (EditText)findViewById(R.id.acDeadLine);
        acPlace = (EditText)findViewById(R.id.acPlace);
        acContent = (EditText)findViewById(R.id.acContent);
        acAudiences = (EditText)findViewById(R.id.acAudiences);
        acPushScope_1 = (Spinner) findViewById(R.id.acPushScope_1);
        acPushScope_2 = (Spinner) findViewById(R.id.acPushScope_2);
        acIssue = (Button)findViewById(R.id.acIssue);
        present = (CheckBox)findViewById(R.id.present);
        outdoors = (CheckBox)findViewById(R.id.outdoors);
        official = (CheckBox)findViewById(R.id.official);
        celebrity = (CheckBox)findViewById(R.id.celebrity);
        grade = (CheckBox)findViewById(R.id.grade);

        acPushScope_1.setAdapter(collegeAdapter);
        acPushScope_2.setAdapter(gradeAdapter);

        present.setOnCheckedChangeListener(new onCheckedChangeListener());
        outdoors.setOnCheckedChangeListener(new onCheckedChangeListener());
        official.setOnCheckedChangeListener(new onCheckedChangeListener());
        celebrity.setOnCheckedChangeListener(new onCheckedChangeListener());
        grade.setOnCheckedChangeListener(new onCheckedChangeListener());
        acPushScope_1.setOnItemSelectedListener(new onItemClickListener());
        acPushScope_2.setOnItemSelectedListener(new onItemClickListener());

        acIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //改变按钮状态为不可按
                acIssue.setEnabled(false);
                //获取EditText上的输入
                String myAcTitle = acTitle.getText().toString();
                String myAcOrganizer = acOrganizer.getText().toString();
                String myAcStarTime = acStartTime.getText().toString();
                String myAcDeadTime = acDeadLine.getText().toString();
                String myAcPlace = acPlace.getText().toString();
                String myAcContent = acContent.getText().toString();
                String myAcAudiences = acAudiences.getText().toString();
                pObjectdId = (String) AsPromulgator.getObjectByKey("objectId");
                List<String> myLabel = new ArrayList<String>();

                if(myPresent != null)myLabel.add(myPresent);
                if(myOutdoors != null)myLabel.add(myOutdoors);
                if(myOfficial != null)myLabel.add(myOfficial);
                if(myCelebrity != null)myLabel.add(myCelebrity);
                if(myGrade != null)myLabel.add(myGrade);
                //发布活动，成功推到活动申请表
                AsAcApply asAcApply = new AsAcApply();
                asAcApply.acApplyAdd(acIssue,myAcTitle, myAcOrganizer, myAcStarTime, myAcDeadTime, myAcPlace, myAcContent,
                        myAcAudiences, myAcPushScope_1, myAcPushScope_2,myLabel,pObjectdId);
            }
        });
    }
    //标签选择监听
    class onCheckedChangeListener implements CompoundButton.OnCheckedChangeListener{

        public void onCheckedChanged(CompoundButton compoundButton, boolean b){

            switch (compoundButton.getId()){
                case R.id.present:
                    if(present.isChecked()){
                        myPresent = present.getText().toString();
                    }else{
                        myPresent = null;
                    }
                    break;
                case R.id.outdoors:
                    if(outdoors.isChecked()){
                        myOutdoors = outdoors.getText().toString();
                    }else{
                        myOutdoors = null;
                    }
                    break;
                case R.id.official:
                    if(official.isChecked()){
                        myOfficial = official.getText().toString();
                    }else{
                        myOfficial = null;
                    }
                    break;
                case R.id.celebrity:
                    if(celebrity.isChecked()){
                        myCelebrity = celebrity.getText().toString();
                    }else{
                        myCelebrity = null;
                    }
                    break;
                case R.id.grade:
                    if(grade.isChecked()){
                        myGrade = grade.getText().toString();
                    }else{
                        myGrade = null;
                    }
                    break;
            }
        }
    }
    //下拉框监听
    class onItemClickListener implements Spinner.OnItemSelectedListener{

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3){

            switch (arg0.getId()){
                case R.id.acPushScope_1:
                    myAcPushScope_1 = mCollege[arg2];
                    break;
                case R.id.acPushScope_2:
                    myAcPushScope_2 = mGrade[arg2];
                    break;
            }
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
}


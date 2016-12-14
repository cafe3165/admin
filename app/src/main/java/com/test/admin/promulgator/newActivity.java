package com.test.admin.promulgator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.test.admin.R;
import com.test.admin.bean.AsPromulgator;
import com.test.admin.model.AsAcApply;

import java.util.ArrayList;
import java.util.List;

import static com.test.admin.bean.Parameters.pObjectdId;

public class newActivity extends AppCompatActivity {

    private EditText acTitle;
    private EditText acOrganizer;
    private EditText acStartTime;
    private EditText acDeadLine;
    private EditText acPlace;
    private EditText acContent;
    private EditText acAudiences;
    private EditText acPushScope_1;
    private EditText acPushScope_2;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        acTitle = (EditText)findViewById(R.id.acTitle);
        acOrganizer = (EditText)findViewById(R.id.acOrganizer);
        acStartTime = (EditText)findViewById(R.id.acStartTime);
        acDeadLine = (EditText)findViewById(R.id.acDeadLine);
        acPlace = (EditText)findViewById(R.id.acPlace);
        acContent = (EditText)findViewById(R.id.acContent);
        acAudiences = (EditText)findViewById(R.id.acAudiences);
        acPushScope_1 = (EditText)findViewById(R.id.acPushScope);
        acIssue = (Button)findViewById(R.id.acIssue);
        present = (CheckBox)findViewById(R.id.present);
        outdoors = (CheckBox)findViewById(R.id.outdoors);
        official = (CheckBox)findViewById(R.id.official);
        celebrity = (CheckBox)findViewById(R.id.celebrity);
        grade = (CheckBox)findViewById(R.id.grade);

        present.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(present.isChecked()){
                    myPresent = present.getText().toString();
                }else{
                    myPresent = null;
                }
            }
        });

        outdoors.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(outdoors.isChecked()){
                    myOutdoors = outdoors.getText().toString();
                }else{
                    myOutdoors = null;
                }
            }
        });

        official.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(official.isChecked()){
                    myOfficial = official.getText().toString();
                }else{
                    myOfficial = null;
                }
            }
        });

        celebrity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(celebrity.isChecked()){
                    myCelebrity = celebrity.getText().toString();
                }else{
                    myCelebrity = null;
                }
            }
        });

        grade.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(grade.isChecked()){
                    myGrade = grade.getText().toString();
                }else{
                    myGrade = null;
                }
            }
        });

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
                String myAcPushScope_1 = acPushScope_1.getText().toString();
                String myAcPushScope_2 = acPushScope_1.getText().toString();
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
}

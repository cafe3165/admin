package com.test.admin.promulgator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

import com.test.admin.R;
import com.test.admin.bean.AsActivity;
import com.test.admin.bean.AsPromulgator;
import com.test.admin.model.AsAcApply;
import com.test.admin.model.AsActi;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

import static com.test.admin.bean.Parameters.pObjectdId;
import static com.test.admin.bean.Parameters.staticObjectdId;

public class PromulgatorActivityModify extends BaseActivity {

    private EditText acTitle;
    private EditText acOrganizer;
    private EditText acStartTime;
    private EditText acDeadLine;
    private EditText acPlace;
    private EditText acContent;
    private EditText acAudiences;
    private Spinner acPushScope_1;
    private Spinner acPushScope_2;
    private Button modify;
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

    private List<String> labelList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promulgator_activity_modify);

        setCustomTitle("活动修改", false);

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
        modify = (Button)findViewById(R.id.modify);
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

        BmobQuery<AsActivity> query = new BmobQuery<AsActivity>();
        query.getObject(staticObjectdId, new QueryListener<AsActivity>() {
            @Override
            public void done(AsActivity asActivity, BmobException e) {

                if(e == null){

                    acTitle.setText(asActivity.getAcTitle());
                    acOrganizer.setText(asActivity.getAcOtganizer());
                    acStartTime.setText(asActivity.getAcStartTime());
                    acDeadLine.setText(asActivity.getAcDeadline());
                    acPlace.setText(asActivity.getAcPlace());
                    acContent.setText(asActivity.getAcContent());
                    acAudiences.setText(asActivity.getAcAudiences());
                    //初始化标签
                    initCheckBox(asActivity.getAcLabel());
                    //初始化下拉框
                    initSpinner1(asActivity.getAcPushScope_1());
                    initSpinner2(asActivity.getAcPushScope_2());
                }
            }
        });
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //改变按钮状态为不可按
                modify.setEnabled(false);
                //获取EditText上的输入
                String myAcTitle = acTitle.getText().toString();
                String myAcOrganizer = acOrganizer.getText().toString();
                String myAcStarTime = acStartTime.getText().toString();
                String myAcDeadTime = acDeadLine.getText().toString();
                String myAcPlace = acPlace.getText().toString();
                String myAcContent = acContent.getText().toString();
                String myAcAudiences = acAudiences.getText().toString();
                List<String> myLabel = new ArrayList<String>();

                if(myPresent != null)myLabel.add(myPresent);
                if(myOutdoors != null)myLabel.add(myOutdoors);
                if(myOfficial != null)myLabel.add(myOfficial);
                if(myCelebrity != null)myLabel.add(myCelebrity);
                if(myGrade != null)myLabel.add(myGrade);
                //发布活动，成功推到活动申请表
                AsActi asActi = new AsActi();
                asActi.asAcModify(modify,myAcTitle,myAcOrganizer,myAcStarTime,myAcDeadTime,myAcPlace,
                        myAcContent,myAcAudiences,myAcPushScope_1,myAcPushScope_2,myLabel,staticObjectdId);
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
    //匹配标签
    void initCheckBox(List<String> list){

        for(int i = 0; i < list.size(); i ++){
            switch (list.get(i)){
                case "礼品" :
                    present.setChecked(true);
                    break;
                case "户外" :
                    outdoors.setChecked(true);
                    break;
                case "官方" :
                    official.setChecked(true);
                    break;
                case "名人" :
                    celebrity.setChecked(true);
                    break;
                case "综测" :
                    grade.setChecked(true);
            }
        }
    }
    //匹配下拉框
    void initSpinner1(String college){

        for (int i = 0; i < mCollege.length; i++){
            if(college.equals(mCollege[i])){
                acPushScope_1.setSelection(i,true);
                break;
            }
        }
    }
    void initSpinner2(String grade){

        for (int i = 0; i < mGrade.length; i ++){
            if(grade.equals(mGrade[i])){
                acPushScope_2.setSelection(i,true);
                break;
            }
        }
    }
}


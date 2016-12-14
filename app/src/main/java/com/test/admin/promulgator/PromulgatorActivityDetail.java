package com.test.admin.promulgator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.test.admin.R;
import com.test.admin.bean.AsActivity;
import com.test.admin.bean.AsParticipant;
import com.test.admin.model.AsActi;
import com.test.admin.model.AsAppForm;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

import static com.test.admin.bean.Parameters.pObjectdId;
import static com.test.admin.bean.Parameters.staticObjectdId;

public class PromulgatorActivityDetail extends AppCompatActivity {

    private TextView acTitle;
    private TextView acParNumbers;
    private TextView acOrganizer;
    private TextView acStartTime;
    private TextView acDeadLine;
    private TextView acPlace;
    private TextView acContent;
    private TextView acAudiences;
    private TextView acLabel;
    private Button modifyActivity;
    private Button endActivity;
    private Button applicationForm;

    //声明活动对象，保存当前查找到的活动对象
    private List<AsActivity> myAsAcApplying = new ArrayList<AsActivity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promulgator_activity_detail);

        acTitle = (TextView)findViewById(R.id.acTitle);
        acParNumbers = (TextView)findViewById(R.id.parNumbers);
        acOrganizer = (TextView)findViewById(R.id.acOrganizer);
        acStartTime = (TextView)findViewById(R.id.acStartTime);
        acDeadLine = (TextView)findViewById(R.id.acDeadLine);
        acPlace = (TextView)findViewById(R.id.acPlace);
        acContent = (TextView)findViewById(R.id.acContent);
        acAudiences = (TextView)findViewById(R.id.acAudiences);
        acLabel = (TextView)findViewById(R.id.acLabel);
        modifyActivity = (Button)findViewById(R.id.modifyActivyty);
        endActivity = (Button)findViewById(R.id.endActivity);
        applicationForm = (Button)findViewById(R.id.applicationForm);

        //查找当前item对应的活动对象并返回值显示在TextView上
        BmobQuery<AsActivity> query = new BmobQuery<AsActivity>();
        query.getObject(staticObjectdId, new QueryListener<AsActivity>() {
            @Override
            public void done(AsActivity asActivity, BmobException e) {

                if(e == null){

                    myAsAcApplying.add(asActivity);

                    acTitle.setText(asActivity.getAcTitle());
                    acParNumbers.setText("报名中");
                    acOrganizer.setText(asActivity.getAcOtganizer());
                    acStartTime.setText(asActivity.getAcStartTime());
                    acDeadLine.setText(asActivity.getAcDeadline());
                    acPlace.setText(asActivity.getAcPlace());
                    acContent.setText(asActivity.getAcContent());
                    acAudiences.setText(asActivity.getAcAudiences());
                    acLabel.setText(asActivity.getAcLabel().toString());

                    if(asActivity.getAcStatus().equals(true))endActivity.setEnabled(true);
                    else endActivity.setEnabled(false);
                }
            }
        });

        modifyActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        endActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //更改按钮状态
                endActivity.setEnabled(false);
                //结束活动
                AsActi asActi = new AsActi();
                asActi.endActivity(endActivity,staticObjectdId);
            }
        });

        applicationForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}

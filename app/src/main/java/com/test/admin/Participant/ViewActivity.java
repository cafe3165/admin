package com.test.admin.Participant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.test.admin.R;
import com.test.admin.bean.AsActivity;
import com.test.admin.bean.AsApplicationForm;
import com.test.admin.bean.AsParticipant;
import com.test.admin.bean.AsPromulgator;
import com.test.admin.model.AsAppForm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

import static com.test.admin.bean.Parameters.pObjectdId;
import static com.test.admin.bean.Parameters.staticObjectdId;

public class ViewActivity extends AppCompatActivity {

    private TextView acTitle;
    private TextView acStatus;
    private TextView acOrganizer;
    private TextView acStartTime;
    private TextView acDeadLine;
    private TextView acPlace;
    private TextView acContent;
    private TextView acAudiences;
    private TextView acLabel;
    private Button apply;

    //声明活动对象，保存当前查找到的活动对象
    private List<AsActivity> myAsAcApplying = new ArrayList<AsActivity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.participant_activity_detail);

        acTitle = (TextView)findViewById(R.id.acTitle);
        acStatus = (TextView)findViewById(R.id.acStatus);
        acOrganizer = (TextView)findViewById(R.id.acOrganizer);
        acStartTime = (TextView)findViewById(R.id.acStartTime);
        acDeadLine = (TextView)findViewById(R.id.acDeadLine);
        acPlace = (TextView)findViewById(R.id.acPlace);
        acContent = (TextView)findViewById(R.id.acContent);
        acAudiences = (TextView)findViewById(R.id.acAudiences);
        acLabel = (TextView)findViewById(R.id.acLabel);
        apply = (Button)findViewById(R.id.apply);

        //查找当前item对应的活动申请对象并返回值显示在TextView上
        BmobQuery<AsActivity> query = new BmobQuery<AsActivity>();
        query.getObject(staticObjectdId, new QueryListener<AsActivity>() {
            @Override
            public void done(AsActivity asActivity, BmobException e) {

                if(e == null){

                    myAsAcApplying.add(asActivity);

                    acTitle.setText(asActivity.getAcTitle());
                    acOrganizer.setText(asActivity.getAcOtganizer());
                    acStartTime.setText(asActivity.getAcStartTime());
                    acDeadLine.setText(asActivity.getAcDeadline());
                    acPlace.setText(asActivity.getAcPlace());
                    acContent.setText(asActivity.getAcContent());
                    acAudiences.setText(asActivity.getAcAudiences());

                    if(asActivity.getAcStatus() == true){

                        acStatus.setText("报名中");
                        apply.setEnabled(true);
                    }else{

                        acStatus.setText("活动已结束");
                        apply.setEnabled(false);
                    }
                }
            }
        });

        pObjectdId = (String) AsParticipant.getObjectByKey("objectId");

        BmobQuery<AsApplicationForm> query1 = new BmobQuery<>();
        query1.addWhereContainsAll("apParId", Arrays.asList(pObjectdId))
                .findObjects(new FindListener<AsApplicationForm>() {
                    @Override
                    public void done(List<AsApplicationForm> list, BmobException e) {

                        if(e == null){

                            if(list.size() > 0)apply.setText("取消报名");
                            else apply.setText("报名");
                        }
                    }
                });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //更改按钮状态为不可按
                apply.setEnabled(false);
                AsAppForm asAppForm = new AsAppForm();
                if(apply.getText().equals("报名")){
                    asAppForm.acParApply(apply,staticObjectdId,pObjectdId);
                }else{
                    asAppForm.acParCancleApply(apply,staticObjectdId,pObjectdId);
                }
            }
        });
    }

}
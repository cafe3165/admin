package com.test.admin.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.test.admin.R;
import com.test.admin.bean.AsAcApplying;
import com.test.admin.model.AsAcApply;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

import static com.test.admin.bean.Parameters.staticObjectdId;
import static com.test.admin.model.Function.showToast;

public class ActivityDetail extends BaseActivity implements View.OnClickListener {

    private TextView acApplyTitle;
    private TextView acApplyStatus;
    private TextView acApplyOrganizer;
    private TextView acApplyStartTime;
    private TextView acApplyDeadLine;
    private TextView acApplyPlace;
    private TextView acApplyContent;
    private TextView acApplyAudiences;
    private TextView acApplyLabel;
    private Button pass;
    private Button not_pass;
    private String acLabel1;
    private String acLabel2;
    private String acLabel3;
    private String acLabel4;
    private String acLabel5;

    //声明活动申请对象，保存当前查找到的活动申请对象
    private List<AsAcApplying> myAsAcApplying = new ArrayList<AsAcApplying>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setCustomTitle("活动详情", false);

        acApplyTitle = (TextView)findViewById(R.id.acApplyTitle);
        acApplyStatus = (TextView)findViewById(R.id.acApplyStatus);
        acApplyOrganizer = (TextView)findViewById(R.id.acApplyOrganizer);
        acApplyStartTime = (TextView)findViewById(R.id.acApplyStartTime);
        acApplyDeadLine = (TextView)findViewById(R.id.acApplyDeadLine);
        acApplyPlace = (TextView)findViewById(R.id.acApplyPlace);
        acApplyContent = (TextView)findViewById(R.id.acApplyContent);
        acApplyAudiences = (TextView)findViewById(R.id.acApplyAudiences);
        acApplyLabel = (TextView)findViewById(R.id.acApplyLabel);
        pass = (Button)findViewById(R.id.actPass_button);
        not_pass = (Button)findViewById(R.id.actNoPass_button);

        //查找当前item对应的活动申请对象并返回值显示在TextView上
        BmobQuery<AsAcApplying> query = new BmobQuery<AsAcApplying>();
        query.getObject(staticObjectdId, new QueryListener<AsAcApplying>() {
            @Override
            public void done(AsAcApplying asAcApplying, BmobException e) {

                if(e == null){

                    myAsAcApplying.add(asAcApplying);

                    acApplyTitle.setText(asAcApplying.getAcApplyTitle());
                    acApplyStatus.setText("报名中");
                    acApplyOrganizer.setText(asAcApplying.getAcApplyOrganizer());
                    acApplyStartTime.setText(asAcApplying.getAcApplyStartTime());
                    acApplyDeadLine.setText(asAcApplying.getAcApplyDeadline());
                    acApplyPlace.setText(asAcApplying.getAcApplyPlace());
                    acApplyContent.setText(asAcApplying.getAcApplyContent());
                    acApplyAudiences.setText(asAcApplying.getAcApplyAudiences());
                    acApplyLabel.setText(asAcApplying.getAcApplyLabel().toString());
                    /*acLabel1 = asAcApplying.getAcApplyLabel().get(0);
                    acLabel2 = asAcApplying.getAcApplyLabel().get(1);
                    acLabel3 = asAcApplying.getAcApplyLabel().get(2);
                    acLabel4 = asAcApplying.getAcApplyLabel().get(3);
                    acLabel5 = asAcApplying.getAcApplyLabel().get(4);

                    acApplyLabel.setText(acLabel1 + acLabel2 + acLabel3 + acLabel4 + acLabel5 );*/
                }
            }
        });
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.actPass_button:
                //改变按钮状态为不可按
                pass.setEnabled(false);
                //审核通过,推送活动
                AsAcApply asAcApply = new AsAcApply();
                asAcApply.acApplySend(pass,not_pass,myAsAcApplying.get(0).getAcApplyProId(),myAsAcApplying.get(0).getObjectId(),
                        myAsAcApplying.get(0).getAcApplyAudiences(),myAsAcApplying.get(0).getAcApplyContent(),
                        myAsAcApplying.get(0).getAcApplyStartTime(),myAsAcApplying.get(0).getAcApplyDeadline(),
                        myAsAcApplying.get(0).getAcApplyOrganizer(),myAsAcApplying.get(0).getAcApplyPlace(),
                        myAsAcApplying.get(0).getAcApplyTitle(),myAsAcApplying.get(0).getAcApplyPushScope_1(),
                        myAsAcApplying.get(0).getAcApplyPushScope_2(),myAsAcApplying.get(0).getAcApplyLabel());

            case R.id.actNoPass_button:
                //改变按钮状态为不可按
                not_pass.setEnabled(false);
                //审核不通过,删除审核列表的活动
                AsAcApply asAcApply_not = new AsAcApply();
                asAcApply_not.acApplyDelete(pass,not_pass,myAsAcApplying.get(0).getObjectId());
        }
    }

}

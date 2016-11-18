package com.test.admin.activitypromulgator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.test.admin.R;
import com.test.admin.bean.AsActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

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
        setContentView(R.layout.act_view);

        acTitle = (TextView)findViewById(R.id.act_biao);
        acStatus = (TextView)findViewById(R.id.act_zhuangtai);
        acOrganizer = (TextView)findViewById(R.id.act_fang);
        acStartTime = (TextView)findViewById(R.id.act_time);
        acDeadLine = (TextView)findViewById(R.id.act_time2);
        acPlace = (TextView)findViewById(R.id.act_place);
        acContent = (TextView)findViewById(R.id.act_nei);
        acAudiences = (TextView)findViewById(R.id.act_shouzhong);
        acLabel = (TextView)findViewById(R.id.act_biaoq);

        //查找当前item对应的活动申请对象并返回值显示在TextView上
        BmobQuery<AsActivity> query = new BmobQuery<AsActivity>();
        query.getObject(staticObjectdId, new QueryListener<AsActivity>() {
            @Override
            public void done(AsActivity asActivity, BmobException e) {

                if(e == null){

                    myAsAcApplying.add(asActivity);

                    acTitle.setText(asActivity.getAcTitle());
                    acStatus.setText("报名中");
                    acOrganizer.setText(asActivity.getAcOtganizer());
                    acStartTime.setText(asActivity.getAcStartTime());
                    acDeadLine.setText(asActivity.getAcDeadline());
                    acPlace.setText(asActivity.getAcPlace());
                    acContent.setText(asActivity.getAcContent());
                    acAudiences.setText(asActivity.getAcAudiences());
                }
            }
        });
    }
}

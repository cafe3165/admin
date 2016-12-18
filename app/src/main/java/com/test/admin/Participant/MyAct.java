package com.test.admin.Participant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.test.admin.R;
import com.test.admin.adapter.ActivityAdapter;
import com.test.admin.adapter.ApplicationFormAdapter;
import com.test.admin.bean.AsActivity;
import com.test.admin.bean.AsParticipant;
import com.test.admin.bean.AsPromulgator;
import com.test.admin.promulgator.PromulgatorActivityDetail;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

import static com.test.admin.bean.Parameters.pObjectdId;
import static com.test.admin.bean.Parameters.staticObjectdId;

public class MyAct extends AppCompatActivity {

    private ListView lv_activity;
    private ActivityAdapter mActivityAdapter;
    private List<AsActivity> asActivityList = new ArrayList<AsActivity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_activity_my);

        lv_activity = (ListView) findViewById(R.id.lv_activity);
        lv_activity.setAdapter(mActivityAdapter);

        pObjectdId = (String) AsParticipant.getObjectByKey("objectId");

        BmobQuery<AsParticipant> query = new BmobQuery<AsParticipant>();
        query.getObject(pObjectdId, new QueryListener<AsParticipant>() {
            @Override
            public void done(AsParticipant asParticipant, BmobException e) {

                if(e == null) {
                    for(int i = 0; i < asParticipant.getParAcId().size(); i ++) {

                        BmobQuery<AsActivity> eq1 = new BmobQuery<AsActivity>();
                        eq1.getObject(asParticipant.getParAcId().get(i), new QueryListener<AsActivity>() {
                            @Override
                            public void done(AsActivity asActivity, BmobException e) {

                                if(e == null) {

                                    asActivityList.add(asActivity);
                                    mActivityAdapter = new ActivityAdapter(MyAct.this,asActivityList);
                                    mActivityAdapter.notifyDataSetChanged();
                                }
                            }
                        });
                    }
                }
            }
        });

        lv_activity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MyAct.this, ViewActivity.class));

                AsActivity asActivity  = asActivityList.get(position);
                staticObjectdId = asActivity.getObjectId().toString();
            }
        });
    }
}

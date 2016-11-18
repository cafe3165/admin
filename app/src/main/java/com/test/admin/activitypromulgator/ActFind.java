package com.test.admin.activitypromulgator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.test.admin.R;
import com.test.admin.adapter.ActivityAdapter;
import com.test.admin.bean.AsActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static com.test.admin.bean.Parameters.staticObjectdId;

public class ActFind extends AppCompatActivity {

    private ListView lv_activity;
    private ActivityAdapter mAdapter;
    private List<AsActivity> asActivityList = new ArrayList<AsActivity>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_activity_act_find);

        lv_activity = (ListView)findViewById(R.id.itemList);

        BmobQuery<AsActivity> bmobQuery = new BmobQuery<AsActivity>();
        bmobQuery.addQueryKeys("acTitle,acContent");
        bmobQuery.findObjects(new FindListener<AsActivity>() {
            @Override
            public void done(List<AsActivity> list, BmobException e) {

                if (e == null) {
                    asActivityList.addAll(list);

                    mAdapter = new ActivityAdapter(ActFind.this,asActivityList);

                    lv_activity.setAdapter(mAdapter);
                }
            }
        });

        lv_activity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(ActFind.this, ViewActivity.class));

                AsActivity asActivity  = asActivityList.get(position);
                staticObjectdId = asActivity.getObjectId().toString();
            }
        });
    }
}

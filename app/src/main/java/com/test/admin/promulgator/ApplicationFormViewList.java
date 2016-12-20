package com.test.admin.promulgator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.test.admin.R;
import com.test.admin.activity.RefreshableView;
import com.test.admin.adapter.ActivityAdapter;
import com.test.admin.adapter.ApplicationFormAdapter;
import com.test.admin.bean.AsActivity;
import com.test.admin.bean.AsParticipant;
import com.test.admin.bean.AsPermissionApplying;
import com.test.admin.bean.AsPromulgator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static com.test.admin.bean.Parameters.pObjectdId;
import static com.test.admin.bean.Parameters.staticObjectdId;
import static com.test.admin.model.Function.showToast;

public class ApplicationFormViewList extends AppCompatActivity {

    private ListView lv_paticipant;
    private ApplicationFormAdapter mApplicationFormAdapter;
    private List<AsParticipant> asParticipantList = new ArrayList<AsParticipant>();
    private RefreshableView refreshableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_pro_application_form);

        lv_paticipant = (ListView)findViewById(R.id.lv_participant);
        refreshableView = (RefreshableView) findViewById(R.id.refreshable_view);

        BmobQuery<AsParticipant> query = new BmobQuery<AsParticipant>();
        query.addWhereContainsAll("parAcId", Arrays.asList(staticObjectdId));
        query.findObjects(new FindListener<AsParticipant>() {
            @Override
            public void done(List<AsParticipant> list, BmobException e) {

                if (e == null) {
                    asParticipantList.addAll(list);

                    mApplicationFormAdapter = new ApplicationFormAdapter(ApplicationFormViewList.this,asParticipantList);

                    lv_paticipant.setAdapter(mApplicationFormAdapter);
                }
            }
        });

        refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {

                try{
                    Thread.sleep(2000);
                    BmobQuery<AsParticipant> query = new BmobQuery<AsParticipant>();
                    query.addWhereContainsAll("parAcId", Arrays.asList(staticObjectdId));
                    query.findObjects(new FindListener<AsParticipant>() {
                        @Override
                        public void done(List<AsParticipant> list, BmobException e) {

                            if (e == null) {
                                asParticipantList.clear();
                                asParticipantList.addAll(list);
                                mApplicationFormAdapter.notifyDataSetChanged();
                                showToast("刷新成功");
                            }
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
                refreshableView.finishRefreshing();
            }
        },7);
    }
}

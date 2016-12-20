package com.test.admin.Participant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.test.admin.R;
import com.test.admin.activity.RefreshableView;
import com.test.admin.adapter.ActivityAdapter;
import com.test.admin.bean.AsActivity;
import com.test.admin.bean.AsParticipant;
import com.test.admin.bean.AsPermissionApplying;
import com.test.admin.promulgator.*;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

import static com.test.admin.bean.Parameters.pObjectdId;
import static com.test.admin.bean.Parameters.staticObjectdId;
import static com.test.admin.model.Function.showToast;

public class HistoryAct extends BaseActivity {

    private ListView lv_activity;
    private ActivityAdapter mActivityAdapter;
    private List<AsActivity> asActivityList = new ArrayList<AsActivity>();
    private RefreshableView refreshableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_activity_history);
        setCustomTitle("历史参与活动", false);
        lv_activity = (ListView) findViewById(R.id.itemList);
        refreshableView = (RefreshableView) findViewById(R.id.refreshable_view);

        pObjectdId = (String) AsParticipant.getObjectByKey("objectId");
        BmobQuery<AsParticipant> query = new BmobQuery<AsParticipant>();
        query.getObject(pObjectdId, new QueryListener<AsParticipant>() {
            @Override
            public void done(final AsParticipant asParticipant, BmobException e) {

                if(e == null) {
                    for(int i = 0; i < asParticipant.getParAcId().size(); i ++) {

                        final int count = i;
                        BmobQuery<AsActivity> eq1 = new BmobQuery<AsActivity>();
                        eq1.addWhereEqualTo("objectId",asParticipant.getParAcId().get(i));
                        BmobQuery<AsActivity> eq2 = new BmobQuery<AsActivity>();
                        eq2.addWhereEqualTo("acStatus",false);
                        List<BmobQuery<AsActivity>> andQuery = new ArrayList<BmobQuery<AsActivity>>();
                        andQuery.add(eq1);
                        andQuery.add(eq2);
                        BmobQuery<AsActivity> query1 = new BmobQuery<AsActivity>();
                        query1.and(andQuery);
                        query1.findObjects(new FindListener<AsActivity>() {
                            @Override
                            public void done(List<AsActivity> list, BmobException e) {

                                if(e == null) {

                                    asActivityList.addAll(list);
                                    if(asParticipant.getParAcId().size() == count + 1){
                                        mActivityAdapter = new ActivityAdapter(HistoryAct.this,asActivityList);
                                        lv_activity.setAdapter(mActivityAdapter);
                                    }
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
                startActivity(new Intent(HistoryAct.this, ViewActivity.class));

                AsActivity asActivity  = asActivityList.get(position);
                staticObjectdId = asActivity.getObjectId().toString();
            }
        });

        refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {

                try{
                    Thread.sleep(2000);
                    BmobQuery<AsParticipant> query = new BmobQuery<AsParticipant>();
                    query.getObject(pObjectdId, new QueryListener<AsParticipant>() {
                        @Override
                        public void done(final AsParticipant asParticipant, BmobException e) {

                            if(e == null) {
                                for(int i = 0; i < asParticipant.getParAcId().size(); i ++) {

                                    final int count = i;
                                    BmobQuery<AsActivity> eq1 = new BmobQuery<AsActivity>();
                                    eq1.addWhereEqualTo("objectId",asParticipant.getParAcId().get(i));
                                    BmobQuery<AsActivity> eq2 = new BmobQuery<AsActivity>();
                                    eq2.addWhereEqualTo("acStatus",false);
                                    List<BmobQuery<AsActivity>> andQuery = new ArrayList<BmobQuery<AsActivity>>();
                                    andQuery.add(eq1);
                                    andQuery.add(eq2);
                                    BmobQuery<AsActivity> query1 = new BmobQuery<AsActivity>();
                                    query1.and(andQuery);
                                    query1.findObjects(new FindListener<AsActivity>() {
                                        @Override
                                        public void done(List<AsActivity> list, BmobException e) {

                                            if(e == null) {

                                                asActivityList.clear();
                                                asActivityList.addAll(list);
                                                if(asParticipant.getParAcId().size() == count + 1){
                                                    mActivityAdapter.notifyDataSetChanged();
                                                }
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    });

                }catch (Exception e){
                    e.printStackTrace();
                }
                refreshableView.finishRefreshing();
            }
        },9);
    }
}

package com.test.admin.PromulgatorFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.test.admin.Participant.ViewActivity;
import com.test.admin.R;
import com.test.admin.activity.ActivityDetail;
import com.test.admin.adapter.AcApplyAdapter;
import com.test.admin.adapter.ActivityAdapter;
import com.test.admin.bean.AsAcApplying;
import com.test.admin.bean.AsActivity;
import com.test.admin.bean.AsPromulgator;
import com.test.admin.model.AsActi;
import com.test.admin.promulgator.PromulgatorActivityDetail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static com.test.admin.bean.Parameters.pObjectdId;
import static com.test.admin.bean.Parameters.staticObjectdId;

/**
 *发布者已发布活动列表
 */

public class YiFaBuHuoDong extends Fragment {

    private ListView lv_activity;
    private ActivityAdapter myActivityAdapter;
    private List<AsActivity> asActivityList = new ArrayList<AsActivity>();

    public YiFaBuHuoDong() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main2, container, false);

        lv_activity = (ListView) view.findViewById(R.id.lv_activity);

        pObjectdId = (String) AsPromulgator.getObjectByKey("objectId");

        BmobQuery<AsActivity> eq1 = new BmobQuery<AsActivity>();
        eq1.addWhereEqualTo("acPromulgator", pObjectdId);
        BmobQuery<AsActivity> eq2 = new BmobQuery<AsActivity>();
        eq2.addWhereEqualTo("acStatus",true);
        List<BmobQuery<AsActivity>> andQuery = new ArrayList<BmobQuery<AsActivity>>();
        andQuery.add(eq1);
        andQuery.add(eq2);
        BmobQuery<AsActivity> query = new BmobQuery<AsActivity>();
        query.and(andQuery);
        query.findObjects(new FindListener<AsActivity>() {
            @Override
            public void done(List<AsActivity> list, BmobException e) {

                if (e == null) {
                    asActivityList.addAll(list);

                    myActivityAdapter = new ActivityAdapter(getActivity(),asActivityList);

                    lv_activity.setAdapter(myActivityAdapter);
                }
            }
        });

        lv_activity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), PromulgatorActivityDetail.class));

                AsActivity asActivity  = asActivityList.get(position);
                staticObjectdId = asActivity.getObjectId().toString();
            }
        });

        return view;
    }

}




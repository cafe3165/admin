package com.test.admin.PaticipantFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.test.admin.Participant.ImformViewActivity;
import com.test.admin.Participant.ViewActivity;
import com.test.admin.R;
import com.test.admin.activity.ActivityDetail;
import com.test.admin.adapter.AcApplyAdapter;
import com.test.admin.adapter.ActivityAdapter;
import com.test.admin.adapter.ImformationAdapter;
import com.test.admin.bean.AsAcApplying;
import com.test.admin.bean.AsActivity;
import com.test.admin.bean.AsImformation;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static com.test.admin.bean.Parameters.staticObjectdId;

/**
 * Created by hc6 on 2016/11/16.
 */

public class Head extends Fragment {

    private ListView lv_activity;
    private ListView lv_imformation;
    private ActivityAdapter mActivityAdapter;
    private ImformationAdapter mImformationAdapter;
    private List<AsActivity> asActivityList = new ArrayList<AsActivity>();
    private List<AsImformation> asImformationList = new ArrayList<AsImformation>();
    private Button imformationMore;
    private Button activityMore;

    public Head() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.paticipant_head, container, false);

        lv_imformation = (ListView) view.findViewById(R.id.p_imformation) ;
        lv_activity = (ListView) view.findViewById(R.id.p_activity);
        imformationMore = (Button) view.findViewById(R.id.inform_more);
        activityMore = (Button) view.findViewById(R.id.activity_more);

        BmobQuery<AsImformation> bmobQuery = new BmobQuery<AsImformation>();
        bmobQuery.addQueryKeys("objectId,imTitle,imContent");
        bmobQuery.findObjects(new FindListener<AsImformation>() {
            @Override
            public void done(List<AsImformation> list, BmobException e) {

                if (e == null) {
                    asImformationList.add(list.get(0));
                    asImformationList.add(list.get(1));
                    //asImformationList.add(list.get(2));

                    mImformationAdapter = new ImformationAdapter(getActivity(),asImformationList);

                    lv_imformation.setAdapter(mImformationAdapter);
                }
            }
        });

        BmobQuery<AsActivity> eq1 = new BmobQuery<AsActivity>();
        eq1.addQueryKeys("objectId,acTitle,acContent");
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
                    asActivityList.add(list.get(0));
                    asActivityList.add(list.get(1));
                    //asActivityList.add(list.get(2));

                    mActivityAdapter = new ActivityAdapter(getActivity(),asActivityList);

                    lv_activity.setAdapter(mActivityAdapter);
                }
            }
        });

        lv_imformation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), ImformViewActivity.class));

                AsImformation asImformation  = asImformationList.get(position);
                staticObjectdId = asImformation.getObjectId().toString();
            }
        });

        lv_activity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), ViewActivity.class));

                AsActivity asActivity  = asActivityList.get(position);
                staticObjectdId = asActivity.getObjectId().toString();
            }
        });

        /*imformationMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),TongZhi.class);
                startActivity(intent);
            }
        });

        activityMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),HuoDongChaZhao.class);
                startActivity(intent);
            }
        });*/

        return view;
    }
}




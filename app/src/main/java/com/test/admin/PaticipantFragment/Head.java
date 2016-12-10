package com.test.admin.PaticipantFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.test.admin.R;
import com.test.admin.activity.ActivityDetail;
import com.test.admin.adapter.AcApplyAdapter;
import com.test.admin.bean.AsAcApplying;

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
    private AcApplyAdapter mAdapter;
    private List<AsAcApplying> asActivityList = new ArrayList<AsAcApplying>();
    public Head() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.paticipant_head, container, false);

        lv_activity = (ListView) view.findViewById(R.id.p_activity);



        BmobQuery<AsAcApplying> bmobQuery = new BmobQuery<AsAcApplying>();
        bmobQuery.addQueryKeys("objectId,acApplyTitle,acApplyContent");
        bmobQuery.findObjects(new FindListener<AsAcApplying>() {
            @Override
            public void done(List<AsAcApplying> list, BmobException e) {

                if (e == null) {
                    asActivityList.addAll(list);

                    mAdapter = new AcApplyAdapter(getActivity(),asActivityList);

                    lv_activity.setAdapter(mAdapter);
                }
            }
        });


        lv_activity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), ActivityDetail.class));

                AsAcApplying asAcApplying  = asActivityList.get(position);
                staticObjectdId = asAcApplying.getObjectId().toString();
            }
        });

        return view;
    }

}




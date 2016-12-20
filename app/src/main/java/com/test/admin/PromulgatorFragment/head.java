package com.test.admin.PromulgatorFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.test.admin.Participant.InfoEdit;
import com.test.admin.R;
import com.test.admin.activity.ActivityDetail;
import com.test.admin.adapter.AcApplyAdapter;
import com.test.admin.bean.AsAcApplying;
import com.test.admin.promulgator.newActivity;
import com.test.admin.promulgator.newNotice;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static com.test.admin.bean.Parameters.staticObjectdId;

/**
 * Created by hc6 on 2016/11/16.
 */

public class head extends Fragment {

    private ListView lv_activity;
    private AcApplyAdapter mAdapter;
    private List<AsAcApplying> asActivityList = new ArrayList<AsAcApplying>();
    public head() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_head, container, false);

        Button buttonAct =(Button) view.findViewById(R.id.widget39);
        buttonAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(),newActivity.class);
                startActivity(intent);
            }
        });
        Button buttonNotice =(Button) view.findViewById(R.id.widget40);
        buttonNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(),newNotice.class);
                startActivity(intent);
            }
        });

        return view;
    }



}




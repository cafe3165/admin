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
import android.widget.TextView;

import com.test.admin.Participant.InfoEdit;
import com.test.admin.R;
import com.test.admin.activity.ActivityDetail;
import com.test.admin.adapter.AcApplyAdapter;
import com.test.admin.bean.AsAcApplying;
import com.test.admin.bean.AsPromulgator;
import com.test.admin.promulgator.newActivity;
import com.test.admin.promulgator.newNotice;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

import static com.test.admin.bean.Parameters.pObjectdId;
import static com.test.admin.bean.Parameters.staticObjectdId;

/**
 * Created by hc6 on 2016/11/16.
 */

public class head extends Fragment {

    private TextView acPermission;
    private TextView imPermission;
    private TextView proIdentity;
    private Button buttonAct;
    private Button buttonNotice;
    public head() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_head, container, false);

        acPermission = (TextView)view.findViewById(R.id.acPermission);
        imPermission = (TextView)view.findViewById(R.id.imPermission);
        proIdentity = (TextView)view.findViewById(R.id.proIdentity);
        buttonAct =(Button) view.findViewById(R.id.widget39);
        buttonNotice =(Button) view.findViewById(R.id.widget40);

        pObjectdId = (String) AsPromulgator.getObjectByKey("objectId");
        BmobQuery<AsPromulgator> query  = new BmobQuery<AsPromulgator>();
        query.getObject(pObjectdId, new QueryListener<AsPromulgator>() {
            @Override
            public void done(AsPromulgator asPromulgator, BmobException e) {

                if(e == null){

                    acPermission.setText(asPromulgator.getProAcPermission_1()/* + " " + asPromulgator.getProAcPermission_2()*/);
                    imPermission.setText(asPromulgator.getProImPermission_1()/* + " " + asPromulgator.getProImPermission_2()*/);
                    proIdentity.setText(asPromulgator.getProIdentity());
                }
            }
        });

        buttonAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(),newActivity.class);
                startActivity(intent);
            }
        });

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




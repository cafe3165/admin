package com.test.admin.PromulgatorFragment;

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
import com.test.admin.adapter.ImformationAdapter;
import com.test.admin.bean.AsAcApplying;
import com.test.admin.bean.AsImformation;
import com.test.admin.bean.AsPromulgator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static com.test.admin.bean.Parameters.pObjectdId;
import static com.test.admin.bean.Parameters.staticObjectdId;

/**
 * Created by hc6 on 2016/11/16.
 */

public class YiFaBuTongZhi extends Fragment {

    private ListView lv_imformation;
    private ImformationAdapter myImformationAdapter;
    private List<AsImformation> asImformationsList = new ArrayList<AsImformation>();

    public YiFaBuTongZhi() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main4, container, false);

        lv_imformation = (ListView) view.findViewById(R.id.lv_imformation);
        pObjectdId = (String) AsPromulgator.getObjectByKey("objectId");

        BmobQuery<AsImformation> bmobQuery = new BmobQuery<AsImformation>();
        bmobQuery.addWhereContainsAll("imPromulgator", Arrays.asList(pObjectdId));
        bmobQuery.findObjects(new FindListener<AsImformation>() {
            @Override
            public void done(List<AsImformation> list, BmobException e) {

                if (e == null) {
                    asImformationsList.addAll(list);

                    myImformationAdapter = new ImformationAdapter(getActivity(),asImformationsList);

                    lv_imformation.setAdapter(myImformationAdapter);
                }
            }
        });

        return view;
    }

}




package com.test.admin.fragment;

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
import com.test.admin.adapter.ActivityAdapter;
import com.test.admin.bean.AsAcApplying;
import com.test.admin.bean.AsActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by hc6 on 2016/11/16.
 */

public class FragmentOne extends Fragment {

    private ListView lv_activity;
    private AcApplyAdapter mAdapter;
    private List<AsAcApplying> asActivityList = new ArrayList<AsAcApplying>();
    public FragmentOne() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_one, container, false);

        lv_activity = (ListView) view.findViewById(R.id.lv_activity);

        //List<ActivityBean> datas = new ArrayList<>();

        /*ActivityBean bean = null;

        bean = new ActivityBean();
        bean.setImg(R.drawable.ic_01);
        bean.setT1("读书笔记");
        bean.setT1("读书使人快乐...");
        datas.add(bean);

        bean = new ActivityBean();
        bean.setImg(R.drawable.ic_02);
        bean.setT1("数计迎新");
        bean.setT1("又是一年...");
        datas.add(bean);

        bean = new ActivityBean();
        bean.setImg(R.drawable.ic_03);
        bean.setT1("早安福大");
        bean.setT1("早起使人快乐...");
        datas.add(bean);*/

        BmobQuery<AsAcApplying> bmobQuery = new BmobQuery<AsAcApplying>();
        bmobQuery.addQueryKeys("acApplyTitle,acApplyContent");
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
            }
        });

        return view;
    }

}




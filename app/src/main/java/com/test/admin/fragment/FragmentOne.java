package com.test.admin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.os.Handler;

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
import static com.test.admin.model.Function.UPDATE_TEXT;

/**
 *活动审核列表
 */

public class FragmentOne extends Fragment {

    private ListView lv_activity;
    private List<AsAcApplying> asActivityList = new ArrayList<AsAcApplying>();
    public AcApplyAdapter mAdapter;

    /*public static Handler handler = new Handler(){

        public void handleMessage(Message msg){

            if(msg.what == UPDATE_TEXT){

                mAdapter.notifyDataSetChanged();
            }
        }
    };*/

    public FragmentOne() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_one, container, false);

        lv_activity = (ListView) view.findViewById(R.id.lv_activity);
        //从数据库拉出活动审核列表信息，通过适配器获取信息
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




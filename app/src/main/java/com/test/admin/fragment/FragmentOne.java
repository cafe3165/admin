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
import com.test.admin.model.AsAcApply;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobRealTimeData;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.ValueEventListener;

import static com.test.admin.bean.Parameters.staticObjectdId;

/**
 *活动审核列表
 */

public class FragmentOne extends Fragment {

    //public static final int UPDATE_TEXT = 1;

    private ListView lv_activity;
    private List<AsAcApplying> asActivityList = new ArrayList<AsAcApplying>();
    public AcApplyAdapter myAcApplyAdapter;

    /*public  Handler handler = new Handler(){

        public void handleMessage(Message msg){

            if(msg.what == UPDATE_TEXT){


                //从数据库拉出活动审核列表信息，通过适配器获取信息
                BmobQuery<AsAcApplying> bmobQuery = new BmobQuery<AsAcApplying>();
                bmobQuery.addQueryKeys("objectId,acApplyTitle,acApplyContent");
                bmobQuery.findObjects(new FindListener<AsAcApplying>() {
                    @Override
                    public void done(List<AsAcApplying> list, BmobException e) {

                        if (e == null) {

                            asActivityList.clear();
                            asActivityList.addAll(list);

                            myAcApplyAdapter = new AcApplyAdapter(getActivity(),asActivityList);

                            lv_activity.setAdapter(myAcApplyAdapter);
                        }
                    }
                });
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

        //final Message msg = new Message();
        //msg.what = UPDATE_TEXT;
        //从数据库拉出活动审核列表信息，通过适配器获取信息
        BmobQuery<AsAcApplying> bmobQuery = new BmobQuery<AsAcApplying>();
        bmobQuery.addQueryKeys("objectId,acApplyTitle,acApplyContent");
        bmobQuery.findObjects(new FindListener<AsAcApplying>() {
            @Override
            public void done(List<AsAcApplying> list, BmobException e) {

                if (e == null) {
                    asActivityList.addAll(list);

                    myAcApplyAdapter = new AcApplyAdapter(getActivity(),asActivityList);

                    lv_activity.setAdapter(myAcApplyAdapter);
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

        /*BmobRealTimeData rtd = new BmobRealTimeData();
        rtd.start(new ValueEventListener() {
            @Override
            public void onConnectCompleted(Exception e) {

            }

            @Override
            public void onDataChange(JSONObject jsonObject) {

                handler.sendMessage(msg);
            }
        });

        if(rtd.isConnected())rtd.subTableDelete("AsAcApplying");*/

        return view;
    }

}




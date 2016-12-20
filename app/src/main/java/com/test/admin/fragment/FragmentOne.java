package com.test.admin.fragment;

import android.content.Intent;
import android.graphics.Color;
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
import com.test.admin.activity.BaseApplication;
import com.test.admin.activity.RefreshableView;
import com.test.admin.adapter.AcApplyAdapter;
import com.test.admin.adapter.PermissionAdapter;
import com.test.admin.bean.AsAcApplying;
import com.test.admin.bean.AsPermissionApplying;
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
import static com.test.admin.model.Function.showToast;

/**
 *活动审核列表
 */

public class FragmentOne extends Fragment {

    private ListView lv_activity;
    private List<AsAcApplying> asActivityList = new ArrayList<AsAcApplying>();
    public AcApplyAdapter myAcApplyAdapter;
    private RefreshableView refreshableView;

    public FragmentOne() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_one, container, false);

        lv_activity = (ListView) view.findViewById(R.id.lv_activity);
        refreshableView = (RefreshableView) view.findViewById(R.id.refreshable_view);

        if(BaseApplication.getInstance().getIsNight()) {
            view.setBackgroundColor(Color.parseColor("#202c38"));
            view.findViewById(R.id.ll_title).setBackgroundColor(Color.parseColor("#acacac"));
            lv_activity.setBackgroundColor(Color.parseColor("#202c38"));
        } else {
            view.setBackgroundColor(Color.parseColor("#ffffff"));
            view.findViewById(R.id.ll_title).setBackgroundColor(Color.parseColor("#A5C5E1"));
            lv_activity.setBackgroundColor(Color.parseColor("#ffffff"));
        }

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

        refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {

                try{
                    Thread.sleep(2000);
                    BmobQuery<AsAcApplying> bmobQuery = new BmobQuery<AsAcApplying>();
                    bmobQuery.addQueryKeys("objectId,acApplyTitle,acApplyContent");
                    bmobQuery.findObjects(new FindListener<AsAcApplying>() {
                        @Override
                        public void done(List<AsAcApplying> list, BmobException e) {

                            if (e == null) {
                                asActivityList.clear();
                                asActivityList.addAll(list);
                                myAcApplyAdapter.notifyDataSetChanged();
                                showToast("刷新成功");
                            }
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
                refreshableView.finishRefreshing();
            }
        },0);
        return view;
    }
}




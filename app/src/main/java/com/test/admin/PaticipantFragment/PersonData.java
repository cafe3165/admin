package com.test.admin.PaticipantFragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.test.admin.Participant.HistoryAct;
import com.test.admin.Participant.InfoEdit;
import com.test.admin.R;
import com.test.admin.activity.PersonDetail;
import com.test.admin.adapter.PermissionAdapter;
import com.test.admin.bean.AsParticipant;
import com.test.admin.bean.AsPermissionApplying;


import com.test.admin.Participant.MyAct;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;

import static com.test.admin.R.id.view;
import static com.test.admin.bean.Parameters.staticObjectdId;

/**
 * Created by hc6 on 2016/11/16.
 */

public class PersonData extends Fragment {

    private ListView lv_permission;
    private PermissionAdapter mAdapter;
    private List<AsPermissionApplying> asPermissionApplyingList = new ArrayList<AsPermissionApplying>();

    private ImageView ge_touxaing;
    private TextView ge_name;
    private TextView ge_yuan;

    public PersonData() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.com_activity_user_info, container, false);


        Button button =(Button) view.findViewById(R.id.ge_edit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(),InfoEdit.class);
                startActivity(intent);
            }
        });



        ImageButton Ibutton1=(ImageButton) view.findViewById(R.id.my_apply);
        Ibutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MyAct.class);
                startActivity(intent);
            }
        });

        ImageButton Ibutton2=(ImageButton) view.findViewById(R.id.my_applied);
        Ibutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),HistoryAct.class);
                startActivity(intent);
            }
        });

        //加载头像及个人信息
        ge_touxaing = (ImageView) view.findViewById(R.id.ge_touxiang);
        ge_name = (TextView) view.findViewById(R.id.ge_name);
        ge_yuan = (TextView) view.findViewById(R.id.ge_yuan);
        AsParticipant par = BmobUser.getCurrentUser(AsParticipant.class);
        ge_name.setText(par.getParName());
        ge_yuan.setText(par.getParCollege());
        BmobFile headpic = par.getParHeadPortrait();
        downloadpic(headpic);

//        lv_permission = (ListView) view.findViewById(R.id.lv_permission);
//
//        BmobQuery<AsPermissionApplying> bmobQuery = new BmobQuery<AsPermissionApplying>();
//        bmobQuery.addQueryKeys("perIdentity,perSupplement");
//        bmobQuery.findObjects(new FindListener<AsPermissionApplying>() {
//            @Override
//            public void done(List<AsPermissionApplying> list, BmobException e) {
//
//                if (e == null) {
//                    asPermissionApplyingList.addAll(list);
//
//                    mAdapter = new PermissionAdapter(getActivity(),asPermissionApplyingList);
//
//                    lv_permission.setAdapter(mAdapter);
//                }
//            }
//        });
//
//
//        lv_permission.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                startActivity(new Intent(getActivity(), PersonDetail.class));
//
//                AsPermissionApplying asPermissionApplying = asPermissionApplyingList.get(position);
//                staticObjectdId = asPermissionApplying.getObjectId().toString();
//            }
//        });

        return view;
    }

    private void downloadpic(final BmobFile file) {
        File saveFile = new File(Environment.getExternalStorageDirectory(),file.getFilename());
        file.download(saveFile, new DownloadFileListener() {
            @Override
            public void done(String s, BmobException e) {
                if (e==null){
                    Bitmap icon = BitmapFactory.decodeFile(s);
                    ge_touxaing.setImageBitmap(icon);
                }
            }

            @Override
            public void onProgress(Integer integer, long l) {

            }
        });
    }

}

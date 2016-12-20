package com.test.admin.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.test.admin.R;
import com.test.admin.activity.BaseApplication;
import com.test.admin.bean.AsParticipant;
import com.test.admin.model.Asadmin;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import static com.test.admin.model.Function.showToast;

/**
 * Created by hc6 on 2016/11/16.
 */

public class FragmentThree extends Fragment {

    private EditText phoneNumber;
    private EditText studentNumber;
    private EditText studentName;
    private EditText removeStudentNumber;
    private Button modify_1;
    private Button modify_2;

    public FragmentThree() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_three,container,false);

        phoneNumber = (EditText)view.findViewById(R.id.phNum_edit);
        studentNumber = (EditText)view.findViewById(R.id.password_edit);
        studentName = (EditText)view.findViewById(R.id.username_edit);
        removeStudentNumber = (EditText)view.findViewById(R.id.userout_edit);
        modify_1 = (Button)view.findViewById(R.id.signin_button);
        modify_2 = (Button)view.findViewById(R.id.signout_button);

        if(BaseApplication.getInstance().getIsNight()) {
            view.setBackgroundColor(Color.parseColor("#202c38"));
        } else {
            view.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        modify_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String myPhoneNumber = phoneNumber.getText().toString();
                final String myStudentNumber = studentNumber.getText().toString();
                final String myStudentName = studentName.getText().toString();

                Asadmin asAdmin = new Asadmin();
                asAdmin.modifyParInformation(myPhoneNumber,myStudentNumber,myStudentName);
            }
        });

        return view;
    }
}

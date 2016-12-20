package com.test.admin.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.test.admin.R;
import com.test.admin.activity.BaseApplication;
import com.test.admin.activity.MainActivity;

/**
 * Created by hc6 on 2016/11/16.
 */

public class FragmentSetting extends Fragment {

    private View mView;
    private CheckBox cb_isNight;

    public FragmentSetting() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.layout_setting11, container, false);
        cb_isNight = (CheckBox)mView.findViewById(R.id.cb_isNight);
        cb_isNight.setChecked(BaseApplication.getInstance().getIsNight());
        updateUI();
        initListener();
        return mView;
    }

    private void updateUI() {
        if(BaseApplication.getInstance().getIsNight()) {
            mView.setBackgroundColor(Color.parseColor("#5c6061"));
            mView.findViewById(R.id.ll_tuisong).setBackgroundColor(Color.parseColor("#212c30"));
            mView.findViewById(R.id.ll_moshi).setBackgroundColor(Color.parseColor("#212c30"));
            mView.findViewById(R.id.ll_bangding).setBackgroundColor(Color.parseColor("#212c30"));
            mView.findViewById(R.id.ll_gongneng).setBackgroundColor(Color.parseColor("#212c30"));
            mView.findViewById(R.id.ll_yijian).setBackgroundColor(Color.parseColor("#212c30"));
            mView.findViewById(R.id.ll_zhanghao).setBackgroundColor(Color.parseColor("#212c30"));
            return;
        }
        mView.setBackgroundColor(Color.parseColor("#f7f7f9"));
        mView.findViewById(R.id.tv_tuisong).setBackgroundColor(Color.parseColor("#ffffff"));
        mView.findViewById(R.id.tv_moshi).setBackgroundColor(Color.parseColor("#ffffff"));
        mView.findViewById(R.id.tv_bangding).setBackgroundColor(Color.parseColor("#ffffff"));
        mView.findViewById(R.id.tv_gongneng).setBackgroundColor(Color.parseColor("#ffffff"));
        mView.findViewById(R.id.tv_yijian).setBackgroundColor(Color.parseColor("#ffffff"));
        mView.findViewById(R.id.tv_zhanghao).setBackgroundColor(Color.parseColor("#ffffff"));
    }

    private void initListener() {
        cb_isNight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                BaseApplication.getInstance().setIsNight(b);
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

}

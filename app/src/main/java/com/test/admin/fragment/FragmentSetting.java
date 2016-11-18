package com.test.admin.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.admin.R;

/**
 * Created by hc6 on 2016/11/16.
 */

public class FragmentSetting extends Fragment {
    public FragmentSetting() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_setting11, container, false);
    }

}

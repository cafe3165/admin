package com.test.admin.activity;

import android.os.Bundle;

import com.test.admin.R;

public class ActivityDetail extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setCustomTitle("活动详情", false);

    }

}

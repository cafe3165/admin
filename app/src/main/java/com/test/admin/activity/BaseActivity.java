package com.test.admin.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.test.admin.R;

import static com.test.admin.R.id.toolbar;


public class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    protected void setCustomTitle(String title, boolean isHome) {

        if (mToolbar == null) {
            mToolbar = (Toolbar) findViewById(toolbar);
            if (mToolbar != null) {
                setSupportActionBar(mToolbar);
            }

            android.support.v7.app.ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(!isHome);
                actionBar.setDisplayShowTitleEnabled(false);
            }
        }

        TextView textView = (TextView) findViewById(R.id.toolbar_title);
        textView.setText(title);


        if(BaseApplication.getInstance().getIsNight()) {
            mToolbar.setBackgroundColor(Color.parseColor("#222c2e"));
            textView.setTextColor(Color.parseColor("#95abb8"));
            getWindow().setBackgroundDrawableResource(R.drawable.night);
            return;
        }
        mToolbar.setBackgroundColor(Color.parseColor("#61ABE8"));
        textView.setTextColor(Color.parseColor("#000000"));
        getWindow().setBackgroundDrawableResource(R.drawable.night);

    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

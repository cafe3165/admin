package com.test.admin.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.test.admin.R;
import com.test.admin.fragment.FragmentOne;
import com.test.admin.fragment.FragmentSetting;
import com.test.admin.fragment.FragmentThree;
import com.test.admin.fragment.FragmentTwo;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    public static final String TAG_ONE = "one";
    public static final String TAG_TWO = "two";
    public static final String TAG_THREE = "three";
    public static final String TAG_SETTING = "setting";
    private DrawerLayout mDrawerLayout;
    private FragmentManager mFragmentManager;
    private FragmentOne mFragmentOne;
    private FragmentTwo mFragmentTwo;
    private FragmentThree mFragmentThree;
    private FragmentSetting mFragmentSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);
        //设置ToolBar
        setCustomTitle(getString(R.string.app_name), true);


        initFragment(savedInstanceState);

        //to fix bug
        showContent(TAG_ONE);

        findViewById(R.id.btn_01).setOnClickListener(this);
        findViewById(R.id.btn_02).setOnClickListener(this);
        findViewById(R.id.btn_03).setOnClickListener(this);
        findViewById(R.id.btn_setting).setOnClickListener(this);
        findViewById(R.id.btn_exit).setOnClickListener(this);

        if(BaseApplication.getInstance().getIsNight()) {
            ((Button)findViewById(R.id.btn_01)).setBackgroundColor(Color.parseColor("#202c38"));
            ((Button)findViewById(R.id.btn_01)).setTextColor(Color.parseColor("#ffffff"));
            ((Button)findViewById(R.id.btn_02)).setBackgroundColor(Color.parseColor("#202c38"));
            ((Button)findViewById(R.id.btn_02)).setTextColor(Color.parseColor("#ffffff"));
            ((Button)findViewById(R.id.btn_03)).setBackgroundColor(Color.parseColor("#202c38"));
            ((Button)findViewById(R.id.btn_03)).setTextColor(Color.parseColor("#ffffff"));
            ((Button)findViewById(R.id.btn_setting)).setBackgroundColor(Color.parseColor("#202c38"));
            ((Button)findViewById(R.id.btn_setting)).setTextColor(Color.parseColor("#ffffff"));
            ((Button)findViewById(R.id.btn_exit)).setBackgroundColor(Color.parseColor("#202c38"));
            ((Button)findViewById(R.id.btn_exit)).setTextColor(Color.parseColor("#ffffff"));

            findViewById(R.id.textView3).setBackgroundColor(Color.parseColor("#202c38"));

        } else {
            findViewById(R.id.btn_01).setBackgroundColor(Color.parseColor("#ffffff"));
            ((Button) findViewById(R.id.btn_01)).setBackgroundColor(Color.parseColor("#ffffff"));
            ((Button) findViewById(R.id.btn_01)).setTextColor(Color.parseColor("#000000"));
            ((Button) findViewById(R.id.btn_02)).setBackgroundColor(Color.parseColor("#ffffff"));
            ((Button) findViewById(R.id.btn_02)).setTextColor(Color.parseColor("#000000"));
            ((Button) findViewById(R.id.btn_03)).setBackgroundColor(Color.parseColor("#ffffff"));
            ((Button) findViewById(R.id.btn_03)).setTextColor(Color.parseColor("#000000"));
            ((Button) findViewById(R.id.btn_setting)).setBackgroundColor(Color.parseColor("#ffffff"));
            ((Button) findViewById(R.id.btn_setting)).setTextColor(Color.parseColor("#000000"));
            ((Button) findViewById(R.id.btn_exit)).setBackgroundColor(Color.parseColor("#ffffff"));
            ((Button) findViewById(R.id.btn_exit)).setTextColor(Color.parseColor("#000000"));

            findViewById(R.id.textView3).setBackgroundColor(Color.parseColor("#ffffff"));
        }
        //设置抽屉DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

    }

    private void initFragment(Bundle savedInstanceState) {
        mFragmentManager = this.getSupportFragmentManager();
        if (savedInstanceState != null) {
            mFragmentOne = (FragmentOne) mFragmentManager.findFragmentByTag(TAG_ONE);
            mFragmentTwo = (FragmentTwo) mFragmentManager.findFragmentByTag(TAG_TWO);
            mFragmentThree = (FragmentThree) mFragmentManager.findFragmentByTag(TAG_THREE);
        }
        if (mFragmentOne == null) {
            mFragmentOne = new FragmentOne();
            mFragmentManager.beginTransaction()
                    .add(R.id.vf_container, mFragmentOne, TAG_ONE).commit();
        }
        if (mFragmentTwo == null) {
            mFragmentTwo = new FragmentTwo();
            mFragmentManager.beginTransaction()
                    .add(R.id.vf_container, mFragmentTwo, TAG_TWO).commit();
        }
        if (mFragmentThree == null) {
            mFragmentThree = new FragmentThree();
            mFragmentManager.beginTransaction()
                    .add(R.id.vf_container, mFragmentThree, TAG_THREE).commit();
        }
        if (mFragmentSetting == null) {
            mFragmentSetting = new FragmentSetting();
            mFragmentManager.beginTransaction()
                    .add(R.id.vf_container, mFragmentSetting, TAG_SETTING).commit();
        }


    }


    private void showContent(String tag) {

        if (tag.equals(TAG_ONE)) {
            mFragmentManager.beginTransaction().show(mFragmentOne).hide(mFragmentTwo).hide(mFragmentThree).hide(mFragmentSetting).commit();
            setCustomTitle("活动审核", true);
        } else if (tag.equals(TAG_TWO)) {
            mFragmentManager.beginTransaction().hide(mFragmentOne).show(mFragmentTwo).hide(mFragmentThree).hide(mFragmentSetting).commit();
            setCustomTitle("权限审核", true);
        } else if (tag.equals(TAG_THREE)) {
            mFragmentManager.beginTransaction().hide(mFragmentOne).hide(mFragmentTwo).show(mFragmentThree).hide(mFragmentSetting).commit();
            setCustomTitle("用户信息更改", true);
        }else if (tag.equals(TAG_SETTING)) {
            mFragmentManager.beginTransaction().hide(mFragmentOne).hide(mFragmentTwo).show(mFragmentThree).show(mFragmentSetting).commit();
            setCustomTitle("设置", true);
        }

    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_01:
                showContent(TAG_ONE);
                break;
            case R.id.btn_02:
                showContent(TAG_TWO);
                break;
            case R.id.btn_03:
                showContent(TAG_THREE);
                break;
            case R.id.btn_setting:
                showContent(TAG_SETTING);
                break;
            case R.id.btn_exit:
                finish();
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}


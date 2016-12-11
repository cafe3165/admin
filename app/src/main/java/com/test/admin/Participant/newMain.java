package com.test.admin.Participant;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;

import com.test.admin.PaticipantFragment.PersonData;
import com.test.admin.PaticipantFragment.Setting;
import com.test.admin.R;
import com.test.admin.PaticipantFragment.Head;//////
import com.test.admin.fragment.FragmentSetting;
import com.test.admin.fragment.FragmentThree;
import com.test.admin.fragment.FragmentTwo;


public class newMain extends BaseActivity implements View.OnClickListener {

    public static final String TAG_ONE = "one";
    public static final String TAG_TWO = "two";
    public static final String TAG_THREE = "three";
    public static final String TAG_FOUR = "four";/////
    public static final String TAG_SETTING = "setting";
    private DrawerLayout mDrawerLayout;
    private FragmentManager mFragmentManager;
    private Head mFragmentOne;
    private PersonData mFragmentTwo;
    private FragmentThree mFragmentThree;

    private Setting mFragmentSetting;///////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.par_main11);//////////////
        //设置ToolBar
        setCustomTitle(getString(R.string.app_name), true);


        initFragment(savedInstanceState);

        //to fix bug
        showContent(TAG_ONE);

        findViewById(R.id.btn_head).setOnClickListener(this);
        findViewById(R.id.btn_person).setOnClickListener(this);
        findViewById(R.id.btn_find).setOnClickListener(this);
        findViewById(R.id.btn_notice).setOnClickListener(this);//////////////
        findViewById(R.id.btn_setting).setOnClickListener(this);
        findViewById(R.id.btn_exit).setOnClickListener(this);

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
            mFragmentOne = (Head) mFragmentManager.findFragmentByTag(TAG_ONE);
            mFragmentTwo = (PersonData) mFragmentManager.findFragmentByTag(TAG_TWO);///////////////
            mFragmentThree = (FragmentThree) mFragmentManager.findFragmentByTag(TAG_THREE);
        }
        if (mFragmentOne == null) {
            mFragmentOne = new Head();
            mFragmentManager.beginTransaction()
                    .add(R.id.vf_container, mFragmentOne, TAG_ONE).commit();
        }
        if (mFragmentTwo == null) {
            mFragmentTwo = new PersonData();
            mFragmentManager.beginTransaction()
                    .add(R.id.vf_container, mFragmentTwo, TAG_TWO).commit();
        }
        if (mFragmentThree == null) {
            mFragmentThree = new FragmentThree();
            mFragmentManager.beginTransaction()
                    .add(R.id.vf_container, mFragmentThree, TAG_THREE).commit();
        }

//        if (mFragmentThree == null) {
//            mFragmentThree = new FragmentFour();
//            mFragmentManager.beginTransaction()
//                    .add(R.id.vf_container, mFragmentThree, TAG_THREE).commit();
//        }

        if (mFragmentSetting == null) {
            mFragmentSetting = new Setting();
            mFragmentManager.beginTransaction()
                    .add(R.id.vf_container, mFragmentSetting, TAG_SETTING).commit();
        }


    }


    private void showContent(String tag) {

        if (tag.equals(TAG_ONE)) {
            mFragmentManager.beginTransaction().show(mFragmentOne).hide(mFragmentTwo).hide(mFragmentThree).hide(mFragmentSetting).commit();
            setCustomTitle("首页", true);
        } else if (tag.equals(TAG_TWO)) {
            mFragmentManager.beginTransaction().hide(mFragmentOne).show(mFragmentTwo).hide(mFragmentThree).hide(mFragmentSetting).commit();
            setCustomTitle("个人中心", true);
        } else if (tag.equals(TAG_THREE)) {
            mFragmentManager.beginTransaction().hide(mFragmentOne).hide(mFragmentTwo).show(mFragmentThree).hide(mFragmentSetting).commit();
            setCustomTitle("活动查找", true);

        }

        else if (tag.equals(TAG_FOUR)) {
            mFragmentManager.beginTransaction().hide(mFragmentOne).hide(mFragmentTwo).show(mFragmentThree).hide(mFragmentSetting).commit();
            setCustomTitle("通知", true);


        }

        else if (tag.equals(TAG_SETTING)) {
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
            case R.id.btn_head:
                showContent(TAG_ONE);
                break;
            case R.id.btn_person:
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

}


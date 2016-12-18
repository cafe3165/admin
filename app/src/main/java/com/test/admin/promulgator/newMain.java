package com.test.admin.promulgator;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.TextView;

import com.test.admin.R;
import com.test.admin.bean.AsPromulgator;
import com.test.admin.fragment.FragmentOne;
import com.test.admin.fragment.FragmentSetting;
import com.test.admin.fragment.FragmentThree;
import com.test.admin.fragment.FragmentTwo;
import com.test.admin.PromulgatorFragment.*;
import com.test.admin.model.AsProm;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

import static com.test.admin.bean.Parameters.pObjectdId;

public class newMain extends BaseActivity implements View.OnClickListener {

    public static final String TAG_ONE = "one";
    public static final String TAG_TWO = "two";
    public static final String TAG_THREE = "three";
    public static final String TAG_FOUR = "four";///
    public static final String TAG_SETTING = "setting";
    private DrawerLayout mDrawerLayout;
    private FragmentManager mFragmentManager;

    private head mFragmentOne;///////////////////////
    private YiFaBuHuoDong mFragmentTwo;
    private YiFaBuTongZhi mFragmentThree;
    private YiJieShuHuoDong mFragmentFour;
    private FaBuZheSetting mFragmentSetting;

    private TextView proIdentity;//发布者身份

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promul_main11);
        //设置ToolBar
        setCustomTitle(getString(R.string.app_name), true);
        //显示发布者身份
        proIdentity = (TextView)findViewById(R.id.proIdentity);
        pObjectdId = (String) AsPromulgator.getObjectByKey("objectId");
        BmobQuery<AsPromulgator> query = new BmobQuery<AsPromulgator>();
        query.getObject(pObjectdId, new QueryListener<AsPromulgator>() {
            @Override
            public void done(AsPromulgator asPromulgator, BmobException e) {

                if(e == null){

                    proIdentity.setText(asPromulgator.getProIdentity());
                }
            }
        });

        initFragment(savedInstanceState);

        //to fix bug
        showContent(TAG_ONE);

        findViewById(R.id.btn_head_promul).setOnClickListener(this);
       //////////////////////////
        findViewById(R.id.btn_issuedAct).setOnClickListener(this);
        findViewById(R.id.btn_issuedNotice).setOnClickListener(this);
        findViewById(R.id.btn_finishedAct).setOnClickListener(this);
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
            mFragmentOne = (head) mFragmentManager.findFragmentByTag(TAG_ONE);
            mFragmentTwo = (YiFaBuHuoDong) mFragmentManager.findFragmentByTag(TAG_TWO);
            mFragmentThree = (YiFaBuTongZhi) mFragmentManager.findFragmentByTag(TAG_THREE);
            mFragmentFour = (YiJieShuHuoDong) mFragmentManager.findFragmentByTag(TAG_FOUR);
        }
        if (mFragmentOne == null) {
            mFragmentOne = new head();
            mFragmentManager.beginTransaction()
                    .add(R.id.vf_container, mFragmentOne, TAG_ONE).commit();
        }
        if (mFragmentTwo == null) {
            mFragmentTwo = new YiFaBuHuoDong();
            mFragmentManager.beginTransaction()
                    .add(R.id.vf_container, mFragmentTwo, TAG_TWO).commit();
        }
        if (mFragmentThree == null) {
            mFragmentThree = new YiFaBuTongZhi();
            mFragmentManager.beginTransaction()
                    .add(R.id.vf_container, mFragmentThree, TAG_THREE).commit();
        }
        if (mFragmentFour == null) {
            mFragmentFour = new YiJieShuHuoDong();
            mFragmentManager.beginTransaction()
                    .add(R.id.vf_container, mFragmentFour, TAG_FOUR).commit();
        }
        if (mFragmentSetting == null) {
            mFragmentSetting = new FaBuZheSetting();
            mFragmentManager.beginTransaction()
                    .add(R.id.vf_container, mFragmentSetting, TAG_SETTING).commit();
        }


    }


    private void showContent(String tag) {

        if (tag.equals(TAG_ONE)) {
            mFragmentManager.beginTransaction().show(mFragmentOne).hide(mFragmentTwo).hide(mFragmentThree).hide(mFragmentFour).hide(mFragmentSetting).commit();
            setCustomTitle("首页", true);
        } else if (tag.equals(TAG_TWO)) {
            mFragmentManager.beginTransaction().hide(mFragmentOne).show(mFragmentTwo).hide(mFragmentThree).hide(mFragmentFour).hide(mFragmentSetting).commit();
            setCustomTitle("已发布活动", true);
        } else if (tag.equals(TAG_THREE)) {
            mFragmentManager.beginTransaction().hide(mFragmentOne).hide(mFragmentTwo).show(mFragmentThree).hide(mFragmentFour).hide(mFragmentSetting).commit();
            setCustomTitle("已发布通知", true);
        }else if (tag.equals(TAG_FOUR)) {
            mFragmentManager.beginTransaction().hide(mFragmentOne).hide(mFragmentTwo).hide(mFragmentThree).show(mFragmentFour).hide(mFragmentSetting).commit();
            setCustomTitle("已结束活动", true);
        }else if (tag.equals(TAG_SETTING)) {
            mFragmentManager.beginTransaction().hide(mFragmentOne).hide(mFragmentTwo).show(mFragmentThree).hide(mFragmentFour).show(mFragmentSetting).commit();
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
            case R.id.btn_head_promul:
                showContent(TAG_ONE);
                break;
            case R.id.btn_issuedAct:
                showContent(TAG_TWO);
                break;
            case R.id.btn_issuedNotice:
                showContent(TAG_THREE);
                break;
            case R.id.btn_finishedAct:
                showContent(TAG_FOUR);
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


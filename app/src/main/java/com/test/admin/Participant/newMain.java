package com.test.admin.Participant;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.test.admin.PaticipantFragment.HuoDongChaZhao;
import com.test.admin.PaticipantFragment.PersonData;
import com.test.admin.PaticipantFragment.Setting;
import com.test.admin.PaticipantFragment.TongZhi;
import com.test.admin.R;
import com.test.admin.PaticipantFragment.Head;//////
import com.test.admin.bean.AsParticipant;
import com.test.admin.bean.AsPromulgator;
import com.test.admin.fragment.FragmentSetting;
import com.test.admin.fragment.FragmentThree;
import com.test.admin.fragment.FragmentTwo;

import java.io.File;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.QueryListener;

import static com.test.admin.bean.Parameters.pObjectdId;


public class newMain extends BaseActivity implements View.OnClickListener {

    public static final String TAG_ONE = "one";
    public static final String TAG_TWO = "two";
    public static final String TAG_THREE = "three";
    public static final String TAG_FOUR = "four";                       /////
    public static final String TAG_SETTING = "setting";
    private DrawerLayout mDrawerLayout;
    private FragmentManager mFragmentManager;
    private Head mFragmentOne;
    private PersonData mFragmentTwo;
    private HuoDongChaZhao mFragmentThree;                      //xcz
    private TongZhi mFragmentFour;                      //xcz
    private Setting mFragmentSetting;///////////

    private TextView parName;//参与者姓名
    private ImageView parheadpic;//参与者头像
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.par_main11);//////////////
        //设置ToolBar
        setCustomTitle(getString(R.string.app_name), true);
        //显示参与者姓名
        parName = (TextView)findViewById(R.id.parName);
        pObjectdId = (String) AsPromulgator.getObjectByKey("objectId");
        BmobQuery<AsParticipant> query = new BmobQuery<AsParticipant>();
        query.getObject(pObjectdId, new QueryListener<AsParticipant>() {
            @Override
            public void done(AsParticipant asParticipant, BmobException e) {

                if(e == null){

                    parName.setText(asParticipant.getParName());
                }
            }
        });
        //加载头像
        parheadpic = (ImageView)findViewById(R.id.imageView2);
        AsParticipant par = BmobUser.getCurrentUser(AsParticipant.class);
        BmobFile headpic = par.getParHeadPortrait();
        downloadpic(headpic);

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

    //下载头像
    private void downloadpic(BmobFile file) {
        File saveFile = new File(Environment.getExternalStorageDirectory(),file.getFilename());
        file.download(saveFile, new DownloadFileListener() {
            @Override
            public void done(String s, BmobException e) {
                if (e==null){
                    Bitmap icon = BitmapFactory.decodeFile(s);
                    parheadpic.setImageBitmap(icon);
                }
                else{
                    Toast.makeText(newMain.this,"头像加载失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onProgress(Integer integer, long l) {

            }
        });
    }

    private void initFragment(Bundle savedInstanceState) {
        mFragmentManager = this.getSupportFragmentManager();
        if (savedInstanceState != null) {
            mFragmentOne = (Head) mFragmentManager.findFragmentByTag(TAG_ONE);
            mFragmentTwo = (PersonData) mFragmentManager.findFragmentByTag(TAG_TWO);///////////////
            mFragmentThree = (HuoDongChaZhao) mFragmentManager.findFragmentByTag(TAG_THREE);
            mFragmentFour = (TongZhi) mFragmentManager.findFragmentByTag(TAG_FOUR);
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
            mFragmentThree = new HuoDongChaZhao();
            mFragmentManager.beginTransaction()
                    .add(R.id.vf_container, mFragmentThree, TAG_THREE).commit();
        }
        if (mFragmentFour == null) {
            mFragmentFour = new TongZhi();
            mFragmentManager.beginTransaction()
                    .add(R.id.vf_container, mFragmentFour, TAG_FOUR).commit();
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
            mFragmentManager.beginTransaction().show(mFragmentOne).hide(mFragmentTwo).hide(mFragmentThree).hide(mFragmentFour).hide(mFragmentSetting).commit();
            setCustomTitle("首页", true);
        } else if (tag.equals(TAG_TWO)) {
            mFragmentManager.beginTransaction().hide(mFragmentOne).show(mFragmentTwo).hide(mFragmentThree).hide(mFragmentFour).hide(mFragmentSetting).commit();
            setCustomTitle("个人中心", true);
        } else if (tag.equals(TAG_THREE)) {
            mFragmentManager.beginTransaction().hide(mFragmentOne).hide(mFragmentTwo).show(mFragmentThree).hide(mFragmentFour).hide(mFragmentSetting).commit();
            setCustomTitle("活动查找", true);

        }

        else if (tag.equals(TAG_FOUR)) {
            mFragmentManager.beginTransaction().hide(mFragmentOne).hide(mFragmentTwo).hide(mFragmentThree).show(mFragmentFour).hide(mFragmentSetting).commit();
            setCustomTitle("通知", true);


        }

        else if (tag.equals(TAG_SETTING)) {
            mFragmentManager.beginTransaction().hide(mFragmentOne).hide(mFragmentTwo).hide(mFragmentThree).hide(mFragmentFour).show(mFragmentSetting).commit();
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
            case R.id.btn_find:
                showContent(TAG_THREE);
                break;
            case R.id.btn_notice:
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


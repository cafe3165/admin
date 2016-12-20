package com.test.admin.model;

import android.widget.Button;

import com.test.admin.bean.AsActivity;
import com.test.admin.bean.AsPromulgator;
import com.test.admin.bean.AsPromulgator_AcImId;

import java.util.Arrays;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import static com.test.admin.model.Function.showToast;

/**
 * Created by Administrator on 2016/11/17 0017.
 */

public class AsActi {


    public void asAcAdd(final Button pass,final Button not_pass,final String proObjectId, String acAudiences,
                        String acStart, String acPushScope1, String acPushScope2, String acPlace, String acOrgan,
                        String acEnd, String acContent, String acTitle, List<String>acLabel) {

        AsActivity asActivity = new AsActivity();

        asActivity.setAcTitle(acTitle);
        asActivity.setAcOtganizer(acOrgan);
        asActivity.setAcContent(acContent);
        asActivity.setAcAudiences(acAudiences);
        asActivity.setAcStartTime(acStart);
        asActivity.setAcDeadline(acEnd);
        asActivity.setAcPlace(acPlace);
        asActivity.setAcPushScope_1(acPushScope1);
        asActivity.setAcPushScope_2(acPushScope2);
        asActivity.setAcLabel(acLabel);
        asActivity.setAcPromulgator(proObjectId);
        asActivity.setAcStatus(true);

        asActivity.save(new SaveListener<String>() {
            @Override
            public void done(final String s, BmobException e) {
                if (e == null) {
                    showToast("审核成功");
                    //更改按钮状态和text
                    pass.setText("审核通过");
                    pass.setEnabled(false);
                    not_pass.setEnabled(false);
                    //更新listView
                    /*Message message = new Message();
                    message.what = UPDATE_TEXT;
                    FragmentOne fragmentOne = new FragmentOne();
                    fragmentOne.handler.sendMessage(message);*/
                    //创建活动对应的报名表
                    AsAppForm asAppForm = new AsAppForm();
                    asAppForm.creatForm(s);
                    //将审核通过的活动ID添加到对应发布者的已发布的活动ID字段
                    /*BmobQuery<AsPromulgator_AcImId> query = new BmobQuery<AsPromulgator_AcImId>();
                    query.addWhereEqualTo("proId",proObjectId);
                    query.findObjects(new FindListener<AsPromulgator_AcImId>() {
                        @Override
                        public void done(List<AsPromulgator_AcImId> list, BmobException e) {

                            if(e == null) {
                                list.get(0).addUnique("proAcId", s);
                                list.get(0).update(new UpdateListener() {
                                    @Override
                                    public void done(BmobException e) {

                                    }
                                });
                            }
                        }
                    });*/
                } else {
                    showToast("操作失败" + "\t" + e.getErrorCode() + ":" + e.getMessage());
                    //更改按钮状态
                    pass.setEnabled(true);
                }
            }
        });
    }
    //活动删除
    public void asAcDelete(final String acObjectdId, final String proObjectdId){

        final AsActivity asActivity = new AsActivity();
        asActivity.setObjectId(acObjectdId);
        asActivity.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {

                if(e == null){
                    showToast("活动取消成功");

                    AsPromulgator asPromulgator = new AsPromulgator();
                    asPromulgator.setObjectId(proObjectdId);
                    asPromulgator.removeAll("proAcId", Arrays.asList(acObjectdId));
                    asPromulgator.update(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {

                        }
                    });
                }else{
                    showToast("活动取消失败");
                }
            }
        });
    }

    //活动修改
    public void asAcModify(final Button modify,String acTitle, String acOrganizer, String acStartTime,
                           String acDeadline, String acPlace, String acContent, String acAudiences,
                           String acPushScope1, String acPushScope2,List<String> acLabel,String acObjectdId){

        AsActivity asActivity = new AsActivity();

        asActivity.setAcTitle(acTitle);
        asActivity.setAcOtganizer(acOrganizer);
        asActivity.setAcStartTime(acStartTime);
        asActivity.setAcDeadline(acDeadline);
        asActivity.setAcPlace(acPlace);
        asActivity.setAcContent(acContent);
        asActivity.setAcAudiences(acAudiences);
        asActivity.setAcPushScope_1(acPushScope1);
        asActivity.setAcPushScope_2(acPushScope2);
        asActivity.setAcLabel(acLabel);

        asActivity.update(acObjectdId, new UpdateListener() {
            @Override
            public void done(BmobException e) {

                if(e == null){
                    showToast("编辑成功");
                    //更改按钮状态
                    modify.setText("编辑成功");
                    modify.setEnabled(true);
                }else{
                    showToast("操作失败" + "\t" + e.getErrorCode() + ":" + e.getMessage());
                    //更改按钮状态
                    modify.setEnabled(true);
                }
            }
        });
    }

    //发布者结束活动
    public void endActivity(final Button endActivity,String acObjectId){

        BmobQuery<AsActivity> query = new BmobQuery<>();
        query.getObject(acObjectId, new QueryListener<AsActivity>() {
            @Override
            public void done(AsActivity asActivity, BmobException e) {

                if(e == null){

                    asActivity.setAcStatus(false);
                    asActivity.update(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {

                            if(e == null){

                                showToast("结束活动成功");
                                //更改按钮状态
                                endActivity.setText("活动已结束");
                            }else{

                                showToast("结束活动操作失败" + "\t" + e.getErrorCode() + ":" + e.getMessage());
                                //更改按钮状态
                                endActivity.setEnabled(true);
                            }
                        }
                    });
                }else{

                    showToast("操作失败" + "\t" + e.getErrorCode() + ":" + e.getMessage());
                    //更改按钮状态
                    endActivity.setEnabled(true);
                }
            }
        });
    }
}

package com.test.admin.model;

import android.widget.Toast;

import com.test.admin.bean.AsApplicationForm;
import com.test.admin.bean.AsParticipant;
import com.test.admin.bean.AsPromulgator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import static cn.bmob.v3.Bmob.getApplicationContext;
import static com.test.admin.model.Function.showToast;

/**
 * Created by Administrator on 2016/11/17 0017.
 */

public class AsAppForm {

    //创建一张报名表,传入报名表对应的活动Id
    public void creatForm(String apAcId){

        AsApplicationForm applicationForm = new AsApplicationForm();

        applicationForm.setApAcId(apAcId);

        applicationForm.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {

            }
        });
    }

    //参与者报名
    public void acParApply(final String acObjectdId, final String parObjectdId){

        //获取当前活动对应的报名表
        BmobQuery<AsApplicationForm> query = new BmobQuery<AsApplicationForm>();
        query.addWhereContainedIn("apAcId",Arrays.asList(acObjectdId));
        query.findObjects(new FindListener<AsApplicationForm>() {
            @Override
            public void done(List<AsApplicationForm> list, BmobException e) {

                //将当前用户的Id添加到报名表的参与者数组
                list.get(0).addUnique("apParId",parObjectdId);
                list.get(0).add("apParStatus","0");
                list.get(0).update(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {

                        if(e == null){

                            showToast("报名成功");

                            //将用户报名的活动添加到用户已报名的活动数组
                            BmobQuery<AsParticipant> query = new BmobQuery<AsParticipant>();
                            query.addWhereEqualTo("objectId",parObjectdId);
                            query.findObjects(new FindListener<AsParticipant>() {
                                @Override
                                public void done(List<AsParticipant> list, BmobException e) {

                                    if (e == null) {
                                        list.get(0).addUnique("parAcId", acObjectdId);
                                        list.get(0).update(new UpdateListener() {
                                            @Override
                                            public void done(BmobException e) {

                                            }
                                        });
                                    }
                                }
                            });
                        }else{

                            showToast("报名失败");
                        }
                    }
                });
            }
        });
    }

    //参与者取消报名
    public void acParCancleApply(String acObjectdId, final String parObjectdId){

        final boolean[] flag = new boolean[1];
        //获取当前活动对应的报名表
        BmobQuery<AsApplicationForm> query = new BmobQuery<AsApplicationForm>();
        query.addWhereEqualTo("apParId",acObjectdId);
        query.findObjects(new FindListener<AsApplicationForm>() {
            @Override
            public void done(List<AsApplicationForm> list, BmobException e) {

                //获取当前用户在报名表报名的参与者数组所在的位置
                List<String> list1 = new ArrayList<String>();
                list1.addAll(list.get(0).getApParId());
                int index = list1.indexOf(parObjectdId);
                //获取当前报名表的参与者状态数组并删除指定位置的值
                List<String> list2 = new ArrayList<String>();
                list2.addAll(list.get(0).getApParStatus());
                list2.remove(index);
                //将当前用户的Id从报名表中删除
                list.get(0).removeAll("apParId", Arrays.asList(parObjectdId));
                list.get(0).update(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {

                        if(e == null){
                            showToast("取消报名成功");
                            flag[0] = true;
                        }else{
                            showToast("取消报名失败");
                        }
                    }
                });
                //判断取消报名是否成功
                if(flag[0]){

                    //更新当前报名表的参与者状态数组
                    list.get(0).setApParStatus(list2);
                    list.get(0).update(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {

                        }
                    });
                }
            }
        });
    }

    //发布者签到
    public void acProSignIn(String acObjectId,int position){

        BmobQuery<AsApplicationForm> query = new BmobQuery<AsApplicationForm>();
        query.addWhereEqualTo("apAcId",acObjectId);
        query.findObjects(new FindListener<AsApplicationForm>() {
            @Override
            public void done(List<AsApplicationForm> list, BmobException e) {

                list.get(0).setValue("apParStatus.position","1");
                list.get(0).update(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {

                    }
                });
            }
        });
    }
}

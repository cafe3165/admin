package com.test.admin.model;

import android.widget.Button;

import com.test.admin.bean.AsImformation;
import com.test.admin.bean.AsParticipant;
import com.test.admin.bean.AsPromulgator_AcImId;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import static com.test.admin.model.Function.judge;
import static com.test.admin.model.Function.showToast;

/**
 * Created by Administrator on 2016/11/18 0018.
 */

public class AsImformationMethod {

    //发布通知
    public void asImAdd(final Button pulish,String imTitle, String imOrganizer, String imContent, String imAudiences,
                        String imPushScope_1, String imPushScope_2, final String proObjectId) {

        List<String> list = new ArrayList<String>();
        list.add(imTitle);
        list.add(imOrganizer);
        list.add(imContent);
        list.add(imAudiences);
        list.add(imPushScope_1);
        list.add(imPushScope_2);

        AsImformation asImformation = new AsImformation();

        asImformation.setImTitle(imTitle);
        asImformation.setImOrganizer(imOrganizer);
        asImformation.setImContent(imContent);
        asImformation.setImAudiences(imAudiences);
        asImformation.setImPushScope_1(imPushScope_1);
        asImformation.setImPushScope_2(imPushScope_2);
        asImformation.setImPromulgator(proObjectId);

        if (judge(list)){

            asImformation.save(new SaveListener<String>() {
                @Override
                public void done(final String s, BmobException e) {
                    if (e == null) {
                        showToast("发布成功");
                        //更改按钮状态和text
                        pulish.setText("发布成功");
                        pulish.setEnabled(false);
                        //保存新发布的通知的ID到该发布者的已发布的通知ID字段
                        /*BmobQuery<AsPromulgator_AcImId> query = new BmobQuery<AsPromulgator_AcImId>();
                        query.addWhereEqualTo("proId",proObjectId);
                        query.findObjects(new FindListener<AsPromulgator_AcImId>() {
                            @Override
                            public void done(List<AsPromulgator_AcImId> list, BmobException e) {

                                if (e == null) {
                                    list.get(0).addUnique("proImId", s);
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
                        pulish.setEnabled(true);
                    }
                }
            });
        }else{

            showToast("输入不准为空");
            //更改按钮状态
            pulish.setEnabled(true);
        }
    }

    //取消通知
    public void asImDelete(String imObjectdId){

        AsImformation asImformation = new AsImformation();
        asImformation.setObjectId(imObjectdId);
        asImformation.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {

                if(e == null){

                    showToast("通知取消成功");
                }else{

                    showToast("通知取消失败");
                }
            }
        });
    }

    //通知修改
    public void asImModify(final Button modify,String imObjectdId,String imTitle, String imOrganizer,
                           String imContent, String imAudiences, String imPushScope_1,String imPushScope_2){

        AsImformation asImformation = new AsImformation();

        asImformation.setImTitle(imTitle);
        asImformation.setImOrganizer(imOrganizer);
        asImformation.setImContent(imContent);
        asImformation.setImAudiences(imAudiences);
        asImformation.setImPushScope_1(imPushScope_1);
        asImformation.setImPushScope_2(imPushScope_2);

        asImformation.update(imObjectdId, new UpdateListener() {
            @Override
            public void done(BmobException e) {

                if(e == null){
                    showToast("编辑成功");
                    //更改按钮状态
                    modify.setText("编辑成功");
                    modify.setEnabled(false);
                }else{
                    showToast("操作失败" + "\t" + e.getErrorCode() + ":" + e.getMessage());
                    //更改按钮状态
                    modify.setEnabled(true);
                }
            }
        });
    }
}

package com.test.admin.model;

import com.test.admin.bean.AsActivity;
import com.test.admin.bean.AsPromulgator;

import java.util.Arrays;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import static com.test.admin.model.Function.showToast;

/**
 * Created by Administrator on 2016/11/17 0017.
 */

public class AsActi {


    public void asAcAdd(String acAudiences, String acStart, String acPushScope1, String acPushScope2,
                        String acPlace, String acOrgan, String acEnd, String acContent, String acTitle) {

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

        asActivity.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    showToast("审核成功");

                    AsAppForm asAppForm = new AsAppForm();
                    asAppForm.creatForm(s);
                } else {
                    showToast("审核失败");
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
    public void asAcModify(String acObjectdId,String acAudiences, String acStart, String acPushScope1, String acPushScope2,
                           String acPlace, String acOrgan, String acEnd, String acContent, String acTitle){

        AsActivity asActivity = new AsActivity();

        asActivity.setAcAudiences(acAudiences);
        asActivity.setAcStartTime(acStart);
        asActivity.setAcPushScope_1(acPushScope1);
        asActivity.setAcPushScope_2(acPushScope2);
        asActivity.setAcPlace(acPlace);
        asActivity.setAcOtganizer(acOrgan);
        asActivity.setAcDeadline(acEnd);
        asActivity.setAcContent(acContent);
        asActivity.setAcTitle(acTitle);

        asActivity.update(acObjectdId, new UpdateListener() {
            @Override
            public void done(BmobException e) {

                if(e == null){
                    showToast("编辑成功");
                }else{
                    showToast("编辑失败");
                }
            }
        });
    }
}

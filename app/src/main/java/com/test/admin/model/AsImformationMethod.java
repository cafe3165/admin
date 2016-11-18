package com.test.admin.model;

import com.test.admin.bean.AsImformation;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import static com.test.admin.model.Function.judge;
import static com.test.admin.model.Function.showToast;

/**
 * Created by Administrator on 2016/11/18 0018.
 */

public class AsImformationMethod {

    //发布通知
    public void asImAdd(String imTitle, String imOrganizer, String imContent, String imAudiences,
                        String imPushScope_1,String imPushScope_2) {

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

        if (judge(list)){

            asImformation.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        showToast("发布成功");
                    } else {
                        showToast("发布失败");
                    }
                }
            });
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

    //活动修改
    public void asAcModify(String imObjectdId,String imTitle, String imOrganizer, String imContent, String imAudiences,
                           String imPushScope_1,String imPushScope_2){

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
                }else{
                    showToast("编辑失败");
                }
            }
        });
    }
}

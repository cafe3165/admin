package com.test.admin.model;

import android.widget.Toast;

import com.test.admin.bean.AsActivity;
import com.test.admin.bean.AsPromulgator;

import java.util.Arrays;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
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

public class AsActi {


    public void asAcAdd(final String proObjectId, String acAudiences, String acStart, String acPushScope1, String acPushScope2,
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
            public void done(final String s, BmobException e) {
                if (e == null) {
                    showToast("审核成功");

                    //创建一张报名表
                    AsAppForm asAppForm = new AsAppForm();
                    asAppForm.creatForm(s);

                    //将活动对应的Id添加到发布者已发布的活动字段
                    BmobQuery<AsPromulgator> query = new BmobQuery<AsPromulgator>();
                    query.addWhereEqualTo("objectId",proObjectId);
                    query.findObjects(new FindListener<AsPromulgator>() {
                        @Override
                        public void done(List<AsPromulgator> list, BmobException e) {

                            if(e == null){

                                list.get(0).addUnique("proAcId",s);
                                list.get(0).update(new UpdateListener() {
                                    @Override
                                    public void done(BmobException e){

                                        if(e == null){
                                            showToast("添加成功");
                                        }else{

                                            Toast.makeText(getApplicationContext(),"添加失败 " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }
                    });
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

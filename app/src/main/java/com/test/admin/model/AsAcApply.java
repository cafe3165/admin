package com.test.admin.model;

import com.test.admin.bean.AsAcApplying;
import com.test.admin.bean.AsActivity;
import com.test.admin.bean.AsPromulgator;

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
 * Created by Administrator on 2016/11/17 0017.
 */

public class AsAcApply {

    public void acApplyAdd(String acTitle,String acOrgan, String acStart,String acPlace, String acEnd,
                           String acContent,String acAudience,String acPushScope_1,String acPushScope_2,String proObjectdId) {

        List<String> list = new ArrayList<String>();
        list.add(acTitle);
        list.add(acOrgan);
        list.add(acContent);
        list.add(acAudience);
        list.add(acStart);
        list.add(acEnd);
        list.add(acPlace);
        list.add(acPushScope_1);
        list.add(acPushScope_2);

        boolean flag = judge(list);

        AsAcApplying acApplying = new AsAcApplying();

        acApplying.setAcApplyTitle(acTitle);
        acApplying.setAcApplyOrganizer(acOrgan);
        acApplying.setAcApplyContent(acContent);
        acApplying.setAcApplyAudiences(acAudience);
        acApplying.setAcApplyStartTime(acStart);
        acApplying.setAcApplyDeadline(acEnd);
        acApplying.setAcApplyPlace(acPlace);
        acApplying.setAcApplyPushScope_1(acPushScope_1);
        acApplying.setAcApplyPushScope_2(acPushScope_2);
        acApplying.setAcApplyProId(proObjectdId);

        //判断输入是否有空值
        if(flag) {

            acApplying.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        showToast("发布成功,等待管理员审核");
                    } else {
                        /*Toast.makeText(getApplicationContext(),"发布失败"+e.getLocalizedMessage()+"("+e.getErrorCode()+")",
                                Toast.LENGTH_SHORT).show();*/
                        showToast("发布失败");
                    }
                }
            });
        }else{
            showToast("输入不准为空");
        }
    }

    //将通过审核的活动推到活动列表
    public void acApplySend(final String proObjectdId, String appObjectdId, String acAudience, String acContent, String acStart, String acEnd,
                            String acOrgan, String acPlace, String acTitle, String acPushScope_1, String acPushScope_2) {

        AsActi asActivity = new AsActi();
        asActivity.asAcAdd(acAudience,acStart,acPushScope_1,acPushScope_2,acPlace,acOrgan,acEnd,acContent,acTitle);

        //获取新创建的活动对应的Id
        final BmobQuery<AsActivity> query = new BmobQuery<AsActivity>();
        query.order("-createdAt");//按时间降序排列
        query.addWhereEqualTo("acTitle",acTitle);
        query.setLimit(1);
        query.findObjects(new FindListener<AsActivity>() {
            @Override
            public void done(List<AsActivity> list, BmobException e) {
                //创建活动对应的报名表
                AsAppForm appForm = new AsAppForm();
                appForm.creatForm(list.get(0).getObjectId());
                //将发布的活动的Id添加到对应的发布者的"已发布的活动的编号"的数组
                AsPromulgator asPromulgator = new AsPromulgator();
                asPromulgator.setObjectId(proObjectdId);
                asPromulgator.add("proAcId",list.get(0).getObjectId());
                asPromulgator.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {

                    }
                });
            }
        });

        //从申请表里删除审核通过的活动
        AsAcApply acApply = new AsAcApply();
        acApply.acApplyDelete(appObjectdId);
    }

    //删除活动申请表里的活动
    public void acApplyDelete(String appObjectdId){

        AsAcApplying acApplying = new AsAcApplying();
        acApplying.setObjectId(appObjectdId);
        acApplying.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {

            }
        });
    }
}

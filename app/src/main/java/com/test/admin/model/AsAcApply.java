package com.test.admin.model;

import android.widget.Button;

import com.test.admin.bean.AsAcApplying;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import static com.test.admin.model.Function.judge;
import static com.test.admin.model.Function.showToast;

/**
 * 申请表操作方法
 */

public class AsAcApply {

    public void acApplyAdd(final Button acIssue,String acTitle, String acOrgan, String acStart, String acPlace, String acEnd,
                           String acContent, String acAudience, String acPushScope_1, String acPushScope_2, List<String> acLabel, String proObjectdId) {

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
        list.addAll(acLabel);

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
        acApplying.setAcApplyLabel(acLabel);

        //判断输入是否有空值
        if(flag) {

            acApplying.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        showToast("发布成功,等待管理员审核");
                        //更改按钮状态和text
                        acIssue.setText("发布成功");
                        acIssue.setEnabled(false);
                    } else {
                        showToast("操作失败" + "\t" + e.getErrorCode() + ":" + e.getMessage());
                        //更改按钮状态
                        acIssue.setEnabled(true);
                    }
                }
            });
        }else{
            showToast("输入不准为空");
            //更改按钮状态
            acIssue.setEnabled(true);
        }
    }

    //将通过审核的活动推到活动列表
    public void acApplySend(Button pass,Button not_pass,final String proObjectdId, final String appObjectdId,
                            String acAudience, String acContent, String acStart, String acEnd, String acOrgan,
                            String acPlace, String acTitle, String acPushScope_1, String acPushScope_2,List<String> acLabel) {

        //将通过审核的活动推到活动列表
        AsActi asActivity = new AsActi();
        asActivity.asAcAdd(pass,not_pass,proObjectdId,acAudience,acStart,acPushScope_1,acPushScope_2,
                acPlace,acOrgan,acEnd,acContent,acTitle,acLabel);

        //从申请表里删除审核通过的活动
        AsAcApplying acApplying = new AsAcApplying();
        acApplying.setObjectId(appObjectdId);
        acApplying.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {

            }
        });
    }

    //删除活动申请表里的活动
    public void acApplyDelete(final Button pass, final Button not_pass, String appObjectdId){

        AsAcApplying acApplying = new AsAcApplying();
        acApplying.setObjectId(appObjectdId);
        acApplying.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {

                if (e == null){

                    showToast("审核不通过");
                    //更改按钮状态
                    not_pass.setText("审核不通过");
                    pass.setEnabled(false);
                    not_pass.setEnabled(false);
                }else{

                    showToast("操作失败" + "\t" + e.getErrorCode() + ":" + e.getMessage());
                    //更改按钮状态
                    not_pass.setEnabled(true);
                }
            }
        });
    }
}

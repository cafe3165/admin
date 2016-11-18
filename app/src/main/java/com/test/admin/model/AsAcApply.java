package com.test.admin.model;

import android.widget.Toast;

import com.test.admin.bean.AsAcApplying;
import com.test.admin.bean.AsActivity;
import com.test.admin.bean.AsPromulgator;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import static cn.bmob.v3.Bmob.getApplicationContext;
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
                        showToast("发布失败");
                    }
                }
            });
        }else{
            showToast("输入不准为空");
        }
    }

    //将通过审核的活动推到活动列表
    public void acApplySend(final String proObjectdId, final String appObjectdId, String acAudience, String acContent, String acStart, String acEnd,
                            String acOrgan, String acPlace, String acTitle, String acPushScope_1, String acPushScope_2) {
        //将通过审核的活动推到活动列表
        AsActi asActivity = new AsActi();
        asActivity.asAcAdd(proObjectdId,acAudience,acStart,acPushScope_1,acPushScope_2,acPlace,acOrgan,acEnd,acContent,acTitle);

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

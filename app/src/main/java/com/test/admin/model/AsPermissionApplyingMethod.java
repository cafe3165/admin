package com.test.admin.model;

import android.widget.Button;

import com.test.admin.bean.AsPermissionApplying;
import com.test.admin.bean.AsPromulgator;
import com.test.admin.bean.AsPromulgator_AcImId;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import static com.test.admin.model.Function.showToast;

/**
 * 管理员权限审核功能
 */

public class AsPermissionApplyingMethod {

    //审核通过
    public void applyAccess(final Button pass,final Button not_pass,String perIdentity, String perEmail, String perTelNumber, String perQQNumber,
                            String perImPushScope_1, String perImPushScope_2, String perAsPushScope_1,
                            String perAsPushScope_2, final String perObjectId){

        AsPromulgator asPromulgator = new AsPromulgator();

        asPromulgator.setProIdentity(perIdentity);
        asPromulgator.setMobilePhoneNumber(perTelNumber);
        asPromulgator.setProQQNumber(perQQNumber);
        asPromulgator.setParStuNumber(perEmail);//新增该字段赋值避免唯一键发生冲突
        asPromulgator.setProImPermission_1(perImPushScope_1);
        asPromulgator.setProImPermission_2(perImPushScope_2);
        asPromulgator.setProAcPermission_1(perAsPushScope_1);
        asPromulgator.setProAcPermission_2(perAsPushScope_2);

        asPromulgator.setUsername(perEmail);
        asPromulgator.setPassword("123456");
        asPromulgator.signUp(new SaveListener<AsPromulgator>() {

            public void done(AsPromulgator s, BmobException e){

                if(e == null){

                    showToast("审核通过");
                    //更改按钮状态
                    pass.setText("审核通过");
                    pass.setEnabled(false);
                    not_pass.setEnabled(false);
                    //将该申请从权限审核列表中删除
                    AsPermissionApplying asPermissionApplying = new AsPermissionApplying();
                    asPermissionApplying.setObjectId(perObjectId);
                    asPermissionApplying.delete(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {

                        }
                    });
                    //创建活动发布者对应的用来保存已发布活动Id或者通知Id的表
                    AsPromulgator_AcImId asPromulgator_acOrImId = new AsPromulgator_AcImId();
                    asPromulgator_acOrImId.setProId(s.getObjectId());
                    asPromulgator_acOrImId.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {

                        }
                    });
                }else{
                    showToast("操作失败" + "\t" + e.getErrorCode() + ":" + e.getMessage());
                    //更改按钮状态
                    pass.setEnabled(true);
                }
            }
        });
    }

    //审核失败
    public void applyNotAccess(final Button pass,final Button not_pass,String perObjectId){

        AsPermissionApplying asPermissionApplying = new AsPermissionApplying();
        asPermissionApplying.setObjectId(perObjectId);
        asPermissionApplying.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {

                if(e == null){

                    showToast("审核未通过");
                    //改变按钮状态
                    not_pass.setText("审核未通过");
                    pass.setEnabled(false);
                    not_pass.setEnabled(false);
                }else{

                    showToast("操作失败" + "\t" + e.getErrorCode() + ":" + e.getMessage());
                    not_pass.setEnabled(true);
                }
            }
        });

    }
}

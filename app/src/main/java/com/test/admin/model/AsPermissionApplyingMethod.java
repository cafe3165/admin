package com.test.admin.model;

import android.widget.Toast;

import com.test.admin.bean.AsPermissionApplying;
import com.test.admin.bean.AsPromulgator;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import static cn.bmob.v3.Bmob.getApplicationContext;
import static com.test.admin.model.Function.showToast;

/**
 * Created by Administrator on 2016/11/19 0019.
 */

public class AsPermissionApplyingMethod {

    //审核通过
    public void applyAccess(String perIdentity, String perEmail, String perTelNumber, String perQQNumber,
                            String perImPushScope_1, String perImPushScope_2, String perAsPushScope_1,
                            String perAsPushScope_2, final String perObjectId){

        AsPromulgator asPromulgator = new AsPromulgator();

        asPromulgator.setProIdentity(perIdentity);
        asPromulgator.setMobilePhoneNumber(perTelNumber);
        asPromulgator.setProQQNumber(perQQNumber);
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

                    AsPermissionApplying asPermissionApplying = new AsPermissionApplying();
                    asPermissionApplying.setObjectId(perObjectId);
                    asPermissionApplying.delete(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {

                        }
                    });
                }else{
                    showToast("审核失败"+e.getLocalizedMessage()+e.getErrorCode());
                   // Toast.makeText(getApplicationContext(),e.getLocalizedMessage() + e.getErrorCode(),Toast.LENGTH_SHORT);
                }
            }
        });
    }

    //审核失败
    public void applyNotAccess(String perObjectId){

        AsPermissionApplying asPermissionApplying = new AsPermissionApplying();
        asPermissionApplying.setObjectId(perObjectId);
        asPermissionApplying.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {

                if(e == null){

                    showToast("审核未通过");
                }
            }
        });

    }
}

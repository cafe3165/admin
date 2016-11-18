package com.test.admin.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.test.admin.R;
import com.test.admin.bean.AsAcApplying;
import com.test.admin.bean.AsPermissionApplying;
import com.test.admin.model.AsPermissionApplyingMethod;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

import static com.test.admin.bean.Parameters.staticObjectdId;

/**
 * Created by hc6 on 2016/11/18.
 */

public class PersonDetail extends BaseActivity implements View.OnClickListener {

    private TextView perIdentity;
    private TextView perEmail;
    private TextView perTelNumber;
    private TextView perQQNumber;
    private TextView perImPermission_2;
    private TextView perImPermission_1;
    private TextView perAcPermission_2;
    private TextView perAcPermission_1;
    private TextView perSupplement;
    private Button pass;
    private Button not_pass;

    //声明权限申请对象，保存当前查找到的权限申请对象
    private List<AsPermissionApplying> myAsPermissionApplying = new ArrayList<AsPermissionApplying>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_detail);

        setCustomTitle("权限申请详情", false);

        perIdentity = (TextView) findViewById(R.id.proIdentity);
        perEmail = (TextView) findViewById(R.id.proEmail);
        perTelNumber = (TextView) findViewById(R.id.proPhone);
        perQQNumber = (TextView) findViewById(R.id.proQQ);
        perImPermission_1 = (TextView) findViewById(R.id.imPushScope_1);
        perImPermission_2 = (TextView) findViewById(R.id.imPushScope_2);
        perAcPermission_1 = (TextView) findViewById(R.id.acPushScope_1);
        perAcPermission_2 = (TextView) findViewById(R.id.acPushScope_2);
        perSupplement = (TextView) findViewById(R.id.proSupplement);

        //查找当前item对应的权限申请对象并返回值显示在TextView上
        BmobQuery<AsPermissionApplying> query = new BmobQuery<AsPermissionApplying>();
        query.getObject(staticObjectdId, new QueryListener<AsPermissionApplying>() {
            @Override
            public void done(AsPermissionApplying asPermissionApplying, BmobException e) {

                if (e == null) {

                    myAsPermissionApplying.add(asPermissionApplying);

                    perIdentity.setText(asPermissionApplying.getPerIdentity());
                    perEmail.setText(asPermissionApplying.getPerEmail());
                    perTelNumber.setText(asPermissionApplying.getPerTelNumber());
                    perQQNumber.setText(asPermissionApplying.getPerQQNumber());
                    perImPermission_1.setText(asPermissionApplying.getPerImPermission_1());
                    perImPermission_2.setText(asPermissionApplying.getPerImPermission_2());
                    perAcPermission_1.setText(asPermissionApplying.getPerAcPermission_1());
                    perAcPermission_2.setText(asPermissionApplying.getPerAcPermission_2());
                    perSupplement.setText(asPermissionApplying.getPerSupplement());
                }
            }
        });

    }

    @Override
    public void onClick(View v) {

        pass = (Button) findViewById(R.id.actPass_button);
        not_pass = (Button) findViewById(R.id.actNoPass_button);

        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AsPermissionApplyingMethod asPermissionApplyingMethod = new AsPermissionApplyingMethod();
                asPermissionApplyingMethod.applyAccess(myAsPermissionApplying.get(0).getPerIdentity(),
                        myAsPermissionApplying.get(0).getPerEmail(), myAsPermissionApplying.get(0).getPerTelNumber(),
                        myAsPermissionApplying.get(0).getPerQQNumber(), myAsPermissionApplying.get(0).getPerImPermission_1(),
                        myAsPermissionApplying.get(0).getPerImPermission_2(), myAsPermissionApplying.get(0).getPerAcPermission_1(),
                        myAsPermissionApplying.get(0).getPerAcPermission_2(), myAsPermissionApplying.get(0).getObjectId());
            }
        });

        not_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AsPermissionApplyingMethod asPermissionApplyingMethod_not = new AsPermissionApplyingMethod();
                asPermissionApplyingMethod_not.applyNotAccess(myAsPermissionApplying.get(0).getObjectId());
            }
        });
    }
}
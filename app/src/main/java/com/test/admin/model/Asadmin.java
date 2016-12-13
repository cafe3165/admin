package com.test.admin.model;

import com.test.admin.bean.AsAdministrator;
import com.test.admin.bean.AsParticipant;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import static com.test.admin.model.Function.showToast;

/**
 * 管理员功能类
 */

public class Asadmin{
    //返回值
    private BmobException login;

    //登录
    public BmobException Asadminsignin(String username, String pw) {
        AsAdministrator admin=new AsAdministrator();
        admin.setUsername(username);
        admin.setPassword(pw);
        admin.login(new SaveListener<AsAdministrator>() {
            @Override
            public void done(AsAdministrator asAdministrator, BmobException e) {
                login=e;
            }
        });
        return login;
    }

    public void modifyParInformation(final String myPhoneNumber, final String myStudentNumber, final String myStudentName){

        BmobQuery<AsParticipant> query = new BmobQuery<AsParticipant>();
        query.addWhereEqualTo("mobilePhoneNumber",myPhoneNumber);
        query.findObjects(new FindListener<AsParticipant>() {
            @Override
            public void done(List<AsParticipant> list, BmobException e) {

                if(e == null) {

                    showToast("0000 " + (String)BmobUser.getObjectByKey("objectId") + " " + myPhoneNumber);

                    /*AsParticipant asParticipant = new AsParticipant();
                    asParticipant.setUsername(list.get(0).getUsername());
                    asParticipant.setPassword(list.get(0).getPassword());
                    asParticipant.login(new SaveListener<AsParticipant>() {
                        @Override
                        public void done(AsParticipant asParticipant, BmobException e) {

                            if(e == null){

                                showToast("1111");
                                asParticipant.setParStuNumber(myStudentNumber);
                                asParticipant.setParName(myStudentName);
                                asParticipant.update(new UpdateListener() {
                                    @Override
                                    public void done(BmobException e) {

                                        if(e == null){

                                            showToast("修改成功");
                                        }else{

                                            showToast("修改失败");
                                        }
                                    }
                                });
                            }else{

                                showToast("2222");
                            }
                        }
                    });*/
                }else{

                    showToast(e.getErrorCode() + e.getMessage() );
                }
            }
        });
    }
}

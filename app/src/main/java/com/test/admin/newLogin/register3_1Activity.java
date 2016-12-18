package com.test.admin.newLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.test.admin.R;
import com.test.admin.bean.AsAdministrator;
import com.test.admin.bean.AsParticipant;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

import static com.test.admin.R.id.btn1;

public class register3_1Activity extends AppCompatActivity {
    Bundle bundle2 = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_step3_1);
        ImageButton imgBtn1 = (ImageButton)findViewById(R.id.imgBtn1);
        final Button btn1 = (Button)findViewById(R.id.btn1);
        final Bundle bundle1 = this.getIntent().getExtras();
        final EditText stuno = (EditText) findViewById(R.id.stuno);
        final EditText name = (EditText) findViewById(R.id.name);
        final EditText pnum = (EditText) findViewById(R.id.pnum);
        bundle2=this.getIntent().getExtras();

        imgBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register3_1Activity.this,register2_1Activity.class);
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });

        //下一步
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1.setEnabled(false);
                if (!TextUtils.isEmpty(stuno.getText().toString())&&!TextUtils.isEmpty(name.getText().toString())&&!TextUtils.isEmpty(pnum.getText().toString())){
                    if (pnum.getText().toString().matches("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(17[0-8])|(147))\\d{8}$")){
                        //检测手机号是否已注册
                        BmobQuery<AsParticipant> query = new BmobQuery<AsParticipant>();
                        query.addWhereEqualTo("username",pnum.getText().toString());
                        query.findObjects(new FindListener<AsParticipant>() {
                            @Override
                            public void done(List<AsParticipant> list, BmobException e) {
                                if (e==null&&list.size()!=0){
                                    Toast.makeText(register3_1Activity.this,"手机号已被注册",Toast.LENGTH_SHORT).show();
                                    btn1.setEnabled(true);
                                }
                                else if(e==null&&list.isEmpty()){
                                    //发送验证短信
                                    BmobSMS.requestSMSCode(pnum.getText().toString(), "verigy", new QueryListener<Integer>() {
                                        @Override
                                        public void done(Integer integer, BmobException e) {
                                            if (e==null){
                                                //发送成功,跳转
                                                Intent intent = new Intent(register3_1Activity.this,register4_1Activity.class);
                                                Bundle bundle = new Bundle();
                                                bundle.putString("college",bundle1.getString("college"));
                                                bundle.putString("grade",bundle1.getString("grade"));
                                                bundle.putString("stuno",stuno.getText().toString());
                                                bundle.putString("name",name.getText().toString());
                                                bundle.putString("pnum",pnum.getText().toString());
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                                btn1.setEnabled(true);
                                            }
                                            else{
                                                Toast.makeText(register3_1Activity.this,"验证码发送失败，请点击按钮重新发送",Toast.LENGTH_SHORT).show();
                                                btn1.setEnabled(true);
                                            }
                                        }
                                    });
                                }
                                else{
                                    Toast.makeText(register3_1Activity.this,"查询手机号是否已注册失败",Toast.LENGTH_SHORT).show();
                                    btn1.setEnabled(true);
                                }
                            }
                        });

                    }
                    else{
                        Toast.makeText(register3_1Activity.this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                        btn1.setEnabled(true);
                    }
                }
                else{
                    Toast.makeText(register3_1Activity.this,"请输入完整的信息",Toast.LENGTH_SHORT).show();
                    btn1.setEnabled(true);
                }
            }
        });
    }



    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            Intent intent = new Intent(register3_1Activity.this, register2_1Activity.class);
            intent.putExtras(bundle2);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

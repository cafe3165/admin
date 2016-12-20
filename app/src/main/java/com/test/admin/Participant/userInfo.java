package com.test.admin.Participant;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.test.admin.R;
import com.test.admin.bean.AsParticipant;

import java.io.File;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;

public class userInfo extends Activity {
    private ImageView get_touxian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_activity_user_info);

        //下载头像
        get_touxian = (ImageView)findViewById(R.id.ge_touxiang);
        AsParticipant par = BmobUser.getCurrentUser(AsParticipant.class);
        BmobFile headpic = par.getParHeadPortrait();
        downloadpic(headpic);


        Button button =(Button) findViewById(R.id.ge_edit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(userInfo.this,InfoEdit.class);
                startActivity(intent);
            }
        });

        ImageButton Ibutton1=(ImageButton) findViewById(R.id.my_apply);
        Ibutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(userInfo.this,MyAct.class);
                startActivity(intent);
            }
        });

        ImageButton Ibutton2=(ImageButton) findViewById(R.id.my_applied);
        Ibutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(userInfo.this,MyAct.class);
                startActivity(intent);
            }
        });
    }

    private void downloadpic(BmobFile file) {
        File saveFile = new File(Environment.getExternalStorageDirectory(),file.getFilename());
        file.download(saveFile, new DownloadFileListener() {
            @Override
            public void done(String s, BmobException e) {
                if (e==null){
                    Bitmap icon = BitmapFactory.decodeFile(s);
                    get_touxian.setImageBitmap(icon);
                }else{
                    Toast.makeText(userInfo.this,"头像加载失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onProgress(Integer integer, long l) {

            }
        });
    }
}

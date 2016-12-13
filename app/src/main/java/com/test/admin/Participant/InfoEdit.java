package com.test.admin.Participant;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.test.admin.R;
import com.test.admin.bean.AsParticipant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import cn.bmob.v3.update.UpdateDialogActivity;

public class InfoEdit extends AppCompatActivity {
    protected static Uri tempUri;
    private ImageView ge_touxian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_activity_info_edit);

        ge_touxian=(ImageView)findViewById(R.id.ge_touxian);
        Button button=(Button) findViewById(R.id.ge_edit_quxiao);
        Button uploadhpic=(Button) findViewById(R.id.ge_touchuan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //上传头像
        uploadhpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoosePicDialog();
            }
        });
    }

    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //显示修改头像的对话框
    private void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置头像");
        String[] items = {"选择本地图片","拍照"};
        builder.setNegativeButton("取消",null);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which){
                    case 0://选择本地照片
                        Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent,0);
                        break;
                    case 1://拍照
                        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        tempUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"image.jpg"));
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,tempUri);
                        startActivityForResult(openCameraIntent,1);
                        break;
                }
            }
        });
        builder.create().show();
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case 1:
                    startPhotoZoom(tempUri);
                    break;
                case 0:
                    startPhotoZoom(data.getData());
                    break;
                case 2:
                    if (data!=null){
                        setImageToView(data);
                    }
                    break;
            }
        }
    }

    //裁剪图片方法实现
    protected void startPhotoZoom(Uri uri){
        if (uri==null){
            toast("The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri,"image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 2);
    }

    //保存裁剪之后的图片数据
    protected  void setImageToView(Intent data){
        Bundle extras = data.getExtras();
        if(extras!=null){
            Bitmap photo = extras.getParcelable("data");
            ge_touxian.setImageBitmap(photo);
            uploadPic(photo);
        }
    }

    //将头像保存到SD卡
    public static String savePhoto(Bitmap photoBitmap, String path,String photoName) {
        String localPath = null;
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File photoFile = new File(path, photoName + ".png");
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(photoFile);
                if (photoBitmap != null) {
                    if (photoBitmap.compress(Bitmap.CompressFormat.PNG, 100,
                            fileOutputStream)) { // 转换完成
                        localPath = photoFile.getPath();
                        fileOutputStream.flush();
                    }
                }
            } catch (FileNotFoundException e) {
                photoFile.delete();
                localPath = null;
                e.printStackTrace();
            } catch (IOException e) {
                photoFile.delete();
                localPath = null;
                e.printStackTrace();
            } finally {
                try {
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                        fileOutputStream = null;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return localPath;
    }
    //上传头像
    private void uploadPic(Bitmap bitmap){
        String imagePath = savePhoto(bitmap, Environment.getExternalStorageDirectory().getAbsolutePath(), String.valueOf(System.currentTimeMillis()));
        final BmobFile icon =new BmobFile(new File(imagePath));
        icon.upload(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){
                    AsParticipant par = BmobUser.getCurrentUser(AsParticipant.class);
                    par.setParHeadPortrait(icon);
                    par.update(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e==null){
                                toast("头像保存成功");
                            }
                            else{
                                toast("头像保存失败");
                            }
                        }
                    });
                }
                else{
                    toast("头像上传失败");
                }
            }
        });


    }
}

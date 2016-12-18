package com.test.admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.admin.R;
import com.test.admin.bean.AsApplicationForm;
import com.test.admin.bean.AsParticipant;
import com.test.admin.model.AsAppForm;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static com.test.admin.bean.Parameters.staticObjectdId;

/**
 *报名表Item
 */

public class ApplicationFormAdapter extends BaseAdapter {

    private List<AsParticipant> datas;
    private Context context;

    public ApplicationFormAdapter(Context context, List<AsParticipant> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_participant, null);
        }

        ImageView item_iv_participant = (ImageView) convertView.findViewById(R.id.item_iv_participant);
        TextView item_tv_parName = (TextView) convertView.findViewById(R.id.item_tv_parName);
        TextView item_tv_parStuNumber = (TextView) convertView.findViewById(R.id.item_tv_parStuNumber);
        final Button item_bt_signIn = (Button) convertView.findViewById(R.id.signIn);

        final AsParticipant bean = datas.get(position);

        item_iv_participant.setImageResource(R.drawable.ic_01);
        item_tv_parName.setText(bean.getParName());
        item_tv_parStuNumber.setText(bean.getParStuNumber());

        //获取当前活动对应的报名表
        BmobQuery<AsApplicationForm> query = new BmobQuery<>();
        query.addWhereEqualTo("apAcId",staticObjectdId);
        query.findObjects(new FindListener<AsApplicationForm>() {
            @Override
            public void done(List<AsApplicationForm> list, BmobException e) {

                if(e == null){

                    //设置按钮状态
                    if(list.get(0).getApParStatus().get(position).equals("0")) {
                        item_bt_signIn.setText("签到");
                    }else {
                        item_bt_signIn.setText("已签到");
                    }
                }
            }
        });

        item_bt_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //更改按钮状态
                item_bt_signIn.setEnabled(false);
                switch (item_bt_signIn.getText().toString()){
                    case "签到":
                        AsAppForm asAppForm = new AsAppForm();
                        asAppForm.acParSignIn(item_bt_signIn,staticObjectdId,position);
                        break;
                    case  "已签到":
                        AsAppForm not_asAppForm = new AsAppForm();
                        not_asAppForm.acParSignOut(item_bt_signIn,staticObjectdId,position);
                        break;
                }
            }
        });
        return convertView;
    }
}

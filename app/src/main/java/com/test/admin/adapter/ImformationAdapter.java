package com.test.admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.admin.R;
import com.test.admin.bean.AsImformation;

import java.util.List;

/**
 * Created by Administrator on 2016/11/18 0018.
 */

public class ImformationAdapter extends BaseAdapter{

    private List<AsImformation> datas;
    private Context context;

    public ImformationAdapter(Context context, List<AsImformation> datas) {
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
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_activity, null);
        }

        ImageView item_iv_activity = (ImageView) convertView.findViewById(R.id.item_iv_activity);
        TextView item_tv_a1 = (TextView) convertView.findViewById(R.id.item_tv_a1);
        TextView item_tv_a2 = (TextView) convertView.findViewById(R.id.item_tv_a2);

        AsImformation bean = datas.get(position);

        item_iv_activity.setImageResource(R.drawable.ic_01);
        item_tv_a1.setText(bean.getImTitle());
        item_tv_a2.setText(bean.getImContent());

        return convertView;
    }
}

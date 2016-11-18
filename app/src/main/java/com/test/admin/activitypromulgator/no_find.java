package com.test.admin.activitypromulgator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.test.admin.R;
import com.test.admin.adapter.ImformationAdapter;
import com.test.admin.bean.AsImformation;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static com.test.admin.bean.Parameters.staticObjectdId;

public class no_find extends AppCompatActivity {

    private ListView lv_imformation;
    private ImformationAdapter mAdapter;
    private List<AsImformation> asImformationList = new ArrayList<AsImformation>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_activity_no_find);

        lv_imformation = (ListView)findViewById(R.id.itemList);

        BmobQuery<AsImformation> bmobQuery = new BmobQuery<AsImformation>();
        bmobQuery.addQueryKeys("objectdId,imTitle,imContent");
        bmobQuery.findObjects(new FindListener<AsImformation>() {
            @Override
            public void done(List<AsImformation> list, BmobException e) {

                if (e == null) {
                    asImformationList.addAll(list);

                    mAdapter = new ImformationAdapter(no_find.this,asImformationList);

                    lv_imformation.setAdapter(mAdapter);
                }
            }
        });

        lv_imformation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(no_find.this, ImformViewActivity.class));

                AsImformation asImformation  = asImformationList.get(position);
                staticObjectdId = asImformation.getObjectId().toString();
            }
        });
    }
}

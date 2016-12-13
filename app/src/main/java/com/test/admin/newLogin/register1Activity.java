package com.test.admin.newLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.test.admin.R;

import java.util.ArrayList;
import java.util.List;

public class register1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_step1);
        ListView lv1 = (ListView)findViewById(R.id.lv1);
        lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,getData()));

        lv1.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(register1Activity.this,register2_1Activity.class);
                        startActivity(intent);
                    }
                }

        );
    }
    private List<String> getData()
    {
        List<String> data = new ArrayList<String>();
        data.add("数计学院");
        data.add("经管学院");
        data.add("人文学院");
        data.add("生工学院");
        data.add("土木学院");
        data.add("海洋学院");
        data.add("建筑学院");
        data.add("物信学院");
        data.add("外语学院");
        data.add("化学学院");
        data.add("紫金学院");
        data.add("法学院");
        data.add("环资学院");
        data.add("机械学院");
        data.add("电气学院");
        data.add("石化学院");

        return data;
    }
}

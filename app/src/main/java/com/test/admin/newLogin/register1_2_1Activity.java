package com.test.admin.newLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.test.admin.R;

import java.util.ArrayList;
import java.util.List;

public class register1_2_1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_step1);

        ImageButton btn1 = (ImageButton)findViewById(R.id.imgBtn1);
        ListView lv1 = (ListView)findViewById(R.id.lv1);
        lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,getData()));

        lv1.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(register1_2_1Activity.this,register1_2_2Activity.class);
                        startActivity(intent);
                    }
                }
        );
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register1_2_1Activity.this,identityActivity.class);
                startActivity(intent);
            }
        });
    }
    private List<String> getData()
    {
        List<String> data = new ArrayList<String>();
        data.add("海外教育学院");
        data.add("人文社会科学学院");
        data.add("经济与管理学院");
        data.add("化学学院");
        data.add("石油化工学院");
        data.add("土木工程学院");
        data.add("外国语学院");
        data.add("厦门工艺美术学院");
        data.add("建筑学院");
        data.add("数学与计算机科学学院/软件学院");
        data.add("机械工程及自动化学院");
        data.add("法学院");
        data.add("材料科学与工程学院");
        data.add("物理与信息工程学院");
        data.add("环境与资源学院");
        data.add("生物科学与工程学院");
        data.add("电气工程及自动化学院");
        data.add("马克思主义学院");
        data.add("紫金矿业学院");
        data.add("海洋学院学院");
        data.add("其他学院");

        return data;
    }
}

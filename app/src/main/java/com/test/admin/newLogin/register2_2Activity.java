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

public class register2_2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_step2);
        ListView lv1 = (ListView)findViewById(R.id.lv1);
        lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,getData()));


        lv1.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(register2_2Activity.this,register2_2Activity.class);
                        startActivity(intent);
                    }
                }
        );
    }
    private List<String> getData()
    {
        List<String> data = new ArrayList<String>();
        data.add("2009级");
        data.add("2010级");
        data.add("2011级");
        data.add("2012级");
        data.add("2013级");
        data.add("2014级");
        data.add("2015级");
        data.add("2016级");
        data.add("2017级");
        data.add("2018级");
        data.add("2019级");
        data.add("2020级");
        data.add("2021级");
        data.add("2022级");
        data.add("2023级");
        data.add("2024级");
        data.add("2025级");
        data.add("2026级");
        data.add("2027级");

        return data;
    }
}

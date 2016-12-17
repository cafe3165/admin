package com.test.admin.newLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.test.admin.R;

import java.util.ArrayList;
import java.util.List;

public class register1_2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_step1_2);

        ImageButton imgBtn1 = (ImageButton)findViewById(R.id.imgBtn1);
        ListView lv1 = (ListView)findViewById(R.id.lv1);
        lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,getData()));

        imgBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register1_2Activity.this,loginRegisterActivity.class);
                startActivity(intent);
            }
        });
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //if(data[position]);
                String str = (String) ((TextView) view).getText();
                if(str.equals("院级负责人"))
                {
                    Intent intent = new Intent(register1_2Activity.this,register1_2_1Activity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("identity","院级负责人");
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(register1_2Activity.this,register2_2xActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("identity","校级负责人");
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            }
        });
    }

    private List<String>getData()
    {
        List<String> data = new ArrayList<String>();
        data.add("院级负责人");
        data.add("校级负责人");
        return data;
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            Intent intent = new Intent(register1_2Activity.this, identityActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

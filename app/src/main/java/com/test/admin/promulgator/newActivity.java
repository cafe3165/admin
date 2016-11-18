package com.test.admin.promulgator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.test.admin.R;
import com.test.admin.model.AsAcApply;

public class newActivity extends AppCompatActivity {

    private EditText acTitle;
    private EditText acOrganizer;
    private EditText acStartTime;
    private EditText acDeadLine;
    private EditText acPlace;
    private EditText acContent;
    private EditText acAudiences;
    private EditText acPushScope_1;
    private EditText acPushScope_2;
    private Button acIssue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        acTitle = (EditText)findViewById(R.id.widget32);
        acOrganizer = (EditText)findViewById(R.id.widget47);
        acStartTime = (EditText)findViewById(R.id.widget37);
        acDeadLine = (EditText)findViewById(R.id.widget40);
        acPlace = (EditText)findViewById(R.id.widget42);
        acContent = (EditText)findViewById(R.id.widget44);
        acAudiences = (EditText)findViewById(R.id.widget46);
        acPushScope_1 = (EditText)findViewById(R.id.widget59);
        acIssue = (Button)findViewById(R.id.widget66);

        acIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String myAcTitle = acTitle.getText().toString();
                String myAcOrganizer = acOrganizer.getText().toString();
                String myAcStarTime = acStartTime.getText().toString();
                String myAcDeadTime = acDeadLine.getText().toString();
                String myAcPlace = acPlace.getText().toString();
                String myAcContent = acContent.getText().toString();
                String myAcAudiences = acAudiences.getText().toString();
                String myAcPushScope_1 = acPushScope_1.getText().toString();
                String myAcPushScope_2 = acPushScope_1.getText().toString();
                String proObjectId = new String();
                AsAcApply asAcApply = new AsAcApply();
                asAcApply.acApplyAdd(myAcTitle, myAcOrganizer, myAcStarTime, myAcDeadTime, myAcPlace, myAcContent,
                        myAcAudiences, myAcPushScope_1, myAcPushScope_2,proObjectId);
            }
        });
    }
}

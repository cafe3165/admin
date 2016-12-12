package com.test.admin.promulgator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.test.admin.R;
import com.test.admin.bean.AsImformation;
import com.test.admin.bean.AsPromulgator;
import com.test.admin.model.AsImformationMethod;

import static com.test.admin.bean.Parameters.pObjectdId;

public class newNotice extends AppCompatActivity {

    private EditText imTitle;
    private EditText imOrganizer;
    private EditText imContent;
    private EditText imAudiences;
    private EditText imPushScope;
    private Button publish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notice);

        imTitle = (EditText)findViewById(R.id.imTitle);
        imOrganizer = (EditText)findViewById(R.id.imOrganizer);
        imContent = (EditText)findViewById(R.id.imContent);
        imAudiences = (EditText)findViewById(R.id.imAudiences);
        imPushScope = (EditText)findViewById(R.id.imPushScope);
        publish = (Button)findViewById(R.id.publish);

        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String myImTitle = imTitle.getText().toString();
                String myImOrganizer = imOrganizer.getText().toString();
                String myImContent = imContent.getText().toString();
                String myImAudiences = imAudiences.getText().toString();
                String myImPushScope = imPushScope.getText().toString();
                pObjectdId = (String) AsPromulgator.getObjectByKey("objectId");

                AsImformationMethod asImformation = new AsImformationMethod();
                asImformation.asImAdd(myImTitle,myImOrganizer,myImContent,myImAudiences,myImPushScope,myImPushScope,pObjectdId);
            }
        });

    }
}

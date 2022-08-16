package com.example.dell.babycare.Activities.developmentActivities.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.dell.babycare.R;

public class Detail_Question extends AppCompatActivity {

    TextView question_tview;
    TextView answer_tview;
    Toolbar toolbar;
    String question,answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__question);

        question_tview= (TextView) findViewById(R.id.detail_quesion_question);
        answer_tview= (TextView) findViewById(R.id.detail_question_answer);
        toolbar= (Toolbar) findViewById(R.id.question_detail_question_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.question_detail_title);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        question= getIntent().getExtras().getString("question");
        answer=getIntent().getExtras().getString("answer");
        question_tview.setText(question);
        answer_tview.setText(answer);

    }
}

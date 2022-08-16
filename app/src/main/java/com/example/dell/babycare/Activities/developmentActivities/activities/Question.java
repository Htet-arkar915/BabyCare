package com.example.dell.babycare.Activities.developmentActivities.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.dell.babycare.R;

import static com.example.dell.babycare.R.id.addfloating;

public class Question extends AppCompatActivity implements View.OnClickListener {
FloatingActionButton addQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        addQuestion= (FloatingActionButton) findViewById(addfloating);
        addQuestion.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent go=new Intent(Question.this,AddQuestion.class);
        startActivity(go);
    }
}

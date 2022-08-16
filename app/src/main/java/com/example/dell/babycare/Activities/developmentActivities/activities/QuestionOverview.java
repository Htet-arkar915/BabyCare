package com.example.dell.babycare.Activities.developmentActivities.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.dell.babycare.Activities.developmentActivities.adapters.Question_Overview_Adapter;
import com.example.dell.babycare.R;

import java.util.ArrayList;

public class QuestionOverview extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ArrayList<String> mytitlelist=new ArrayList<String>();
    ArrayList<String> jsonfilelist=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_overview);

        recyclerView= (RecyclerView) findViewById(R.id.question_overview_recyclerview);
        toolbar= (Toolbar) findViewById(R.id.question_overview_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.question_overview_title);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mytitlelist.add("ႏို႔တိုက္ေကြၽးျခင္းအေၾကာင္း");
        mytitlelist.add("ေမြးကင္းစကေလးငယ္၏သြင္ျပင္ လကၡဏာမ်ား");
        mytitlelist.add("မိဘမ်ား၏အယူအဆ အမွားမ်ား ႏွင့္ အေထြေထြဗဟုသုတမ်ား");
        mytitlelist.add("ေရာဂါ မ်ားအေၾကာင္း");
        /*mytitlelist.add("ကေလးမ်ား၏ ဆီး၊ ဝမ္းအက်င့္");
        mytitlelist.add("အစာစားျခင္း");*/
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter= new Question_Overview_Adapter(QuestionOverview.this,mytitlelist);
        recyclerView.setAdapter(adapter);
    }

}









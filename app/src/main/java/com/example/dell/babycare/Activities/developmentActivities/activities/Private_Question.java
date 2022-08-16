package com.example.dell.babycare.Activities.developmentActivities.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.dell.babycare.Activities.developmentActivities.model.MDQuestion_Model;
import com.example.dell.babycare.Activities.developmentActivities.adapters.QuestionAdapter;
import com.example.dell.babycare.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class Private_Question extends AppCompatActivity implements View.OnClickListener {
    RecyclerView questionRecycler;
    Firebase newFirebase;
    ArrayList<MDQuestion_Model>  questiondata=new ArrayList<>();
    ArrayList<String> questionArray=new ArrayList<>();
    ArrayList<Integer> accountArray=new ArrayList<>();
    RecyclerView.Adapter adapter;
    SharedPreferences share;
    String keyString;
    FloatingActionButton addQuestionbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private__question);
        addQuestionbtn= (FloatingActionButton) findViewById(R.id.addqfloating);
        addQuestionbtn.setOnClickListener(this);
        questionRecycler= (RecyclerView) findViewById(R.id.recycle_private_question);
        share=getSharedPreferences("AccountData", MODE_PRIVATE);
        keyString=share.getString("key",null);
        Firebase.setAndroidContext(Private_Question.this);
        newFirebase=new Firebase("https://myschoolserver-dcae9.firebaseio.com/");
        newFirebase.child("ParentQuestion").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                questiondata.clear();
                for (DataSnapshot sh:dataSnapshot.getChildren())
                {
                    MDQuestion_Model questionModel=new MDQuestion_Model();
                    questionModel=sh.getValue(MDQuestion_Model.class);
                    if (questionModel.getKey().equals(keyString))
                        questiondata.add(questionModel);

                }


                questionRecycler.setLayoutManager(new LinearLayoutManager(Private_Question.this));
                adapter=new QuestionAdapter(Private_Question.this,questiondata);
                questionRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.addqfloating:
                Intent goo=new Intent(Private_Question.this,AddQuestion.class);
                startActivity(goo);
                break;
        }
    }
}

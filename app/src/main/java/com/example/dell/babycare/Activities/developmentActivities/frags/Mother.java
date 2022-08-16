package com.example.dell.babycare.Activities.developmentActivities.frags;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.babycare.Activities.developmentActivities.activities.AddQuestion;
import com.example.dell.babycare.Activities.developmentActivities.model.MDQuestion_Model;
import com.example.dell.babycare.Activities.developmentActivities.adapters.QuestionAdapter;
import com.example.dell.babycare.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by hp on 15-Jan-18.
 */

public class Mother extends Fragment implements View.OnClickListener {
    RecyclerView questionRecycler;
    Firebase newFirebase;
    ArrayList<MDQuestion_Model> questiondata=new ArrayList<>();
    ArrayList<String> questionArray=new ArrayList<>();
    ArrayList<Integer> accountArray=new ArrayList<>();
    RecyclerView.Adapter adapter;
    SharedPreferences share;
    String keyString;
    FloatingActionButton addQuestionbtn;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_private__question,container,false);
        addQuestionbtn= (FloatingActionButton) v.findViewById(R.id.addqfloating);
        questionRecycler= (RecyclerView) v.findViewById(R.id.recycle_private_question);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addQuestionbtn.setOnClickListener(this);
        share=getActivity().getSharedPreferences("AccountData", MODE_PRIVATE);
        keyString=share.getString("key",null);
        Firebase.setAndroidContext(getActivity());
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


                questionRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                adapter=new QuestionAdapter(getActivity(),questiondata);
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
                Intent goo=new Intent(getContext(),AddQuestion.class);
                startActivity(goo);
                break;
        }

    }
}

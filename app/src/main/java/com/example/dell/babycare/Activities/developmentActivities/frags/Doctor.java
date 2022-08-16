package com.example.dell.babycare.Activities.developmentActivities.frags;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


import com.example.dell.babycare.Activities.developmentActivities.DevelopmentModel;
import com.example.dell.babycare.Activities.developmentActivities.activities.Post_activity;
import com.example.dell.babycare.Activities.developmentActivities.adapters.NewAdapter;
import com.example.dell.babycare.Activities.developmentActivities.model.Comment_Count_Model;
import com.example.dell.babycare.Activities.developmentActivities.model.Like_model;
import com.example.dell.babycare.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by hp on 15-Jan-18.
 */

public class Doctor extends Fragment implements View.OnClickListener {
    View v;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ProgressBar pbar;
    ArrayList<DevelopmentModel> postData;
    public ArrayList<Like_model> likedata=new ArrayList<>();
    ArrayList<Comment_Count_Model> comment_count_list=new ArrayList<Comment_Count_Model>();
    ArrayList<Comment_Count_Model> real_comment_count_list=new ArrayList<Comment_Count_Model>();
    ArrayList<Boolean> isLike=new ArrayList<>();
    Firebase newFirebase;
    Like_model like=new Like_model();
    FloatingActionButton addpostFloating;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.activity_new_feed,container,false);
        addpostFloating= (FloatingActionButton) v.findViewById(R.id.addpost);
        pbar= (ProgressBar) v.findViewById(R.id.new_progress);
        recyclerView= (RecyclerView) v.findViewById(R.id.post_recycler);
        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addpostFloating.setOnClickListener(this);
        postData=new ArrayList<DevelopmentModel>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Firebase.setAndroidContext(getContext());
        newFirebase=new Firebase("https://myschoolserver-dcae9.firebaseio.com/");

        newFirebase.child("Discard_Mother").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                postData.clear();
                upDateData(dataSnapshot);

            }


            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });





    }

    private void upDateData(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh : dataSnapshot.getChildren()) {

            DevelopmentModel eModel = new DevelopmentModel();
            eModel = sh.getValue(DevelopmentModel.class);
            postData.add(eModel);
            // isLike.add(false);
        }
        newFirebase.child("Comment_Count_Table").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                comment_count_list.clear();
                real_comment_count_list.clear();
                getCommentCount(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void getCommentCount(DataSnapshot dataSnapshot) {
        for(DataSnapshot sh:dataSnapshot.getChildren()){
            Comment_Count_Model comment_count_model=sh.getValue(Comment_Count_Model.class);
            comment_count_list.add(comment_count_model);
        }
        for (DevelopmentModel d:postData){
            for (int k=0;k<comment_count_list.size();k++){
                if(comment_count_list.get(k).getCommentkey().equals(d.getCommentKey()))
                    real_comment_count_list.add(comment_count_list.get(k));
            }

        }
        adapter = new NewAdapter(getActivity(), postData, isLike,real_comment_count_list);
        pbar.setVisibility(View.INVISIBLE);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        Intent goo=new Intent(getContext(),Post_activity.class);
        startActivity(goo);
    }
}


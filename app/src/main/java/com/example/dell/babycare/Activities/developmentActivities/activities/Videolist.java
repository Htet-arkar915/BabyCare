package com.example.dell.babycare.Activities.developmentActivities.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.example.dell.babycare.Activities.developmentActivities.adapters.MyVideoViewRecycle;
import com.example.dell.babycare.Activities.developmentActivities.model.Videoinfo_model;
import com.example.dell.babycare.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

public class Videolist extends AppCompatActivity {
    VideoView videoView;
    RecyclerView videoview_recycle;
    RecyclerView.Adapter MyrecycleAdapter;
    ArrayList<Videoinfo_model> videolist;
    YouTubePlayerView youTubePlayerView;
    Firebase firebase;
    Toolbar tb;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videolist);
        tb=(Toolbar)findViewById(R.id.tool);
        pb=(ProgressBar)findViewById(R.id.progress_vd);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("Videos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Firebase.setAndroidContext(this);
        videolist=new ArrayList<Videoinfo_model>();
        firebase= new Firebase("https://challenge-1d2ec.firebaseio.com/");

        firebase.child("Videoinfo").child("videolist").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                videolist.clear();
                produceVideo(dataSnapshot);
            }
            private void produceVideo(DataSnapshot dataSnapshot) {


                for (DataSnapshot dsh:dataSnapshot.getChildren()){
                    Videoinfo_model mymodel=new Videoinfo_model();
                    mymodel.setVideocode(dsh.getValue(Videoinfo_model.class).getVideocode());
                    mymodel.setTitlename(dsh.getValue(Videoinfo_model.class).getTitlename());

                    videolist.add(mymodel);
                    //Toast.makeText(Videolist.this, mymodel.getTitlename()+"", Toast.LENGTH_SHORT).show();
                }

                videoview_recycle=(RecyclerView)findViewById(R.id.recycle_videoview);
                MyrecycleAdapter=new MyVideoViewRecycle(Videolist.this,videolist);
                //Toast.makeText(Videolist.this,videolist.get(0).getTitlename()+ "", Toast.LENGTH_SHORT).show();
                videoview_recycle.setLayoutManager(new GridLayoutManager(Videolist.this,2));
                videoview_recycle.setAdapter(MyrecycleAdapter);
                pb.setVisibility(View.INVISIBLE);
            }


            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}

package com.example.dell.babycare.Activities.developmentActivities.youtubeplayer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.babycare.Activities.developmentActivities.model.Videoinfo_model;
import com.example.dell.babycare.R;
import com.firebase.client.Firebase;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayerView;

public class Play_Video extends YouTubeBaseActivity {

    YouTubePlayerView youTubePlayerView;
    EditText title,code;
    Button btn,btnremove;
    Firebase fb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        Firebase.setAndroidContext(this);
        fb=new Firebase("https://challenge-1d2ec.firebaseio.com/");
        title=(EditText)findViewById(R.id.titlename);
        code=(EditText)findViewById(R.id.videocode);
        btn=(Button)findViewById(R.id.savebtn);
        btnremove=(Button)findViewById(R.id.removeall);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (title.getText()!=null&&code.getText()!=null){
                    Videoinfo_model mymodel=new Videoinfo_model();
                    mymodel.setTitlename(title.getText().toString());
                    mymodel.setVideocode(code.getText().toString());
                    fb.child("Videoinfo").child("videolist").push().setValue(mymodel);
                    Toast.makeText(Play_Video.this, "finish", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fb.child("Videoinfo").removeValue();
            }
        });

    }
}

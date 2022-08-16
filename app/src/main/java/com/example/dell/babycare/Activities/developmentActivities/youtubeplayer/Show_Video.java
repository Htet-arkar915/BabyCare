package com.example.dell.babycare.Activities.developmentActivities.youtubeplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dell.babycare.Activities.developmentActivities.activities.Videolist;
import com.example.dell.babycare.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Show_Video extends YouTubeBaseActivity {YouTubePlayerView youTubePlayer;
    String code,titlename;
    TextView titleshow;
    Button btnback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_video);
        code=getIntent().getStringExtra("videocode");
        titlename=getIntent().getStringExtra("title");
        titleshow=(TextView)findViewById(R.id.video_titleshow);
        titleshow.setText(titlename);
        youTubePlayer=(YouTubePlayerView) findViewById(R.id.videoplayershow);
        youTubePlayer.initialize(" AIzaSyCNjZowEQITOuuL0FBUmXDCgrryeKHAjZE ",
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {

                        // do any work here to cue video, play video, etc.
                        youTubePlayer.cueVideo(code);
                    }
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });
        btnback=(Button)findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Show_Video.this,Videolist.class));
                finish();
            }
        });


    }
}

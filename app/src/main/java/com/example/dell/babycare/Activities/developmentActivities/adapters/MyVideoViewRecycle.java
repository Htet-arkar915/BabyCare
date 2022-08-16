package com.example.dell.babycare.Activities.developmentActivities.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.babycare.Activities.developmentActivities.model.Videoinfo_model;
import com.example.dell.babycare.Activities.developmentActivities.youtubeplayer.Show_Video;
import com.example.dell.babycare.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

import java.util.ArrayList;

/**
 * Created by Admin on 1/15/2018.
 */
public class MyVideoViewRecycle extends RecyclerView.Adapter <MyVideoViewRecycle.MyHolder> {
        Context con;
        ArrayList<Videoinfo_model> videoinfo_list;


        public MyVideoViewRecycle(Context context, ArrayList<Videoinfo_model> videolist) {
            this.con=context;
            this.videoinfo_list=videolist;
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(con).inflate(R.layout.title_recycle_cu,parent,false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, final int position){
            holder.titleview.setText(videoinfo_list.get(position).getTitlename());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    con.startActivity(new Intent(con,Show_Video.class)
                            .putExtra("videocode",videoinfo_list.get(position).getVideocode())
                            .putExtra("title",videoinfo_list.get(position).getTitlename()));
                }
            });
          /*  holder.videoplayer.cueVideo(videoinfo_list.get(position).getVideocode());
        holder.videoplayer.initialize(" AIzaSyBfEaFyOQurO7MtOzMrAh_KUPldfaJp1h4  ",
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {

                        // do any work here to cue video, play video, etc.
                        youTubePlayer.cueVideo("6bCvCrj6jhM");
                    }
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });*/


        }

        @Override
        public int getItemCount() {
            return videoinfo_list.size();
        }

        public class MyHolder extends RecyclerView.ViewHolder {
            YouTubePlayer videoplayer;
            TextView titleview;

            public MyHolder(View itemView) {
                super(itemView);
                titleview = (TextView) itemView.findViewById(R.id.title_txt);
            }

    }
}

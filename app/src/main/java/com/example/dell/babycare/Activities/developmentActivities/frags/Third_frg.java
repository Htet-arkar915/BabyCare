package com.example.dell.babycare.Activities.developmentActivities.frags;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dell.babycare.R;

/**
 * Created by Admin on 1/14/2018.
 */

public class Third_frg extends Fragment{
    ImageView imgone;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.frag_three,container,false);
        imgone=(ImageView)view.findViewById(R.id.animal_imgthree);
        imgone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediathree=MediaPlayer.create(view.getContext(),R.raw.cryingbaby);
                mediathree.start();
            }
        });
        return view;
    }
}

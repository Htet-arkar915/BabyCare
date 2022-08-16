package com.example.dell.babycare.Activities.developmentActivities.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.dell.babycare.Activities.developmentActivities.adapters.MyPagerAdapter;
import com.example.dell.babycare.R;

public class Animals_Voice extends AppCompatActivity {

    ImageView next,previous;
    ViewPager viewPager;
    MediaPlayer mediaone,mediatwo,mediathree,mediafour,mediafive;
    boolean soundone,soundtwo,soundthree,soundfour,soundfive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals);
        next=(ImageView)findViewById(R.id.next);
        previous=(ImageView)findViewById(R.id.previous);
        previous.setVisibility(View.INVISIBLE);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
            }
        });
        soundone = false;
        soundfive = false;
        soundthree = false;
        soundfour = false;
        soundtwo = false;
        viewPager = (ViewPager) findViewById(R.id.animal_pager);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        mediaone = MediaPlayer.create(this, R.raw.birdsong);
        mediafive = MediaPlayer.create(this, R.raw.catvoice);
        mediatwo = MediaPlayer.create(this, R.raw.tigervoice);
        mediathree = MediaPlayer.create(this, R.raw.lionvoice);
        mediafour = MediaPlayer.create(this, R.raw.monkeyvoice);
        mediaone.start();
        soundone = true;

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    previous.setVisibility(View.INVISIBLE);
                    if (soundtwo) {
                        mediatwo.pause();
                        mediaone.start();
                        soundtwo = false;
                    } else if (soundthree) {
                        mediathree.pause();
                        mediaone.start();
                        soundthree = false;
                    } else if (soundfour) {
                        mediafour.pause();
                        mediaone.start();
                        soundfour = false;
                    } else if (soundfive) {
                        mediafive.pause();
                        mediaone.start();
                        soundfive = false;
                    } else
                        mediaone.start();
                    soundone = true;

                } else if (position == 1) {
                    previous.setVisibility(View.VISIBLE);
                    next.setVisibility(View.VISIBLE);
                    if (soundone) {
                        mediaone.pause();
                        mediatwo.start();
                        soundone = false;
                    } else if (soundthree) {
                        mediathree.pause();
                        mediatwo.start();
                        soundthree = false;
                    } else if (soundfour) {
                        mediafour.pause();
                        mediatwo.start();
                        soundfour = false;
                    } else if (soundfive) {
                        mediafive.pause();
                        mediatwo.start();
                        soundfive = false;
                    } else
                        mediatwo.start();
                    soundtwo = true;
                } else if (position == 2) {
                    previous.setVisibility(View.VISIBLE);
                    next.setVisibility(View.VISIBLE);
                    if (soundone) {
                        mediaone.pause();
                        mediathree.start();
                        soundone = false;
                    } else if (soundtwo) {
                        mediatwo.pause();
                        mediathree.start();
                        soundtwo = false;
                    } else if (soundfour) {
                        mediafour.pause();
                        mediathree.start();
                        soundfour = false;
                    } else if (soundfive) {
                        mediafive.pause();
                        mediathree.start();
                        soundfive = false;
                    } else
                        mediathree.start();
                    soundthree = true;
                } else if (position == 3) {
                    previous.setVisibility(View.VISIBLE);
                    next.setVisibility(View.VISIBLE);
                    if (soundone) {
                        mediaone.pause();
                        mediafour.start();
                        soundone = false;
                    } else if (soundthree) {
                        mediathree.pause();
                        mediafour.start();
                        soundthree = false;
                    } else if (soundtwo) {
                        mediatwo.pause();
                        mediafour.start();
                        soundtwo = false;
                    } else if (soundfive) {
                        mediafive.pause();
                        mediafour.start();
                        soundfive = false;
                    } else
                        mediafour.start();
                    soundfour = true;
                } else if (position == 4) {previous.setVisibility(View.VISIBLE);
                    next.setVisibility(View.INVISIBLE);
                    if (soundone) {
                        mediaone.pause();
                        mediafive.start();
                        soundone = false;
                    } else if (soundthree) {
                        mediathree.pause();
                        mediafive.start();
                        soundthree = false;
                    } else if (soundfour) {
                        mediafour.pause();
                        mediafive.start();
                        soundfour = false;
                    } else if (soundtwo) {
                        mediatwo.pause();
                        mediafive.start();
                        soundtwo = false;
                    } else
                        mediafive.start();
                    soundfive = true;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (soundone){
            mediaone.pause();
        }else if (soundtwo){
            mediatwo.pause();
        }else if (soundthree){
            mediathree.pause();
        }else if (soundfour){
            mediafour.pause();
        }else if (soundfive){
            mediafive.pause();
        }
        startActivity(new Intent(Animals_Voice.this,Happy_Baby.class));
    }
}

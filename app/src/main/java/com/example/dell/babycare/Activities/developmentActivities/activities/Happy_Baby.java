package com.example.dell.babycare.Activities.developmentActivities.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.dell.babycare.R;

public class Happy_Baby extends AppCompatActivity implements View.OnClickListener {

    Button btn_video,btn_zoo,btn_phone;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.happy_baby);
        toolbar = (Toolbar) findViewById(R.id.happytool);
        toolbar.setTitle("သားေလးေပ်ာ္ဖုိ ့");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_video=(Button)findViewById(R.id.video_play);
        btn_phone=(Button)findViewById(R.id.phone_void);
        btn_zoo=(Button)findViewById(R.id.void_zoo);
        btn_video.setOnClickListener(this);
        btn_phone.setOnClickListener(this);
        btn_zoo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.video_play:
                startActivity(new Intent(Happy_Baby.this,Videolist.class));
                break;
            case R.id.void_zoo:
                startActivity(new Intent(Happy_Baby.this,Animals_Voice.class));
                finish();
                break;
            case R.id.phone_void:
                startActivity(new Intent(Happy_Baby.this,Phone_Voice.class));
                finish();
                break;
        }
    }
}

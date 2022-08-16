package com.example.dell.babycare.Activities.developmentActivities.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.dell.babycare.R;

public class Phone_Voice extends AppCompatActivity implements View.OnClickListener {

    Button btn_one,btn_two,btn_three,btn_four,btn_five,btn_six,btn_seven,btn_eight,btn_nine,btn_zero,btn_star,btn_ch;

    MediaPlayer laughmedia,birdmedia,media3,media4,media5,media6,media7,media8,media9,media0,mediastar,mediach;

    boolean start1=false;
    boolean start2=false;
    boolean start3=false;
    boolean start4=false;
    boolean start5=false;
    boolean start6=false;
    boolean start7=false;
    boolean start8=false;
    boolean start9=false;
    boolean start0=false;
    boolean startstar=false;
    boolean startch=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_voice);

        btn_one=(Button)findViewById(R.id.btn1);
        btn_two=(Button)findViewById(R.id.btn2);
        btn_three=(Button)findViewById(R.id.btn3);
        btn_four=(Button)findViewById(R.id.btn4);
        btn_five=(Button)findViewById(R.id.btn5);
        btn_six=(Button)findViewById(R.id.btn6);
        btn_seven=(Button)findViewById(R.id.btn7);
        btn_eight=(Button)findViewById(R.id.btn8);
        btn_nine=(Button)findViewById(R.id.btn9);
        btn_zero=(Button)findViewById(R.id.btn0);
        btn_star=(Button)findViewById(R.id.btnstar);
        btn_ch=(Button)findViewById(R.id.btnch);

        laughmedia=MediaPlayer.create(Phone_Voice.this,R.raw.laugh);
        birdmedia=MediaPlayer.create(Phone_Voice.this,R.raw.birdsong);
        media3=MediaPlayer.create(Phone_Voice.this,R.raw.cryingbaby);
        media4=MediaPlayer.create(Phone_Voice.this,R.raw.chickensong);
        media5=MediaPlayer.create(Phone_Voice.this,R.raw.birdsonghaha);
        media6=MediaPlayer.create(Phone_Voice.this,R.raw.funnybaby);
        media7=MediaPlayer.create(Phone_Voice.this,R.raw.laugh);
        media8=MediaPlayer.create(Phone_Voice.this,R.raw.cryingbaby);
        media9=MediaPlayer.create(Phone_Voice.this,R.raw.funnybaby);
        media0=MediaPlayer.create(Phone_Voice.this,R.raw.birdsong);
        mediastar=MediaPlayer.create(Phone_Voice.this,R.raw.chickensong);
        mediach=MediaPlayer.create(Phone_Voice.this,R.raw.birdsong);


        btn_one.setOnClickListener(this);
        btn_two.setOnClickListener(this);
        btn_three.setOnClickListener(this);
        btn_four.setOnClickListener(this);
        btn_five.setOnClickListener(this);
        btn_six.setOnClickListener(this);
        btn_seven.setOnClickListener(this);
        btn_eight.setOnClickListener(this);
        btn_nine.setOnClickListener(this);
        btn_zero.setOnClickListener(this);
        btn_star.setOnClickListener(this);
        btn_ch.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {




        switch (v.getId()){

            case R.id.btn1:


                    if (start2){

                        birdmedia.pause();
                        start2=false;
                        laughmedia.start();
                    }else if (start3){
                        media3.pause();
                        start3=false;
                        laughmedia.start();
                    }else if (start4){
                        media4.pause();
                        start4=false;
                        laughmedia.start();
                    }else if (start5){
                        media5.pause();
                        start5=false;
                        laughmedia.start();
                    }else if (start6){
                        media6.pause();
                        start6=false;
                        laughmedia.start();
                    }else if (start7){
                        media7.pause();
                        start7=false;
                        laughmedia.start();
                    }else if (start8){
                        media8.pause();
                        start8=false;
                        laughmedia.start();
                    }else if (start9){
                        media9.pause();
                        start9=false;
                        laughmedia.start();
                    }else if (start0){
                        media0.pause();
                        start0=false;
                        laughmedia.start();
                    }else if (startstar){
                        mediastar.pause();
                        startstar=false;
                        laughmedia.start();
                    }else if (startch){
                        mediach.pause();
                        startch=false;
                        laughmedia.start();
                    }
                    else
                        laughmedia.start();
                start1=true;

                break;
            case R.id.btn2:
                if (start1){

                    laughmedia.pause();
                    start1=false;
                    birdmedia.start();
                }else if (start3){
                    media3.pause();
                    start3=false;
                    birdmedia.start();
                }else if (start4){
                    media4.pause();
                    start4=false;
                    birdmedia.start();
                }else if (start5){
                    media5.pause();
                    start5=false;
                    birdmedia.start();
                }else if (start6){
                    media6.pause();
                    start6=false;
                    birdmedia.start();
                }else if (start7){
                    media7.pause();
                    start7=false;
                    birdmedia.start();
                }else if (start8){
                    media8.pause();
                    start8=false;
                    birdmedia.start();
                }else if (start9){
                    media9.pause();
                    start9=false;
                    birdmedia.start();
                }else if (start0){
                    media0.pause();
                    start0=false;
                    birdmedia.start();
                }else if (startstar){
                    mediastar.pause();
                    startstar=false;
                    birdmedia.start();
                }else if (startch){
                    mediach.pause();
                    startch=false;
                    birdmedia.start();
                }
                else
                    birdmedia.start();
                start2=true;

                break;
            case R.id.btn3:
                if (start1){

                    laughmedia.pause();
                    start1=false;
                    media3.start();
                }else if (start2){
                    birdmedia.pause();
                    start2=false;
                    media3.start();
                }else if (start4){
                    media4.pause();
                    start4=false;
                    media3.start();
                }else if (start5){
                    media5.pause();
                    start5=false;
                    media3.start();
                }else if (start6){
                    media6.pause();
                    start6=false;
                    media3.start();
                }else if (start7){
                    media7.pause();
                    start7=false;
                    media3.start();
                }else if (start8){
                    media8.pause();
                    start8=false;
                    media3.start();
                }else if (start9){
                    media9.pause();
                    start9=false;
                    media3.start();
                }else if (start0){
                    media0.pause();
                    start0=false;
                    media3.start();
                }else if (startstar){
                    mediastar.pause();
                    startstar=false;
                    media3.start();
                }else if (startch){
                    mediach.pause();
                    startch=false;
                    media3.start();
                }
                else
                    media3.start();
                start3=true;

                break;
            case R.id.btn4:
                if (start1){

                    laughmedia.pause();
                    start1=false;
                    media4.start();
                }else if (start3){
                    media3.pause();
                    start3=false;
                    media4.start();
                }else if (start2){
                    birdmedia.pause();
                    start2=false;
                    media4.start();
                }else if (start5){
                    media5.pause();
                    start5=false;
                    media4.start();
                }else if (start6){
                    media6.pause();
                    start6=false;
                    media4.start();
                }else if (start7){
                    media7.pause();
                    start7=false;
                    media4.start();
                }else if (start8){
                    media8.pause();
                    start8=false;
                    media4.start();
                }else if (start9){
                    media9.pause();
                    start9=false;
                    media4.start();
                }else if (start0){
                    media0.pause();
                    start0=false;
                    media4.start();
                }else if (startstar){
                    mediastar.pause();
                    startstar=false;
                    media4.start();
                }else if (startch){
                    mediach.pause();
                    startch=false;
                    media4.start();
                }
                else
                    media4.start();
                start4=true;

                break;
            case R.id.btn5:
                if (start1){

                    laughmedia.pause();
                    start1=false;
                    media5.start();
                }else if (start3){
                    media3.pause();
                    start3=false;
                    media5.start();
                }else if (start2){
                    birdmedia.pause();
                    start2=false;
                    media5.start();
                }else if (start4){
                    media4.pause();
                    start4=false;
                    media5.start();
                }else if (start6){
                    media6.pause();
                    start6=false;
                    media5.start();
                }else if (start7){
                    media7.pause();
                    start7=false;
                    media5.start();
                }else if (start8){
                    media8.pause();
                    start8=false;
                    media5.start();
                }else if (start9){
                    media9.pause();
                    start9=false;
                    media5.start();
                }else if (start0){
                    media0.pause();
                    start0=false;
                    media5.start();
                }else if (startstar){
                    mediastar.pause();
                    startstar=false;
                    media5.start();
                }else if (startch){
                    mediach.pause();
                    startch=false;
                    media5.start();
                }
                else
                    media5.start();
                start5=true;

                break;
            case R.id.btn6:
                if (start1){

                    laughmedia.pause();
                    start1=false;
                    media6.start();
                }else if (start3){
                    media3.pause();
                    start3=false;
                    media6.start();
                }else if (start2){
                    birdmedia.pause();
                    start2=false;
                    media6.start();
                }else if (start4){
                    media4.pause();
                    start4=false;
                    media6.start();
                }else if (start5){
                    media5.pause();
                    start5=false;
                    media6.start();
                }else if (start7){
                    media7.pause();
                    start7=false;
                    media6.start();
                }else if (start8){
                    media8.pause();
                    start8=false;
                    media6.start();
                }else if (start9){
                    media9.pause();
                    start9=false;
                    media6.start();
                }else if (start0){
                    media0.pause();
                    start0=false;
                    media6.start();
                }else if (startstar){
                    mediastar.pause();
                    startstar=false;
                    media6.start();
                }else if (startch){
                    mediach.pause();
                    startch=false;
                    media6.start();
                }
                else
                    media6.start();
                start6=true;

                break;
            case R.id.btn7:
                if (start1){

                    laughmedia.pause();
                    start1=false;
                    media7.start();
                }else if (start3){
                    media3.pause();
                    start3=false;
                    media7.start();
                }else if (start2){
                    birdmedia.pause();
                    start2=false;
                    media7.start();
                }else if (start4){
                    media4.pause();
                    start4=false;
                    media7.start();
                }else if (start5){
                    media5.pause();
                    start5=false;
                    media7.start();
                }else if (start6){
                    media6.pause();
                    start6=false;
                    media7.start();
                }else if (start8){
                    media8.pause();
                    start8=false;
                    media7.start();
                }else if (start9){
                    media9.pause();
                    start9=false;
                    media7.start();
                }else if (start0){
                    media0.pause();
                    start0=false;
                    media7.start();
                }else if (startstar){
                    mediastar.pause();
                    startstar=false;
                    media7.start();
                }else if (startch){
                    mediach.pause();
                    startch=false;
                    media7.start();
                }
                else
                    media7.start();
                start7=true;

                break;
            case R.id.btn8:
                if (start1){

                    laughmedia.pause();
                    start1=false;
                    media8.start();
                }else if (start3){
                    media3.pause();
                    start3=false;
                    media8.start();
                }else if (start2){
                    birdmedia.pause();
                    start2=false;
                    media8.start();
                }else if (start4){
                    media4.pause();
                    start4=false;
                    media8.start();
                }else if (start5){
                    media5.pause();
                    start5=false;
                    media8.start();
                }else if (start6){
                    media6.pause();
                    start6=false;
                    media8.start();
                }else if (start7){
                    media7.pause();
                    start7=false;
                    media8.start();
                }else if (start9){
                    media9.pause();
                    start9=false;
                    media8.start();
                }else if (start0){
                    media0.pause();
                    start0=false;
                    media8.start();
                }else if (startstar){
                    mediastar.pause();
                    startstar=false;
                    media8.start();
                }else if (startch){
                    mediach.pause();
                    startch=false;
                    media8.start();
                }
                else
                    media8.start();
                start8=true;

                break;
            case R.id.btn9:
                if (start1){

                    laughmedia.pause();
                    start1=false;
                    media9.start();
                }else if (start3){
                    media3.pause();
                    start3=false;
                    media9.start();
                }else if (start2){
                    birdmedia.pause();
                    start2=false;
                    media9.start();
                }else if (start4){
                    media4.pause();
                    start4=false;
                    media9.start();
                }else if (start5){
                    media5.pause();
                    start5=false;
                    media9.start();
                }else if (start6){
                    media6.pause();
                    start6=false;
                    media9.start();
                }else if (start7){
                    media7.pause();
                    start7=false;
                    media9.start();
                }else if (start8){
                    media8.pause();
                    start8=false;
                    media9.start();
                }else if (start0){
                    media0.pause();
                    start0=false;
                    media9.start();
                }else if (startstar){
                    mediastar.pause();
                    startstar=false;
                    media9.start();
                }else if (startch){
                    mediach.pause();
                    startch=false;
                    media9.start();
                }
                else
                    media9.start();
                start9=true;

                break;
            case R.id.btn0:
                if (start1){

                    laughmedia.pause();
                    start1=false;
                    media0.start();
                }else if (start3){
                    media3.pause();
                    start3=false;
                    media0.start();
                }else if (start2){
                    birdmedia.pause();
                    start2=false;
                    media0.start();
                }else if (start4){
                    media4.pause();
                    start4=false;
                    media0.start();
                }else if (start5){
                    media5.pause();
                    start5=false;
                    media0.start();
                }else if (start6){
                    media6.pause();
                    start6=false;
                    media0.start();
                }else if (start7){
                    media7.pause();
                    start7=false;
                    media0.start();
                }else if (start8){
                    media8.pause();
                    start8=false;
                    media0.start();
                }else if (start9){
                    media9.pause();
                    start9=false;
                    media0.start();
                }else if (startstar){
                    mediastar.pause();
                    startstar=false;
                    media0.start();
                }else if (startch){
                    mediach.pause();
                    startch=false;
                    media0.start();
                }
                else
                    media0.start();
                start0=true;

                break;
            case R.id.btnstar:
                if (start1){

                    laughmedia.pause();
                    start1=false;
                    mediastar.start();
                }else if (start3){
                    media3.pause();
                    start3=false;
                    mediastar.start();
                }else if (start2){
                    birdmedia.pause();
                    start2=false;
                    mediastar.start();
                }else if (start4){
                    media4.pause();
                    start4=false;
                    mediastar.start();
                }else if (start5){
                    media5.pause();
                    start5=false;
                    mediastar.start();
                }else if (start6){
                    media6.pause();
                    start6=false;
                    mediastar.start();
                }else if (start7){
                    media7.pause();
                    start7=false;
                    mediastar.start();
                }else if (start8){
                    media8.pause();
                    start8=false;
                    mediastar.start();
                }else if (start9){
                    media9.pause();
                    start9=false;
                    mediastar.start();
                }else if (start0){
                    media0.pause();
                    start0=false;
                    mediastar.start();
                }else if (startch){
                    mediach.pause();
                    startch=false;
                    mediastar.start();
                }
                else
                    mediastar.start();
                startstar=true;

                break;
            case R.id.btnch:
                if (start1){

                    laughmedia.pause();
                    start1=false;
                    mediach.start();
                }else if (start3){
                    media3.pause();
                    start3=false;
                    mediach.start();
                }else if (start2){
                    birdmedia.pause();
                    start2=false;
                    mediach.start();
                }else if (start4){
                    media4.pause();
                    start4=false;
                    mediach.start();
                }else if (start5){
                    media5.pause();
                    start5=false;
                    mediach.start();
                }else if (start6){
                    media6.pause();
                    start6=false;
                    mediach.start();
                }else if (start7){
                    media7.pause();
                    start7=false;
                    mediach.start();
                }else if (start8){
                    media8.pause();
                    start8=false;
                    mediach.start();
                }else if (start9){
                    media9.pause();
                    start9=false;
                    mediach.start();
                }else if (start0){
                    media0.pause();
                    start0=false;
                    mediach.start();
                }else if (startstar){
                    mediastar.pause();
                    startstar=false;
                    mediach.start();
                }
                else
                    mediach.start();
                startch=true;

                break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (start0){
            media0.pause();
        }else if (start1){
            laughmedia.pause();
        }else if (start2){
            birdmedia.pause();
        }else if (start3){
            media3.pause();
        }else if (start4){
            media4.pause();
        }else if (start5){
            media5.pause();
        }else if (start6){
            media6.pause();
        }else if (start7){
            media7.pause();
        }else if (start8){
            media8.pause();
        }else if (start9){
            media9.pause();
        }else if (startch){
            mediach.pause();
        }else if (startstar){
            mediastar.pause();
        }
        startActivity(new Intent(Phone_Voice.this,Happy_Baby.class));
        finish();

    }
}

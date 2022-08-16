package com.example.dell.babycare.Activities.developmentActivities.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.babycare.Activities.developmentActivities.model.Answer_Model;
import com.example.dell.babycare.Activities.developmentActivities.model.MDQuestion_Model;
import com.example.dell.babycare.R;
import com.firebase.client.Firebase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddQuestion extends AppCompatActivity implements View.OnClickListener {
    EditText question;
    Button questionButton, question_abscancel;
    Firebase newFirebase;
    static String stime, sday;
    String questionUser, questionprofile, questionKey, accountKey;
    String currentTime, currentDate;
    SharedPreferences share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.tool2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ဆရာဝန္အားေမးျမန္းရန္");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        question = (EditText) findViewById(R.id.questiontext);
        questionButton = (Button) findViewById(R.id.questionok);
        question_abscancel = (Button) findViewById(R.id.question_abscancel);
        questionButton.setOnClickListener(this);
        question_abscancel.setOnClickListener(this);
        Firebase.setAndroidContext(AddQuestion.this);
        newFirebase = new Firebase("https://myschoolserver-dcae9.firebaseio.com/");
        share = getSharedPreferences("AccountData", MODE_PRIVATE);
        questionUser = share.getString("name", null);
        accountKey = share.getString("key", null);
        questionprofile = share.getString("profile", null);
        currentDate = Date();
        currentTime = Time();
        questionKey = accountKey + currentDate + currentTime;
    }

    public static String Time() {
        DateFormat time = new SimpleDateFormat(" HH:mm:ss");
        time.setLenient(false);
        Date todaytime = new Date();
        stime = time.format(todaytime);
        return stime;
    }

    public static String Date() {
        DateFormat date = new SimpleDateFormat("yyyy:MM:dd ");
        date.setLenient(false);
        Date todaydate = new Date();
        sday = date.format(todaydate);
        return sday;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.question_abscancel:
                finish();
                break;
            case R.id.questionok:
                if (Login_Activity.haveNetworkConnection(AddQuestion.this)) {
                    String questionS = question.getText().toString();
                    if (questionS.equals("")) {
                        Toast.makeText(this, "ျဖည့္ရန္က်န္ပါေသးသည္", Toast.LENGTH_SHORT).show();
                    } else {
                        MDQuestion_Model qModel = new MDQuestion_Model();
                        qModel.setQuestion(questionS);
                        qModel.setDate(Date());
                        qModel.setTime(Time());
                        qModel.setUsername(questionUser);
                        qModel.setProfile(questionprofile);
                        qModel.setKey(accountKey);
                        qModel.setAnswerKey(questionKey);
                        newFirebase.child("ParentQuestion").child(questionKey).setValue(qModel);
                        Answer_Model aModel = new Answer_Model();
                        aModel.setQuestion(questionS);
                        aModel.setaDate("");
                        aModel.setaTime("");
                        aModel.setAnswer("");
                        aModel.setKey(accountKey);
                        aModel.setQdate(Date());
                        aModel.setQtime(Time());
                        aModel.setAnswerkey(questionKey);
                        newFirebase.child("DoctorQuestion").child(questionKey).setValue(aModel);
                        question.setText("");
                    }
                } else {
                    Snackbar.make(view, "အင္တာနက္ဖြင့္ရန္လိုအပ္ပါသည္", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
                break;

        }
    }
}

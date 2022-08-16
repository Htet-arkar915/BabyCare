package com.example.dell.babycare.Activities.developmentActivities.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dell.babycare.Activities.developmentActivities.model.Answer_Model;
import com.example.dell.babycare.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Answer_ParentActivity extends AppCompatActivity implements View.OnClickListener {
    TextView replyText;
    Button replyButton;
    ArrayList<String> questionArray=new ArrayList<>();
    ArrayList<String> profile=new ArrayList<>();
    ArrayList<String> name=new ArrayList<>();
    ArrayList<String> date=new ArrayList<>();
    ArrayList<String> time=new ArrayList<>();
    ArrayList<String> accountKey=new ArrayList<>();
    int positionn=0;
   // CircleImageView repiyProfile;
   // TextView repiyname;
    TextView repiyQuestion;
    ArrayList<String> answerKey;
    static String stime,sday;
    Firebase firebase;
    String realAnswer="";
    TextView answerText;
    RelativeLayout answerCard;
    TextView dateTextVieww;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer__parent);
        Toolbar toolbar;
        toolbar= (Toolbar) findViewById(R.id.tool6);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("အေျဖၾကည့္ရန္");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
      //  repiyProfile= (CircleImageView) findViewById(R.id.answerprofileimage);
      //  repiyname= (TextView) findViewById(R.id.answerusername);
        repiyQuestion= (TextView) findViewById(R.id.answerquestion_text);

        answerText= (TextView) findViewById(R.id.answertextview);
        answerCard= (RelativeLayout) findViewById(R.id.visiblelayout);
        questionArray=getIntent().getStringArrayListExtra("question");
        profile=getIntent().getStringArrayListExtra("profile");
        name=getIntent().getStringArrayListExtra("name");
        answerKey=getIntent().getStringArrayListExtra("answerkey");
        positionn=getIntent().getIntExtra("position",0);
      //  repiyname.setText(name.get(positionn));
        repiyQuestion.setText(questionArray.get(positionn));

        Firebase.setAndroidContext(Answer_ParentActivity.this);
        firebase = new Firebase("https://myschoolserver-dcae9.firebaseio.com/");

        firebase.child("DoctorQuestion").child(answerKey.get(positionn)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               realAnswer=dataSnapshot.getValue(Answer_Model.class).getAnswer();
                if (realAnswer.equals(""))
                {
                    answerCard.setVisibility(View.INVISIBLE);
                }
                else
                {
                    answerCard.setVisibility(View.VISIBLE);
                    answerText.setText(realAnswer);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        //replyText= (EditText) findViewById(R.id.reply_text);
       // replyButton= (Button) findViewById(repiyButton);
      //  positionn=getIntent().getIntExtra("position",0);
       // answerKey=getIntent().getStringArrayListExtra("answerkey");
       // date=getIntent().getStringArrayListExtra("date");
      //  time=getIntent().getStringArrayListExtra("time");
     //   accountKey=getIntent().getStringArrayListExtra("key");
      //  replyButton.setOnClickListener(this);
    }
    public static String Time(){
        DateFormat time = new SimpleDateFormat(" HH:mm:ss");
        time.setLenient(false);
        Date todaytime = new Date();
        stime = time.format(todaytime);
        return stime;
    }
    public static String Date(){
        DateFormat date = new SimpleDateFormat("yyyy:MM:dd ");
        date.setLenient(false);
        Date todaydate = new Date();
        sday = date.format(todaydate);
        return sday;
    }

    @Override
    public void onClick(View view) {
      /*  String replystring=replyText.getText().toString();
        Answer_Model aaModel=new Answer_Model();
        aaModel.setQuestion(repiyQuestion.getText().toString());
        aaModel.setaDate(Date());
        aaModel.setaTime(Time());
        aaModel.setAnswer(replystring);
        aaModel.setAnswerkey(answerKey.get(positionn));
        aaModel.setQdate(date.get(positionn));
        aaModel.setQtime(time.get(positionn));
        aaModel.setKey(accountKey.get(positionn));
        firebase.child("DoctorQuestion").child(answerKey.get(positionn)).setValue(aaModel);
*/



    }
}

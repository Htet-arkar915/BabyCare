package com.example.dell.babycare.Activities.developmentActivities.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.dell.babycare.Activities.developmentActivities.adapters.QuestionsAdapter;
import com.example.dell.babycare.Activities.developmentActivities.mainHomeActivity.AlbumsAdapter;
import com.example.dell.babycare.Activities.developmentActivities.model.MDQuestion_Model;
import com.example.dell.babycare.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class Doctor_Activity extends AppCompatActivity implements View.OnClickListener {
RecyclerView questionRecycler;
    Firebase newFirebase;
    ArrayList<MDQuestion_Model>  questiondata=new ArrayList<>();
    ArrayList<String> questionArray=new ArrayList<>();
    ArrayList<Integer> accountArray=new ArrayList<>();
    RecyclerView.Adapter adapter;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_);
        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.tool4);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ဆရာဝန္မွ ေျဖၾကားရန္");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        questionRecycler = (RecyclerView) findViewById(R.id.recycle_doctor_question);
        Firebase.setAndroidContext(Doctor_Activity.this);
        newFirebase = new Firebase("https://myschoolserver-dcae9.firebaseio.com/");
        newFirebase.child("ParentQuestion").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                questiondata.clear();
                for (DataSnapshot sh : dataSnapshot.getChildren()) {
                    MDQuestion_Model questionModel = new MDQuestion_Model();
                    questionModel = sh.getValue(MDQuestion_Model.class);
                    questiondata.add(questionModel);

                }


                questionRecycler.setLayoutManager(new LinearLayoutManager(Doctor_Activity.this));
                adapter = new QuestionsAdapter(Doctor_Activity.this, questiondata);
                questionRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        AlbumsAdapter.addDoctorLogin(Doctor_Activity.this, true);
    }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu, menu);
            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.doctor_logout:
                    View v1= LayoutInflater.from(Doctor_Activity.this).inflate(R.layout.check_login_dialog,null);
                    AlertDialog.Builder dialog=new AlertDialog.Builder(Doctor_Activity.this);
                    dialog.setView(v1);
                    alertDialog=dialog.create();
                    alertDialog.show();

                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            AlbumsAdapter.addDoctorLogin(Doctor_Activity.this,false);
                            startActivity(new Intent(Doctor_Activity.this,Login_Activity.class));
                            alertDialog.dismiss();
                            finish();
                        }
                    },4000);

                    break;

            }
            return super.onOptionsItemSelected(item);
        }



    @Override
    public void onClick(View view) {

    }
}

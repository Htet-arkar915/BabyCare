package com.example.dell.babycare.Activities.developmentActivities.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dell.babycare.Activities.developmentActivities.model.Accountcount_Model;
import com.example.dell.babycare.Activities.developmentActivities.model.Data_model;
import com.example.dell.babycare.Activities.developmentActivities.model.Login_and_Password_Model;
import com.example.dell.babycare.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Form extends AppCompatActivity implements View.OnClickListener {
Button btnok;
    EditText bith,time,city;
    String biths,times,citys;
    Firebase newFirebase;
    int numaccount;
    EditText name,password;
    static String names,passwords;
    static String stime,sday;
    String timess,days;
    ImageView profile;
    int RESULT_LOAD_IMG=0;
    ProgressDialog pd;
    Uri imageUri;
    String fromStorageUri="";
    StorageReference firebaseStorage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Toolbar toolbar;
        toolbar= (Toolbar) findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("အေကာင့္ဖြင့္ရန္");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnok= (Button) findViewById(R.id.btnok);
        bith= (EditText) findViewById(R.id.birthedit);
        time= (EditText) findViewById(R.id.timeedit);
        city= (EditText) findViewById(R.id.cityedit);
        name= (EditText) findViewById(R.id.shine_edit);
        password= (EditText) findViewById(R.id.passwordedit);
        profile= (ImageView) findViewById(R.id.profile);
        Firebase.setAndroidContext(Form.this);
        newFirebase=new Firebase("https://myschoolserver-dcae9.firebaseio.com/");
        firebaseStorage= FirebaseStorage.getInstance().getReference("photo");
        pd=new ProgressDialog(this);
        pd.setMessage("Please Wait!");
        newFirebase.child("logincount").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                numaccount= dataSnapshot.getValue(Accountcount_Model.class).getNumlogin();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        btnok.setOnClickListener(this);
        profile.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(pd!=null){
            pd.setCancelable(false);
            pd.show();
        }
        imageUri =data.getData();
        UploadTask uploadTask=firebaseStorage.child(imageUri.toString()).putFile(imageUri);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fromStorageUri=taskSnapshot.getDownloadUrl().toString();

               // Toast.makeText(Post_activity.this, "Data", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
            }
        });
        Glide.with(this).load(imageUri).into(profile);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {case R.id.profile:
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);

            break;
            case R.id.btnok:
                
                if (Login_Activity.haveNetworkConnection(Form.this)){
                    
                    timess=getTime();
                    days=getDate();
                    biths=bith.getText().toString();
                    times=time.getText().toString();
                    citys=city.getText().toString();
                    names=name.getText().toString();
                    passwords=password.getText().toString();
                    if(biths.equals("")&&times.equals("")&&citys.equals("")&&names.equals("")&&passwords.equals("")){
                        Toast.makeText(this, "ျဖည့္ရန္က်န္ပါေသးသည္။", Toast.LENGTH_LONG).show();
                    }
                    else {
                    Data_model data_model=new Data_model();
                    data_model.setName(names);
                    data_model.setPassword(passwords);
                    data_model.setBirth(biths);
                    data_model.setTime(times);
                    data_model.setCity(citys);
                    data_model.setKey(names+passwords+biths+days+timess);
                    data_model.setImage(fromStorageUri);
                    Login_and_Password_Model login=new Login_and_Password_Model();
                    login.setName(names);
                    login.setPassword(passwords);
                    login.setKey(names+passwords+biths+days+timess);
                    newFirebase.child("useraccounttable").child(names+passwords+biths+days+timess).setValue(data_model);
                    newFirebase.child("LoginTable").child(names+passwords+biths+days+timess).setValue(login);
                    newFirebase.child("logincount").child("numlogin").setValue(numaccount+1);
                    Intent goo=new Intent(Form.this,Login_Activity.class);
                    goo.putExtra("namee",names);
                    goo.putExtra("pass",passwords);
                    startActivity(goo);
                    finish();}
                }
                else
                {
                    Snackbar.make(view,"အင္တာနက္ဖြင့္ရန္လိုအပ္ပါသည္", Snackbar.LENGTH_SHORT).setAction("Action",null).show();
                   // Toast.makeText(this, "Check Network", Toast.LENGTH_SHORT).show();
                }


                break;
        }

    }

    public static String getTime(){
        DateFormat time = new SimpleDateFormat(" HH:mm:ss");
        time.setLenient(false);
        Date todaytime = new Date();
        stime = time.format(todaytime);
        return stime;
    }
    public static String getDate(){
        DateFormat date = new SimpleDateFormat("yyyy:MM:dd ");
        date.setLenient(false);
        Date todaydate = new Date();
        sday = date.format(todaydate);
        return sday;
    }

}

package com.example.dell.babycare.Activities.developmentActivities.activities;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dell.babycare.Activities.developmentActivities.DevelopmentModel;
import com.example.dell.babycare.Activities.developmentActivities.model.Comment_Count_Model;
import com.example.dell.babycare.Activities.developmentActivities.model.PostcountModel;
import com.example.dell.babycare.R;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Post_activity extends AppCompatActivity implements View.OnClickListener {


    Firebase firebase;
    PostcountModel postCount=new PostcountModel();
    StorageReference firebaseStorage;
    ImageView getImage;
    int RESULT_LOAD_IMG=0;
    ProgressDialog pd;
    Uri imageUri;
    String fromStorageUri="";
    Button ok,abscancel;
    TextView name;EditText fact;
    int numPostin;
    String nameString,factString;
    SharedPreferences sh;
    static String stime,sday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_development);
        Toolbar toolbar;
        toolbar= (Toolbar) findViewById(R.id.tool1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("မိခင္အခ်င္းခ်င္းေဆြးေႏြးရန္");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sh = getSharedPreferences("AccountData", MODE_PRIVATE);

        Firebase.setAndroidContext(Post_activity.this);
        getImage= (ImageView) findViewById(R.id.post_image);
        ok= (Button) findViewById(R.id.post_ok);
        abscancel= (Button) findViewById(R.id.abscancel);
        ok.setOnClickListener(this);
        abscancel.setOnClickListener(this);

        fact= (EditText) findViewById(R.id.post_fact);
        getImage.setOnClickListener(this);
        firebase=new Firebase("https://myschoolserver-dcae9.firebaseio.com/");
        firebaseStorage= FirebaseStorage.getInstance().getReference("photo");
        pd=new ProgressDialog(this);
        pd.setMessage("Please Wait!");



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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.post_image:
                getImageFromAlbum();
                break;
            case R.id.post_ok:
                //nameString=name.getText().toString();
                if (Login_Activity.haveNetworkConnection(Post_activity.this)){
                    factString=fact.getText().toString();
                    if (factString.equals("")){
                        Toast.makeText(this, "ျဖည့္ရန္က်န္ပါေသးသည္", Toast.LENGTH_SHORT).show();
                    }else {
                        DevelopmentModel dm = new DevelopmentModel();
                        dm.setImage(fromStorageUri);
                        dm.setName(sh.getString("name", null));
                        Toast.makeText(this, Date(), Toast.LENGTH_SHORT).show();
                        dm.setProfilepicture(sh.getString("profile", null));
                        dm.setKey(sh.getString("key", null));
                        dm.setCommentKey(sh.getString("name", null) + Date() + Time());
                        dm.setTime(Time());
                        dm.setDate(Date());
                        dm.setFact(factString);
                        Comment_Count_Model comment_count_model = new Comment_Count_Model();
                        comment_count_model.setCommentkey(dm.getCommentKey());
                        comment_count_model.setCommentcount(0);
                        firebase.child("Comment_Count_Table").child(dm.getCommentKey()).setValue(comment_count_model);
                        firebase.child("Discard_Mother").push().setValue(dm);
                        fact.setText("");
                        getImage.setImageResource(R.mipmap.qanda);
                    }
                }
                else
                {
                    Toast.makeText(this, getString(R.string.checkinternet), Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.abscancel:
                finish();
                break;
        }
    }




    private void getImageFromAlbum() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
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
                Toast.makeText(Post_activity.this, "Data", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
            }
        });
        Glide.with(this).load(imageUri).into(getImage);
    }
}

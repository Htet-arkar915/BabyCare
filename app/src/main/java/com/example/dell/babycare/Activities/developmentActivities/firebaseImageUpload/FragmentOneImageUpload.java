package com.example.dell.babycare.Activities.developmentActivities.firebaseImageUpload;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dell.babycare.Activities.developmentActivities.model.FragmentModel;
import com.example.dell.babycare.R;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class FragmentOneImageUpload extends AppCompatActivity implements View.OnClickListener {

    Firebase firebase;
    ProgressDialog pd;
    StorageReference firebaseStorage;
    ImageView imageView;
    int RESULT_LOAD_IMG=0;
    Uri imageUri;
    String fromStorageUri;
    Button button,btnGetImage;
    EditText development_edittext,development_position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_one_image_upload);
        Firebase.setAndroidContext(FragmentOneImageUpload.this);
        imageView= (ImageView) findViewById(R.id.fOne_image);
        button= (Button) findViewById(R.id.fOne_upload);
        development_position= (EditText) findViewById(R.id.development_position);
        btnGetImage= (Button) findViewById(R.id.getImage);
        development_edittext= (EditText) findViewById(R.id.development_edittext);
        btnGetImage.setOnClickListener(this);
        button.setOnClickListener(this);
        firebase=new Firebase("https://myschoolserver-dcae9.firebaseio.com/");
        firebaseStorage= FirebaseStorage.getInstance().getReference("ShopImage");
        pd=new ProgressDialog(this);
        pd.setMessage("Please Wait!");
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId()){
            case R.id.getImage:
                getImageFromAlbum();
                break;
            case R.id.fOne_upload:
                FragmentModel model=new FragmentModel();
                model.setDevelopmentImage(fromStorageUri);
                model.setTitle(development_edittext.getText().toString());
                firebase.child("DevelopmentImage").child("1to3").child(development_position.getText().toString()).setValue(model);
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
        if (pd!=null){
            pd.setCancelable(false);
            pd.show();
        }
        imageUri=data.getData();
        UploadTask uploadTask=firebaseStorage.child(imageUri.toString()).putFile(imageUri);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fromStorageUri=taskSnapshot.getDownloadUrl().toString();
                pd.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
            }
        });
        Glide.with(this).load(imageUri).into(imageView);
    }
}

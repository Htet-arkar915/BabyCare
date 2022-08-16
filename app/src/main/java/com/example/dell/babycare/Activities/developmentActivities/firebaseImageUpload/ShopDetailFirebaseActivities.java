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
import com.example.dell.babycare.Activities.developmentActivities.model.ShopDetailModel;
import com.example.dell.babycare.R;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ShopDetailFirebaseActivities extends AppCompatActivity implements View.OnClickListener {

    ///https://myschoolserver-dcae9.firebaseio.com/
    Firebase firebase;
    StorageReference firebaseStorage;
    Button getImage;
    int RESULT_LOAD_IMG=0;
    ProgressDialog pd;
    Uri imageUri;
    String fromStorageUri="";
    Button ok;
    EditText name,address,ph_no;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail_firebase_activities);
        Firebase.setAndroidContext(ShopDetailFirebaseActivities.this);
        getImage= (Button) findViewById(R.id.getImage);
        ok= (Button) findViewById(R.id.ok);
        name= (EditText) findViewById(R.id.fshop_detail_name);
        address= (EditText) findViewById(R.id.fshop_detail_price);
        ok.setOnClickListener(this);
        imageView= (ImageView) findViewById(R.id.image);
        getImage.setOnClickListener(this);
        firebase=new Firebase("https://myschoolserver-dcae9.firebaseio.com/");
        firebaseStorage= FirebaseStorage.getInstance().getReference("ShopDetails");
        pd=new ProgressDialog(this);
        pd.setMessage("Please Wait!");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.getImage:
                getImageFromAlbum();
                break;
            case R.id.ok:
                ShopDetailModel dm=new ShopDetailModel();
                dm.setImage(fromStorageUri);
                dm.setName(name.getText().toString());
                dm.setPrice(address.getText().toString());
                firebase.child("ShopDetails3").push().setValue(dm);
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
                Toast.makeText(ShopDetailFirebaseActivities.this, "Data", Toast.LENGTH_SHORT).show();
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

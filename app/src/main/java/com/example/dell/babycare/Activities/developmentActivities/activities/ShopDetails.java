package com.example.dell.babycare.Activities.developmentActivities.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dell.babycare.Activities.developmentActivities.adapters.ShopDetailAdapter;
import com.example.dell.babycare.Activities.developmentActivities.model.ShopDetailModel;
import com.example.dell.babycare.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import static com.example.dell.babycare.Activities.developmentActivities.adapters.ShopAdapter.shop_position;

public class ShopDetails extends AppCompatActivity {

    ImageView shop_detail_image;
    TextView shop_detail_name,shop_detail_price;
    Firebase firebase;
    RecyclerView recycler;
    int postion=0;
    ArrayList<ShopDetailModel> ShopData;
    ArrayList<ShopDetailModel> ShopData1;
    ArrayList<ShopDetailModel> ShopData2;
    ArrayList<ShopDetailModel> ShopData3;
    //ArrayList<ShopDetailModel> arrayList;
    RecyclerView.Adapter adapter;
    Toolbar toolbar;
    ProgressBar progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_recycler);
        Firebase.setAndroidContext(ShopDetails.this);
        ShopData=new ArrayList<ShopDetailModel>();
        ShopData1=new ArrayList<>();
        ShopData2=new ArrayList<>();
        ShopData3=new ArrayList<>();
        //arrayList=new ArrayList<>();
        toolbar= (Toolbar) findViewById(R.id.shop_detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ေစ်းဆုိင္မ်ား");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        progressbar= (ProgressBar) findViewById(R.id.shop_detail_progressbar);
        recycler= (RecyclerView) findViewById(R.id.shop_detail_recycler);
        recycler.setLayoutManager(new GridLayoutManager(this,2));
        firebase=new Firebase("https://myschoolserver-dcae9.firebaseio.com/");
        /*firebase.child("ShopDetails").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                changeData(dataSnapshot);
            }



            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });*/
        postion=shop_position;
        if( postion == 0 ){
            firebase.child("ShopDetails").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ShopData.clear();
                    changeData(dataSnapshot);
                }



                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }
        else if(postion == 1){
            firebase.child("ShopDetails1").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ShopData1.clear();
                    changeData1(dataSnapshot);
                }



                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }
        else if(postion == 2){
            firebase.child("ShopDetails2").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ShopData2.clear();
                    changeData2(dataSnapshot);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }
        else if(postion == 3){
            firebase.child("ShopDetails3").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ShopData3.clear();
                    changeData3(dataSnapshot);
                }



                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }
        //arrayList= (ArrayList<ShopDetailModel>) getIntent().getSerializableExtra("Data1");
        shop_detail_image= (ImageView) findViewById(R.id.shop_detail_image);
        shop_detail_name= (TextView) findViewById(R.id.shop_detail_name);
        shop_detail_price= (TextView) findViewById(R.id.shop_detail_price);

    }

    private void changeData3(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh:dataSnapshot.getChildren()){
            ShopDetailModel model=sh.getValue(ShopDetailModel.class);
            ShopData3.add(model);
        }
        adapter=new ShopDetailAdapter(ShopDetails.this,ShopData3);
        progressbar.setVisibility(View.INVISIBLE);
        recycler.setAdapter(adapter);
    }

    private void changeData2(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh:dataSnapshot.getChildren()){
            ShopDetailModel model=sh.getValue(ShopDetailModel.class);
            ShopData2.add(model);
        }
        adapter=new ShopDetailAdapter(ShopDetails.this,ShopData2);
        progressbar.setVisibility(View.INVISIBLE);
        recycler.setAdapter(adapter);
    }

    private void changeData1(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh:dataSnapshot.getChildren()){
            ShopDetailModel model=sh.getValue(ShopDetailModel.class);
            ShopData1.add(model);
        }
        adapter=new ShopDetailAdapter(ShopDetails.this,ShopData1);
        progressbar.setVisibility(View.INVISIBLE);
        recycler.setAdapter(adapter);
    }

    private void changeData(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh:dataSnapshot.getChildren()){
            ShopDetailModel model=sh.getValue(ShopDetailModel.class);
            ShopData.add(model);


        }
        adapter=new ShopDetailAdapter(ShopDetails.this,ShopData);
        progressbar.setVisibility(View.INVISIBLE);
        recycler.setAdapter(adapter);
    }
}

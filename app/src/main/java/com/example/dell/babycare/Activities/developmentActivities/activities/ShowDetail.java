package com.example.dell.babycare.Activities.developmentActivities.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.dell.babycare.Activities.developmentActivities.model.FragmentModel;
import com.example.dell.babycare.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import static com.example.dell.babycare.Activities.developmentActivities.adapters.FirstRVAdatpter.DevelopmentImageDetailData;
import static com.example.dell.babycare.Activities.developmentActivities.adapters.FirstRVAdatpter.development_position;

public class ShowDetail extends AppCompatActivity  {

    Toolbar toolbar;
    Firebase firebase;
    int position =0;
    ArrayList<FragmentModel> f1 =new ArrayList<>();
    ArrayList<FragmentModel> f2 =new ArrayList<>();
    ArrayList<FragmentModel> f3 =new ArrayList<>();
    ArrayList<FragmentModel> f4 =new ArrayList<>();
    ArrayList<FragmentModel> f5 =new ArrayList<>();
    ArrayList<FragmentModel> f6 =new ArrayList<>();
    ArrayList<FragmentModel> f7 =new ArrayList<>();
    RecyclerView recycler;
    RecyclerView.Adapter adapter;
    ProgressBar progress;
    String detail;
    ArrayList<FragmentModel> DevelopmentImageData=new ArrayList<>();
    String key;
    ImageView detail_image;
    TextView textView;
    ProgressBar shop_loading;
    ImageView shop_refresh;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        toolbar = (Toolbar) findViewById(R.id.show_detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ဖြံ့ျဖဳိးမွုမ်ား");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        detail_image = (ImageView) findViewById(R.id.detailimage);
        textView = (TextView) findViewById(R.id.textdetail);
        shop_loading= (ProgressBar) findViewById(R.id.detail_loading);
        shop_refresh= (ImageView) findViewById(R.id.detail_refresh);
        //firebase.setAndroidContext(ShowDetail.this);
        //shop_loading.setOnClickListener(this);
        //firebase=new Firebase("https://myschoolserver-dcae9.firebaseio.com/");

        Bundle b = getIntent().getExtras();

        detail=b.getString("detail");
        position=b.getInt("pos");
        key=b.getString("key");
        if(HomeActivity.haveNetworkConnection(ShowDetail.this)) {
            Firebase.setAndroidContext(ShowDetail.this);
            firebase = new Firebase("https://myschoolserver-dcae9.firebaseio.com/");
            firebase.child("DevelopmentImage").child(key).child(position+"").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    f1.add(dataSnapshot.getValue(FragmentModel.class));
                    Glide.with(ShowDetail.this).load(f1.get(position).getDevelopmentImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                            .listener(new RequestListener<String, GlideDrawable>() {
                                @Override
                                public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                   detail_image.setVisibility(View.INVISIBLE);
                                   shop_loading.setVisibility(View.GONE);
                                   shop_refresh.setVisibility(View.VISIBLE);
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                   detail_image.setVisibility(View.VISIBLE);
                                   shop_loading.setVisibility(View.GONE);
                                   shop_refresh.setVisibility(View.GONE);
                                    return false;
                                }
                            }).into(detail_image);
                   shop_refresh.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Glide.with(ShowDetail.this).load(f1.get(position).getDevelopmentImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .listener(new RequestListener<String, GlideDrawable>() {
                                        @Override
                                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                           detail_image.setVisibility(View.INVISIBLE);
                                           shop_loading.setVisibility(View.GONE);
                                           shop_refresh.setVisibility(View.VISIBLE);
                                            return false;
                                        }

                                        @Override
                                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                           detail_image.setVisibility(View.VISIBLE);
                                           shop_loading.setVisibility(View.GONE);
                                           shop_refresh.setVisibility(View.GONE);
                                            return false;
                                        }
                                    }).into(detail_image);
                        }
                    });
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
            textView.setText(detail);

        }
        else {
           textView.setText(detail);
           detail_image.setImageResource(R.drawable.baby);
           shop_loading.setVisibility(View.INVISIBLE);
        }
        //if(position == 0){
       /* firebase.child("DevelopmentImage").child(key).child(position+"").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    f1.clear();
                    DevelopmentImageData.add(dataSnapshot.getValue(FragmentModel.class).getDevelopmentImage());
                    //upDateData(dataSnapshot);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });*/
        //}



        //imageView.setImageResource(R.drawable.baby);

        //textView.setText(detail);
       /* recycler= (RecyclerView) findViewById(R.id.recycler_show_detail);
        recycler.setLayoutManager(new LinearLayoutManager(ShowDetail.this));
        progress= (ProgressBar) findViewById(R.id.show_detail_progress);
        adapter=new ShowDetailAdapter(ShowDetail.this,detail,key);
        progress.setVisibility(View.INVISIBLE);
        recycler.setAdapter(adapter);*/

    }





}

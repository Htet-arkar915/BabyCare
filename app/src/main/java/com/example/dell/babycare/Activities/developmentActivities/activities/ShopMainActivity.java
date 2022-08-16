package com.example.dell.babycare.Activities.developmentActivities.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.example.dell.babycare.Activities.developmentActivities.adapters.ShopAdapter;
import com.example.dell.babycare.Activities.developmentActivities.model.DevelopmentModel;
import com.example.dell.babycare.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ShopMainActivity extends AppCompatActivity {

    RecyclerView shop_recycler;
    RecyclerView.Adapter shop_adapter;
    Toolbar toolbar;
    Firebase firebase;
    StorageReference firebase_shop_image;
    ArrayList<DevelopmentModel> shop_iamge;
    //int[] shop_image={R.drawable.sky, R.drawable.sky, R.drawable.sky, R.drawable.sky, R.drawable.sky, R.drawable.sky, R.drawable.sky, R.drawable.sky, R.drawable.sky, R.drawable.sky};
    String[] shop_name= {"ေရႊမာလာ","၀င္းအိ","ေနရဲမာန္","အိမ္႕ ","Beauty","ေကာင္းထက္သာ","သံုးရာသီ","ေရႊအိမ္မက္","လ၀န္းအိမ္","ေအာင္ကမၻာ"};
    String[] shop_address={"ရန္ကုန္တိုင္းေဒသႀကီး၊ လိွဳင္ၿမိဳ႕နယ္၊ ၈၆လမ္းနွင္႔ ၈၇ လမ္းၾကား","မႏၱေလးတိုင္းေဒသႀကီး၊ ခ်မ္းေအးသာဇံၿမိဳ႕နယ္ ၊ ၁၂ လမ္းနွင္႕ ၁၃ လမ္းၾကား","ရန္ကုန္တိုင္းေဒသႀကီး၊ ျပည္သာယာၿမိဳ႔နယ္၊ ၂၁ လမ္းနွင္ ႕ ၂၂ လမ္းၾကား",
    " မႏၱေလးတိုင္းေဒသႀကီး၊ မိတၳီလာၿမိဳ႕နယ္၊ ၇၈လမ္း","မႏၱေလးတိုင္းေဒသႀကီး၊ တပ္ကုန္းၿမိဳ႕  "," မႏၱေလးတိုင္းေဒသႀကီး၊ ေက်ာက္ဆည္ၿမိဳ႕နယ္၊ ၇၈လမ္း","မႏၱေလးတိုင္းေဒသႀကီး၊ ရမည္းသင္းၿမိဳ႔နယ္","မႏၱေလးတိုင္းေဒသႀကီး၊ ျပင္ဦးလြင္ၿမိဳ႕နယ္ ",
    " ရွမ္းျပည္နယ္၊ ေတာင္ႀကီးၿမိဳ႕၊ ၂၁ လမ္းနွင္႔ ၂၂လမ္းၾကား ","ေနျပည္ေတာ္တိုင္းေဒသႀကီး၊ ၀ဏၰသိဒိၶရပ္ကြက္ ၊ ၁၃ လမ္း"};
    String[] shop_phno={"09 254 831 731","09 787816733","09799877456","09776543890","09798745654","09734567890","099877896","099265478",
    "09933547094","09252265284"};
    ProgressBar progressbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_main);
        Firebase.setAndroidContext(ShopMainActivity.this);
        progressbar= (ProgressBar) findViewById(R.id.shop_progressbar);
        shop_iamge=new ArrayList<>();
        firebase=new Firebase("https://myschoolserver-dcae9.firebaseio.com/");
        firebase.child("ShopImage").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              updateData(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        toolbar= (Toolbar) findViewById(R.id.shop_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ေစ်းဆုိင္မ်ား");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        shop_recycler= (RecyclerView) findViewById(R.id.shop_recycler);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        shop_recycler.setLayoutManager(layoutManager);
        shop_recycler.setItemAnimator(new DefaultItemAnimator());
    }


    private void updateData(DataSnapshot dataSnapshot) {
        for(DataSnapshot sh:dataSnapshot.getChildren()){
            DevelopmentModel dmodel=sh.getValue(DevelopmentModel.class);
            shop_iamge.add(dmodel);
            shop_adapter=new ShopAdapter(ShopMainActivity.this,shop_iamge);
            progressbar.setVisibility(View.INVISIBLE);
            shop_recycler.setAdapter(shop_adapter);
        }




    }



}

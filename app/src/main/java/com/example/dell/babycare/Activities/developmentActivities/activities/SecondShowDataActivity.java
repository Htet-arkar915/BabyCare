package com.example.dell.babycare.Activities.developmentActivities.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.dell.babycare.Activities.developmentActivities.adapters.FirstRVAdatpter;
import com.example.dell.babycare.Activities.developmentActivities.model.BegintoThree;
import com.example.dell.babycare.R;

import java.util.ArrayList;
import java.util.List;

import san.db.handler.SanDBHandler;

public class SecondShowDataActivity extends AppCompatActivity {

    RecyclerView recsecond;
    String childKey="Dieases";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_show_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("ကေလးေရာဂါမ်ား");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        recsecond = (RecyclerView) findViewById(R.id.recRecycler);
        recsecond.setHasFixedSize(true);
        recsecond.setLayoutManager(new LinearLayoutManager(SecondShowDataActivity.this));
        SanDBHandler sanDBHandler = new SanDBHandler(SecondShowDataActivity.this);
        sanDBHandler.createDatabaseFromAsset(SecondShowDataActivity.this, "Baby.db", BegintoThree.class);
        List<BegintoThree> blist = BegintoThree.getAllData(BegintoThree.class, "DiseasesinChild");
        //Log.d("size","blist size"+blist.size());

        final List<String> namelist = new ArrayList<>();
        List<String> detaillist = new ArrayList<>();
        for (int i = 0; i < blist.size(); i++) {
            namelist.add(blist.get(i).getBNAME());
            detaillist.add(blist.get(i).getBDETAIL());
        }

        FirstRVAdatpter adatpter = new FirstRVAdatpter(SecondShowDataActivity.this,detaillist,namelist,childKey);
        recsecond.setAdapter(adatpter);
       /* adatpter.setOnitemClickListener(new FirstRVAdatpter.OnItemClick() {
            @Override
            public void onclick(String detail, int position) {
                Intent intent = new Intent(SecondShowDataActivity.this,ShowDetail.class);
                intent.putExtra("data",detail);
                startActivity(intent);
            }
        });
*/
    }

    }





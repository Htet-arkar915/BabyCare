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

public class ArticlesActivity extends AppCompatActivity {
    int image[] = {R.drawable.babyfour, R.drawable.threemonthbaby, R.drawable.tmonthbaby, R.drawable.towmonthbaby, R.drawable.twobaby, R.drawable.babyfour, R.drawable.threemonthbaby, R.drawable.tmonthbaby, R.drawable.towmonthbaby, R.drawable.twobaby, R.drawable.babyfour, R.drawable.threemonthbaby, R.drawable.tmonthbaby, R.drawable.towmonthbaby, R.drawable.twobaby, R.drawable.babyfour};

    String childKey="article";

    RecyclerView recArticle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_show_data);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("ဆုံးမနည္းမ်ား");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        recArticle = (RecyclerView) findViewById(R.id.recRecycler);
        recArticle.setHasFixedSize(true);
        recArticle.setLayoutManager(new LinearLayoutManager(ArticlesActivity.this));
        SanDBHandler sanDBHandler = new SanDBHandler(ArticlesActivity.this);
        sanDBHandler.createDatabaseFromAsset(ArticlesActivity.this,"Baby.db",BegintoThree.class);
        List<BegintoThree> blist = BegintoThree.getAllData(BegintoThree.class,"Articles");
        //Log.d("size","blist size"+blist.size());

        final List<String> namelist = new ArrayList<>();
        List<String> detaillist = new ArrayList<>();
        for (int i=0;i<blist.size();i++){
            namelist.add(blist.get(i).getBNAME());
            detaillist.add(blist.get(i).getBDETAIL());
        }


        recArticle = (RecyclerView) findViewById(R.id.recRecycler);
        recArticle.setHasFixedSize(true);
        recArticle.setLayoutManager(new LinearLayoutManager(ArticlesActivity.this));
       FirstRVAdatpter adatpter = new FirstRVAdatpter(ArticlesActivity.this,detaillist,namelist,childKey);
        recArticle.setAdapter(adatpter);


        /* adatpter.setOnitemClickListener(new FirstRVAdatpter.OnItemClick() {
            @Override
            public void onclick(String detail, int position) {
                Intent intent = new Intent(ArticlesActivity.this,ShowDetail.class);
                intent.putExtra("data",detail);
                startActivity(intent);
            }
        });*/
    }

}

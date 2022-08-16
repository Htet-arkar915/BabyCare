package com.example.dell.babycare.Activities.developmentActivities.frags;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.dell.babycare.Activities.developmentActivities.activities.HomeActivity;
import com.example.dell.babycare.Activities.developmentActivities.activities.ShowDetail;
import com.example.dell.babycare.Activities.developmentActivities.adapters.FirstRVAdatpter;
import com.example.dell.babycare.Activities.developmentActivities.adapters.RVAdapter;
import com.example.dell.babycare.Activities.developmentActivities.model.BegintoThree;
import com.example.dell.babycare.Activities.developmentActivities.model.DevelopmentModel;
import com.example.dell.babycare.Activities.developmentActivities.model.FragmentModel;
import com.example.dell.babycare.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

import san.db.handler.SanDBHandler;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment  {
    int image[] = {R.drawable.babyfour, R.drawable.threemonthbaby, R.drawable.tmonthbaby, R.drawable.towmonthbaby, R.drawable.twobaby, R.drawable.babyfour, R.drawable.threemonthbaby, R.drawable.tmonthbaby, R.drawable.towmonthbaby, R.drawable.twobaby, R.drawable.babyfour, R.drawable.threemonthbaby, R.drawable.tmonthbaby, R.drawable.towmonthbaby, R.drawable.twobaby, R.drawable.babyfour};
    RecyclerView recyclerView;
    View v;
    Firebase firebase;
    List<String> namelist;
    FirstRVAdatpter adatpter;
    List<String> detaillist;
    String childKey="1to3";
    //ProgressBar progressBar;



    public FragmentOne() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_fragment_one, container, false);
        //progressBar= (ProgressBar) v.findViewById(R.id.frag_one_progress);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SanDBHandler sanDBHandler = new SanDBHandler(getActivity());
        sanDBHandler.createDatabaseFromAsset(getActivity(), "Baby.db", BegintoThree.class);
        List<BegintoThree> blist = BegintoThree.getAllData(BegintoThree.class,"ThreetoSixMonth");
        List<BegintoThree> list = new ArrayList<>();
        //Log.d("size","blist size"+blist.size());

         namelist = new ArrayList<>();
         detaillist = new ArrayList<>();
        for (int i = 0; i < blist.size(); i++) {
            namelist.add(blist.get(i).getBNAME());
            detaillist.add(blist.get(i).getBDETAIL());

        }



        recyclerView = (RecyclerView) v.findViewById(R.id.recview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adatpter = new FirstRVAdatpter(getActivity(),detaillist,namelist,childKey);
        //progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setAdapter(adatpter);
      /*  adatpter.setOnitemClickListener(new FirstRVAdatpter.OnItemClick() {
            @Override
            public void onclick(String name,String detail, int position) {
                Intent intent = new Intent(getActivity(),ShowDetail.class);
                Bundle b = new Bundle();
                b.putString("name",name);
                b.putString("detail",detail);
                b.putString("key",childKey);
                b.putInt("pos",position);
                intent.putExtras(b);
                //Log.e("NameForName",name);

                startActivity(intent);

            }
        });*/



    }



}

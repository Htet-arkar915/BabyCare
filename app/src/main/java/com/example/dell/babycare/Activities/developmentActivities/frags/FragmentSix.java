package com.example.dell.babycare.Activities.developmentActivities.frags;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.babycare.Activities.developmentActivities.activities.ShowDetail;
import com.example.dell.babycare.Activities.developmentActivities.adapters.FirstRVAdatpter;
import com.example.dell.babycare.Activities.developmentActivities.model.ThreetoFiveYear;
import com.example.dell.babycare.R;

import java.util.ArrayList;
import java.util.List;

import san.db.handler.SanDBHandler;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSix extends Fragment {
    RecyclerView recyclerView;
    View v;
    String childKey="3to5";


    public FragmentSix() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_fragment_one, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SanDBHandler sanDBHandler = new SanDBHandler(getActivity());
        sanDBHandler.createDatabaseFromAsset(getActivity(),"Baby.db",ThreetoFiveYear.class);
        List<ThreetoFiveYear> blist = ThreetoFiveYear.getAllData(ThreetoFiveYear.class);
        //Log.d("size","blist size"+blist.size());

        List<String> namelist = new ArrayList<>();
        List<String> detaillist = new ArrayList<>();
        for (int i=0;i<blist.size();i++){
            namelist.add(blist.get(i).getTNAME());
            detaillist.add(blist.get(i).getTDETAIL());
        }
        recyclerView = (RecyclerView) v.findViewById(R.id.recview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

       FirstRVAdatpter adatpter;
        adatpter = new FirstRVAdatpter(getActivity(),namelist,detaillist,childKey);
        recyclerView.setAdapter(adatpter);
        adatpter.setOnitemClickListener(new FirstRVAdatpter.OnItemClick() {
            @Override
           public void onclick(String name,String detail, int position) {
               Intent intent = new Intent(getActivity(),ShowDetail.class);
               intent.putExtra("data",detail);
               intent.putExtra("name",name);

               startActivity(intent);

           }
        });
    }
}

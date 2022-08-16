package com.example.dell.babycare.Activities.developmentActivities.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.babycare.Activities.developmentActivities.activities.About_Mother;
import com.example.dell.babycare.Activities.developmentActivities.activities.Characteristics;
import com.example.dell.babycare.Activities.developmentActivities.activities.General;
import com.example.dell.babycare.Activities.developmentActivities.activities.Habit_Urine;
import com.example.dell.babycare.Activities.developmentActivities.activities.Physical_Bad;
import com.example.dell.babycare.R;

import java.util.ArrayList;

/**
 * Created by Aspire on 1/11/2018.
 */

public class Question_Overview_Adapter extends RecyclerView.Adapter<Question_Overview_Adapter.MyHolder> {
    Context context;
    ArrayList<String > titlelist=new ArrayList<>();
    ArrayList<String> jsonfilelist=new ArrayList<>();
    public Question_Overview_Adapter(Context context, ArrayList<String> title){
        this.context=context;
        this.titlelist=title;
    }


    @Override
    public Question_Overview_Adapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.question_overview_recycler,parent,false);
        MyHolder holder=new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(Question_Overview_Adapter.MyHolder holder, final int position) {

        holder.title.setText(titlelist.get(position));
        Log.d("titlelistpostion",titlelist.size()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){
                    case 0:
                       /**/
                        Intent go=new Intent(context,About_Mother.class);
                        context.startActivity(go);
                        break;
                    case 1:
                        context.startActivity(new Intent(context,Characteristics.class));
                       break;
                    case 2:
                        context.startActivity(new Intent(context,General.class));
                        break;
                    case 3:
                        context.startActivity(new Intent(context,Physical_Bad.class));
                        break;
                    case 4:
                        context.startActivity(new Intent(context,Habit_Urine.class));
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return titlelist.size();
    }
   /*
   */

    public class MyHolder extends RecyclerView.ViewHolder  {
        TextView title;
        public MyHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.question_overview_recycler_title);
        }

    }
}
/*
*/
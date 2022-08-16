package com.example.dell.babycare.Activities.developmentActivities.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.dell.babycare.Activities.developmentActivities.activities.Answer_ParentActivity;
import com.example.dell.babycare.Activities.developmentActivities.model.MDQuestion_Model;
import com.example.dell.babycare.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by hp on 11-Jan-18.
 */

public class QuestionAdapter extends RecyclerView.Adapter <QuestionAdapter.MyHolder> implements View.OnClickListener {
    Context context;
    ArrayList<MDQuestion_Model> questiondata=new ArrayList<MDQuestion_Model>();
    ArrayList<String> onlyQuestion=new ArrayList<>();
    ArrayList<String> oniyProfile=new ArrayList<>();
    ArrayList<String> oniyName=new ArrayList<>();
    ArrayList<String> onlyKey=new ArrayList<>();
    ArrayList<String> onlyDate=new ArrayList<>();
    ArrayList<String> oniyTime=new ArrayList<>();
    ArrayList<String> accountkey=new ArrayList<>();
    public QuestionAdapter(Context doctor_activity, ArrayList<MDQuestion_Model> questiondata) {
        this.context=doctor_activity;
        this.questiondata=questiondata;
        Log.e("asdf",questiondata.size()+"" );
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.doctot_question_card,parent,false);
        QuestionAdapter.MyHolder eventholder=new QuestionAdapter.MyHolder(v);
        return eventholder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        for (int i=0;i<questiondata.size();i++){
            onlyQuestion.add(questiondata.get(i).getQuestion());
            oniyName.add(questiondata.get(i).getUsername());
            oniyProfile.add(questiondata.get(i).getProfile());
            onlyKey.add(questiondata.get(i).getAnswerKey());
            oniyTime.add(questiondata.get(i).getTime());
            onlyDate.add(questiondata.get(i).getDate());
            accountkey.add(questiondata.get(i).getKey());

        }Log.e("asdfg",questiondata.get(position).getQuestion() );
       holder.question_text.setText(questiondata.get(questiondata.size()-position-1).getQuestion()+"");
       holder.userName.setText(questiondata.get(questiondata.size()-position-1).getUsername()+"");
        holder.dateTextView.setText(questiondata.get(questiondata.size()-position-1).getTime());

        Glide.with(context).load(questiondata.get(questiondata.size()-position-1).getProfile()).diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        holder.profile.setVisibility(View.INVISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        holder.profile.setVisibility(View.VISIBLE);

                        return false;
                    }
                }).into(holder.profile);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go=new Intent(context,Answer_ParentActivity.class);
                go.putExtra("question",onlyQuestion);
                go.putExtra("name",oniyName);
                go.putExtra("profile",oniyProfile);
                go.putExtra("position",questiondata.size()-position-1);
                go.putExtra("answerkey",onlyKey);
                go.putExtra("key",accountkey);
                go.putExtra("date",onlyDate);
                go.putExtra("time",oniyTime);
                context.startActivity(go);

            }
        });
    }

    @Override
    public int getItemCount() {
        return questiondata.size();
    }

    @Override
    public void onClick(View view) {

    }


    public class MyHolder extends RecyclerView.ViewHolder {
        TextView question_text;
        CircleImageView profile;
        TextView userName;
        TextView dateTextView;
        public MyHolder(View itemView) {
            super(itemView);
            question_text= (TextView) itemView.findViewById(R.id.questiontext);
            profile= (CircleImageView) itemView.findViewById(R.id.profileimage);
            userName= (TextView) itemView.findViewById(R.id.username);
            dateTextView= (TextView) itemView.findViewById(R.id.timeanddate);


        }
    }
}

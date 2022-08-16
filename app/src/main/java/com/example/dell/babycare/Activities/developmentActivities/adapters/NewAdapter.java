
package com.example.dell.babycare.Activities.developmentActivities.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.dell.babycare.Activities.developmentActivities.DevelopmentModel;
import com.example.dell.babycare.Activities.developmentActivities.activities.Login_Activity;
import com.example.dell.babycare.Activities.developmentActivities.model.Comment_Count_Model;
import com.example.dell.babycare.Activities.developmentActivities.model.Comment_model;
import com.example.dell.babycare.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by hp on 05-Jan-18.
 */

public class NewAdapter extends RecyclerView.Adapter <NewAdapter.MyHolder>{
    RecyclerView commentrecycler;
    Context new_feed;
    //  boolean isLike;
    Firebase firebase;
    ImageView combtn;
    EditText comedit;
    String comment_text;
    ArrayList<DevelopmentModel> postData=new ArrayList<>();
    ArrayList<Comment_model> commentdata=new ArrayList<>();
    ArrayList<Comment_model> commentfire=new ArrayList<>();
    ArrayList<Comment_Count_Model> real_comment_count_list=new ArrayList<>();
    ArrayList<Integer> likelist=new ArrayList<>();
    ArrayList<Boolean> isLike=new ArrayList<>();
    RecyclerView.Adapter adapter;
    String sharename,shareprofile,sharecommentkey;
    SharedPreferences share;
    static String stime,sday;
    String profileUri;

    public NewAdapter(Context new_feed, ArrayList<DevelopmentModel> postData, ArrayList<Boolean> isLike, ArrayList<Comment_Count_Model> real_comment_count_list) {
        this.new_feed=new_feed;
        this.postData=postData;
        this.isLike=isLike;
        this.real_comment_count_list=real_comment_count_list;
        Firebase.setAndroidContext(new_feed);
        firebase = new Firebase("https://myschoolserver-dcae9.firebaseio.com/");



    }



    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(new_feed).inflate(R.layout.new_feed_card,parent,false);
        MyHolder eventholder=new MyHolder(v);
        return eventholder;
    }

    public static String Time(){
        DateFormat time = new SimpleDateFormat(" HH:mm:ss");
        time.setLenient(false);
        Date todaytime = new Date();
        stime = time.format(todaytime);
        return stime;
    }
    public static String Date(){
        DateFormat date = new SimpleDateFormat("yyyy:MM:dd ");
        date.setLenient(false);
        Date todaydate = new Date();
        sday = date.format(todaydate);
        return sday;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        holder.name.setText(postData.get(postData.size()-position-1).getName());
        holder.fact.setText(postData.get(postData.size()-position-1).getFact());
        holder.comment.setText(real_comment_count_list.get(postData.size()-position-1).getCommentcount()+" Comment");

        share=new_feed.getSharedPreferences("AccountData", MODE_PRIVATE);
        sharename=share.getString("name",null);
        shareprofile=share.getString("profile",null);



        holder.comment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                View v1=LayoutInflater.from(new_feed).inflate(R.layout.comment_dialog_style,null);
                AlertDialog.Builder dialog=new AlertDialog.Builder(new_feed);
                dialog.setView(v1);
                dialog.show();
                sharecommentkey= postData.get(postData.size()-position-1).getCommentKey();
               /* firebase.child("Discard_Mother").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        accountdata.clear();
                        for(DataSnapshot sh:dataSnapshot.getChildren())
                        {
                            DevelopmentModel data=new DevelopmentModel();

                            data=sh.getValue(DevelopmentModel.class);
                            accountdata.add(data);
                        }

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });*/


                combtn= (ImageView) v1.findViewById(R.id.commentbutton);
                comedit= (EditText) v1.findViewById(R.id.commenttext);
                commentrecycler= (RecyclerView) v1.findViewById(R.id.commentrecycler);
                commentrecycler.setLayoutManager(new LinearLayoutManager(new_feed));


                firebase.child("Comment").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        commentfire.clear();
                        for (DataSnapshot sh:dataSnapshot.getChildren()){
                            Comment_model ccModel=new Comment_model();

                            ccModel=sh.getValue(Comment_model.class);
                            if (ccModel.getCommentkey().equals(sharecommentkey))
                                commentfire.add(ccModel);
                        }
                        adapter = new CommentAdapter(new_feed,commentfire,sharecommentkey);
                        commentrecycler.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
                combtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Login_Activity.haveNetworkConnection(new_feed)) {
                            comment_text = comedit.getText().toString();
                            if (comment_text.equals("")) {
                                Toast.makeText(new_feed, "ျဖည့္ရန္က်န္ပါေသးသည္", Toast.LENGTH_SHORT).show();
                            } else {
                                Comment_model cc = new Comment_model();
                                cc.setCommenttext(comment_text);
                                cc.setCommentkey(sharecommentkey);
                                cc.setUsername(sharename);
                                cc.setProfile(shareprofile);
                                cc.setDate(Date());
                                cc.setTime(Time());
                                firebase.child("Comment").push().setValue(cc);
                                Comment_Count_Model comment_count_model = new Comment_Count_Model();
                                comment_count_model.setCommentkey(postData.get(postData.size() - position - 1).getCommentKey());
                                comment_count_model.setCommentcount(real_comment_count_list.get(postData.size() - position - 1).getCommentcount() + 1);
                                firebase.child("Comment_Count_Table").child(postData.get(postData.size() - position - 1).getCommentKey()).setValue(comment_count_model);
                                comedit.setText("");
                            }
                        }
                        else
                        { Snackbar.make(view,"အင္တာနက္ဖြင့္ရန္လိုအပ္ပါသည္", Snackbar.LENGTH_SHORT).setAction("Action",null).show();}





                    }
                });



            }
        });
        Glide.with(new_feed).load(postData.get(postData.size()-position-1).getProfilepicture()).diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        holder.circleProfile.setVisibility(View.INVISIBLE);
                       // holder.pro.setVisibility(View.GONE);
                       // holder.refreshImage.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        holder.circleProfile.setVisibility(View.VISIBLE);
                     //   holder.pro.setVisibility(View.GONE);
                       // holder.refreshImage.setVisibility(View.GONE);
                        return false;
                    }
                }).into(holder.circleProfile);

        Glide.with(new_feed).load(postData.get(postData.size()-position-1).getImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        holder.postimage.setVisibility(View.INVISIBLE);
                        holder.pro.setVisibility(View.GONE);
                        holder.refreshImage.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        holder.postimage.setVisibility(View.VISIBLE);
                        holder.pro.setVisibility(View.GONE);
                        holder.refreshImage.setVisibility(View.GONE);
                        return false;
                    }
                }).into(holder.postimage);


        holder.refreshImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(new_feed).load(postData.get(postData.size()-position-1).getImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                holder.postimage.setVisibility(View.INVISIBLE);
                                holder.pro.setVisibility(View.GONE);
                                holder.refreshImage.setVisibility(View.VISIBLE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                holder.postimage.setVisibility(View.VISIBLE);
                                holder.pro.setVisibility(View.GONE);
                                holder.refreshImage.setVisibility(View.GONE);
                                return false;
                            }
                        }).into(holder.postimage);
            }
        });



    }

    @Override
    public int getItemCount() {
        return postData.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        CircleImageView circleProfile;
        TextView name,fact,like,comment;
        ProgressBar pro;
        ImageView postimage,refreshImage;
        public MyHolder(View v) {
            super(v);
            name= (TextView) v.findViewById(R.id.name_text);
            fact= (TextView) v.findViewById(R.id.fact_text);
            comment= (TextView) v.findViewById(R.id.like);
            circleProfile= (CircleImageView) v.findViewById(R.id.circleprofile);
            pro= (ProgressBar) v.findViewById(R.id.new_loading);
            postimage= (ImageView) v.findViewById(R.id.new_image);
            refreshImage= (ImageView) v.findViewById(R.id.new_refresh);
        }
    }
}

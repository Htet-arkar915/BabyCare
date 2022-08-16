package com.example.dell.babycare.Activities.developmentActivities.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.dell.babycare.Activities.developmentActivities.model.Comment_model;
import com.example.dell.babycare.R;
import com.firebase.client.Firebase;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by hp on 09-Jan-18.
 */

class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyHolder>{
    Context context;
    ArrayList<Comment_model> commentdata;
    ArrayList<Comment_model> commentdata1;
    Firebase firebase;
    String sharecommentkey;
    public CommentAdapter(Context new_feed, ArrayList<Comment_model> commentdata, String sharecommentkey) {
        this.context=new_feed;
        this.commentdata=commentdata;
        this.sharecommentkey=sharecommentkey;
        Firebase.setAndroidContext(new_feed);
        firebase = new Firebase("https://myschoolserver-dcae9.firebaseio.com/");
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.comment_card,parent,false);
        MyHolder commentHolder=new MyHolder(v);
        return commentHolder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
//holder.commentText.setText(commentdata.get(commentdata.size()-position-1).getCcomment());

        {
            holder.commentText.setText(commentdata.get(commentdata.size()-position-1).getCommenttext());
            holder.commentuser.setText(commentdata.get(commentdata.size()-position-1).getUsername());

            Glide.with(context).load(commentdata.get(commentdata.size()-position-1).getProfile()).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            holder.circle.setVisibility(View.INVISIBLE);
                            // holder.pro.setVisibility(View.GONE);
                            // holder.refreshImage.setVisibility(View.VISIBLE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            holder.circle.setVisibility(View.VISIBLE);
                            //   holder.pro.setVisibility(View.GONE);
                            // holder.refreshImage.setVisibility(View.GONE);
                            return false;
                        }
                    }).into(holder.circle);

        }



    }

    @Override
    public int getItemCount() {
        return commentdata.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView commentText;
        TextView commentuser;
        CircleImageView circle;
        public MyHolder(View itemView) {
            super(itemView);
            commentText= (TextView) itemView.findViewById(R.id.real_comment);
            commentuser= (TextView) itemView.findViewById(R.id.username);

            circle= (CircleImageView) itemView.findViewById(R.id.commentcircleprofile);
        }
    }
}

package com.example.dell.babycare.Activities.developmentActivities.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.dell.babycare.Activities.developmentActivities.activities.HomeActivity;
import com.example.dell.babycare.Activities.developmentActivities.activities.ShowDetail;
import com.example.dell.babycare.Activities.developmentActivities.model.FragmentModel;
import com.example.dell.babycare.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import static com.example.dell.babycare.Activities.developmentActivities.adapters.FirstRVAdatpter.development_position;


/**
 * Created by MAT on 1/14/2018.
 */

public class ShowDetailAdapter extends RecyclerView.Adapter<ShowDetailAdapter.MyShowHolder> {
    Context context;
    String detail;
    ArrayList<FragmentModel> f1;
    String childKey;
    Firebase firebase;
    int pos=development_position;
    public ShowDetailAdapter(ShowDetail showDetail, String detail, String key) {
        this.context=showDetail;
        this.detail=detail;
        childKey=key;
        f1=new ArrayList<FragmentModel>();

    }

    @Override
    public MyShowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.activity_show_detail,parent,false);
        MyShowHolder holder=new MyShowHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyShowHolder holder, final int position) {

        if(HomeActivity.haveNetworkConnection(context)) {
            Firebase.setAndroidContext(context);
            firebase = new Firebase("https://myschoolserver-dcae9.firebaseio.com/");
            firebase.child("DevelopmentImage").child(childKey).child(pos+"").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    holder.textView.setText(detail);
                    FragmentModel model=new FragmentModel();
                    model=dataSnapshot.getValue(FragmentModel.class);
                    f1.add(dataSnapshot.getValue(FragmentModel.class));
                    Glide.with(context).load(model.getDevelopmentImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                            .listener(new RequestListener<String, GlideDrawable>() {
                                @Override
                                public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                    holder.detail_image.setVisibility(View.INVISIBLE);
                                    holder.shop_loading.setVisibility(View.GONE);
                                    holder.shop_refresh.setVisibility(View.VISIBLE);
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                    holder.detail_image.setVisibility(View.VISIBLE);
                                    holder.shop_loading.setVisibility(View.GONE);
                                    holder.shop_refresh.setVisibility(View.GONE);
                                    return false;
                                }
                            }).into(holder.detail_image);
                    final FragmentModel finalModel = model;
                    holder.shop_refresh.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Glide.with(context).load(finalModel.getDevelopmentImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .listener(new RequestListener<String, GlideDrawable>() {
                                        @Override
                                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                            holder.detail_image.setVisibility(View.INVISIBLE);
                                            holder.shop_loading.setVisibility(View.GONE);
                                            holder.shop_refresh.setVisibility(View.VISIBLE);
                                            return false;
                                        }

                                        @Override
                                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                            holder.detail_image.setVisibility(View.VISIBLE);
                                            holder.shop_loading.setVisibility(View.GONE);
                                            holder.shop_refresh.setVisibility(View.GONE);
                                            return false;
                                        }
                                    }).into(holder.detail_image);
                        }
                    });
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }
        else {
            holder.textView.setText(detail);
            holder.detail_image.setImageResource(R.drawable.baby);
            holder.shop_loading.setVisibility(View.INVISIBLE);
        }


    }

    @Override
    public int getItemCount() {
        return detail.length();
    }

    public class MyShowHolder extends RecyclerView.ViewHolder {
        ImageView detail_image;
        TextView textView;
        ProgressBar shop_loading;
        ImageView shop_refresh;
        public MyShowHolder(View itemView) {
            super(itemView);
            detail_image = (ImageView) itemView.findViewById(R.id.detailimage);
            textView = (TextView) itemView.findViewById(R.id.textdetail);
            shop_loading= (ProgressBar) itemView.findViewById(R.id.detail_loading);
            shop_refresh= (ImageView) itemView.findViewById(R.id.detail_refresh);
        }
    }
}

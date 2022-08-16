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
import com.example.dell.babycare.Activities.developmentActivities.model.ShopDetailModel;
import com.example.dell.babycare.Activities.developmentActivities.activities.ShopDetails;
import com.example.dell.babycare.R;

import java.util.ArrayList;

/**
 * Created by MAT on 1/12/2018.
 */

public class ShopDetailAdapter extends RecyclerView.Adapter<ShopDetailAdapter.MyViewHolder> {
    Context context;
    ArrayList<ShopDetailModel> shopData;
    public ShopDetailAdapter(ShopDetails shopDetails, ArrayList<ShopDetailModel> shopData) {
        this.context=shopDetails;
        this.shopData=shopData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(context).inflate(R.layout.activity_shop_details,parent,false);
        MyViewHolder holder=new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Glide.with(context).load(shopData.get(position).getImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        holder.shop_image.setVisibility(View.INVISIBLE);
                        holder.shop_loading.setVisibility(View.GONE);
                        holder.shop_refresh.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        holder.shop_image.setVisibility(View.VISIBLE);
                        holder.shop_loading.setVisibility(View.GONE);
                        holder.shop_refresh.setVisibility(View.GONE);
                        return false;
                    }
                }).into(holder.shop_image);
        holder.shop_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(context).load(shopData.get(position).getImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                holder.shop_image.setVisibility(View.INVISIBLE);
                                holder.shop_loading.setVisibility(View.GONE);
                                holder.shop_refresh.setVisibility(View.VISIBLE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                holder.shop_image.setVisibility(View.VISIBLE);
                                holder.shop_loading.setVisibility(View.GONE);
                                holder.shop_refresh.setVisibility(View.GONE);
                                return false;
                            }
                        }).into(holder.shop_image);
            }
        });
        holder.shop_name.setText(shopData.get(position).getName());
        holder.shop_price.setText(shopData.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return shopData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView shop_image;
        TextView shop_name,shop_price;
        ProgressBar shop_loading;
        ImageView shop_refresh;
        public MyViewHolder(View itemView) {
            super(itemView);
            shop_image= (ImageView) itemView.findViewById(R.id.shop_detail_image);
            shop_name= (TextView) itemView.findViewById(R.id.shop_detail_name);
            shop_loading= (ProgressBar) itemView.findViewById(R.id.shop_detail_loading);
            shop_refresh= (ImageView) itemView.findViewById(R.id.shop_detail_refresh);
            shop_price= (TextView) itemView.findViewById(R.id.shop_detail_price);
        }
    }
}

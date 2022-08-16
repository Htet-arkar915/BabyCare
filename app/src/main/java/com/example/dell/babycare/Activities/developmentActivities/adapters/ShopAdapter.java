package com.example.dell.babycare.Activities.developmentActivities.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.example.dell.babycare.Activities.developmentActivities.model.DevelopmentModel;
import com.example.dell.babycare.Activities.developmentActivities.activities.ShopDetails;
import com.example.dell.babycare.Activities.developmentActivities.activities.ShopMainActivity;
import com.example.dell.babycare.R;

import java.util.ArrayList;

/**
 * Created by MAT on 1/11/2018.
 */

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.MyViewHolder> {
    Context context;

    public static int shop_position =0;
    ArrayList<DevelopmentModel> shop_image=new ArrayList<>();
   /* String[] shop_name;
    String[] shop_address;
    String[] shop_phno;*/
    public ShopAdapter(ShopMainActivity shopMainActivity, ArrayList<DevelopmentModel> shop_image) {
        this.context=shopMainActivity;
        this.shop_image=shop_image;
       /* Firebase.setAndroidContext(context);
        firebase=new Firebase("https://myschoolserver-dcae9.firebaseio.com/");*/

        /*this.shop_name=shop_name;
        this.shop_address=shop_address;
        this.shop_phno=shop_phno;*/
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.shop_adapter,parent,false);
        MyViewHolder holder=new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        //holder.tel_image.setImageResource(shop_image.get(position).get));
        Glide.with(context).load(shop_image.get(shop_image.size()-position-1).getShopImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
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
                Glide.with(context).load(shop_image.get(shop_image.size()-position-1).getShopImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
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
        holder.loc_name.setText(shop_image.get(shop_image.size()-position-1).getShop_name());
        holder.loc_phno.setText(shop_image.get(shop_image.size()-position-1).getShop_phno());
        holder.loc_address.setText(shop_image.get(shop_image.size()-position-1).getShop_address());

        /*if(position ==0){
            holder.see_more.setVisibility(View.VISIBLE);
            firebase.child("ShopDetails").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    changeData1(dataSnapshot);
                }



                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
            holder.see_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(context,ShopDetails2.class);
                   *//* Bundle bundle=new Bundle();
                    bundle.putSerializable("Data1",ShopData1);
                    i.putExtras(bundle);
                    context.startActivity(i);*//*
                 *//*  i.putParcelableArrayListExtra("Data1",ShopData1);
                    context.startActivity(i);*//*
                   *//*i.putExtra("Data1",ShopData1);
                    context.startActivity(i);*//*


                }
            });
        }*/

        if(position == 0 || position == 1|| position == 2 || position == 3  ){
            holder.see_more.setVisibility(View.VISIBLE);
            holder.see_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shop_position=position;
                    Intent i=new Intent(context,ShopDetails.class);
                    context.startActivity(i);


                }
            });
        }
        else {
            holder.see_more.setVisibility(View.GONE);
        }
        holder.tel_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phone=new Intent(Intent.ACTION_DIAL);
                if(position == 0){
                    phone.setData(Uri.parse("tel:09976556433"));

                }
                else if(position == 1){
                    phone.setData(Uri.parse("tel:09776544675"));

                }
                else if( position == 2){
                    phone.setData(Uri.parse("tel:09787816733"));

                }
                else if( position == 3){
                    phone.setData(Uri.parse("tel:09977665553"));
                }
                else if( position == 4){
                    phone.setData(Uri.parse("tel:09988765437"));

                }
                else if( position == 5){
                    phone.setData(Uri.parse("tel:09954768987"));

                }
                else if( position == 6){
                    phone.setData(Uri.parse("tel:09765456435"));

                }
                else if( position == 7){
                    phone.setData(Uri.parse("tel:09759687632"));

                }
                else if( position == 8){
                    phone.setData(Uri.parse("tel:09405456754"));

                } else if( position == 9){
                    phone.setData(Uri.parse("tel:09252265284"));

                }


                context.startActivity(phone);

            }
        });



    }




    @Override
    public int getItemCount() {
        return shop_image.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView loc_address,loc_phno,loc_name;
        ImageView tel_image;
        TextView see_more;
        ImageView shop_refresh;
        ImageView shop_image;
                ProgressBar shop_loading;
        public MyViewHolder(View itemView) {
            super(itemView);
            shop_image= (ImageView) itemView.findViewById(R.id.shop_image);
            loc_address= (TextView) itemView.findViewById(R.id.shop_address);
            loc_phno= (TextView) itemView.findViewById(R.id.shop_ph_no);
            loc_name= (TextView) itemView.findViewById(R.id.shop_name);
            tel_image= (ImageView) itemView.findViewById(R.id.shop_ph_image);
            see_more= (TextView) itemView.findViewById(R.id.shop_see_more);
            shop_refresh= (ImageView) itemView.findViewById(R.id.shop_refresh);
            shop_loading= (ProgressBar) itemView.findViewById(R.id.shop_loading);
        }
    }
}

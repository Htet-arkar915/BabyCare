package com.example.dell.babycare.Activities.developmentActivities.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.dell.babycare.Activities.developmentActivities.model.FavoriteList;
import com.example.dell.babycare.R;

import java.util.ArrayList;
import java.util.List;

import san.db.handler.SanDBHandler;

/**
 * Created by DELL on 1/11/2018.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyHolder> {
    Context context;
    View v;
    List<String> nameList,detailList;
    RVAdapter.OnItemClick onItemClick;
    public static boolean favClick;


    public RVAdapter(Context context, List<String> nameList,List<String> detailList) {
        this.context = context;
        this.nameList = nameList;
        this.detailList = detailList;
    }


    SanDBHandler sanDBHandler = new SanDBHandler(context);


    @Override
    public RVAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        v = LayoutInflater.from(context).inflate(R.layout.firstcardview,parent,false);
        return new RVAdapter.MyHolder(v);
    }

    @Override
    public void onBindViewHolder(final RVAdapter.MyHolder holder, final int position) {




        favClick = false;
        holder.baby.setImageResource(R.drawable.baby);
        holder.tdetail.setText(nameList.get(position));

        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!favClick){
                    holder.favorite.setImageResource(R.drawable.favorite);
                    favClick = true;
                    //favarraylist.add(namelist.get(position));
                    //favdetaillist.add(detaillist.get(position));
                    sanDBHandler.createDatabaseByClass("FavoriteInfo.db",FavoriteList.class);
                     FavoriteList flist = new FavoriteList(nameList.get(position),nameList.get(position));
                    flist.insert();
                }
                else {
                    holder.favorite.setImageResource(R.drawable.favorite1);
                    favClick = false;
                    FavoriteList.deleteByQuery("FavoriteList","FNAME=?",new String[]{nameList.get(position)});
                }
                List<FavoriteList> list = FavoriteList.getAllData(FavoriteList.class);
                List<String> name = new ArrayList<String>();
                List<String> detail = new ArrayList<String>();
                for (int i=0;i<list.size();i++){
                    name.add(list.get(position).getFNAME());
                    detail.add(list.get(position).getFDETAIL());
                }
                onItemClick.onfavItemClick(favClick,name,detail,position );
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onClick(detailList.get(holder.getAdapterPosition()),position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }


    public void setOnitemClickListener(RVAdapter.OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView baby,favorite;
        TextView tdetail;
        CardView cardView;
        public MyHolder(View itemView) {
            super(itemView);
            baby = (ImageView) itemView.findViewById(R.id.bimage);
            tdetail = (TextView) itemView.findViewById(R.id.tdetil);
            cardView = (CardView) itemView.findViewById(R.id.card);
        }
    }
    public interface OnItemClick{
        public void onClick(String bone, int position);
        public void onfavItemClick(boolean favclick, List<String> fname, List<String> fdetail, int position);
    }
}

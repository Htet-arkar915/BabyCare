package com.example.dell.babycare.Activities.developmentActivities.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.dell.babycare.R;

import java.util.HashMap;
import java.util.List;


/**
 * Created by MAT on 1/6/2018.
 */

public class LocationAdapter extends BaseExpandableListAdapter {
    private Context ctx;
    private HashMap<String,List<String>> ChildTitles;
    private List<String>HeaderTitles;
    public LocationAdapter(Context ctx, HashMap<String, List<String>> ChildTitles, List<String> HeaderTitles){
        this.ctx=ctx;
        this.ChildTitles=ChildTitles;
        this.HeaderTitles=HeaderTitles;
    }

    @Override
    public int getGroupCount() {
        return HeaderTitles.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return ChildTitles.get(HeaderTitles.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return HeaderTitles.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return ChildTitles.get(HeaderTitles.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String title=(String)this.getGroup(groupPosition);
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.location_adapter,null);

        }
        TextView txt=(TextView)convertView.findViewById(R.id.location_text);
        txt.setTypeface(null, Typeface.BOLD);
        txt.setText(title);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String title=(String)this.getChild(groupPosition,childPosition);
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.towns,null);
        }

        TextView txt=(TextView)convertView.findViewById(R.id.textviewtowns);
        txt.setTypeface(null, Typeface.BOLD);
        txt.setText(title);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}


























/*extends android.support.v7.widget.RecyclerView.Adapter<LocationAdapter.MyViewHolder> {
    Context context;
    HashMap<String, List<String>> c_name;
    List<String> child;


    public LocationAdapter(LocationDetails locationDetails, HashMap<String, List<String>> header, List<String> child) {
        this.c_name=header;
        this.child=child;
        this.context=locationDetails;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.location_adapter,parent,false);
        MyViewHolder holder=new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Iterator o=c_name.keySet().iterator();
        //String key=o.next();
        holder.text1.setText( c_name.get(o).get(position));
        holder.text2.setText(child.get(position));
        //ChildTitles.get(HeaderTitles.get(groupPosition)).get(childPosition)

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text1,text2;
        public MyViewHolder(View itemView) {
            super(itemView);
            text1= (TextView) itemView.findViewById(R.id.location_text);
            text1= (TextView) itemView.findViewById(R.id.location_text_details);

        }
    }
}*/

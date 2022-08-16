package com.example.dell.babycare.Activities.developmentActivities.mainHomeActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dell.babycare.Activities.developmentActivities.activities.ArticlesActivity;
import com.example.dell.babycare.Activities.developmentActivities.activities.Doctor_Activity;
import com.example.dell.babycare.Activities.developmentActivities.activities.Happy_Baby;
import com.example.dell.babycare.Activities.developmentActivities.activities.HomeActivity;
import com.example.dell.babycare.Activities.developmentActivities.activities.LocationDetails;
import com.example.dell.babycare.Activities.developmentActivities.activities.Login_Activity;
import com.example.dell.babycare.Activities.developmentActivities.activities.QuestionOverview;
import com.example.dell.babycare.Activities.developmentActivities.activities.SecondShowDataActivity;
import com.example.dell.babycare.Activities.developmentActivities.activities.ShopMainActivity;
import com.example.dell.babycare.Activities.developmentActivities.activities.ShowDataActivity;
import com.example.dell.babycare.Activities.developmentActivities.frags.FragmentMainActivity;
import com.example.dell.babycare.R;

import java.util.List;

import static android.content.Context.MODE_APPEND;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder>  {

    private Context mContext;
    private List<Album> albumList;



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, count;
        public ImageView thumbnail, overflow;

        private Album album;

        public MyViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.title);
            //count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            //overflow = (ImageView) view.findViewById(R.id.overflow);
            view.setOnClickListener(this);
           /* overflow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopupMenu(overflow);
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Click on "+album.getName(), Toast.LENGTH_SHORT).show();
                }
            });*/
        }

        public void bindData(Album album) {

            this.album = album;
            title.setText(album.getName());
           // count.setText(album.getNumOfSongs() + " songs");

            // loading album cover using Glide library
            Glide.with(mContext).load(album.getThumbnail()).into(thumbnail);
        }

        @Override
        public void onClick(View v) {
            switch (getAdapterPosition()){
                case 0:
                    Intent intent = new Intent(mContext,ShowDataActivity.class);
                    mContext.startActivity(intent);
                    break;
                case 1:
                    Intent intent2 = new Intent(mContext,SecondShowDataActivity.class);
                    mContext.startActivity(intent2);

                    break;
                case 2:
                    Intent intent3 = new Intent(mContext,ArticlesActivity.class);
                    mContext.startActivity(intent3);

                    break;
                case 3:
                    Intent intent4 = new Intent(mContext,QuestionOverview.class);
                    mContext.startActivity(intent4);

                    break;
                case 4:
                    final AlertDialog alertDialog;
                    View v1= LayoutInflater.from(mContext).inflate(R.layout.check_login_dialog,null);
                    AlertDialog.Builder dialog=new AlertDialog.Builder(mContext);
                    dialog.setView(v1);
                    alertDialog=dialog.create();
                    alertDialog.show();


                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(checkLogin()){
                                alertDialog.dismiss();
                                mContext.startActivity(new Intent(mContext,FragmentMainActivity.class));
                            }else if (checkLoginDoctor()){

                                mContext.startActivity(new Intent(mContext,Doctor_Activity.class));
                                alertDialog.dismiss();
                            }
                            else {
                                mContext.startActivity(new Intent(mContext,Login_Activity.class));
                                alertDialog.dismiss();
                            }
                        }
                    },4000);
                    break;
                case 5:
                    Intent intent6=new Intent(mContext,Happy_Baby.class);
                    mContext.startActivity(intent6);

                    break;
                case 6:
                    Intent i=new Intent(mContext,LocationDetails.class);
                    mContext.startActivity(i);

                    break;
                case 7:
                    Intent shop=new Intent(mContext, ShopMainActivity.class);
                    mContext.startActivity(shop);
                    break;
            }
        }
    }

    public AlbumsAdapter(Context mContext, List<Album> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.bindData(album);

    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    private boolean checkLoginDoctor() {
        SharedPreferences sh=mContext.getSharedPreferences("doctorlogin",MODE_APPEND);
        return sh.getBoolean("okdoctorlogin",false);
    }

    /**/
    private boolean checkLogin() {
        SharedPreferences sh=mContext.getSharedPreferences("parentlogin",MODE_APPEND);
        return sh.getBoolean("oklogin",false);
    }
    public static void addParentLogin(Context context, boolean value){
        SharedPreferences sh=context.getSharedPreferences("parentlogin",MODE_APPEND);
        SharedPreferences.Editor editor=sh.edit();
        editor.putBoolean("oklogin",value);
        editor.commit();
    }
    public static void addDoctorLogin(Context context, boolean value){
        SharedPreferences sh=context.getSharedPreferences("doctorlogin",MODE_APPEND);
        SharedPreferences.Editor editor=sh.edit();
        editor.putBoolean("okdoctorlogin",value);
        editor.commit();
    }


    @Override
    public int getItemCount() {
        return albumList.size();
    }
}

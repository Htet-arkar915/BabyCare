package com.example.dell.babycare.Activities.developmentActivities.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.dell.babycare.Activities.developmentActivities.frags.FragmentMainActivity;
import com.example.dell.babycare.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn1= (Button) findViewById(R.id.first);
        btn2= (Button) findViewById(R.id.second);
        btn3= (Button) findViewById(R.id.third);
        btn4= (Button) findViewById(R.id.fourth);
        btn5= (Button) findViewById(R.id.fifth);
        btn6= (Button) findViewById(R.id.sixth);
        btn7= (Button) findViewById(R.id.location_btn);
        btn8= (Button) findViewById(R.id.shop);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);

    }
    public static boolean haveNetworkConnection(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.first:
                Intent intent = new Intent(HomeActivity.this,ShowDataActivity.class);
                startActivity(intent);
                break;
            case R.id.second:
                Intent intent2 = new Intent(HomeActivity.this,SecondShowDataActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.third:
                Intent intent3 = new Intent(HomeActivity.this,ArticlesActivity.class);
                startActivity(intent3);
                finish();
                break;
            case R.id.fourth:
                Intent intent4 = new Intent(HomeActivity.this,QuestionOverview.class);
                startActivity(intent4);
                finish();
                break;
            case R.id.fifth:
                final AlertDialog alertDialog;
                View v1= LayoutInflater.from(HomeActivity.this).inflate(R.layout.check_login_dialog,null);
                AlertDialog.Builder dialog=new AlertDialog.Builder(HomeActivity.this);
                dialog.setView(v1);
                alertDialog=dialog.create();
                alertDialog.show();


                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(checkLogin()){
                            alertDialog.dismiss();
                            startActivity(new Intent(HomeActivity.this,FragmentMainActivity.class));
                        }else if (checkLoginDoctor()){

                            startActivity(new Intent(HomeActivity.this,Doctor_Activity.class));
                            alertDialog.dismiss();
                        }
                        else {
                            startActivity(new Intent(HomeActivity.this,Login_Activity.class));
                            alertDialog.dismiss();
                        }
                    }
                },4000);
                break;
            case R.id.sixth:
                Intent intent6=new Intent(HomeActivity.this,Happy_Baby.class);
                startActivity(intent6);
                finish();
                break;
            case R.id.location_btn:
                Intent i=new Intent(HomeActivity.this,LocationDetails.class);
                startActivity(i);
                finish();
                break;
            case R.id.shop:
                Intent shop=new Intent(HomeActivity.this, ShopMainActivity.class);
                startActivity(shop);
                finish();
                break;

        }
    }

    private boolean checkLoginDoctor() {
        SharedPreferences sh=getSharedPreferences("doctorlogin",MODE_APPEND);
        return sh.getBoolean("okdoctorlogin",false);
    }

    /**/
    private boolean checkLogin() {
        SharedPreferences sh=getSharedPreferences("parentlogin",MODE_APPEND);
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
}

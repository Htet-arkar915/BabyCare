package com.example.dell.babycare.Activities.developmentActivities.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.dell.babycare.Activities.developmentActivities.adapters.DataProvider;
import com.example.dell.babycare.Activities.developmentActivities.adapters.LocationAdapter;
import com.example.dell.babycare.Activities.developmentActivities.magwayClinic.M_Clinic2;
import com.example.dell.babycare.Activities.developmentActivities.magwayClinic.M_clinic1;
import com.example.dell.babycare.Activities.developmentActivities.mandalayClinic.ManClinic1;
import com.example.dell.babycare.Activities.developmentActivities.mandalayClinic.ManClinic2;
import com.example.dell.babycare.Activities.developmentActivities.mandalayClinic.ManClinic3;
import com.example.dell.babycare.Activities.developmentActivities.mandalayClinic.ManClinic4;
import com.example.dell.babycare.Activities.developmentActivities.mandalayClinic.ManClinic5;
import com.example.dell.babycare.Activities.developmentActivities.mandalayClinic.ManClinic6;
import com.example.dell.babycare.Activities.developmentActivities.mandalayClinic.ManClinic7;
import com.example.dell.babycare.Activities.developmentActivities.meiktilaClinic.MeiktilaClinic1;
import com.example.dell.babycare.Activities.developmentActivities.naypyitawClinic.NClinic1;
import com.example.dell.babycare.Activities.developmentActivities.naypyitawClinic.NClinic2;
import com.example.dell.babycare.Activities.developmentActivities.naypyitawClinic.NClinic3;
import com.example.dell.babycare.Activities.developmentActivities.pyiOoLwinClinic.P_clinic1;
import com.example.dell.babycare.Activities.developmentActivities.taunggyiClinic.T_Clinic1;
import com.example.dell.babycare.Activities.developmentActivities.yangonClinic.YClinic1;
import com.example.dell.babycare.Activities.developmentActivities.yangonClinic.YClinic2;
import com.example.dell.babycare.Activities.developmentActivities.yangonClinic.YClinic3;
import com.example.dell.babycare.Activities.developmentActivities.yangonClinic.YClinic4;
import com.example.dell.babycare.Activities.developmentActivities.yangonClinic.YClinic5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LocationDetails extends AppCompatActivity implements LocationListener {

  /*  ChildDetails1.add("ေဒါက္တာ ေမာင္ေမာင္ႀကီးေဆးခန္း(မႏၱေလး)");
        ChildDetails1.add("ကုတင္ ၅၅၀ ကေလးအထူးကုေဆးရံုႀကီး(မႏၱေလး)");
        ChildDetails1.add("ျမတ္သုခေဆးရံု (မႏၱေလး)");
        ChildDetails1.add("ခ်မ္းၿငိမ္းေအာင္(မႏၱေလး)");
        ChildDetails1.add("ေအာင္ေဆးခန္း(မႏၱေလး)");
        ChildDetails1.add("ေအးသုခ(မႏၱေလး)");
        ChildDetails1.add("ေဒါက္တာ ခင္ျမင္႔သန္း(မိတၳီလာ) ");
        ChildDetails1.add("မႏၱေလးေဆးရံုႀကီး");*/
    Toolbar toolbar;
    ExpandableListView expandableListView;
    LocationAdapter adapter;
    //String[] arr={"a1","a2",}
    HashMap<String,List<String>> header;
    List<String> child;
    public static double latitude;
    public static double longtitude;
    LocationManager locationManager;
    //String city_name[]={"မႏၱေလး","ျပင္ဦးလြင္","မိတၳီလာ","ေနျပည္ေတာ္","ရန္ကုန္","ေတာင္ႀကီး"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.dell.babycare.R.layout.activity_location_details);
        toolbar= (Toolbar) findViewById(com.example.dell.babycare.R.id.location_tool);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        getSupportActionBar().setTitle("ကေလးေဆးရုံမ်ား");

        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }



        header = DataProvider.getInfo();
        child = new ArrayList<String>(header.keySet());
        expandableListView= (ExpandableListView) findViewById(com.example.dell.babycare.R.id.location_list);
        //recyclerView.setLayoutManager(new LinearLayoutManager(LocationDetails.this));
        adapter=new LocationAdapter(LocationDetails.this,header,child);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String child= (String) adapter.getChild(groupPosition,childPosition);
                if (child.equals("ေဒါက္တာ ေမာင္ေမာင္ႀကီးေဆးခန္း")){
                    getLocation();
                    Intent i1=new Intent(LocationDetails.this, ManClinic1.class);
                    startActivity(i1);


                }
                else if (child.equals("ကုတင္ ၅၅၀ ကေလးအထူးကုေဆးရံုႀကီး")){
                    getLocation();
                    Intent i2=new Intent(LocationDetails.this, ManClinic2.class);
                    startActivity(i2);


                }
                else {
                    if (child.equals("ျမတ္သုခေဆးရံု ")) {
                        getLocation();
                        Intent i3 = new Intent(LocationDetails.this, ManClinic3.class);
                        startActivity(i3);



                    } else if (child.equals("ခ်မ္းၿငိမ္းေအာင္")) {
                        getLocation();
                        Intent i3 = new Intent(LocationDetails.this, ManClinic4.class);
                        startActivity(i3);


                    } else if (child.equals("ေအာင္ေဆးခန္း")) {
                        getLocation();
                        Intent i3 = new Intent(LocationDetails.this, ManClinic5.class);
                        startActivity(i3);
                    } else if (child.equals("ေအးသုခ")) {
                        getLocation();
                        Intent i3 = new Intent(LocationDetails.this, ManClinic6.class);
                        startActivity(i3);
                    } else if (child.equals("ေဒါက္တာ ခင္ျမင္႔သန္း")) {
                        getLocation();
                        Intent i3 = new Intent(LocationDetails.this, MeiktilaClinic1.class);
                        startActivity(i3);
                    } else if (child.equals("မႏၱေလးေဆးရံုႀကီး")) {
                        getLocation();
                        Intent i3 = new Intent(LocationDetails.this, ManClinic7.class);
                        startActivity(i3);
                    } else if (child.equals("မေကြးတိုင္းေဒသႀကီး ျပည္႕သူ႕ေဆးရံု")) {
                        getLocation();
                        Intent i3 = new Intent(LocationDetails.this, M_clinic1.class);
                        startActivity(i3);
                    } else if (child.equals("မိခင္နွင္႔ ကေလးက်န္းမာေရး ေဆးခန္း")) {
                        getLocation();
                        Intent i3 = new Intent(LocationDetails.this, M_Clinic2.class);
                        startActivity(i3);
                    } else if (child.equals("ရန္ကုန္ကေလးေဆးရံုႀကီး")) {
                        getLocation();
                        Intent i4 = new Intent(LocationDetails.this, YClinic1.class);
                        startActivity(i4);
                    } else if (child.equals("ရန္ကင္းကေလးေဆးရံု")) {
                        getLocation();
                        Intent i8 = new Intent(LocationDetails.this, YClinic2.class);
                        startActivity(i8);
                    }
                /*ChildDetails2.add("ရန္ကင္းကေလးေဆးရံု");
                ChildDetails2.add("၀ိတိုရိယေဆးရံုႀကီး");
                ChildDetails2.add("ပါရမီအေထြေထြေရာဂါကုေဆးရံု");
                ChildDetails2.add("ေအးေမတၱာကေလးေဆးရံု");*/
                    else if (child.equals("၀ိတိုရိယေဆးရံုႀကီး")) {
                        getLocation();
                        Intent i5 = new Intent(LocationDetails.this, YClinic3.class);
                        startActivity(i5);
                    } else if (child.equals("ပါရမီအေထြေထြေရာဂါကုေဆးရံု")) {
                        getLocation();
                        Intent i6 = new Intent(LocationDetails.this, YClinic4.class);
                        startActivity(i6);
                    } else if (child.equals("ေအးေမတၱာကေလးေဆးရံု")) {
                        getLocation();
                        Intent i7 = new Intent(LocationDetails.this, YClinic5.class);
                        startActivity(i7);
                    } else if (child.equals("ခ်မ္းသာသုခ ေဆးရံု")) {
                        getLocation();
                        Intent i7 = new Intent(LocationDetails.this, P_clinic1.class);
                        startActivity(i7);
                    }
               /* ChildDetails4.add("တပ္မေတာ္၊ သားဖြား၊ မီးယပ္နွင္႔ ကေလးေဆးရံု");
                ChildDetails4.add("ကေလးေရာဂါ အထူးကုေဆးရံုႀကီး");
                ChildDetails4.add("ေဘာဂသိဒၶိေဆးရံု");*/
                    else if (child.equals("တပ္မေတာ္၊ သားဖြား၊ မီးယပ္နွင္႔ ကေလးေဆးရံု")) {
                        getLocation();
                        Intent i10 = new Intent(LocationDetails.this, NClinic1.class);
                        startActivity(i10);
                    } else if (child.equals("ကေလးေရာဂါ အထူးကုေဆးရံုႀကီး")) {
                        getLocation();
                        Intent i11 = new Intent(LocationDetails.this, NClinic2.class);
                        startActivity(i11);
                    } else if (child.equals("ေဘာဂသိဒၶိေဆးရံု")) {
                        getLocation();
                        Intent i11 = new Intent(LocationDetails.this, NClinic3.class);
                        startActivity(i11);
                    } else if (child.equals("မိခင္နွွင္႔ကေလးေဆးရုံ")) {
                        getLocation();
                        Intent i12 = new Intent(LocationDetails.this, T_Clinic1.class);
                        startActivity(i12);
                    }
                }

                return false;
            }


        });

    }

    private void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5,  this);


        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude=location.getLatitude();
        longtitude=location.getLongitude();

        //locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());

       /* try {

            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            locationText.setText(locationText.getText() + "\n"+addresses.get(0).getAddressLine(0)+", "+
                    addresses.get(0).getAddressLine(1)+", "+addresses.get(0).getAddressLine(2));

        }catch(Exception e)
        {

        }*/
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Toast.makeText(LocationDetails.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(LocationDetails.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(LocationDetails.this, " သင္၏ အင္တာနက္ႏွင့္ တည္ေနရာအား အရင္ဖြင္႔ေပးပါ။", Toast.LENGTH_LONG).show();
        Toast.makeText(LocationDetails.this, " သင္၏ အင္တာနက္ႏွင့္ တည္ေနရာအား အရင္ဖြင္႔ေပးပါ။", Toast.LENGTH_LONG).show();


    }
}

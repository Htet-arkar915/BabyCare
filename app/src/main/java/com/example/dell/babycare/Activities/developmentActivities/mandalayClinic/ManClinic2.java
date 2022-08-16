package com.example.dell.babycare.Activities.developmentActivities.mandalayClinic;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dell.babycare.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import static com.example.dell.babycare.Activities.developmentActivities.activities.LocationDetails.latitude;
import static com.example.dell.babycare.Activities.developmentActivities.activities.LocationDetails.longtitude;


public class ManClinic2 extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_clinic2);
        SupportMapFragment supportMapFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.man_map2);
        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;
        LatLng here=new LatLng(latitude,longtitude);
        mMap.addMarker(new MarkerOptions().position(here).title("Your location is here"));

        LatLng clinic1=new LatLng(21.980620,96.105429);
        MarkerOptions markerOptions=new MarkerOptions();
        markerOptions.position(clinic1);
        markerOptions.title("ကုတင္ ၅၅၀ ကေလးအထူးကုေဆးရံုႀကီး(မႏၱေလး)");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(markerOptions);

        PolylineOptions polylineOptions=new PolylineOptions();
        polylineOptions.width(3);
        polylineOptions.color(Color.RED);
        polylineOptions.add(here,clinic1);
        mMap.addPolyline(polylineOptions);


        LatLng half=new LatLng((here.latitude+clinic1.latitude)/2,(here.longitude+clinic1.longitude)/2);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(half));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(7));
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style));
    }
}

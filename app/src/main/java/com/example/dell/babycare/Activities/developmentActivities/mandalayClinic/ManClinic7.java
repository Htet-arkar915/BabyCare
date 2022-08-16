package com.example.dell.babycare.Activities.developmentActivities.mandalayClinic;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.dell.babycare.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.text.BreakIterator;
import static com.example.dell.babycare.Activities.developmentActivities.activities.LocationDetails.latitude;
import static com.example.dell.babycare.Activities.developmentActivities.activities.LocationDetails.longtitude;

public class ManClinic7 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Location mLastLocation;
    GoogleApiClient mGoogleApiClient;
    BreakIterator mLatitudeText;
    BreakIterator mLongitudeText;
    double lat,longti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_clinic7);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.man_map7);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //double[] location= CurrentLocation.getLocation(MapsActivity.this);
        LatLng here = new LatLng(latitude, longtitude);
        mMap.addMarker(new MarkerOptions().position(here).title("Your location is here"));//
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(here));


        LatLng clinic7=new LatLng(21.977414,96.090759);
        MarkerOptions markerOptions4=new MarkerOptions();
        markerOptions4.position(clinic7);
        markerOptions4.title("မႏၱေလးေဆးရံုႀကီး");
        markerOptions4.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(markerOptions4);


        PolylineOptions polyline = new PolylineOptions();
        polyline.width(3);
        polyline.color(Color.RED);
        polyline.add(here, clinic7);
        mMap.addPolyline(polyline);

        LatLng haftWay = new LatLng((here.latitude + clinic7.latitude)/2 , (here.longitude + clinic7.longitude)/2 );

        //CameraUpdate mPlace = CameraUpdateFactory.newLatLng(haftWay);
        //CameraUpdate zoon = CameraUpdateFactory.zoomTo(170);
        mMap.moveCamera((CameraUpdateFactory.newLatLng(haftWay)));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(7));
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style));

    }






}

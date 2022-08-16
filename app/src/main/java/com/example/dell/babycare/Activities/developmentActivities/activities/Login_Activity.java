package com.example.dell.babycare.Activities.developmentActivities.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.babycare.Activities.developmentActivities.frags.FragmentMainActivity;
import com.example.dell.babycare.Activities.developmentActivities.model.Data_model;
import com.example.dell.babycare.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener {
    EditText name,pass;
    String loginname,loginpass;
    Button loginbtn;
    Firebase newFirebase;
    Data_model login;
    TextView openaccountbtn;
    ArrayList<Data_model> loginData=new ArrayList<>();
    boolean isaccount=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        name= (EditText) findViewById(R.id.loginname);
        pass= (EditText) findViewById(R.id.loginpass);
        loginbtn= (Button) findViewById(R.id.loginbutton);
        openaccountbtn= (TextView) findViewById(R.id.openaccount);
        openaccountbtn.setOnClickListener(this);
        Firebase.setAndroidContext(Login_Activity.this);
        String sss=getIntent().getStringExtra("namee");
        String ssss=getIntent().getStringExtra("pass");
        pass.setText(ssss);
        name.setText(sss);
        // name.setText(names);
        //name.setText(passwords);
        newFirebase=new Firebase("https://myschoolserver-dcae9.firebaseio.com/");
        login=new Data_model();
        newFirebase.child("useraccounttable").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                loginData.clear();
                for (DataSnapshot sh:dataSnapshot.getChildren())
                {
                    login=sh.getValue(Data_model.class);
                    loginData.add(login);
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        loginbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        { case R.id.openaccount:
            Intent goo=new Intent(Login_Activity.this,Form.class);
            startActivity(goo);
            break;
            case R.id.loginbutton:
                if (haveNetworkConnection(Login_Activity.this)){

                    loginname=name.getText().toString();
                    loginpass=pass.getText().toString();

                    for (int i=0;i<loginData.size();i++)
                    {
                        if(loginData.get(i).getName().equals(loginname)&&loginData.get(i).getPassword().equals(loginpass))
                        {
                            isaccount=true;
                            SharedPreferences shdatamain = getSharedPreferences("AccountData", MODE_PRIVATE);
                            SharedPreferences.Editor editor = shdatamain.edit();
                            editor.putString("profile",loginData.get(i).getImage());
                            editor.putString("name",loginData.get(i).getName());
                            editor.putString("key",loginData.get(i).getKey());
                            editor.commit();
                            break;}
                        else
                        {isaccount=false;}
                    }
                    if(isaccount)
                    {
                        Intent go=new Intent(Login_Activity.this,FragmentMainActivity.class);
                        startActivity(go);
                        finish();
                    }
                    else
                    {
                        if (loginname.equals("Doctor")&&loginpass.equals("Doctor"))
                        { Intent gooo=new Intent(Login_Activity.this,Doctor_Activity.class);
                            startActivity(gooo);
                            finish();
                        }
                        else
                            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Snackbar.make(view,"အင္တာနက္ဖြင့္ရန္လိုအပ္ပါသည္", Snackbar.LENGTH_SHORT).setAction("Action",null).show();
                }

                break;
        }
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
}

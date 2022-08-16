package com.example.dell.babycare.Activities.developmentActivities.adapters;

import com.example.dell.babycare.Activities.developmentActivities.activities.LocationDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataProvider extends LocationDetails {
    public static HashMap<String,List<String>> getInfo()
    {
        //LayoutInflater
        HashMap<String,List<String>>HeaderDetails=new HashMap<String,List<String>>();
        List<String>ChildDetails1=new ArrayList<String>();
        ChildDetails1.add("ေဒါက္တာ ေမာင္ေမာင္ႀကီးေဆးခန္း");
        ChildDetails1.add("ကုတင္ ၅၅၀ ကေလးအထူးကုေဆးရံုႀကီး");
        ChildDetails1.add("ျမတ္သုခေဆးရံု ");
        ChildDetails1.add("ခ်မ္းၿငိမ္းေအာင္");
        ChildDetails1.add("ေအာင္ေဆးခန္း");
        ChildDetails1.add("ေအးသုခ");
        ChildDetails1.add("မႏၱေလးေဆးရံုႀကီး");


        List<String>ChildDetails2=new ArrayList<String>();
        ChildDetails2.add("ရန္ကုန္ကေလးေဆးရံုႀကီး");
        ChildDetails2.add("ရန္ကင္းကေလးေဆးရံု");
        ChildDetails2.add("၀ိတိုရိယေဆးရံုႀကီး");
        ChildDetails2.add("ပါရမီအေထြေထြေရာဂါကုေဆးရံု");
        ChildDetails2.add("ေအးေမတၱာကေလးေဆးရံု");




        List<String>ChildDetails3=new ArrayList<String>();
        ChildDetails3.add("ေဒါက္တာ ခင္ျမင္႔သန္း");

        List<String>ChildDetails4=new ArrayList<String>();
        ChildDetails4.add("တပ္မေတာ္၊ သားဖြား၊ မီးယပ္နွင္႔ ကေလးေဆးရံု");
        ChildDetails4.add("ကေလးေရာဂါ အထူးကုေဆးရံုႀကီး");
        ChildDetails4.add("ေဘာဂသိဒၶိေဆးရံု");

        List<String>ChildDetails5=new ArrayList<String>();
        ChildDetails5.add("မေကြးတိုင္းေဒသႀကီး ျပည္႕သူ႕ေဆးရံု");
        ChildDetails5.add("မိခင္နွင္႔ ကေလးက်န္းမာေရး ေဆးခန္း");

        List<String>ChildDetails6=new ArrayList<String>();
        ChildDetails6.add("မိခင္နွွင္႔ကေလးေဆးရုံ");


        List<String>ChildDetails7=new ArrayList<String>();
        ChildDetails7.add("ခ်မ္းသာသုခ ေဆးရံု");





        HeaderDetails.put("မႏၱေလး",ChildDetails1);
        HeaderDetails.put("ရန္ကုန္",ChildDetails2);
        HeaderDetails.put("မိတၳီလာ",ChildDetails3);
        HeaderDetails.put("ေနျပည္ေတာ္",ChildDetails4);
        HeaderDetails.put( "ျပင္ဦးလြင္",ChildDetails7);
        HeaderDetails.put("ေတာင္ႀကီး",ChildDetails6);
        HeaderDetails.put("မေကြး",ChildDetails5);

        return HeaderDetails;


    }

}
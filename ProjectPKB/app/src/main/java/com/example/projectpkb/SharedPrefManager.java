package com.example.projectpkb;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static SharedPrefManager instance;
    private static Context ctx;

    private static final String SHARED_PREF_NAME = "mysharedpref12";
    private static final String Kscode = "servicecode";
    private static final String Kname = "name";
    private static final String Kemail = "email";
    private static final String Kproduct = "product";
    private static final String Ksdate = "servicedate";
    private static final String Ksinquiry = "serviceinquiry";
    private static final String Kstatus = "status";

    private SharedPrefManager(Context context){
        ctx = context;
    }

    public static synchronized  SharedPrefManager getInstance(Context context){
        if(instance == null)
            instance = new SharedPrefManager(context);
        return instance;
    }

    public boolean customerLogin(String scode, String name, String email, String product, String sdate, String sinquiry, String status){
        SharedPreferences sharedPreferences =ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(Kscode, scode);
        editor.putString(Kname, name);
        editor.putString(Kemail, email);
        editor.putString(Kproduct, product);
        editor.putString(Ksdate, sdate);
        editor.putString(Ksinquiry, sinquiry);
        editor.putString(Kstatus, status);
        editor.apply();

        return true;
    }

    public String getScode(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(Kscode, null);
    }

    public String getSName(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(Kname, null);
    }

    public String getSemail(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(Kemail, null);
    }

    public String getSproduct(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(Kproduct, null);
    }

    public String getSdate(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(Ksdate, null);
    }

    public String getSinquiry(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(Ksinquiry, null);
    }

    public String getSstatus(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(Kstatus, null);
    }
}

package com.example.projectpkb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "ProjectPKB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE services (servicecode TEXT primary key, name TEXT, email TEXT, phone TEXT, product TEXT, serialnumber TEXT, servicedate TEXT, problem TEXT, status TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE if exists services");
    }

    public Boolean CreateService(String scode, String name, String email, String phone, String product, String snumber, String sdate, String problem, String status){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("servicecode", scode);
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("product", product);
        contentValues.put("serialnumber", snumber);
        contentValues.put("servicedate", sdate);
        contentValues.put("problem", problem);
        contentValues.put("status", status);

        long result = DB.insert("services", null, contentValues);

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean UpdateService(String scode, String name, String email, String phone, String product, String snumber, String sdate, String problem, String status){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("product", product);
        contentValues.put("serialnumber", snumber);
        contentValues.put("servicedate", sdate);
        contentValues.put("problem", problem);
        contentValues.put("status", status);

        Cursor cursor =DB.rawQuery("SELECT * FROM services WHERE servicecode = ?", new String[]{scode});
        if(cursor.getCount() > 0){
            long result = DB.update("services", contentValues, "servicecode = ?", new String[]{scode});
            if(result == -1){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return  false;
        }
    }

    public Boolean getServiceCode(String scode){
        SQLiteDatabase DB = getWritableDatabase();

        Cursor cursor = DB.rawQuery("SELECT * FROM services WHERE servicecode = ?", new String[] {scode});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Cursor getByServiceCode(String scode){
        SQLiteDatabase DB =this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM services WHERE servicecode = ?", new String[] {scode});
        return cursor;
    }

    public Cursor getData(){
        SQLiteDatabase DB =this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM services", null);
        return cursor;
    }

}

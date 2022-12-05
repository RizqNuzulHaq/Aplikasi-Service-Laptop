package com.example.projectpkb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    ArrayList<String> scode, name, sdate, status;
    DBHelper DB;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionBar)));

        DB = new DBHelper(this);
        scode = new ArrayList<>();
        name = new ArrayList<>();
        sdate = new ArrayList<>();
        status = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this, scode, name, sdate, status);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admincontrol, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.createservice:
                finish();
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.editservice:
                finish();
                startActivity(new Intent(this, EditActivity.class));
                break;
            case R.id.logout:
                finish();
                startActivity(new Intent(this, AdminLogin.class));
                break;
        }
        return true;
    }

    private void displayData() {
        Cursor cursor = DB.getData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Service Data Exist", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            while (cursor.moveToNext()){
                scode.add(cursor.getString(0));
                name.add(cursor.getString(1));
                sdate.add(cursor.getString(6));
                status.add(cursor.getString(8));
            }
        }
    }
}
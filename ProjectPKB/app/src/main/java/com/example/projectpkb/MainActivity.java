package com.example.projectpkb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText scode;
    private Button search;
    private ProgressDialog progressDialog;
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionBar)));

        scode = (EditText) findViewById(R.id.editTextScode);
        search = (Button) findViewById(R.id.buttonSearch);
        search.setOnClickListener(this);

        DB = new DBHelper(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
    }

    private void searchService(){
        String Kscode = "", Kname = "", Kemail= "", Kproduct = "", Ksdate = "", Ksinquiry = "", Kstatus = "";
        String scode = this.scode.getText().toString().trim();
        Cursor cursor = DB.getByServiceCode(scode);
        progressDialog.show();

        if(scode.equals("")){
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, "Please enter your Service Code", Toast.LENGTH_SHORT).show();
        }
        else{
            progressDialog.dismiss();
            Boolean checkservicecode = DB.getServiceCode(scode);
            while(cursor.moveToNext()){
                Kscode = cursor.getString(0);
                Kname = cursor.getString(1);
                Kemail = cursor.getString(2);
                Kproduct = cursor.getString(4);
                Ksdate = cursor.getString(6);
                Ksinquiry = cursor.getString(7);
                Kstatus = cursor.getString(8);
            }
            if(checkservicecode == true){
                SharedPrefManager.getInstance(getApplicationContext())
                        .customerLogin(Kscode, Kname, Kemail, Kproduct, Ksdate, Ksinquiry, Kstatus);
                Toast.makeText(MainActivity.this, "Service Data Found", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, CustomerActivity.class));
            }
            else{
                Toast.makeText(MainActivity.this, "No Service Data Found", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.loginadmin:
                finish();
                startActivity(new Intent(this, AdminLogin.class));
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        if(view == this.search)
            searchService();
    }
}
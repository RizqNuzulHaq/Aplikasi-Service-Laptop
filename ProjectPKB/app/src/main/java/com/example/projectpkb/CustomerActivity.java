package com.example.projectpkb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class CustomerActivity extends AppCompatActivity {

    private DBHelper DB;
    private TextView scode, name, email, product, sdate, sinquiry, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionBar)));

        DB = new DBHelper(this);

        scode = (TextView) findViewById(R.id.textViewScode);
        name = (TextView) findViewById(R.id.textViewName);
        email = (TextView) findViewById(R.id.textViewEmail);
        product = (TextView) findViewById(R.id.textViewProduct);
        sdate = (TextView) findViewById(R.id.textViewSdate);
        sinquiry = (TextView) findViewById(R.id.textViewSinquiry);
        status = (TextView) findViewById(R.id.textViewStatus);

        String cont = SharedPrefManager.getInstance(this).getScode();

        Cursor res = this.DB.getByServiceCode(cont);
        while(res.moveToNext()){
            scode.setText(SharedPrefManager.getInstance(this).getScode());
            name.setText(SharedPrefManager.getInstance(this).getSName());
            email.setText(SharedPrefManager.getInstance(this).getSemail());
            product.setText(SharedPrefManager.getInstance(this).getSproduct());
            sdate.setText(SharedPrefManager.getInstance(this).getSdate());
            sinquiry.setText(SharedPrefManager.getInstance(this).getSinquiry());
            status.setText(SharedPrefManager.getInstance(this).getSstatus());
        }
    }
}
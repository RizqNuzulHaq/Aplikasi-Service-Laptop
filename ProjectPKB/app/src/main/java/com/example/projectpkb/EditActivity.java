package com.example.projectpkb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText scode, name, email, phone, product, snumber, sinquiry, status;
    private TextView sdate;
    private Button buttonsave, buttonsearch, buttonBack;
    private ProgressDialog progressDialog;
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionBar)));

        scode = (EditText) findViewById(R.id.editTextScode);
        name = (EditText) findViewById(R.id.editTextName);
        email = (EditText) findViewById(R.id.editTextEmail);
        phone = (EditText) findViewById(R.id.editTextPhone);
        product = (EditText) findViewById(R.id.editTextProduct);
        snumber = (EditText) findViewById(R.id.editTextSnumber);
        sdate = (TextView) findViewById(R.id.editTextSdate);
        sinquiry = (EditText) findViewById(R.id.editTextSinquiry);
        status = (EditText) findViewById(R.id.editTextStatus);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");

        buttonsave = (Button) findViewById(R.id.buttonSave);
        buttonsearch = (Button) findViewById(R.id.buttonSearch);
        buttonBack = (Button) findViewById(R.id.buttonBack);

        buttonsave.setOnClickListener(this);
        buttonsearch.setOnClickListener(this);
        buttonBack.setOnClickListener(this);
        sdate.setOnClickListener(this);

        DB = new DBHelper(this);
    }

    private void searchService(){
        String scode = this.scode.getText().toString().trim();
        progressDialog.show();
        Boolean checkservicecode = DB.getServiceCode(scode);

        if(scode.equals("")){
            progressDialog.dismiss();
            Toast.makeText(EditActivity.this, "Please Fill the Service Code filed", Toast.LENGTH_SHORT).show();
        }
        else{
            progressDialog.dismiss();
            if(checkservicecode == true){
                Toast.makeText(EditActivity.this, "Found Service data", Toast.LENGTH_SHORT).show();
                showData();
            }
            else{
                Toast.makeText(EditActivity.this, "Wrong service code", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showData(){
        String scode = this.scode.getText().toString().trim();
        Cursor showdata = DB.getByServiceCode(scode);
        while(showdata.moveToNext()){
            this.name.setText(showdata.getString(1));
            this.email.setText(showdata.getString(2));
            this.phone.setText(showdata.getString(3));
            this.product.setText(showdata.getString(4));
            this.snumber.setText(showdata.getString(5));
            this.sdate.setText(showdata.getString(6));
            this.sinquiry.setText(showdata.getString(7));
            this.status.setText(showdata.getString(8));
        }
    }

    private void editService(){
        progressDialog.show();

        String scode = this.scode.getText().toString().trim();
        String name = this.name.getText().toString().trim();
        String email = this.email.getText().toString().trim();
        String phone = this.phone.getText().toString().trim();
        String product = this.product.getText().toString().trim();
        String snumber = this.snumber.getText().toString().trim();
        String sdate = this.sdate.getText().toString().trim();
        String sinquiry = this.sinquiry.getText().toString().trim();
        String status = this.status.getText().toString().trim();

        Boolean checkUpdateService = DB.UpdateService(scode, name, email, phone, product, snumber, sdate, sinquiry, status);
        if(checkUpdateService == true){
            progressDialog.dismiss();
            Toast.makeText(EditActivity.this, "Service Succesfully Updated", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(this, AdminActivity.class));
        }
        else{
            progressDialog.dismiss();
            Toast.makeText(EditActivity.this, "Failed to Update Service Data", Toast.LENGTH_SHORT).show();
        }

    }

    private void setDate(){
        Calendar calendar = Calendar.getInstance();

        int MONTH = calendar.get(Calendar.MONTH);
        int YEAR = calendar.get(Calendar.YEAR);
        int DAY = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(EditActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
                sdate.setText(i2+"/"+ i1 + "/" + i);
            }
        }, YEAR , MONTH, DAY);

        datePickerDialog.show();
    }

    @Override
    public void onClick(View view) {
        if(view == buttonsearch)
            searchService();

        if(view == buttonsave)
            editService();

        if(view == this.sdate)
            setDate();

        if(view == this.buttonBack){
            finish();
            startActivity(new Intent(this, AdminActivity.class));
        }
    }
}
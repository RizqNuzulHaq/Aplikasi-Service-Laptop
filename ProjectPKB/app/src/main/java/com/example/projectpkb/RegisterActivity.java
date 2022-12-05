package com.example.projectpkb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText scode, name, email, phone, product, snumber, sinquiry, status;
    TextView sdate;
    private Button buttonadd, buttonBack;
    private ProgressDialog progressDialog;
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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
        progressDialog.setMessage("Registering Service");

        buttonadd = (Button) findViewById(R.id.buttonAdd);
        buttonBack = (Button) findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(this);
        buttonadd.setOnClickListener(this);
        sdate.setOnClickListener(this);

        DB = new DBHelper(this);
    }

    private void registerService(){
        String scode = this.scode.getText().toString().trim();
        String name = this.name.getText().toString().trim();
        String email = this.email.getText().toString().trim();
        String phone = this.phone.getText().toString().trim();
        String product = this.product.getText().toString().trim();
        String snumber = this.snumber.getText().toString().trim();
        String sdate = this.sdate.getText().toString().trim();
        String sinquiry = this.sinquiry.getText().toString().trim();
        String status = this.status.getText().toString().trim();

        progressDialog.show();

        if(scode.equals("") && name.equals("") && email.equals("") && phone.equals("") && product.equals("") && snumber.equals("") && sdate.equals("") && sinquiry.equals("") && status.equals("")){
            progressDialog.dismiss();
            Toast.makeText(RegisterActivity.this, "Please Fill All Required fileds", Toast.LENGTH_SHORT).show();
        }
        else{
            Boolean checkCreateService = DB.CreateService(scode, name, email, phone, product, snumber, sdate, sinquiry, status);
            if(checkCreateService == true){
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, "Service Succesfully Created", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(this, AdminActivity.class));
            }
            else{
                Toast.makeText(RegisterActivity.this, "Service Already Exists, please input a different Service Code", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setDate(){
        Calendar calendar = Calendar.getInstance();

        int MONTH = calendar.get(Calendar.MONTH);
        int YEAR = calendar.get(Calendar.YEAR);
        int DAY = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        if(view == this.buttonadd)
            registerService();

        if(view == this.sdate)
            setDate();

        if(view == this.buttonBack){
            finish();
            startActivity(new Intent(this, AdminActivity.class));
        }
    }
}
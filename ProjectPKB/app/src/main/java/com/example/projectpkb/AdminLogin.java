package com.example.projectpkb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity implements View.OnClickListener{

    EditText editTextUsername, editTextpassword;
    ProgressDialog progressDialog;
    Button login, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionBar)));

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextpassword = findViewById(R.id.editTextPassword);
        login = (Button) findViewById(R.id.buttonLogin);
        back = (Button) findViewById(R.id.buttonBack);

        back.setOnClickListener(this);
        login.setOnClickListener(this);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait..");
    }

    public void loginAdmin(){
        progressDialog.show();
        String username = "admin";
        String password = "admin";

        String contUname = editTextUsername.getText().toString().trim();
        String contPw = editTextpassword.getText().toString().trim();

        if(contUname.equals(username) && contPw.equalsIgnoreCase(password)){
            progressDialog.dismiss();
            Toast.makeText(this, "Login Succesfully", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(this, AdminActivity.class));
        }
        else{
            progressDialog.dismiss();
            Toast.makeText(this, "Invalid password or username", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        if(view == login)
            loginAdmin();

        if(view == back) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
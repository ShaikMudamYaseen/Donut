package com.example.gist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText first,last,roll1,phone1,email4,password4,cpassword4;
    private FirebaseAuth Auth;
    ProgressDialog progressDialog;
    private static  int splash=900;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        first=findViewById(R.id.first);
        last=findViewById(R.id.last);
        roll1=findViewById(R.id.roll);
        phone1=findViewById(R.id.phone);
        email4=findViewById(R.id.emailr);
        password4=findViewById(R.id.passr);
        cpassword4=findViewById(R.id.cpassr);
        Auth=FirebaseAuth.getInstance();

    }

    public void signupr(View view) {
        String firstname=first.getText().toString().trim();
        String lastname=last.getText().toString().trim();
        String roll=roll1.getText().toString().trim();
        String phone=phone1.getText().toString().trim();
        String email=email4.getText().toString().trim();
        String password=password4.getText().toString().trim();
        String cpassword=cpassword4.getText().toString().trim();
        if(TextUtils.isEmpty(firstname)||TextUtils.isEmpty(lastname)||TextUtils.isEmpty(roll)||TextUtils.isEmpty(phone)||TextUtils.isEmpty(password)||TextUtils.isEmpty(cpassword))
        {
            Toast.makeText(this, "fill the details", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.equals(cpassword)) {
            progressDialog = new ProgressDialog(Register.this);
            progressDialog.show();
            progressDialog.setContentView(R.layout.progress_bar);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            Auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        Toast.makeText(Register.this, "successfully Register", Toast.LENGTH_SHORT).show();
                        finish();

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(Register.this, "Email already registered", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            });
        }
        else{
            Toast.makeText(this, "Password doesn''t match", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    public void loginr(View view) {
        progressDialog = new ProgressDialog(Register.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_bar);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                startActivity(new Intent(Register.this, MainActivity.class));
                return;


            }
        }, splash);

    }
}
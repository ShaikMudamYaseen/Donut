package com.example.gist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot extends AppCompatActivity {
    EditText email4;
    FirebaseAuth Auth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        email4=findViewById(R.id.Email);
        Auth=FirebaseAuth.getInstance();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void fforgot(View view) {
        String email=email4.getText().toString().trim();
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Enter a Email", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog=new ProgressDialog(Forgot.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_bar);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                progressDialog.dismiss();
                Toast.makeText(Forgot.this, "Reset link send", Toast.LENGTH_SHORT).show();
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(Forgot.this, "Enter correct Email", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}
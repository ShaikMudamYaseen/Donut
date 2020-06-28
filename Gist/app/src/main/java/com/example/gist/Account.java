package com.example.gist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Account extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    TextView display_name,display_roll,display_email,display_phone;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    private static  int splash=500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        display_name=findViewById(R.id.display_name);
        display_roll=findViewById(R.id.display_roll);
        display_email=findViewById(R.id.display_email);
        display_phone=findViewById(R.id.display_phone);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
       databaseReference=firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid());
        progressDialog = new ProgressDialog(Account.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_bar);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Pojo pojo=snapshot.getValue(Pojo.class);

                display_name.setText(pojo.getFirstname()+" "+pojo.getLastname());
                display_roll.setText(pojo.getRoll());
                display_email.setText(pojo.getEmail());
                display_phone.setText(pojo.getPhone());
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();

                    }
                }, splash);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Account.this,error.getCode(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void Edit(View view) {
        startActivity(new Intent(Account.this,Update.class));
    }
}
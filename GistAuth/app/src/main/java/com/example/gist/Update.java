package com.example.gist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Update extends AppCompatActivity {
    EditText update_first,update_last,update_roll,update_phone;
    TextView update_email;
    FirebaseAuth firebaseAuth;
    DatabaseReference reference;
    FirebaseDatabase firebaseDatabase;
    ProgressDialog progressDialog;
    private static int splash=500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        update_first=findViewById(R.id.update_first);
        update_last=findViewById(R.id.update_last);
        update_roll=findViewById(R.id.update_roll);
        update_phone=findViewById(R.id.update_phone);
        update_email=findViewById(R.id.update_email);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Pojo pojo=snapshot.getValue(Pojo.class);

                update_first.setText(pojo.getFirstname());
                update_last.setText(pojo.getLastname());
                update_roll.setText(pojo.getRoll());
                update_phone.setText(pojo.getPhone());
                update_email.setText(pojo.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Update.this,error.getCode(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void update(View view) {

         String firstname=update_first.getText().toString();
        String lastname=update_last.getText().toString();
        String roll=update_roll.getText().toString().trim();
        String phone=update_phone.getText().toString().trim();
        String email=update_email.getText().toString().trim();
        Pojo pojo=new Pojo(firstname,lastname,roll,phone,email);
        reference.setValue(pojo);
        progressDialog = new ProgressDialog(Update.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_bar);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                finish();
            }
        }, splash);

    }
}
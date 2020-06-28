package com.example.gist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Signout extends AppCompatActivity {
    FirebaseAuth Auth;
    ProgressDialog progressDialog;
    private  static int splash=500;
    NavigationView Nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    androidx.appcompat.widget.Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signout);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Nav=findViewById(R.id.nav_view);
        drawerLayout=findViewById(R.id.drawer_layout);
        Auth=FirebaseAuth.getInstance();
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        View headerView=Nav.getHeaderView(0);
        Auth=FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
       final DatabaseReference reference=firebaseDatabase.getReference().child("Users").child(Auth.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TextView header_name=findViewById(R.id.header_name);
                Pojo pojo=snapshot.getValue(Pojo.class);

                header_name.setText(pojo.getFirstname()+" "+pojo.getLastname());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Signout.this,error.getCode(), Toast.LENGTH_SHORT).show();

            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new fragment_home()).commit();
        Nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new fragment_home()).commit();
                        break;

                    case R.id.nav_account:
                        startActivity(new Intent(Signout.this,Account.class));
                        break;
                    case R.id.nav_civil:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new fragment_Civil()).commit();
                        break;
                    case R.id.nav_cse:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new fragment_Cse()).commit();
                        break;
                    case R.id.nav_ece:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new fragment_Ece()).commit();
                        break;
                    case R.id.nav_eee:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new fragment_Eee()).commit();
                        break;
                    case R.id.nav_mech:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new fragment_Mech()).commit();
                        break;
                    case R.id.nav_sandh:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new fragment_Sandh()).commit();
                        break;
                    case R.id.nav_placements:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new fragment_Placements()).commit();
                        break;
                    case R.id.nav_contactus:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new fragment_Contactus()).commit();
                        break;



                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.signout:
                AlertDialog.Builder alert = new AlertDialog.Builder(Signout.this);
                alert.setMessage("Do you want to Sign Out").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                progressDialog=new ProgressDialog(Signout.this);
                                progressDialog.show();
                                progressDialog.setContentView(R.layout.progress_bar);
                                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                                Auth.signOut();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressDialog.dismiss();
                                        startActivity(new Intent(Signout.this,MainActivity.class));
                                        finish();

                                    }
                                },splash);
                                Toast.makeText(Signout.this, "Signout Successful", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                alert.show();

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(Signout.this);
        alert.setMessage("Do you want to close the app").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        alert.show();
    }



}
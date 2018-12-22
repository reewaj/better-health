package com.reewaj.betterhealthdemo.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.github.clans.fab.FloatingActionMenu;
import com.reewaj.betterhealthdemo.R;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

//    DatabaseHandlerAppointment databaseHandlerAppointment;
    FloatingActionMenu floatingActionMenu;
    com.github.clans.fab.FloatingActionButton Add_Appointment;
    com.github.clans.fab.FloatingActionButton Add_Dose;
    com.github.clans.fab.FloatingActionButton Add_Medicine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        databaseHandlerAppointment = new DatabaseHandlerAppointment(this);
//        Userinfo info = databaseHandlerAppointment.getUser();
        floatingActionMenu = (FloatingActionMenu) findViewById(R.id.floating_action_menu);
        Add_Appointment = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.floating_add_appointment);
        Add_Dose = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.floating_add_doses);
        Add_Medicine = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.floating_add_medicine);

        Add_Appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  Toast.makeText(MainActivity.this, "Add Appointment",Toast.LENGTH_SHORT).show();

                Intent ii = new Intent(MainActivity.this, Appointment.class);
                startActivity(ii);
            }
        });

        Add_Dose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              // Toast.makeText(MainActivity.this, "Add Dose", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this,AddStock.class);
                startActivity(i);
            }
        });

        Add_Medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(MainActivity.this, "Add Medicine", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, AddMedicine.class);
                startActivity(intent);
            }
        });


        BottomNavigationView bottomNav = findViewById(R.id.navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            android.support.v4.app.Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.navigation_home:
                    selectedFragment = new HomeFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
                    break;
                case R.id.navigation_medicine:
                    selectedFragment = new MedicinesFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MedicineListFragment()).commit();

                    break;
                case R.id.navigation_more:
                    selectedFragment = new MoreFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();

                    break;
            }
            return true;
        }
    };

    @Override
    public void onBackPressed() {
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
        AlertDialog.Builder bd=new AlertDialog.Builder(MainActivity.this);
        bd.setTitle("Are you sure ?")
                //.setMessage("sure exit?")
                .setPositiveButton("ok",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int i){
                        MainActivity.super.onBackPressed();
                    }
                }).setNegativeButton("cancel",null);
        AlertDialog alert=bd.create();
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.user_name) {
            // Handle the camera action
        } else if (id == R.id.user_age) {

        } else if (id == R.id.edit_profile) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EditProfileFragment()).commit();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}

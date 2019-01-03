package com.example.adriana.pocketcloset;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import  android.widget.ArrayAdapter;
import android.widget.*;
import java.util.*;


 public class MainActivity extends AppCompatActivity {

     private LocationManager locationManager;
     private String provider;
     Spinner sTypes ;
     Spinner sLocations;
     GridView gridview = (GridView) findViewById(R.id.gridview);

     private String Location;

     private String Type;

     public static Integer[] picIds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final  DBHandler db = new DBHandler(getApplicationContext());
        sTypes = (Spinner) findViewById(R.id.type_spinner);
        sLocations = (Spinner) findViewById(R.id.location_spinner);

        // location
        String locationProvider = LocationManager.NETWORK_PROVIDER;
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED)

        {return;}
            Location location = locationManager.getLastKnownLocation(locationProvider);



            if (location != null) {
                //save location
            } else {

            }


            //save selected location and type

    sTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            Type = adapterView.getItemAtPosition(i).toString();
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
        return;
        }
    });

        sLocations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Location = adapterView.getItemAtPosition(i).toString();
                // change the gridview
              //  db.getItemsWithCriteria();
              //  gridview.DataBind();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        loadTypeSpinnerData();
        loadLocSpinnerData();


        gridview.setAdapter(new ImageAdapter(this));

         gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
            Intent myIntent = new Intent(MainActivity.this, ItemActivity.class);
                myIntent.putExtra("picID", picIds[position]);
                startActivity(myIntent);
           }
        });



    }





     private void loadTypeSpinnerData() {

        DBHandler db = new DBHandler(getApplicationContext());


         List<String> types = db.getAllTypes();

         // Creating adapter for spinner
         ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                 android.R.layout.simple_spinner_item, types);

         // Drop down layout style - list view with radio button
         dataAdapter
                 .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

         sTypes.setAdapter(dataAdapter);
     }


     private void loadLocSpinnerData() {
         // database handler
         DBHandler db = new DBHandler(getApplicationContext());

         // Spinner Drop down elements
         List<String> locations = db.getAllLocations();

         // Creating adapter for spinner
         ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                 android.R.layout.simple_spinner_item, locations);

         // Drop down layout style - list view with radio button
         dataAdapter
                 .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

         // attaching data adapter to spinner
         sLocations.setAdapter(dataAdapter);
     }


 }

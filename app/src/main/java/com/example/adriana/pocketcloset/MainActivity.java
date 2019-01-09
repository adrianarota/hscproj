package com.example.adriana.pocketcloset;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import  android.widget.ArrayAdapter;
import android.widget.*;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;


 public class MainActivity extends AppCompatActivity {

     private LocationManager locationManager;
     private String provider;
     Spinner sTypes ;
     Spinner sLocations;

     private String Location="All";

     private String Type="All";

     public static  Integer[] picIds= {
             R.drawable.p1, R.drawable.p4,
             R.drawable.p3, R.drawable.p5,
             R.drawable.p2, R.drawable.p6
     };
     private String m_Text = "";
     private FusedLocationProviderClient mFusedLocationClient;

     @Override
     protected void onResume (){
         super.onResume();
         final  DBHandler db = new DBHandler(getApplicationContext());



                 List<Integer> ids = db.getAllPicIDs();
               picIds = ids.toArray(new Integer[0]);

         final    GridView gridview = (GridView) findViewById(R.id.gridview);

         gridview.setAdapter(new ImageAdapter(this));

         gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             public void onItemClick(AdapterView<?> parent, View v,
                                     int position, long id) {
                 Intent myIntent = new Intent(MainActivity.this, ItemActivity.class);
                 myIntent.putExtra("picID", picIds[position]);
                 // myIntent.putExtra("picID", 1);

                 startActivity(myIntent);
             }
         });

     }
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      final  DBHandler db = new DBHandler(getApplicationContext());


       final    GridView gridview = (GridView) findViewById(R.id.gridview);
        Button newplace = (Button) findViewById(R.id.button8);

        Button newtype = (Button) findViewById(R.id.button3);


        Button newitem = (Button) findViewById(R.id.button4);

       sTypes = (Spinner) findViewById(R.id.type_spinner);
        sLocations = (Spinner) findViewById(R.id.location_spinner);

         List<Integer> ids = db.getAllPicIDs();
         picIds = ids.toArray(new Integer[0]);

         gridview.setAdapter(new ImageAdapter(this));

         gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             public void onItemClick(AdapterView<?> parent, View v,
                                     int position, long id) {
                 Intent myIntent = new Intent(MainActivity.this, ItemActivity.class);
                 myIntent.putExtra("picID", picIds[position]);
                 // myIntent.putExtra("picID", 1);

                 startActivity(myIntent);
             }
         });

         //save selected location and type

        sTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            /*
                String Location = sLocations.getSelectedItem().toString();
 String Type = sTypes.getSelectedItem().toString();
                List<Integer> ids = db.getAllPicIDsLocType(Location, Type);
                picIds = ids.toArray(new Integer[0]);
                gridview.setAdapter(new ImageAdapter(MainActivity.this));

                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v,
                                            int position, long id) {
                        Intent myIntent = new Intent(MainActivity.this, ItemActivity.class);
                        myIntent.putExtra("picID", picIds[position]);
                        // myIntent.putExtra("picID", 1);

                        startActivity(myIntent);
                    }
                });

*/}

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        sLocations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
     /*
                String Location = sLocations.getSelectedItem().toString();

                List<Integer> ids = db.getAllPicIDsLocType(Location, "");
                picIds = ids.toArray(new Integer[0]);
                gridview.setAdapter(new ImageAdapter(MainActivity.this));

                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v,
                                            int position, long id) {
                        Intent myIntent = new Intent(MainActivity.this, ItemActivity.class);
                        myIntent.putExtra("picID", picIds[position]);
                        // myIntent.putExtra("picID", 1);

                        startActivity(myIntent);
                    }
                });

*/
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        loadTypeSpinnerData();
        loadLocSpinnerData();
        // location
        /*
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

*/


        newitem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, ItemActivity.class);
                startActivity(myIntent);
            }
        });



        newplace.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              allertLoc();
            }
        });

        newtype.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                allertType();
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



     private  void allertType()
     {
         AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setTitle("Enter new type");

         final EditText input = new EditText(this);
         input.setInputType(InputType.TYPE_CLASS_TEXT);
         builder.setView(input);

         builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {

         m_Text = input.getText().toString();
         DBHandler db = new DBHandler(getApplicationContext());

                 // Spinner Drop down elements
                  db.insertType(m_Text);

                 Toast.makeText(MainActivity.this, "Item type added", Toast.LENGTH_LONG)
                        .show();
                 loadTypeSpinnerData();
             }
         });
         builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 dialog.cancel();
             }
         });

         builder.show();
     }


     private  void allertLoc()
     {
         AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setTitle("Enter new location");

         final EditText input = new EditText(this);
         input.setInputType(InputType.TYPE_CLASS_TEXT);
         builder.setView(input);

         builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {

                 m_Text = input.getText().toString();
                 //db.insertType(m_Text);

                 DBHandler db = new DBHandler(getApplicationContext());

                 // Spinner Drop down elements
                 db.insertLocation(m_Text);

                 Toast.makeText(MainActivity.this, "Location added", Toast.LENGTH_LONG)
                         .show();
                 loadLocSpinnerData();
             }
         });
         builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 dialog.cancel();
             }
         });

         builder.show();
     }


 }

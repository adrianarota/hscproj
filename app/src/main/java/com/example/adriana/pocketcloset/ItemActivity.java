package com.example.adriana.pocketcloset;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ItemActivity extends AppCompatActivity {

    private int PICK_IMAGE_REQUEST = 1;
    Spinner sTypes;
    Spinner sLocations;

    String CurrentType;
    String CurrentLoc;
String Type;
String Location;
String path;
String Desc;  public Uri currImageURI;

   int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        //button to select picture
        Button save = (Button) findViewById(R.id.button);
        Button camera = (Button) findViewById(R.id.button5);

        Button delete = (Button) findViewById(R.id.button2);

        ImageButton pick = (ImageButton) findViewById(R.id.imageButton2);

        sTypes = (Spinner) findViewById(R.id.spinner2);
    sLocations = (Spinner) findViewById(R.id.spinner);
       final EditText et =(EditText) findViewById(R.id.editText);


       Intent myIntent = getIntent();

        id = myIntent.getIntExtra("picID", 0);



        final  DBHandler db = new DBHandler(getApplicationContext());
        Item current = db.getItem(id);
      //  List<Integer> ids =db.getAllPicIDs();

        //Toast.makeText(ItemActivity.this, "Item     " + ids.get(2) + " xxxx "+  ids.get(3), Toast.LENGTH_LONG)
          //      .show();
       Location= current.Itemlocation;

       Desc = current.ItemDescription;
       Type = current.Itemtype;

       et.setText(Desc);
       loadTypeSpinnerData();
      //  if(sLocations!=null)
        loadLocSpinnerData();
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
          //  checkPermissions();
       // }
   //     Uri uri = Uri.parse(MainActivity.picIds[1]);
        ImageView iv = (ImageView) findViewById(R.id.imageView);
        iv.setImageResource(id);
      //  iv.setImageURI(uri);

        sTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
           //     Type = adapterView.getItemAtPosition(i).toString();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        sLocations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Location = adapterView.getItemAtPosition(i).toString();

            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });



        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(id!=0) {
                    DBHandler db = new DBHandler(getApplicationContext());
                    // path = currImageURI.toString();
                    String Location = sLocations.getSelectedItem().toString();

                    String Type = sTypes.getSelectedItem().toString();
                    Desc = et.getText().toString();

                    db.updateItem(id, Location, Type, Desc);

                    Toast.makeText(ItemActivity.this, "Item saved!", Toast.LENGTH_LONG)
                            .show();
                }
                else
                {   DBHandler db = new DBHandler(getApplicationContext());
                    String Location = sLocations.getSelectedItem().toString();

                    String Type = sTypes.getSelectedItem().toString();
                    Desc = et.getText().toString();
                    db.insertItem(R.drawable.p6, Type,Location, Desc);
                    Toast.makeText(ItemActivity.this, "Item saved!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // delete entry
              //  db.deleteItem(id);
                allertDelete();
            }
        });

        pick.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });


    }
    static final int REQUEST_IMAGE_CAPTURE = 2;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
      //  if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
       // }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView iv = (ImageView) findViewById(R.id.imageView);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                iv.setImageBitmap(imageBitmap);
            }
            if (requestCode == 1) {

                // currImageURI is the global variable I'm using to hold the content:// URI of the image
                currImageURI = data.getData();
        //        Uri uri = Uri.parse("content://com.android.providers.media.documents/document/image%3A1872");
                iv.setImageURI(currImageURI);
            }
        }
    }

    private void loadTypeSpinnerData() {
        // database handler
        DBHandler db = new DBHandler(getApplicationContext());

        // Spinner Drop down elements
        List<String> types = db.getAllTypes();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, types);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        sTypes.setAdapter(dataAdapter);

        //if(CurrentType!=null) {
        int spinnerPosition = dataAdapter.getPosition(Type);
            sTypes.setSelection(spinnerPosition);
        //}
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

        Item current = db.getItem(id);
        //  List<Integer> ids =db.getAllPicIDs();

        //Toast.makeText(ItemActivity.this, "Item     " + ids.get(2) + " xxxx "+  ids.get(3), Toast.LENGTH_LONG)
        //      .show();
//        Location= current.Itemlocation;

       // Desc = current.ItemDescription;
      //  Type = current.Itemtype;
//Location = "Timisoara";
        // attaching data adapter to spinner

        sLocations.setAdapter(dataAdapter);
        //if(Location!=null) {
//if(Location=="Timisoara"){
     //   if(Location.equals("Timisoara")) {
            int spinnerPosition = dataAdapter.getPosition(Location);

            sLocations.setSelection(spinnerPosition);
      //  }
       // else
         //   Toast.makeText(ItemActivity.this, Location + "fdsfdsfd", Toast.LENGTH_LONG)
          //.show();
        // }
        //}
    }

    private  void allertDelete()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete item?");

// Set up the input


// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                DBHandler db = new DBHandler(getApplicationContext());

                // Spinner Drop down elements
                db.deleteItem(id);

                Toast.makeText(ItemActivity.this, "Item deleted!", Toast.LENGTH_LONG)
                        .show();

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

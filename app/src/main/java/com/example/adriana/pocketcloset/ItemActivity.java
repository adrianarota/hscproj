package com.example.adriana.pocketcloset;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class ItemActivity extends AppCompatActivity {


    Spinner sTypes;
    Spinner sLocations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //button to select picture
        Button save = (Button) findViewById(R.id.button);

        Button delete = (Button) findViewById(R.id.button2);


        Spinner sTypes = (Spinner) findViewById(R.id.spinner);
        Spinner sLocations = (Spinner) findViewById(R.id.spinner2);
        EditText et =(EditText) findViewById(R.id.editText);


        setContentView(R.layout.activity_item);
        Intent myIntent = getIntent();
        int  id = myIntent.getIntExtra("picID", 0);
        final  DBHandler db = new DBHandler(getApplicationContext());

        List<Item> items =  db.getItemsWithCriteria("pictureID= 'id'");
        Item a;
        if(items!=null)
        {   a = items.get(0);
            if(a!=null)
            {  //populate info on the given item

            }
        }
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // update entry
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // delete entry

            }
        });

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

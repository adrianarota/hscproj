package com.example.adriana.pocketcloset;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import  android.widget.ArrayAdapter;
import android.widget.*;
import java.util.*;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("item1");
        spinnerArray.add("item2");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.type_array,android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.type_spinner);
        sItems.setAdapter(adapter);
        //Spinner spinner = (Spinner) findViewById(R.id.type_spinner);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.array.type_array, android.R.layout.simple_spinner_item);

       // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
     //   spinner.setAdapter(adapter);
    }
}

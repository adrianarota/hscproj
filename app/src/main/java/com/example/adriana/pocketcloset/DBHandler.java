package com.example.adriana.pocketcloset;
import java.util.ArrayList;
import java.util.List;

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
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.content.Context.MODE_PRIVATE;


public class DBHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "mydatabase";

    // Labels table name
    private static final String TABLE_ITEMS = "Items";
    private static final String TABLE_LOCATIONS = "Locations";
    private static final String TABLE_TYPES= "Types";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Items( pictureID VARCHAR, type VARCHAR, location VARCHAR, details VARCHAR);");

        db.execSQL("CREATE TABLE IF NOT EXISTS Locations(location VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS Types(type VARCHAR);");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}
    /**
     * Inserting new lable into lables table
     * */
    public void insertLocation(String loc){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        db.execSQL("INSERT INTO Locations VALUES('loc');");
        db.close(); // Closing database connection
    }

    public void insertType(String type){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        db.execSQL("INSERT INTO Types VALUES('type');");
        db.close(); // Closing database connection
    }


    public void insertItem(String type){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        db.execSQL("INSERT INTO ITEMS VALUES('');");
        db.close(); // Closing database connection
    }

    /**
     * Getting all labels
     * returns list of labels
     * */
    public List<String> getAllLocations(){
        List<String> locations = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_LOCATIONS;



        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                locations.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return locations;
    }

    public List<Item> getAllItems(){
        List<Item> items = new ArrayList<Item>();
        SQLiteDatabase db = this.getReadableDatabase();
        Item aux;
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ITEMS;



        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

           //     locations.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        return items;
    }

    public List<String> getAllTypes(){
        List<String> types = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TYPES;


        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                types.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return types;
    }

    public List<Item> getItemsWithCriteria(String criteria){
        List<Item> items = new ArrayList<Item>();
        SQLiteDatabase db = this.getReadableDatabase();
        Item aux;
        // Select All Query
        String selectQuery = "SELECT  * FROM Items WHERE " + criteria;


        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                //     locations.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        return items;
    }




}

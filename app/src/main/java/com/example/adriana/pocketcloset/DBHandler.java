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
    private static final String DATABASE_NAME = "BazaMeaDeDate";

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

        db.execSQL("CREATE TABLE IF NOT EXISTS Items( pictureID NUMBER, type VARCHAR, location VARCHAR, details VARCHAR);");

        db.execSQL("CREATE TABLE IF NOT EXISTS Locations(location VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS Types(type VARCHAR);");
        db.execSQL("INSERT INTO Items VALUES('"+R.drawable.p3+"', 'Tops', 'Timisoara', 'ZARA');");
        db.execSQL("INSERT INTO Items VALUES('"+R.drawable.p1+"', 'Dresses', 'Timisoara', 'HM');");
        db.execSQL("INSERT INTO Items VALUES('"+R.drawable.p2+"', 'Tops', 'Timisoara', '?');");
        db.execSQL("INSERT INTO Items VALUES('"+R.drawable.p4+"', 'Dresses', 'Timisoara', 'HM');");
        db.execSQL("INSERT INTO Items VALUES('"+R.drawable.p5+"', 'Tops', 'Timisoara', 'CA');");
     //   db.execSQL("INSERT INTO Items VALUES('"+R.drawable.p6+"', 'Dresses', 'Timisoara', 'HM');");

        db.execSQL("INSERT INTO Types VALUES('All');");
        db.execSQL("INSERT INTO Types VALUES('Dresses');");
        db.execSQL("INSERT INTO Types VALUES('Tops');");
        db.execSQL("INSERT INTO Locations VALUES('All');");
        db.execSQL("INSERT INTO Locations VALUES('Timisoara');");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE Locations");
        db.execSQL("DROP TABLE Types");
        onCreate(db);
    }
    /**
     * Inserting new lable into lables table
     * */
    public void insertLocation(String loc){
        SQLiteDatabase db = this.getWritableDatabase();

       // ContentValues values = new ContentValues();

        db.execSQL("INSERT INTO Locations VALUES('"+loc+"');");
        db.close(); // Closing database connection
    }

    public void insertType(String type){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT INTO Types VALUES('"+type+"');");
        db.close(); // Closing database connection
    }


    public void insertItem(int id, String type, String loc, String details){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT INTO ITEMS VALUES('"+id+"','"+type+"','"+loc+"','"+details+"');");
        db.close(); // Closing database connection
    }


    public List<String> getAllLocations(){
        List<String> locations = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_LOCATIONS;


        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                locations.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();
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
/*
    public List<String> getAllPicIDs(){
        List<String> ids = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        // Select All Query
        String selectQuery = "SELECT pictureID FROM " + TABLE_ITEMS;


        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ids.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return ids;
    }
    */

    public List<Integer> getAllPicIDs(){
        List<Integer> ids = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        // Select All Query
        String selectQuery = "SELECT pictureID FROM " + TABLE_ITEMS;


        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ids.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return ids;
    }



    public List<Integer> getAllPicIDsLocType(String Loc, String Type){
        List<Integer> ids = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_ITEMS;

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

             if(Type=="All"&&Loc=="All") {
                 if(cursor.getString(1)==Type&&cursor.getString(2)==Loc)
                     ids.add(cursor.getInt(0));
             }

                if(Type!="All"&&Loc=="All")
                {        if(cursor.getString(1)==Type)
                    ids.add(cursor.getInt(0));}
                if(Type=="All"&&Loc!="All")
              {         if(cursor.getString(2)==Loc)
                  ids.add(cursor.getInt(0));}
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return ids;
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

    public Item getItem(Integer ID){
        Item item;
        String Loc = null;
        String Type = null;
        String Details = null;

        SQLiteDatabase db = this.getReadableDatabase();
       // Item aux;
        // Select All Query
        String selectQuery = "SELECT  * FROM Items";


        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
if(cursor.getInt(0)==ID) {

    Loc = cursor.getString(2);

    Type = cursor.getString(1);

    Details = cursor.getString(3);
}
                //     locations.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();
item= new Item(ID, Type, Loc, Details);
        return item;
    }

    public void deleteItem(Integer ID){

        SQLiteDatabase db = this.getReadableDatabase();

        db.delete(TABLE_ITEMS, "pictureID"+ "=" + ID, null);

        db.close();

    }
    public void updateItem(Integer ID, String loc, String type, String det){
        ContentValues cv = new ContentValues();
        cv.put("location",loc);
        cv.put("type",type);
        cv.put("details",det);

        SQLiteDatabase db = this.getReadableDatabase();

        db.update(TABLE_ITEMS,cv,"pictureID"+ "=" + ID, null);

        db.close();

    }


}

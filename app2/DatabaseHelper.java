package com.example.connormonson.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mynewlist.db";
    public static final String TABLE_NAME = "mylist_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "ITEM1";
    public static final String COL3 = "ITEM2";
    //public static final String COL4 = "ITEM3";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INT PRIMARY KEY, " + " ITEM1 TEXT, " + " ITEM2 TEXT) ";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME); // may be an issue!!!
        onCreate(db);
    }

    public boolean addData(String item1, String item2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item1);
        contentValues.put(COL3, item2);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //returning all data from database
    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    //delete
    public Integer deleteName(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ITEM2 = ?", new String[] {name});

        //String query = "IF EXISTS " + "(SELECT * FROM " +  TABLE_NAME + " WHERE " + COL1 + " = '" + id + "'" +
               // " OR " + COL3 + " = '" + name + "')";
        //db.execSQL(query);

       // String query2 = "DELETE FROM " + TABLE_NAME + " WHERE "
       //         + COL3 + " = '" + name + "'";

        // db.execSQL(query2);
    }

    //delete
    public Integer deleteID(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, " ID = ?", new String[] {id});

        //String query = "IF EXISTS " + "(SELECT * FROM " +  TABLE_NAME + " WHERE " + COL1 + " = '" + id + "'" +
        // " OR " + COL3 + " = '" + name + "')";
        //db.execSQL(query);

       // String query2 = "DELETE FROM " + TABLE_NAME + " WHERE "
       //         + COL3 + " = '" + name + "'";

       // db.execSQL(query2);
    }

    //check if exists in data base
    public Cursor nameExists(String id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        //SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE "
                + COL3 + " = '" + name + "'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
        /*
        if (cursor.getCount() <= 0){
            //cursor.close();
            return false;
        }
        else{
            //cursor.close();
            return true;
        }
        */
    }

}



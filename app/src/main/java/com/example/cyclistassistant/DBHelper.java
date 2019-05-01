package com.example.cyclistassistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "bikeservices2.db";
    public static final String TABLE_NAME = "bike_services";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "DATE";
    public static final String COL_3 = "DISTANCE";
    public static final String COL_4 = "PROPULSION";
    public static final String COL_5 = "SUSPENSION";
    public static final String COL_6 = "BRAKES";
    public static final String COL_7 = "WHEELS";
    public static final String COL_8 = "OTHER";
    public static final String COL_9 = "COST";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
//        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, DISTANCE INTEGER, PROPULSION TEXT, SUSPENSION TEXT, BRAKES TEXT, WHEELS TEXT, OTHER TEXT, COST TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertdata(String date, String distance, String propulsion, String suspension, String brakes, String wheels, String other, String cost){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, date);
        contentValues.put(COL_3, distance);
        contentValues.put(COL_4, propulsion);
        contentValues.put(COL_5, suspension);
        contentValues.put(COL_6, brakes);
        contentValues.put(COL_7, wheels);
        contentValues.put(COL_8, other);
        contentValues.put(COL_9, cost);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public Integer deleteRowOfDB (String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }

    public boolean updateData(String id, String date, String distance, String propulsion, String suspension, String brakes, String wheels, String other, String cost){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, date);
        contentValues.put(COL_3, distance);
        contentValues.put(COL_4, propulsion);
        contentValues.put(COL_5, suspension);
        contentValues.put(COL_6, brakes);
        contentValues.put(COL_7, wheels);
        contentValues.put(COL_8, other);
        contentValues.put(COL_9, cost);
        db.update(TABLE_NAME, contentValues, "id = ?", new String[] {id});
        return true;
    }


}

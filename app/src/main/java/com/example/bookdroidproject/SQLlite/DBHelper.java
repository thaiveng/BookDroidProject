package com.example.bookdroidproject.SQLlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="logged_user";
    public static final String TABLE="user";

    public static final String Email="logged_user";
    public static final String Id="user";

    public DBHelper(Context context){
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE +
                "(id integer primary key autoincrement, id_user text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE);
        onCreate(db);
    }

    public void change(String id_user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        db.execSQL("DELETE FROM "+TABLE+";");

        values.put("id_user", id_user);

        db.insert(TABLE, null, values);
        db.close();
        Log.d("Insert Data::", "change account successful");
    }

    public String getUser(){
        String id_user = "";

        SQLiteDatabase db = getReadableDatabase();

        // first count if there is ip
        Cursor cursor  = db.rawQuery("select count(*) from " + TABLE, null);
        cursor.moveToFirst();
        int the_count = cursor.getInt(0);
        if(the_count == 0){
            id_user = "";
        }else{
            cursor  = db.rawQuery("select * from " + TABLE, null);
            cursor.moveToFirst();
            id_user = cursor.getString(1);
        }

        return id_user;
    }

    public void clear(){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        db.execSQL("DELETE FROM "+TABLE+";");
        db.close();
        Log.d("Delete Data::", "change account successful");
    }
}

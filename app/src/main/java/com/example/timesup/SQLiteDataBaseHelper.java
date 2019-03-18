package com.example.timesup;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDataBaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_MOTS = "table_mots";
    public static final String COL_ID = "ID";
    public static final String COL_MOT = "MOT";
    public static final String COL_FILTRE = "FILTRE";
    public static final String CREATE_BDD = "CREATE TABLE "+ TABLE_MOTS +" ("
            +COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_MOT + " TEXT NOT NULL, " + COL_FILTRE + " INT(1) NOT NULL);";

    public SQLiteDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}

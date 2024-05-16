package com.example.f_manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "farm_app.db";
    private static final int DATABASE_VERSION = 1;

    // Defining table creation SQL statements
    private static final String SQL_CREATE_CYCLE =
            "CREATE TABLE IF NOT EXISTS Cycles (" +
                    "Cycle_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "Cycle_title TEXT(255),"+
                    "Created_date DATE,"+
                    "User_id INTEGER,"+
                    "FOREIGN KEY(User_id) REFERENCES USER(User_id) ON DELETE CASCADE)";
    private static final String SQL_CREATE_FARM =
            "CREATE TABLE IF NOT EXISTS Farm (" +
                    "Farm_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Farm_name TEXT(255)," +
                    "Budget_allotment REAL,"+
                    "Created_date DATE,"+
                    "User_id INTEGER ,"+
                    "Cycle_id INTEGER,"+
                    "FOREIGN KEY(Cycle_id) REFERENCES CYCLES(Cycle_id) ON DELETE CASCADE,"+
                    "FOREIGN KEY(User_id) REFERENCES USER(User_id) ON DELETE CASCADE)";

    private static final String SQL_CREATE_USER=
            "CREATE TABLE IF NOT EXISTS USER (" +
                    "User_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Email TEXT(255)," +
                    "Role TEXT(255),"+
                    "Region TEXT(255),"+
                    "Phone_number INTEGER,"+
                    "Farm_id INTEGER,"+
                    "Name TEXT,"+
                    "FOREIGN KEY(Farm_id) REFERENCES Farm(Farm_id) ON DELETE CASCADE)";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Execute SQL to create all tables
        sqLiteDatabase.execSQL("PRAGMA foreign_keys = ON;");
        sqLiteDatabase.execSQL(SQL_CREATE_FARM);
        sqLiteDatabase.execSQL(SQL_CREATE_USER);
        sqLiteDatabase.execSQL(SQL_CREATE_CYCLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Handle database upgrades
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS my_table");
        onCreate(sqLiteDatabase);
    }
}

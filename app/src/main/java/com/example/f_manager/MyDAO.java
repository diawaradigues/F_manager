package com.example.f_manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MyDAO {

        private SQLiteDatabase database;
        private MyDatabaseHelper dbHelper;

        public MyDAO(Context context) {
            dbHelper = new MyDatabaseHelper(context);
        }

        public void open() {
            database = dbHelper.getWritableDatabase();
        }

        public void close() {
            dbHelper.close();
        }

        public long inserFarmtData(String farmName, String budgetAllotment, String createdDate) {
            //  SQLiteDatabase database = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Farm_name", farmName);
            values.put("Budget_allotment", budgetAllotment);
            values.put("Created_date", createdDate);

            return database.insert("Farm", null, values);
        }

        public long insertUserUserData( String email, String Role, String Region,
                                         int PhoneNumber, String Name) {
            //  SQLiteDatabase database = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Email", email);
            values.put("Role", Role);
            values.put("Region", Region);
            values.put("Phone_number", PhoneNumber);
            values.put("Name", Name);

            // Insert the values into the table
            return database.insert("USER", null, values);
        }
        public long insertFarmCycleData(String cycleTitle, String createdDate){
            ContentValues values = new ContentValues();
            values.put("Cycle_title",cycleTitle);
            values.put("Created_date", createdDate);
            return  database.insert("Cycles",null,values);
        }

    public List<MyData> getAllData() {
        List<MyData> dataList = new ArrayList<>();

        // Define the columns you want to retrieve
        String[] columns = {"id", "name", "age"};

        // Query the database
        Cursor cursor = database.query("my_table", columns, null, null, null, null, null);

        // Iterate through the cursor to retrieve data
        if (cursor != null && cursor.moveToFirst()) {
            do {
                /*
                Retrieve data from cursor
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                 String name = cursor.getString(cursor.getColumnIndex("name"));
               int age = cursor.getInt(cursor.getColumnIndex("age"));
                Create a MyData object and add it to the list
                MyData data = new MyData(id, name, age);
                dataList.add(data);
               */

            } while (cursor.moveToNext());

            // Close the cursor
            cursor.close();
        }

        return dataList;
    }





    // Add more methods as needed (update, delete, query specific data, etc.)
}



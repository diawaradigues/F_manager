package com.example.f_manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
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

        public long insertFarmData(String farmName, Integer budgetAllotment, Date createdDate) {
            //  SQLiteDatabase database = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Farm_name", farmName);
            values.put("Budget_allotment", budgetAllotment);
            values.put("Created_date", createdDate.getTime());

            return database.insert("Farm", null, values);
        }
    public long insertexpenditure(String expCategory,String fixedExpense, String expense,Float amount, Date createdDate) {
        //  SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Expense_Category", expCategory);
        values.put("fixed_expense",fixedExpense);
        values.put("Expenditure_", expense);
        values.put("Amount", amount);
        values.put("Created_date_", createdDate.getTime());

        return database.insert("Expenditure", null, values);
    }

    public long insertSales(String Customer,Integer Quantity, Float Unit_cost, Float Price, Date date) {
        //  SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("customer", Customer);
        values.put("quantity", Quantity);
        values.put("unit_cost", Unit_cost);
        values.put("price", Price);
        values.put("date_", date.getTime());

        return database.insert("Sales", null, values);
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

    public List<MyData> getCycleData() {

        List<MyData> dataList = new ArrayList<>();
        // Define the columns you want to retrieve
        String[] columns = {"Cycle_title", "Created_date"};
        // Query the database
        Cursor cursor = database.query("Cycles", columns, null, null, null, null, null);
        cursor.moveToFirst();
        // Iterate through the cursor to retrieve data
        while (!cursor.isAfterLast()) {
            String Cycle_tile = ""; // Initialize with an empty string or some default value
            String Created_date = ""; // Initialize with an empty string or some default value
            try {
                int cycleTitleIndex = cursor.getColumnIndexOrThrow("Cycle_title");
                int createdDateIndex = cursor.getColumnIndexOrThrow("Created_date");
                MyData data = new MyData(Cycle_tile, Created_date);
                data.setCycleTitle(cursor.getString(cycleTitleIndex));
                data.setCycleDate(cursor.getString(createdDateIndex));
                dataList.add(data);
                cursor.moveToNext();
            } catch (IllegalArgumentException e) {
                // Handle the exception (e.g., log the error, notify the user, etc.)
                e.printStackTrace();
            }
        }
        // Close the cursor
        cursor.close();
        return dataList;
    }
    /* public List<MyData> getFarmStatsData() {
        List<cardItem> dataList = new ArrayList<>();

        // Define the columns you want to retrieve
        String[] columns = {"Cycle_title", "Created_date"};

        // Query the database
        Cursor cursor = database.query("Cycles", columns, null, null, null, null, null);
        cursor.moveToFirst();
        // Iterate through the cursor to retrieve data
        while (!cursor.isAfterLast()) {
            String Cycle_tile = ""; // Initialize with an empty string or some default value
            String Created_date = ""; // Initialize with an empty string or some default value
            try {
                int cycleTitleIndex = cursor.getColumnIndexOrThrow("Cycle_title");
                int createdDateIndex = cursor.getColumnIndexOrThrow("Created_date");
                cardItem data = new cardItem(Cycle_tile, Created_date);
                data.setCycleTitle(cursor.getString(cycleTitleIndex));
                data.setCycleDate(cursor.getString(createdDateIndex));
                dataList.add(data);
                cursor.moveToNext();
            } catch (IllegalArgumentException e) {
                // Handle the exception (e.g., log the error, notify the user, etc.)
                e.printStackTrace();
            }
        }
        // Close the cursor
        cursor.close();
        return dataList;
    }*/





    // Add more methods as needed (update, delete, query specific data, etc.)
}



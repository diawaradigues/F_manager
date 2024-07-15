package com.example.f_manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyDAO {

    private static final String COL_AMOUNT = "directAssetsAmount";
    private static final String TABLE_NAME_DIRECT_E ="directAssets_Expenditure";
    private static final String COL_INDIR_ASSETS_AMOUNT = "indirectAssetsAmount";
    private static final String TABLE_NAME_INDIR ="indirectAssets_Expenditure";
    private static final String COL_PRICE = "Price";
    private static final String TABLE_NAME_S ="Sales";
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

    /////////////DB INSERT METHODS//////////////////////////////////////////////////////////////////
        public long insertFarmData(String farmName, Float budgetAllotment,String Farmslocation, String createdDate) {
            //  SQLiteDatabase database = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Farm_name", farmName);
            values.put("Budget_allotment", budgetAllotment);
            values.put("farms_Location",Farmslocation);
            values.put("Created_date", createdDate);
            return database.insert("Farm", null, values);
        }
    public long insertAssetExpenditure(String assetCategory,String assetDescript, Float assetDepreciation,Float amount, Date createdDate) {
        //  SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("catAssets", assetCategory);
        values.put("descriptionAsset",assetDescript);
        values.put("depreciationAsset", assetDepreciation);
        values.put("Amount", amount);
        values.put("dateCreated", createdDate.getTime());
        return database.insert("Assets_Expenditure", null, values);
    }
    public long insertDirectAssetExpenditure(String directAssetCategory,String directAssetDescript,Float amount, String createdDate) {
        //  SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("directCatAssets", directAssetCategory);
        values.put("descriptionDirectAsset",directAssetDescript);
        values.put("Amount", amount);
        values.put("dateCreated", createdDate);
        return database.insert("directAssets_Expenditure", null, values);
    }
    public long insertIndirectAssetExpenditure(String indirectAssetCategory,String indirectAssetDescript,Float amount, String createdDate) {
        //  SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("indirectCatAssets", indirectAssetCategory);
        values.put("indirectDescriptionAsset",indirectAssetDescript);
        values.put("Amount", amount);
        values.put("dateCreated", createdDate);
        return database.insert("Assets_Expenditure", null, values);
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
        public long insertUserUserData( String Name,String email, String Role, String Region,
                                         int PhoneNumber ) {
            //  SQLiteDatabase database = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Name", Name);
            values.put("Email", email);
            values.put("Role", Role);
            values.put("Region", Region);
            values.put("Phone_number", PhoneNumber);
            // Insert the values into the table
            return database.insert("USER", null, values);
        }
    ////////////////////////////////////////////////////////////////////////////////////////////////
        public long insertFarmCycleData(String cycleTitle, String createdDate,String cycleDate_ended){
            ContentValues values = new ContentValues();
            values.put("Cycle_title",cycleTitle);
            values.put("Created_date", createdDate);
            values.put("endedDateCycle",cycleDate_ended);
            values.put("status", "active");  // Set initial status to "active"
            long cycles = database.insert("Cycles", null, values);
             // Set the alarm for the cycle end date
            return cycles;
        }

    public List<MyData> getEndedCycles() {
        List<MyData> endedCycles = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT * FROM Cycles WHERE status = 'ended'";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String Cycle_title = ""; // Initialize with an empty string or some default value
                String Created_date = "";// Initialize with an empty string or some default value
                String endedCycleDate ="";
                String status = "";
                MyData cycle = new MyData(Cycle_title,Created_date,endedCycleDate,status);
                //cycle.setId(cursor.getInt(cursor.getColumnIndex("Cycle_id")));
                cycle.setCycleTitle(cursor.getString(cursor.getColumnIndexOrThrow("Cycle_title")));
                cycle.setCycleDate(cursor.getString(cursor.getColumnIndexOrThrow("Created_date")));
                cycle.setCycleEndDate(cursor.getString(cursor.getColumnIndexOrThrow("Ended_date")));
                cycle.setStatus(cursor.getString(cursor.getColumnIndexOrThrow("status")));
                endedCycles.add(cycle);
            } while (cursor.moveToNext());
        }
        cursor.close();
        //db.close();

        return endedCycles;
    }
    //mark cycles as ended
    public void markCycleAsEnded(long cycleId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status", "ended");  // Update status to "ended"

        db.update("Cycles", values, "Cycle_id = ?", new String[]{String.valueOf(cycleId)});
        db.close();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public long insertNonFinancialData(Double productYield, Double foodConvRatio,Double mortalityRate, Double averageDailyGain
            ,Double waterConsumption, Double breakEvenPoint ) {
        ContentValues values = new ContentValues();
        values.put("productionY", productYield);
        values.put("fcr", foodConvRatio);
        values.put("mortality_rate",mortalityRate);
        values.put("avd", averageDailyGain);
        values.put("water_consumption",waterConsumption);
        values.put("bep",breakEvenPoint);
        return database.insert("nonFinancialsValues", null, values);
    }
    ////////////GET DATA FROM DB METHODS////////////////////////////////////////////////////////////
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
            String Created_date = "";// Initialize with an empty string or some default value
            String endedCycleDate ="";
            String status = "";
            try {
                int cycleTitleIndex = cursor.getColumnIndexOrThrow("Cycle_title");
                int createdDateIndex = cursor.getColumnIndexOrThrow("Created_date");
                MyData data = new MyData(Cycle_tile, Created_date,endedCycleDate,status);
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
    public List<myNonFinancialData> getNonFinancialData() {
        List<myNonFinancialData> dataList = new ArrayList<>();
        // Define the columns you want to retrieve
        String[] columns = {"productionYield","foodConversionRatio","mortalityRate","averageDailyGain","waterConsumption","breakEvenPoint"};
        // Query the database
        Cursor cursor = database.query("nonFinancialsValues", columns, null, null, null, null, null);
        cursor.moveToFirst();
        // Iterate through the cursor to retrieve data
        while (!cursor.isAfterLast()) {
            Double productionYield = 0.0;
            Double foodConversionRatio = 0.0;
            Double mortalityRate = 0.0;
            Double averageDailyGain = 0.0;
            Double waterConsumption = 0.0;
            Double breakEvenPoint = 0.0;
            try {
                double productionYieldIndex = cursor.getColumnIndexOrThrow("productionYield");
                double foodConversionRatioIndex = cursor.getColumnIndexOrThrow("foodConversionRatio");
                double mortalityRateIndex = cursor.getColumnIndexOrThrow("mortalityRate");
                double averageDailyGainIndex = cursor.getColumnIndexOrThrow("averageDailyGain");
                double waterConsumptionIndex = cursor.getColumnIndexOrThrow("waterConsumption");
                double breakEvenPointIndex = cursor.getColumnIndexOrThrow("breakEvenPoint");
                /*int cycleTitleIndex = cursor.getColumnIndexOrThrow("Cycle_title");
                int createdDateIndex = cursor.getColumnIndexOrThrow("Created_date");*/
                myNonFinancialData data = new myNonFinancialData(productionYield,foodConversionRatio,mortalityRate,averageDailyGain,waterConsumption,breakEvenPoint);
                data.setProductionYield(cursor.getDouble((int) productionYieldIndex));
                data.setFoodConversionRatio(cursor.getDouble((int) foodConversionRatioIndex));
                data.setMortalityRate(cursor.getDouble((int) mortalityRateIndex));
                data.setAverageDailyGain(cursor.getDouble((int) averageDailyGainIndex));
                data.setWaterConsumption(cursor.getDouble((int) waterConsumptionIndex));
                data.setBreakEvenPoint(cursor.getDouble((int) breakEvenPointIndex));
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

    //total for exp and salary columns
    public float getDirectCost() {
       //open the db before querying for expenditure information
        Cursor cursor = database.rawQuery("SELECT SUM(" + COL_AMOUNT + ") as Total FROM " + TABLE_NAME_DIRECT_E, null);
        float total = 0;
        if (cursor.moveToFirst()) {
            total = cursor.getFloat(cursor.getColumnIndexOrThrow("Total"));
        }
        cursor.close();
        return total;
    }
    public float getTotalRevenue() {
        //open the db before querying for sales information
        Cursor cursor = database.rawQuery("SELECT SUM(" + COL_PRICE + ") as Total FROM " + TABLE_NAME_S, null);
        float total = 0;
        if (cursor.moveToFirst()) {
            total = cursor.getFloat(cursor.getColumnIndexOrThrow("Total"));
        }
        cursor.close();
        return total;
    }
    public float getTotalIndirectExp() {
        //open the db before querying for sales information
        Cursor cursor = database.rawQuery("SELECT SUM(" + COL_INDIR_ASSETS_AMOUNT + ") as Total FROM " + TABLE_NAME_INDIR, null);
        float total = 0;
        if (cursor.moveToFirst()) {
            total = cursor.getFloat(cursor.getColumnIndexOrThrow("Total"));
        }
        cursor.close();
        return total;
    }



}



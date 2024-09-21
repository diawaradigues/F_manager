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
                    "Created_date TEXT(255),"+
                    "Ended_date TEXT(255),"+
                    "status TEXT(50), " + ///added the stats column
                    "User_id INTEGER,"+
                    "FOREIGN KEY(User_id) REFERENCES USER(User_id) ON DELETE CASCADE)";
    private static final String SQL_CREATE_FINANCIALSTATS =
            "CREATE TABLE IF NOT EXISTS FinancialStatistics (" +
                    "FinancialStatistics_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "Total_Sales REAL,"+
                    "Gross_Profit REAL,"+
                    "Net_Profit REAL)";
    private static final String SQL_CREATE_FARM =
            "CREATE TABLE IF NOT EXISTS Farm (" +
                    "Farm_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Farm_name TEXT(255)," +
                    "Budget_allotment REAL,"+
                    "Farm_location TEXT(255)," +
                    "Created_date TEXT(255),"+
                    "User_id INTEGER ,"+
                    "Cycle_id INTEGER,"+
                    "FOREIGN KEY(Cycle_id) REFERENCES CYCLES(Cycle_id) ON DELETE CASCADE,"+
                    "FOREIGN KEY(User_id) REFERENCES USER(User_id) ON DELETE CASCADE)";

    private static final String SQL_CREATE_USER=
            "CREATE TABLE IF NOT EXISTS USER (" +
                    "User_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Name TEXT,"+
                    "Email TEXT(255)," +
                    "Role TEXT(255),"+
                    "Region TEXT(255),"+
                    "Phone_number INTEGER,"+
                    "Farm_id INTEGER,"+
                    "FOREIGN KEY(Farm_id) REFERENCES Farm(Farm_id) ON DELETE CASCADE)";

    private static final String SQL_CREATE_SALES=
            "CREATE TABLE IF NOT EXISTS Sales (" +
                    "sales_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Customer TEXT(255),"+
                    "Quantity_of_Birds INTEGER,"+
                    "Unit_cost REAL,"+
                    "Price REAL,"+
                    "Date DATE," +
                    "User_id INTEGER,"+
                    "Cycle_id INTEGER,"+
                    "FOREIGN KEY(Cycle_id) REFERENCES CYCLES(Cycle_id) ON DELETE CASCADE,"+
                    "FOREIGN KEY(User_id) REFERENCES USER(User_id) ON DELETE CASCADE)";

    private static final String SQL_CREATE_NON_FINANCIALS=
            "CREATE TABLE IF NOT EXISTS nonFinancialsValues (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "productionYield REAL," +
                    "foodConversionRatio REAL,"+
                    "mortalityRate REAL,"+
                    "averageDailyGain REAL,"+
                    "waterConsumption REAL,"+
                    "breakEvenPoint REAL)";
    private static final String SQL_CREATE_NON_FINANCIAL_METRICS=
            "CREATE TABLE IF NOT EXISTS nonFinancialsMetrics (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "NumberOfBroilers REAL,"+
                    "TimePeriod REAL,"+
                    "TotalFeedConsumed REAL,"+
                    "TotalWeightGain REAL,"+
                    "NumberOfBroilersDied REAL,"+
                    "TotalNumberOfBroilers REAL,"+
                    "NumberOfDays REAL,"+
                    "TotalWaterConsumed REAL,"+
                    "SellingPricePerUnit REAL,"+
                    "VariableCostPerUnit REAL)";

    private static final String SQL_CREATE_ASSETS_EXP=
            "CREATE TABLE IF NOT EXISTS Assets_Expenditure(" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "AssetsCategory TEXT(255)," +
                    "AssetsDescription TEXT(255),"+
                    "AssetDepreciation REAL,"+
                    "AssetsAmount REAL,"+
                    "AssetCreatedDate DATE,"+
                    "User_id INTEGER,"+
                    "Cycle_id INTEGER,"+
                    "Farm_id INTEGER,"+
                    "FOREIGN KEY(Farm_id) REFERENCES Farm(Farm_id) ON DELETE CASCADE,"+
                    "FOREIGN KEY(Cycle_id) REFERENCES CYCLES(Cycle_id) ON DELETE CASCADE,"+
                    "FOREIGN KEY(User_id) REFERENCES USER(User_id) ON DELETE CASCADE)";
    private static final String SQL_CREATE_DIRECT_ASSETS_EXP=
            "CREATE TABLE IF NOT EXISTS directAssets_Expenditure (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "directAssetsCategory TEXT(255)," +
                    "directAssetsDescription TEXT(255),"+
                    "directAssetsAmount REAL,"+
                    "directAssetCreatedDate DATE,"+
                    "User_id INTEGER,"+
                    "Cycle_id INTEGER,"+
                    "Farm_id INTEGER,"+
                    "FOREIGN KEY(Farm_id) REFERENCES Farm(Farm_id) ON DELETE CASCADE,"+
                    "FOREIGN KEY(Cycle_id) REFERENCES CYCLES(Cycle_id) ON DELETE CASCADE,"+
                    "FOREIGN KEY(User_id) REFERENCES USER(User_id) ON DELETE CASCADE)";
    private static final String SQL_CREATE_INDIRECT_ASSETS_EXP=
            "CREATE TABLE IF NOT EXISTS indirectAssets_Expenditure (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "indirectAssetsCategory TEXT(255)," +
                    "indirectAssetsDescription TEXT(255),"+
                    "indirectAssetsAmount REAL,"+
                    "indirectAssetCreatedDate DATE,"+
                    "User_id INTEGER,"+
                    "Cycle_id INTEGER,"+
                    "Farm_id INTEGER,"+
                    "FOREIGN KEY(Farm_id) REFERENCES Farm(Farm_id) ON DELETE CASCADE,"+
                    "FOREIGN KEY(Cycle_id) REFERENCES CYCLES(Cycle_id) ON DELETE CASCADE,"+
                    "FOREIGN KEY(User_id) REFERENCES USER(User_id) ON DELETE CASCADE)";
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Execute SQL to create all tables
        sqLiteDatabase.execSQL("PRAGMA foreign_keys = ON;");
        sqLiteDatabase.execSQL(SQL_CREATE_FARM);
        sqLiteDatabase.execSQL(SQL_CREATE_NON_FINANCIAL_METRICS);
        sqLiteDatabase.execSQL(SQL_CREATE_FINANCIALSTATS);
        sqLiteDatabase.execSQL(SQL_CREATE_USER);
        sqLiteDatabase.execSQL(SQL_CREATE_ASSETS_EXP);
        sqLiteDatabase.execSQL(SQL_CREATE_DIRECT_ASSETS_EXP);
        sqLiteDatabase.execSQL(SQL_CREATE_INDIRECT_ASSETS_EXP);
        sqLiteDatabase.execSQL(SQL_CREATE_CYCLE);
        sqLiteDatabase.execSQL(SQL_CREATE_NON_FINANCIALS);
        sqLiteDatabase.execSQL(SQL_CREATE_SALES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Handle database upgrades
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS my_table");
        onCreate(sqLiteDatabase);
    }

}

package com.example.f_manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyDAO {

    private static final String COL_AMOUNT = "directAssetsAmount";
    private static final String TABLE_NAME_DIRECT_E ="directAssets_Expenditure";
    private static final String COL_INDIR_ASSETS_AMOUNT = "indirectAssetsAmount";
    private static final String TABLE_NAME_INDIR ="indirectAssets_Expenditure";
    private static final String COL_PRICE = "Price";
    private static final String TABLE_NAME_S ="Sales";
    private SQLiteDatabase database;
    private MyDatabaseHelper dbHelper;
    private Double productYield;
    private Double foodConvRatio;
    private Double mortalityRate;
    private Double averageDailyGain;
    private Double waterConsumption;
    private Double breakEvenPoint;
    private String cycleTitle;
    private String createdDate;
    private String cycleDateEnded;
    private String cycleStatus;
    private String name;
    private Map<String, String> columnValues;
    private Float budgetAllotment;
    private String farmslocation;
    private String email;
    private String role;
    private String region;
    private int phoneNumber;
    private String customer;
    private Integer quantity;
    private Float unitCost;
    private Float price;
    private String date;
    private Float totalSales;
    private Float grossProfit;
    private Float netProfit;
    private String indirectAssetCategory;
    private String indirectAssetDescript;
    private Float amount;
    private String directAssetCategory;
    private String directAssetDescript;
    private String assetCategory;
    private String assetDescript;
    private Float assetDepreciation;
    private Double numberOfBroilers;
    private Double timePeriod;
    private Double totalFeedConsumed;
    private Double totalWeightGain;
    private Double numberOfBroilersDied;
    private Double totalNumberOfBroilers;
    private Double numberOfDays;
    private Double totalWaterConsumed;
    private Double sellingPricePerUnit;
    private Double variableCostPerUnit;


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
    public void insertData(String tableName, Map<String, String> columnValues) {
        this.name = tableName;
        this.columnValues = columnValues;
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for (Map.Entry<String, String> entry : columnValues.entrySet()) {
            contentValues.put("column" + entry.getKey(), entry.getValue());
        }
        database.insert(tableName, null, contentValues);
    }
        public long insertFarmData(String farmName, Float budgetAllotment, String Farmslocation, String createdDate) {
            this.name = farmName;
            this.budgetAllotment = budgetAllotment;
            this.farmslocation = Farmslocation;
            this.createdDate = createdDate;
            //SQLiteDatabase database = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Farm_name", farmName);
            values.put("Budget_allotment", budgetAllotment);
            values.put("Farm_location",Farmslocation);
            values.put("Created_date", createdDate);
            long farmList = database.insert("Farm", null, values);
            return farmList;
        }
    public void insertAssetExpenditure(String assetCategory, String assetDescript, Float assetDepreciation, Float amount, String createdDate) {
        this.assetCategory = assetCategory;
        this.assetDescript = assetDescript;
        this.assetDepreciation = assetDepreciation;
        this.amount = amount;
        this.date = String.valueOf(createdDate);
        //  SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("AssetsCategory", assetCategory);
        values.put("AssetsDescription",assetDescript);
        values.put("AssetDepreciation", assetDepreciation);
        values.put("AssetsAmount", amount);
        values.put("AssetCreatedDate", createdDate);
        database.insert("Assets_Expenditure", null, values);
    }
    public long insertDirectAssetExpenditure(String directAssetCategory, String directAssetDescript, Float amount, String createdDate) {
        this.directAssetCategory = directAssetCategory;
        this.directAssetDescript = directAssetDescript;
        this.amount = amount;
        this.createdDate = createdDate;
        //  SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("directAssetsCategory", directAssetCategory);
        values.put("directAssetsDescription",directAssetDescript);
        values.put("directAssetsAmount", amount);
        values.put("directAssetCreatedDate", createdDate);
        long list = database.insert("directAssets_Expenditure", null, values);
        return list;
    }
    public void insertIndirectAssetExpenditure(String indirectAssetCategory, String indirectAssetDescript, Float amount, String createdDate) {
        this.indirectAssetCategory = indirectAssetCategory;
        this.indirectAssetDescript = indirectAssetDescript;
        this.amount = amount;
        this.createdDate = createdDate;
        //  SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("indirectAssetsCategory", indirectAssetCategory);
        values.put("indirectAssetsDescription",indirectAssetDescript);
        values.put("indirectAssetsAmount", amount);
        values.put("indirectAssetCreatedDate", createdDate);
        database.insert("Assets_Expenditure", null, values);
    }
    public void insertFinancialStats(Float totalSales, Float grossProfit, Float netProfit) {
        this.totalSales = totalSales;
        this.grossProfit = grossProfit;
        this.netProfit = netProfit;
        //  SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Total_Sales", totalSales);
        values.put("Gross_Profit", grossProfit);
        values.put("Net_Profit", netProfit);
        database.insert("FinancialStatistics", null, values);
    }
    public long insertSales(String Customer, Integer Quantity, Float Unit_cost, Float Price, String date) {
        this.customer = Customer;
        this.quantity = Quantity;
        this.unitCost = Unit_cost;
        this.price = Price;
        this.date = date;
        //  SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Customer", Customer);
        values.put("Quantity_of_Birds", Quantity);
        values.put("Unit_cost", Unit_cost);
        values.put("Price", Price);
        values.put("Date", date);
        long list = database.insert("Sales", null, values);
        return list;
    }
        public void insertUserUserData(String Name, String email, String Role, String Region,
                                       int PhoneNumber ) {
            this.name = Name;
            this.email = email;
            this.role = Role;
            this.region = Region;
            this.phoneNumber = PhoneNumber;
            //  SQLiteDatabase database = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Name", Name);
            values.put("Email", email);
            values.put("Role", Role);
            values.put("Region", Region);
            values.put("Phone_number", PhoneNumber);
            // Insert the values into the table
            database.insert("USER", null, values);
        }
    ////////////////////////////////////////////////////////////////////////////////////////////////
        public long insertFarmCycleData(String cycleTitle, String createdDate,String cycleDate_ended,String cycleStatus){
            this.cycleTitle = cycleTitle;
            this.createdDate = createdDate;
            this.cycleDateEnded = cycleDate_ended;
            this.cycleStatus = cycleStatus;
            ContentValues values = new ContentValues();
            values.put("Cycle_title",cycleTitle);
            values.put("Created_date", createdDate);
            values.put("endedDateCycle",cycleDate_ended);
            values.put("status",cycleStatus);  // Set initial status to "active"
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
    public void insertNonFinancialData(Double productYield, Double foodConvRatio, Double mortalityRate, Double averageDailyGain
            , Double waterConsumption, Double breakEvenPoint ) {
        this.productYield = productYield;
        this.foodConvRatio = foodConvRatio;
        this.mortalityRate = mortalityRate;
        this.averageDailyGain = averageDailyGain;
        this.waterConsumption = waterConsumption;
        this.breakEvenPoint = breakEvenPoint;
        ContentValues values = new ContentValues();
        values.put("productionYield", productYield);
        values.put("foodConversionRatio", foodConvRatio);
        values.put("mortalityRate",mortalityRate);
        values.put("averageDailyGain", averageDailyGain);
        values.put("waterConsumption",waterConsumption);
        values.put("breakEvenPoint",breakEvenPoint);
        database.insert("nonFinancialsValues", null, values);
    }
    public void insertNonFinancialMetrics(Double NumberOfBroilers, Double TimePeriod, Double TotalFeedConsumed, Double TotalWeightGain
            , Double NumberOfBroilersDied, Double TotalNumberOfBroilers, Double NumberOfDays, Double TotalWaterConsumed, Double SellingPricePerUnit
            , Double VariableCostPerUnit) {
        this.numberOfBroilers = NumberOfBroilers;
        this.timePeriod = TimePeriod;
        this.totalFeedConsumed = TotalFeedConsumed;
        this.totalWeightGain = TotalWeightGain;
        this.numberOfBroilersDied = NumberOfBroilersDied;
        this.totalNumberOfBroilers = TotalNumberOfBroilers;
        this.numberOfDays = NumberOfDays;
        this.totalWaterConsumed = TotalWaterConsumed;
        this.sellingPricePerUnit = SellingPricePerUnit;
        this.variableCostPerUnit = VariableCostPerUnit;
        ContentValues values = new ContentValues();
        values.put("NumberOfBroilers", NumberOfBroilers);
        values.put("TimePeriod", TimePeriod);
        values.put("TotalFeedConsumed",TotalFeedConsumed);
        values.put("TotalWeightGain", TotalWeightGain);
        values.put("NumberOfBroilersDied",NumberOfBroilersDied);
        values.put("TotalNumberOfBroilers",TotalNumberOfBroilers);
        values.put("NumberOfDays", NumberOfDays);
        values.put("TotalWaterConsumed",TotalWaterConsumed);
        values.put("SellingPricePerUnit",SellingPricePerUnit);
        values.put("VariableCostPerUnit", VariableCostPerUnit);
        database.insert("nonFinancialsMetrics", null, values);
    }


    ////////////GET DATA FROM DB METHODS////////////////////////////////////////////////////////////
    public List<EmailDataList> getEmail(){
            List<EmailDataList> emailList = new ArrayList<>();
            //defining the columns to pick
            String[] columns = {"Email"};
            Cursor cursor = database.query("USER", columns, null, null, null, null, null);
            cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String email = "unknown";
            try {
                int emailIndex = cursor.getColumnIndexOrThrow("Email");
                EmailDataList data = new EmailDataList(email);
                data.setReceipientEmail(cursor.getString(emailIndex));
                emailList.add(data);
                cursor.moveToNext();
            } catch (IllegalArgumentException e) {
                // Handle the exception (e.g., log the error, notify the user, etc.)
                e.printStackTrace();
            }
        }
            cursor.close();
            return emailList;
    }
    public List<FinancialDataList> getFinancialData(){
            List<FinancialDataList> financialLists = new ArrayList<>();
            //defining the columns to pick
            String[] columns = {"Total_Sales","Gross_Profit","Net_Profit"};
            Cursor cursor = database.query("FinancialStatistics", columns, null, null, null, null, null);
            cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Float totalSales =0.0f;
            Float grossProfit =0.0f;
            Float netProfit =0.0f;
            try {
                int totalSalesIndex = cursor.getColumnIndexOrThrow("Total_Sales");
                int grossProfitIndex = cursor.getColumnIndexOrThrow("Gross_Profit");
                int netProfitIndex = cursor.getColumnIndexOrThrow("Net_Profit");
                FinancialDataList data = new FinancialDataList(totalSales,grossProfit,netProfit);
                data.setTotalSales(Float.valueOf(cursor.getString(totalSalesIndex)));
                data.setGrossProfit(Float.valueOf(cursor.getString(grossProfitIndex)));
                data.setNetProfit(Float.valueOf(cursor.getString(netProfitIndex)));
                financialLists.add(data);
                cursor.moveToNext();
            } catch (IllegalArgumentException e) {
                // Handle the exception (e.g., log the error, notify the user, etc.)
                e.printStackTrace();
            }
        }
        cursor.close();
        return financialLists;
    }
    public List<salesDataList> getSalesData(){
            List<salesDataList> salesList = new ArrayList<>();
            //defining the columns to pick
            String[] columns = {"Customer","Quantity_of_Birds","Price"};
            Cursor cursor = database.query("Sales", columns, null, null, null, null, null);
            cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String Customer = "unknown"; // Initialize with an empty string or some default value
            Integer Quantity_of_Birds = 0;// Initialize with an empty string or some default value
            Double Price = 0.0;
            try {
                int customerIndex = cursor.getColumnIndexOrThrow("Customer");
                int Quantity_of_BirdsIndex = cursor.getColumnIndexOrThrow("Quantity_of_Birds");
                int PriceIndex = cursor.getColumnIndexOrThrow("Price");
                salesDataList data = new salesDataList(Customer, Quantity_of_Birds,Price);
                data.setCustomer(cursor.getString(customerIndex));
                data.setQuantityOfBirds(Integer.valueOf(cursor.getString(Quantity_of_BirdsIndex)));
                data.setPrice(Double.valueOf(cursor.getString(PriceIndex)));
                salesList.add(data);
                cursor.moveToNext();
            } catch (IllegalArgumentException e) {
                // Handle the exception (e.g., log the error, notify the user, etc.)
                e.printStackTrace();
            }
        }
        cursor.close();
        return salesList;
    }
    public List<expenditureDataList> getExpData(){
            List<expenditureDataList> expDataList = new ArrayList<>();
            // Define the columns you want to retrieve
            String[] columns = {"directAssetsCategory","directAssetsDescription","directAssetsAmount"};
             // Query the database
            Cursor cursor = database.query("directAssets_Expenditure", columns, null, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String directAssetsCategory = "unknown"; // Initialize with an empty string or some default value
                String directAssetsDescription = "unknown";// Initialize with an empty string or some default value
                Double directAssetsAmount = 0.0;
                try {
                    int directAssetsCategoryIndex = cursor.getColumnIndexOrThrow("directAssetsCategory");
                    int directAssetsDescriptionIndex = cursor.getColumnIndexOrThrow("directAssetsDescription");
                    int directAssetsAmountIndex = cursor.getColumnIndexOrThrow("directAssetsAmount");

                    expenditureDataList data = new expenditureDataList(directAssetsCategory, directAssetsDescription,directAssetsAmount);

                    data.setDirectAssetsCategory(cursor.getString(directAssetsCategoryIndex));
                    data.setDirectAssetsDescription(cursor.getString(directAssetsDescriptionIndex));
                    data.setDirectAssetsAmount(Double.valueOf(cursor.getString(directAssetsAmountIndex)));
                    expDataList.add(data);
                    cursor.moveToNext();
                } catch (IllegalArgumentException e) {
                    // Handle the exception (e.g., log the error, notify the user, etc.)
                    e.printStackTrace();
                }
            }
            cursor.close();
            return expDataList;
    }
    public List<MyData> getCycleData() {
        List<MyData> dataList = new ArrayList<>();

        // Define the columns you want to retrieve
        String[] columns = {"Cycle_title","Created_date","status"};
        // Query the database
        Cursor cursor = database.query("Cycles", columns, null, null, null, null, null);
        cursor.moveToFirst();
        // Iterate through the cursor to retrieve data
        while (!cursor.isAfterLast()) {
            String Cycle_tile = "unknown";
            String Created_date = "unknown";
            String endedCycleDate ="unknown";
            String status = "unknown";
            try {

                int cycleTitleIndex = cursor.getColumnIndexOrThrow("Cycle_title");
                int createdDateIndex = cursor.getColumnIndexOrThrow("Created_date");
                int statusIndex = cursor.getColumnIndexOrThrow("status");

                MyData data = new MyData(Cycle_tile, Created_date,endedCycleDate,status);

                data.setCycleTitle(cursor.getString(cycleTitleIndex));
                data.setCycleDate(cursor.getString(createdDateIndex));
                data.setStatus(cursor.getString(statusIndex));
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
    public List<farmDataList> getFarmData(){
        List<farmDataList> farmList = new ArrayList<>();
        //columns to get from the db
        String[] farmColumns = {"Farm_id","Farm_name","Budget_allotment","Farm_location","User_id"};
        //query the database
        Cursor farmCursor = database.query("Farm",farmColumns,null, null, null, null, null);
        farmCursor.moveToFirst();
        // Iterate through the cursor to retrieve data
        while (!farmCursor.isAfterLast()) {
            Integer Farm_id = 0;
            String Farm_name = "unknown";
            Double Budget_allotment = 0.0;
            String Farm_location = "unknown";
            Integer User_id = 0;
            try {

                int farmIdIndex = farmCursor.getColumnIndexOrThrow("Farm_id");
                int  farmNameIndex = farmCursor.getColumnIndexOrThrow("Farm_name");
                int budgetAllotmentIndex = farmCursor.getColumnIndexOrThrow("Budget_allotment");
                int farmLocationIndex = farmCursor.getColumnIndexOrThrow("Farm_location");
                int userIdIndex = farmCursor.getColumnIndexOrThrow("User_id");

                farmDataList data = new farmDataList(Farm_id,User_id,Farm_name,Farm_location,Budget_allotment);

                data.setFarmId(farmCursor.getInt(farmIdIndex));
                data.setUserId(farmCursor.getInt( userIdIndex));
                data.setFarmName(farmCursor.getString(farmNameIndex));
                data.setFarmLocation(farmCursor.getString(farmLocationIndex));
                data.setBudgetAllotment(farmCursor.getDouble(budgetAllotmentIndex));
                farmList.add(data);
                farmCursor.moveToNext();
            } catch (IllegalArgumentException e) {
                // Handle the exception (e.g., log the error, notify the user, etc.)
                //Toast.makeText(Dashboard.this,"Data posted Successfully",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
        // Close the cursor
        farmCursor.close();
        return farmList;
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
                int productionYieldIndex = cursor.getColumnIndexOrThrow("productionYield");
                int foodConversionRatioIndex = cursor.getColumnIndexOrThrow("foodConversionRatio");
                int mortalityRateIndex = cursor.getColumnIndexOrThrow("mortalityRate");
                int averageDailyGainIndex = cursor.getColumnIndexOrThrow("averageDailyGain");
                int waterConsumptionIndex = cursor.getColumnIndexOrThrow("waterConsumption");
                int breakEvenPointIndex = cursor.getColumnIndexOrThrow("breakEvenPoint");

                myNonFinancialData data = new myNonFinancialData(productionYield,foodConversionRatio,mortalityRate,averageDailyGain,waterConsumption,breakEvenPoint);
                data.setProductionYield(cursor.getDouble(productionYieldIndex));
                data.setFoodConversionRatio(cursor.getDouble(foodConversionRatioIndex));
                data.setMortalityRate(cursor.getDouble(mortalityRateIndex));
                data.setAverageDailyGain(cursor.getDouble(averageDailyGainIndex));
                data.setWaterConsumption(cursor.getDouble(waterConsumptionIndex));
                data.setBreakEvenPoint(cursor.getDouble(breakEvenPointIndex));
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

    public List<nonFinancialDataMetricsList> getNonFinancialMetrics() {
        List<nonFinancialDataMetricsList> dataMetricsList = new ArrayList<>();
        // Define the columns you want to retrieve
        String[] columns = {"NumberOfBroilers","TimePeriod","TotalFeedConsumed","TotalWeightGain"
                ,"NumberOfBroilersDied","TotalNumberOfBroilers","NumberOfDays","TotalWaterConsumed"
                ,"SellingPricePerUnit","VariableCostPerUnit"};

        // Query the database
        Cursor metricsCursor = database.query("nonFinancialsMetrics", columns, null, null, null, null, null);
        metricsCursor.moveToFirst();
        // Iterate through the cursor to retrieve data
        while (!metricsCursor.isAfterLast()) {
            Double NumberOfBroilers =0.0;
            Double TimePeriod =0.0;
            Double TotalFeedConsumed =0.0;
            Double TotalWeightGain =0.0;
            Double NumberOfBroilersDied =0.0;
            Double TotalNumberOfBroilers =0.0;
            Double NumberOfDays =0.0;
            Double TotalWaterConsumed =0.0;
            Double SellingPricePerUnit =0.0;
            Double VariableCostPerUnit = 0.0;
            try {
                int NumberOfBroilersIndex = metricsCursor.getColumnIndexOrThrow("NumberOfBroilers");
                int TimePeriodIndex = metricsCursor.getColumnIndexOrThrow("TimePeriod");
                int TotalFeedConsumedIndex = metricsCursor.getColumnIndexOrThrow("TotalFeedConsumed");
                int TotalWeightGainIndex = metricsCursor.getColumnIndexOrThrow("TotalWeightGain");
                int NumberOfBroilersDiedIndex = metricsCursor.getColumnIndexOrThrow("NumberOfBroilersDied");
                int TotalNumberOfBroilersIndex = metricsCursor.getColumnIndexOrThrow("TotalNumberOfBroilers");
                int NumberOfDaysIndex = metricsCursor.getColumnIndexOrThrow("NumberOfDays");
                int TotalWaterConsumedIndex = metricsCursor.getColumnIndexOrThrow("TotalWaterConsumed");
                int SellingPricePerUnitIndex = metricsCursor.getColumnIndexOrThrow("SellingPricePerUnit");
                int VariableCostPerUnitIndex = metricsCursor.getColumnIndexOrThrow("VariableCostPerUnit");

                nonFinancialDataMetricsList data = new nonFinancialDataMetricsList(NumberOfBroilers,TimePeriod
                        ,TotalFeedConsumed,TotalWeightGain,NumberOfBroilersDied,TotalNumberOfBroilers,NumberOfDays,TotalWaterConsumed,
                        SellingPricePerUnit,VariableCostPerUnit);

                data.setNumberOfBroilers(metricsCursor.getDouble(NumberOfBroilersIndex));
                data.setTimePeriod(metricsCursor.getDouble(TimePeriodIndex));
                data.setTotalFeedConsumed(metricsCursor.getDouble(TotalFeedConsumedIndex));
                data.setTotalWeightGain(metricsCursor.getDouble(TotalWeightGainIndex));
                data.setNumberOfBroilersDied(metricsCursor.getDouble(NumberOfBroilersDiedIndex));
                data.setNumberOfBroilersDied(metricsCursor.getDouble(TotalNumberOfBroilersIndex));
                data.setNumberOfDays(metricsCursor.getDouble(NumberOfDaysIndex));
                data.setTotalWaterConsumed(metricsCursor.getDouble(TotalWaterConsumedIndex));
                data.setSellingPricePerUnit(metricsCursor.getDouble(SellingPricePerUnitIndex));
                data.setVariableCostPerUnit(metricsCursor.getDouble(VariableCostPerUnitIndex));
                dataMetricsList.add(data);
                metricsCursor.moveToNext();
            } catch (IllegalArgumentException e) {
                // Handle the exception (e.g., log the error, notify the user, etc.)
                e.printStackTrace();
            }
        }
        // Close the cursor
        metricsCursor.close();
        return dataMetricsList;
    }

    //total for exp and salary columns
    public float getDirectCost() {
       //open the db before querying for expenditure information
        Cursor cursor = database.rawQuery("SELECT SUM(" + COL_AMOUNT + ") as Total FROM " + TABLE_NAME_DIRECT_E, null);
        float total = 0.0f;
        if (cursor.moveToFirst()) {
            total = cursor.getFloat(cursor.getColumnIndexOrThrow("Total"));
        }
        cursor.close();
        return total;
    }
    public float getTotalRevenue() {
        //open the db before querying for sales information
        Cursor cursor = database.rawQuery("SELECT SUM(" + COL_PRICE + ") as Total FROM " + TABLE_NAME_S, null);
        float total = 0.0f;
        if (cursor.moveToFirst()) {
            total = cursor.getFloat(cursor.getColumnIndexOrThrow("Total"));
        }
        cursor.close();
        return total;
    }
    public float getTotalIndirectExp() {
        //open the db before querying for sales information
        Cursor cursor = database.rawQuery("SELECT SUM(" + COL_INDIR_ASSETS_AMOUNT + ") as Total FROM " + TABLE_NAME_INDIR, null);
        float total = 0.0f;
        if (cursor.moveToFirst()) {
            total = cursor.getFloat(cursor.getColumnIndexOrThrow("Total"));
        }
        cursor.close();
        return total;
    }

}



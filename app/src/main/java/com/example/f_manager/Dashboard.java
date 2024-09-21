package com.example.f_manager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Dashboard extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    private List<MyData> myDataList;
    private Button buttonFarm ,buttonUser,buttonCalculate,button;
    private MyDAO myDAO;
    private Float fixedCostsInput;
    private EditText cycleDateEnded,status,cycleDatedCreated,title_cycle,farmName_,budgetFarm,locationFarm,createdDate,user,phone,email,region,role,numBroilersProducedInput, timePeriodInput, totalFeedConsumedInput, totalWeightGainInput,
            numBroilersDiedInput, totalNumBroilersInput, numDaysInput, totalWaterConsumedInput,
            sellingPricePerUnitInput, variableCostPerUnitInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.form), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button = findViewById(R.id.button_add_cycle);
        buttonFarm = findViewById(R.id.button_registerFarm);
        buttonCalculate =findViewById(R.id.calculate_button);
        buttonUser =  findViewById(R.id.button_registerUser);

        //initialising cycles
        title_cycle = findViewById(R.id.cycle_title);
        cycleDatedCreated = findViewById(R.id.cycle_date_created);
        cycleDateEnded = findViewById(R.id.cycle_date_ended);
        status = findViewById(R.id.cycle_status);

        //initialising non financial metrics
        numBroilersProducedInput = findViewById(R.id.num_broilers_produced);
        timePeriodInput = findViewById(R.id.time_period_days);
        totalFeedConsumedInput = findViewById(R.id.total_feed_consumed);
        totalWeightGainInput = findViewById(R.id.total_weight_gain);
        numBroilersDiedInput = findViewById(R.id.num_broilers_died);
        totalNumBroilersInput = findViewById(R.id.total_num_broilers);
        numDaysInput = findViewById(R.id.num_days);
        totalWaterConsumedInput = findViewById(R.id.total_water_consumed);
        sellingPricePerUnitInput = findViewById(R.id.selling_price_per_unit);
        variableCostPerUnitInput = findViewById(R.id.variable_cost_per_unit);

        //initialising the edit text objects for users
        user = findViewById(R.id.user_name);
        phone = findViewById(R.id.editTextPhone);
        email = findViewById(R.id.editTextTextEmailAddress);
        region = findViewById(R.id.region);
        role = findViewById(R.id.role);

        //initialising the edittext objects for farm
        farmName_ = findViewById(R.id.farm_name);
        budgetFarm = findViewById(R.id.budget_farm);
        locationFarm = findViewById(R.id.farmLocation);
        createdDate = findViewById(R.id.editTextDate);

        //initialising the recyclerView
        recyclerView = findViewById(R.id.cycles_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //get data from DB for the recycle x                                                                                                                                             rView
        myDAO = new MyDAO(this);
        myDAO.open();
        myDataList = myDAO.getCycleData();
        cardAdapter =new CardAdapter(this,myDataList);
        recyclerView.setAdapter(cardAdapter);
        myDAO.close();

        //sending data to Farm Cycles in the DB
        button.setOnClickListener(view -> sendCycleDataToDb());

        buttonUser.setOnClickListener(view -> {
            postUserDataToDb();
        });
        buttonFarm.setOnClickListener(view -> {
            postFarmDataToDb();
        });
        buttonCalculate.setOnClickListener(view -> {
            calculateMetrics();
        });
    }
    private void sendCycleDataToDb() {
        //grab the input from the views and store in the db
        String title_cycle_input = title_cycle.getText().toString().trim();
        String cycle_date_input = cycleDatedCreated.getText().toString().trim();
        String cycleDateEndedInput = cycleDateEnded.getText().toString().trim();
        String cycleStatus = status.getText().toString().trim();

        //insert the data nto the database
        myDAO = new MyDAO(Dashboard.this);
        myDAO.open();
        long cycleId = myDAO.insertFarmCycleData(title_cycle_input, cycle_date_input,cycleDateEndedInput,cycleStatus);
        Toast.makeText(Dashboard.this,"Data posted Successfully",Toast.LENGTH_SHORT).show();
        setCycleEndAlarm(cycleId,cycleDateEndedInput);
        myDAO.close();
    }
    private void setCycleEndAlarm(long cycleId, String endDate) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, EndedCycles.class);
        intent.putExtra("cycleId", cycleId);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, (int) cycleId, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            Date end = sdf.parse(endDate);
            if (end != null && alarmManager != null) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, end.getTime(), pendingIntent);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    //method to calculate non financials
    private void calculateMetrics() {
        //get input values
        double numBroilersProduced = Double.parseDouble(numBroilersProducedInput.getText().toString());
        double timePeriod = Double.parseDouble(timePeriodInput.getText().toString());
        double totalFeedConsumed = Double.parseDouble(totalFeedConsumedInput.getText().toString());
        double totalWeightGain = Double.parseDouble(totalWeightGainInput.getText().toString());
        double numBroilersDied = Double.parseDouble(numBroilersDiedInput.getText().toString());
        double totalNumBroilers = Double.parseDouble(totalNumBroilersInput.getText().toString());
        double numDays = Double.parseDouble(numDaysInput.getText().toString());
        double totalWaterConsumed = Double.parseDouble(totalWaterConsumedInput.getText().toString());
        double sellingPricePerUnit = Double.parseDouble(sellingPricePerUnitInput.getText().toString());
        double variableCostPerUnit = Double.parseDouble(variableCostPerUnitInput.getText().toString());

        //getting direct cost from the Db

        myDAO.open();
        myDAO.insertNonFinancialMetrics(numBroilersProduced,timePeriod,totalFeedConsumed,totalWeightGain,numBroilersDied,
                                        totalNumBroilers,numDays,totalWaterConsumed,sellingPricePerUnit,variableCostPerUnit);
        fixedCostsInput = myDAO.getDirectCost();


        // Calculate contribution margin per unit
        double contributionMarginPerUnit = sellingPricePerUnit - variableCostPerUnit;

        // Calculate metrics
        double productionYield = numBroilersProduced / timePeriod;
        double fcr = totalFeedConsumed / totalWeightGain;
        double mortalityRate = numBroilersDied / totalNumBroilers;
        double adg = totalWeightGain / numDays;
        double waterConsumption = totalWaterConsumed / timePeriod;
        double bep = fixedCostsInput / contributionMarginPerUnit;
        //insert values in the DB, write function below.
        try {
            myDAO.insertNonFinancialData(productionYield,fcr,mortalityRate,adg,waterConsumption,bep);
            myDAO.close();
            Toast.makeText(Dashboard.this,"Data posted Successfully",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(Dashboard.this,"Function cannot be completed at this moment",Toast.LENGTH_SHORT).show();
        }

    }
    private void postUserDataToDb() {
        int phone_int=0;
        //grab new information from the user
        String user_input = user.getText().toString().trim();
        String phone_input = phone.getText().toString().trim();
        String email_input = email.getText().toString().trim();
        String region_input = region.getText().toString().trim();
        String role_input = role.getText().toString().trim();
        try {
            phone_int = Integer.parseInt(phone_input);
            // initialise the myDAO class and open the database for data input
            myDAO = new MyDAO(Dashboard.this);
            myDAO.open();
            myDAO.insertUserUserData(user_input,email_input,role_input,region_input, phone_int);
            myDAO.close();
            Toast.makeText(Dashboard.this,"Data posted Successfully",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(Dashboard.this,"Function cannot be completed at this moment",Toast.LENGTH_SHORT).show();
        }
    }

    private void postFarmDataToDb() {
        float budget_float=0;
        //grab information about the farm
        String farmName_input = farmName_.getText().toString().trim();
        String budgetary_input = budgetFarm.getText().toString().trim();
        String farmLocationInput = locationFarm.getText().toString().trim();
        String createdDate_input = createdDate.getText().toString().trim();
        try {
            budget_float = Float.parseFloat(budgetary_input);
            myDAO = new MyDAO(Dashboard.this);
            myDAO.open();
            long resultFarmInsert = myDAO.insertFarmData(farmName_input,budget_float,farmLocationInput,createdDate_input);

            if (resultFarmInsert != -1) {
                Log.d("Database Insert farm", "Insertion successful, ID: " + resultFarmInsert);
            } else {
                Log.d("Database Insertion", "Insertion failed.");
            }

            Toast.makeText(Dashboard.this,"Data posted Successfully",Toast.LENGTH_SHORT).show();
            myDAO.close();
        }catch (Exception e){
            Toast.makeText(Dashboard.this,"Function cannot be completed at this moment",Toast.LENGTH_SHORT).show();
        }
    }
}
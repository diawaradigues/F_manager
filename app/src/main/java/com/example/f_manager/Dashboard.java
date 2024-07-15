package com.example.f_manager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class Dashboard extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private ViewPagerAdapter viewPagerAdapter;
    //initialising the button
    private Button buttonFarm ,buttonUser,buttonCalculate;
    private MyDAO myDAO;
    private Float fixedCostsInput;
    private EditText farmName_,budgetFarm,locationFarm,createdDate,user,phone,email,region,role,numBroilersProducedInput, timePeriodInput, totalFeedConsumedInput, totalWeightGainInput,
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
        //tab views
       /* tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });
        */
        buttonFarm = findViewById(R.id.button_registerFarm);
        buttonCalculate =findViewById(R.id.calculate_button);
        buttonUser =  findViewById(R.id.button_registerUser);

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

        //setting an on click listener to the button
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
        try {
            myDAO.open();
            fixedCostsInput = myDAO.getDirectCost();
        }catch (NegativeArraySizeException e){
            Toast.makeText(Dashboard.this,"Function cannot be completed at this moment",Toast.LENGTH_SHORT).show();
        }

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
        }catch (NumberFormatException e){
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
        }catch (NumberFormatException e){
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
            myDAO.insertFarmData(farmName_input,budget_float,farmLocationInput,createdDate_input);
            myDAO.close();
        }catch (NumberFormatException e){
            Toast.makeText(Dashboard.this,"Function cannot be completed at this moment",Toast.LENGTH_SHORT).show();
        }
    }
}
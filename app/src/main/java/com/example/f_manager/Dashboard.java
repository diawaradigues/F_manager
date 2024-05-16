package com.example.f_manager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {
    Button button;
    private MyDAO myDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        button = findViewById(R.id.button_register);
        button.setOnClickListener(view -> {
            //grab new information from the user
            EditText user = findViewById(R.id.user_name);
            String user_input = user.getText().toString();

            EditText phone = findViewById(R.id.editTextPhone);
            String phone_input = phone.getText().toString();

            EditText email = findViewById(R.id.editTextTextEmailAddress);
            String email_input = email.getText().toString();

            EditText region = findViewById(R.id.region);
            String region_input = region.getText().toString();

            EditText role = findViewById(R.id.role);
            String role_input = role.getText().toString();

            //grab information about the farm
            EditText farmName_ = findViewById(R.id.farm_name);
            String farmName_input = farmName_.getText().toString();

            EditText budgetFarm = findViewById(R.id.budget_farm);
            String budgetary_input = budgetFarm.getText().toString();

            EditText createdDate = findViewById(R.id.editTextDate);
            String createdDate_input = createdDate.getText().toString();



            // initialise the myDAO class and open the database for data input
            myDAO = new MyDAO(this);
            myDAO.open();
            myDAO.insertUserUserData(email_input,role_input,region_input, Integer.parseInt(phone_input),user_input);
            myDAO.inserFarmtData(farmName_input,budgetary_input,createdDate_input);
        });
    }
}
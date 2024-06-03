package com.example.f_manager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class Dashboard extends AppCompatActivity {
    //initialising the button
    Button button = findViewById(R.id.button_register);
    private MyDAO myDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        //initialising the editext objects for users
        EditText user = findViewById(R.id.user_name);
        EditText phone = findViewById(R.id.editTextPhone);
        EditText email = findViewById(R.id.editTextTextEmailAddress);
        EditText region = findViewById(R.id.region);
        EditText role = findViewById(R.id.role);

        //initialising the edittext objects for farm
        EditText farmName_ = findViewById(R.id.farm_name);
        EditText budgetFarm = findViewById(R.id.budget_farm);
        EditText createdDate = findViewById(R.id.editTextDate);

        //setting an onclicklistener to the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int phone_int=0;
                int budget_int=0;

                    //grab new information from the user
                    String user_input = user.getText().toString();
                    String phone_input = phone.getText().toString();
                    String email_input = email.getText().toString();
                    String region_input = region.getText().toString();
                    String role_input = role.getText().toString();
                    //grab information about the farm
                    String farmName_input = farmName_.getText().toString();
                    String budgetary_input = budgetFarm.getText().toString();
                    Date createdDate_input = (Date) createdDate.getText();

                try {
                    //checking if important input fields are empty
                    if(phone_input.isEmpty() || user_input.isEmpty() || region_input.isEmpty() || farmName_input.isEmpty() || budgetary_input.isEmpty()){
                        throw  new NumberFormatException("Input fields cannot be empty");
                    }
                    phone_int = Integer.parseInt(phone_input);
                    budget_int = Integer.parseInt(budgetary_input);
                    // initialise the myDAO class and open the database for data input

                }catch (NumberFormatException e){
                    Toast.makeText(Dashboard.this,"Please enter valid numbers in both fields",Toast.LENGTH_SHORT).show();
                }
                myDAO = new MyDAO(Dashboard.this);
                myDAO.open();
                myDAO.insertUserUserData(email_input,role_input,region_input, phone_int,user_input);
                myDAO.insertFarmData(farmName_input,budget_int,createdDate_input);
                myDAO.close();
            }
        });
    }
}
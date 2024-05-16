package com.example.f_manager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Farm_cycles extends AppCompatActivity {
    Button button;
    MyDAO myDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_farm_cycles);

        //getting th button by its ID
      button = findViewById(R.id.button_add_cycle);
      button.setOnClickListener(view -> {
          //grab the input from the views and store in the db
          EditText title_cycle = findViewById(R.id.cycle_title);
          String title_cycle_input = title_cycle.getText().toString();

          EditText cycle_date = findViewById(R.id.cycle_date);
          String cycle_date_input = cycle_date.getText().toString();

          //initialise DB AN CALL THE Insert method
          myDAO = new MyDAO(this);
          myDAO.open();
          myDAO.insertFarmCycleData(title_cycle_input,cycle_date_input);



      });
    }
}
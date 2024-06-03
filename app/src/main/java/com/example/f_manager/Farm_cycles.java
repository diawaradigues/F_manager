package com.example.f_manager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Farm_cycles extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    private List<MyData> myDataList;
    Button button;
    MyDAO myDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_farm_cycles);

        //initialising the recyclerView
        recyclerView = findViewById(R.id.cycles_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Initialising button
        button = findViewById(R.id.button_add_cycle);
        //Initialising edittext fields
        EditText title_cycle = findViewById(R.id.cycle_title);
        EditText cycle_date = findViewById(R.id.cycle_date);

      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              //grab the input from the views and store in the db
              String title_cycle_input = title_cycle.getText().toString();
              String cycle_date_input = cycle_date.getText().toString();

              //insert the data nto the database
              myDAO = new MyDAO(Farm_cycles.this);
              myDAO.open();
              myDAO.insertFarmCycleData(title_cycle_input, cycle_date_input);
              myDAO.close();
          }

      });
      myDAO = new MyDAO(this);
      myDAO.open();
      myDataList = myDAO.getCycleData();
      myDAO.close();
      cardAdapter =new CardAdapter(myDataList);
      recyclerView.setAdapter(cardAdapter);
    }
}
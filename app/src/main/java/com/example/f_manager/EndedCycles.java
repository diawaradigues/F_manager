package com.example.f_manager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EndedCycles extends AppCompatActivity {
    private RecyclerView recyclerView,farmRecyclerView,nonFinancialRecyclerView;
    private CyclesEndedRecyclerAdapter myAdapter;
    private farmAdapter farmAdapter;
    private nonFinancialmetricsAdapter nonfinancialMetricsAdapter;
    private MyDAO dao;
    private List<farmDataList> farmDataLists;
    private List<MyData> endedCycles;
    private List<nonFinancialDataMetricsList> dataMetricsList;
    private Button refreshCyclesEndedButton,buttonFarmData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ended_cycles);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cyclesEnded), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /*initiating buttons
        refreshCyclesEndedButton = findViewById(R.id.refreshCyclesEnded);
        buttonFarmData = findViewById(R.id.button_farm_data);*/

        //initialising the recyclerViews
        recyclerView = findViewById(R.id.recyclerView_endedCycles);
        farmRecyclerView = findViewById(R.id.recyclerView_farmData);
        nonFinancialRecyclerView = findViewById(R.id.recyclerView_nonFinancialDataMetrics);

        dao = new MyDAO(this);
        dao.open();

        //financial metrics recycler setup
        nonFinancialRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataMetricsList = dao.getNonFinancialMetrics();
        nonfinancialMetricsAdapter = new nonFinancialmetricsAdapter(this,dataMetricsList);
        nonFinancialRecyclerView.setAdapter(nonfinancialMetricsAdapter);

        //cycles ended recycler setup
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        endedCycles = dao.getEndedCycles();
        myAdapter = new CyclesEndedRecyclerAdapter(this,endedCycles);
        recyclerView.setAdapter(myAdapter);

        //farm data recycler setup
        farmRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        farmDataLists = dao.getFarmData();

        if (farmDataLists != null && !farmDataLists.isEmpty()) {
            for (farmDataList cycle : farmDataLists) {
                Log.d("Data Retrieval", "Fetched Cycle: " + cycle.getFarmName() + cycle.getFarmLocation());
            }
        } else {
            Log.d("Data Retrieval", "No data found in database.");
        }

        farmAdapter = new farmAdapter(this,farmDataLists);
        farmRecyclerView.setAdapter(farmAdapter);

        dao.close();
    }


}
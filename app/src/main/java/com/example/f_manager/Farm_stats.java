package com.example.f_manager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Farm_stats extends AppCompatActivity {
    private MyDAO myDAO;
    private salesAdapter adapter;
    private expendituresAdapter expAdapter;
    private RecyclerView recyclerView,recyclerView2;
    private List<salesDataList> salesList;
    private List<expenditureDataList> expList;

    private List<myNonFinancialData> nonFinancialDataList;
    private final int percentageMargin =100;
    private TextView productionYield,feedConversionRate,averageDailyGain,mortalityRate,waterConsumption,breakEvenP,totalSalesView
            ,grossProfitView,netProfitView;
    private Float totalSales, totalExpenses, grossProfit,netProfit,totalIndirectExp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_farm_stats);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.farmStats), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //sales recycler view setup
        recyclerView = findViewById(R.id.sales_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myDAO = new MyDAO(this);
        myDAO.open();
        salesList = myDAO.getSalesData();
        /*if (salesList != null && !salesList.isEmpty()) {
            for (salesDataList cycle : salesList) {
                Log.d("Data Retrieval", "Fetched Cycle: " + cycle.getCustomer());
            }
        } else {
            Log.d("Data Retrieval", "No data found in database.");
        }*/
        adapter =new salesAdapter(this,salesList);
        recyclerView.setAdapter(adapter);

        //expenditure recycler view setup
        recyclerView2 = findViewById(R.id.exp_recyclerView);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        expList = myDAO.getExpData();

        if (expList != null && !expList.isEmpty()) {
            for (expenditureDataList cycle : expList) {
                Log.d("Data Retrieval", "Fetched Cycle: " + cycle.getDirectAssetsCategory());
            }
        } else {
            Log.d("Data Retrieval", "No data found in database.");
        }
        expAdapter =new expendituresAdapter(this,expList);
        recyclerView2.setAdapter(expAdapter);
        myDAO.close();

        productionYield = findViewById(R.id.productionYieldValue);
        feedConversionRate = findViewById(R.id.feedConversionRateValue);
        averageDailyGain = findViewById(R.id.averageDailyGainValue);
        mortalityRate = findViewById(R.id.mortalityRateValue);
        waterConsumption = findViewById(R.id.waterConsumptionValue);
        breakEvenP = findViewById(R.id.breakEvenPValue);

        //initiate the button here
        Button statsButton = findViewById(R.id.getStatsButton);
        statsButton.setOnClickListener(view -> {
            getFarmStatistics();
        });

    }//your next task is to link the data from db non financials to this activity textViews
    private void getFarmStatistics() {
        myDAO.open();

        /*values Gross Profit = Revenue − Direct Costs
        call the totals from the DB for in farm metrics
        */
        totalSalesView = findViewById(R.id.totalSalesValue);
        grossProfitView = findViewById(R.id.grossProfitValue);
        netProfitView = findViewById(R.id.netProfitValue);

        totalSales = myDAO.getTotalRevenue();
        totalExpenses = myDAO.getDirectCost();
        totalIndirectExp = myDAO.getTotalIndirectExp();

        //value grossProfit
        grossProfit = totalSales - totalExpenses;
        netProfit = grossProfit - totalIndirectExp;
        myDAO.insertFinancialStats(totalSales,grossProfit,netProfit);
        myDAO.close();

        //setting the total sales, net profit and gross to the text views
        totalSalesView.setText(String.valueOf(totalSales));
        grossProfitView.setText(String.valueOf(grossProfit));
        netProfitView.setText(String.valueOf(netProfit));

        nonFinancialDataList = myDAO.getNonFinancialData();

        if (!nonFinancialDataList.isEmpty()) {
            myNonFinancialData data = nonFinancialDataList.get(0);
            productionYield.setText(String.valueOf(data.getProductionYield()));
            feedConversionRate.setText(String.valueOf(data.getFoodConversionRatio()));
            averageDailyGain.setText(String.valueOf(data.getAverageDailyGain()));
            mortalityRate.setText(String.valueOf(data.getMortalityRate()));
            waterConsumption.setText(String.valueOf(data.getWaterConsumption()));
            breakEvenP.setText(String.valueOf(data.getBreakEvenPoint()));
        }else {
            Toast.makeText(Farm_stats.this,"NO DATA FOUND",Toast.LENGTH_SHORT).show();
        }
    }
}
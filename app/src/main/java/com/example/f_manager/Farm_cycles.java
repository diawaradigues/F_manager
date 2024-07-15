package com.example.f_manager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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

public class Farm_cycles extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    private List<MyData> myDataList;
    private EditText title_cycle,cycleDatedCreated,cycleDateEnded;
    MyDAO myDAO;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_farm_cycles);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cycles), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Initialising button
        button = findViewById(R.id.button_add_cycle);

        //Initialising edittext fields
        title_cycle = findViewById(R.id.cycle_title);
        cycleDatedCreated = findViewById(R.id.cycle_date_created);
        cycleDateEnded = findViewById(R.id.cycle_date_ended);

        //sending data to Farm Cycles in the DB
        button.setOnClickListener(view -> sendCycleDataToDb());

        //initialising the recyclerView
        recyclerView = findViewById(R.id.cycles_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //get data from DB for the recyclerView
        myDAO = new MyDAO(this);
        myDAO.open();
        myDataList = myDAO.getCycleData();
        myDAO.close();
        cardAdapter =new CardAdapter(myDataList);
        recyclerView.setAdapter(cardAdapter);
    }
    private void sendCycleDataToDb() {
        //grab the input from the views and store in the db
        String title_cycle_input = title_cycle.getText().toString().trim();
        String cycle_date_input = cycleDatedCreated.getText().toString().trim();
        String cycleDateEndedInput = cycleDateEnded.getText().toString().trim();

        //insert the data nto the database
        myDAO = new MyDAO(Farm_cycles.this);
        myDAO.open();
        long cycleId = myDAO.insertFarmCycleData(title_cycle_input, cycle_date_input,cycleDateEndedInput);
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
}
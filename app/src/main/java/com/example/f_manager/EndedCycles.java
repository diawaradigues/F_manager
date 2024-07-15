package com.example.f_manager;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EndedCycles extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CyclesEndedRecyclerAdapter myAdapter;
    private List<MyData> endedCycles;
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
        recyclerView = findViewById(R.id.recyclerView_endedCycles);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Fetch ended cycles from DB
        MyDAO dao = new MyDAO(this);
        endedCycles = dao.getEndedCycles();

        myAdapter = new CyclesEndedRecyclerAdapter(endedCycles);
        recyclerView.setAdapter(myAdapter);
    }


}
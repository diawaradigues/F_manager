package com.example.f_manager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {
    interface  request_user{
        @GET("/api/users/{uid}")
        Call<userData> getUser(@Path("uid") String uid);

    }
    TextView text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Finding cardView by ID
        CardView farmStats_ = findViewById(R.id.farmstats_card);
        CardView form_ = findViewById(R.id.form_card);
        CardView farmCycles_ = findViewById(R.id.farm_cycle_card);

        //Setting an onclick listener on the card
        farmStats_.setOnClickListener(v -> {
            //create an intent to start the Farm_stats activity
            Intent intent = new Intent(MainActivity.this,Farm_stats.class);
            startActivity(intent);
        });
        form_.setOnClickListener(view -> {
            //create an intent to start the Farm_stats activity
            Intent intent = new Intent(MainActivity.this, Dashboard.class);
            startActivity(intent);
        });
        farmCycles_.setOnClickListener(view -> {
            //create an intent to start the Farm_stats activity
            Intent intent = new Intent(MainActivity.this, Farm_cycles.class);
            startActivity(intent);
        });


        text_view=findViewById(R.id.textview_dashboard);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        request_user requestUser = retrofit.create(request_user.class);
        requestUser.getUser("2").enqueue(new Callback<userData>() {
            @Override
            public void onResponse(Call<userData> call, Response<userData> response) {
                text_view.setText(response.body().data.last_name);
            }

            @Override
            public void onFailure(Call<userData> call, Throwable t) {
                text_view.setText(t.getMessage());

            }
        });

    }
}
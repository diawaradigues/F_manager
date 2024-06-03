package com.example.f_manager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_EXCEL_FILE = 1;
    TextView text_view;
    Button floatingExcelButton;

    //networking interface
    interface  request_user{
        @GET("/api/users/{uid}")
        Call<userData> getUser(@Path("uid") String uid);

    }

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

        //Linking floatingAction button for Excel sheet
        floatingExcelButton = findViewById(R.id.floatingButtonReadExcel);
        //linking cardViews
        CardView farmStats_ = findViewById(R.id.farmstats_card);
        CardView form_ = findViewById(R.id.form_card);
        CardView farmCycles_ = findViewById(R.id.farm_cycle_card);
        //OnClickListener for floatingAction button to call the method openFilePicker
        floatingExcelButton.setOnClickListener(view -> openFilePicker());

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
    //creates intent to get excel files from the device storage.
    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        startActivityForResult(intent,PICK_EXCEL_FILE);
    }
    //handles the results generated from the intent and stores in a file for processing
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_EXCEL_FILE && resultCode == RESULT_OK) {
            Uri fileUri = data.getData();
            if (fileUri != null) {
                readExcelFile(fileUri);
            }
        }
    }
    //processes the variable contaning the excel file and inserts the data in the database.
    private void readExcelFile(Uri fileUri) {
        try (InputStream inputStream = getContentResolver().openInputStream(fileUri)) {
            assert inputStream != null;
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
           // for (Row row : sheet) {
                //String column1 = row.getCell(0).getStringCellValue();
                //String column2 = row.getCell(1).getStringCellValue();
                // Add other columns as needed
                //databaseHelper.insertData(column1, column2);
            //}
            Toast.makeText(this, "Data Imported Successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to Read Excel File", Toast.LENGTH_SHORT).show();
        }
    }
}
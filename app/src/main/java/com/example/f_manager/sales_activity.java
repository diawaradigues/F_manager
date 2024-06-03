package com.example.f_manager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class sales_activity extends AppCompatActivity {
    MyDAO myDAO;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sales);

        //initialising the edittext and button
        button = findViewById(R.id.add_sale);
        EditText customer_name = findViewById(R.id.edit_customer_name);

        EditText quantity = findViewById(R.id.edit_quantity);
        EditText unit_cost = findViewById(R.id.edit_unit_cost);
        EditText Price = findViewById(R.id.edit_price);
        EditText date = findViewById(R.id.edit_date_sales);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity_int = 0;
                float unitCost_float = 0;
                float price_int = 0;

                //grab sales data from the fields
                String customer_name_input = customer_name.getText().toString();
                String quantity_input = quantity.getText().toString();
                String unit_input = unit_cost.getText().toString();
                String price_input = Price.getText().toString();
                Date date_input = (Date) date.getText();

                //check if the input is not empty to avoid NumberFormatException
                if(!quantity_input.isEmpty() || !unit_input.isEmpty() || !price_input.isEmpty()){
                    try {
                         quantity_int = Integer.parseInt(quantity_input);
                         unitCost_float = Float.parseFloat(unit_input);
                         price_int = Float.parseFloat(price_input);
                    }catch (NumberFormatException e){
                        Toast.makeText(sales_activity.this,"invalid number entered",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(sales_activity.this,"Please enter Quantity/Unit cost/Price",Toast.LENGTH_SHORT).show();
                }
                myDAO = new MyDAO(sales_activity.this);
                myDAO.open();
                myDAO.insertSales(customer_name_input,quantity_int,unitCost_float,price_int,date_input);
                myDAO.close();

                Toast.makeText(sales_activity.this,"data sent successfully",Toast.LENGTH_SHORT).show();
            }
        });



    }
}
package com.example.f_manager;

import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class expenditure_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_expenditure);

        EditText expCat = findViewById(R.id.expenses_cat);
        String expCatInput = expCat.getText().toString();

        EditText exps = findViewById(R.id.expenses);
        String expsInput = exps.getText().toString();
        int expsInputParse = Integer.parseInt(expCatInput);

        EditText expAmount = findViewById(R.id.expense_amount);
        String expAmountInput= expAmount.getText().toString();
        int expAmtParse = Integer.parseInt(expAmountInput);

        EditText expDate = findViewById(R.id.exp_creatd_date);



    }
}
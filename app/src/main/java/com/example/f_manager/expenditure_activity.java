package com.example.f_manager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class expenditure_activity extends AppCompatActivity {
    MyDAO myDAO;
    //initialising the button
    Button button_expenditure;

    //initialising AutoCompleteTextView for the fixed expenses fields category
    AutoCompleteTextView fixedExpenses = findViewById(R.id.fixed_expenses);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_expenditure);

        //initialising the edittext and expense category AutoCompleteTextView
        AutoCompleteTextView expCat = findViewById(R.id.expenses_cat);
        EditText exp = findViewById(R.id.expenses);
        EditText expAmount = findViewById(R.id.expense_amount);
        EditText expDate = findViewById(R.id.exp_creatd_date);

        //get array of suggestions from the resource for the Expense Category
        String[] dropDownList = getResources().getStringArray(R.array.dropdown_array_expCat);
        //create an arrayAdapter using the string array and default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,dropDownList);
        //apply adapter to the AutocompleteTextview
        expCat.setAdapter(adapter);

        // Listening for user input in the first AutoCompleteTextView expCat
        expCat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedOption = (String) adapterView.getItemAtPosition(i);
                populateSuggestions(selectedOption);
            }
        });

        //onClickListener set to send data to the DB
        button_expenditure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float expAmtParse = 0;

                String expCatInput = expCat.getText().toString();
                String fixedExpInput = fixedExpenses.getText().toString();
                String expInput = exp.getText().toString();
                String expAmountInput= expAmount.getText().toString();
                Date expDateInput = (Date) expDate.getText();

                if(!expAmountInput.isEmpty()){
                    try {
                        expAmtParse = Float.parseFloat(expAmountInput);
                    }catch (NumberFormatException e){
                        Toast.makeText(expenditure_activity.this,"Invalid number entered",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(expenditure_activity.this,"enter number",Toast.LENGTH_SHORT).show();
                }
                if(!expCatInput.isEmpty() || !expInput.isEmpty()){
                    myDAO = new MyDAO(expenditure_activity.this);
                    myDAO.open();
                    myDAO.insertexpenditure(expCatInput,fixedExpInput,expInput,expAmtParse,expDateInput);
                    myDAO.close();
                }
                else{
                    Toast.makeText(expenditure_activity.this,"Please Fill in the Missing Information",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
    private void populateSuggestions(String selectedOption) {
        String[] suggestions = {};
        switch (selectedOption) {
            case "Housing and Equipment":
                suggestions = getResources().getStringArray(R.array.dropdownArray_fixedHousing);
                break;
            case "Insurance":
                suggestions = getResources().getStringArray(R.array.dropdownArray_fixedInsurance);
                break;
            case "Others":
                suggestions = getResources().getStringArray(R.array.dropdownArray_fixedOthers);
                break;
            default:
                suggestions = getResources().getStringArray(R.array.dropdown_array_fixedCat);
                break;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, suggestions);
        fixedExpenses.setAdapter(adapter);
    }

}
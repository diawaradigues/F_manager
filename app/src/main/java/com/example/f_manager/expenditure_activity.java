package com.example.f_manager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class expenditure_activity extends AppCompatActivity {
    MyDAO myDAO;
    private  EditText assetDescription,assetDepreciation,amountOnAsset,dateCreated,directAssetDescription
            ,amountOnDirectAsset,dateCreated_directA,indirectDescription,amountOnIndirect_A,dateCreated_indirectA
            ,customer_name,quantity,unit_cost,Price,date;
    private Float depreciationParse;
    private Float indirectAmountParse;
    private Float assetAmountParse;
    private Float directAmountParse;
    private String selectedTextAsset;
    private String selectedTextIndirectAsset;
    private String selectedTextDirectAsset;
    private Button buttonAssets,buttonSales, buttonDirectAssets ,buttonIndirectAssets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_expenditure);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.exp), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //initialising the AutoComplete Views
        AutoCompleteTextView assetsCategory = findViewById(R.id.assetsCat);
        AutoCompleteTextView directAssetsCategory = findViewById(R.id.directAssetsCat);
        AutoCompleteTextView indirectAssetsCategory = findViewById(R.id.indirectAssetsCat);

        //initialising edit text for Assets
         assetDescription = findViewById(R.id.descriptionAssets);
         assetDepreciation = findViewById(R.id.depreciationPercentageAssets);
         amountOnAsset = findViewById(R.id.assetsAmount);
         dateCreated = findViewById(R.id.assetsCreatedDate);

        //initialising edit text for Direct Assets
         directAssetDescription = findViewById(R.id.descriptionDirectAssets);
         amountOnDirectAsset = findViewById(R.id.directAssetAmount);
         dateCreated_directA = findViewById(R.id.directAssetCreatedDate);

        //initialising edit text for indirect Assets
         indirectDescription = findViewById(R.id.indirectDescriptionAssets);
         amountOnIndirect_A = findViewById(R.id.indirectExpenseAmount);
         dateCreated_indirectA = findViewById(R.id.indirectExpCreatedDate);

        //initialising edit text for sales
         customer_name = findViewById(R.id.edit_customer_name);
         quantity = findViewById(R.id.edit_quantity);
         unit_cost = findViewById(R.id.edit_unit_cost);
         Price = findViewById(R.id.edit_price);
         date = findViewById(R.id.edit_date_sales);

        //initialising the buttons
        buttonSales = findViewById(R.id.add_sale);
        buttonAssets = findViewById(R.id.addAssetExp);
        buttonDirectAssets = findViewById(R.id.addDirectAssetsExp);
        buttonIndirectAssets = findViewById(R.id.addIndirectAssets);

        //get array of suggestions from the resource for the Expense Category
        String[] assetsDropDownList = getResources().getStringArray(R.array.dropdownArray_Assets);
        String[] directAssetsDropDownList = getResources().getStringArray(R.array.dropdownArray_directCosts);
        String[] indirectAssetsDropDownList = getResources().getStringArray(R.array.dropdownArray_indirectCosts);

        //create an arrayAdapter for each AutoComplete dropdown lists
        ArrayAdapter<String> assetAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,assetsDropDownList);
        assetsCategory.setAdapter(assetAdapter);

        ArrayAdapter<String> directAssetAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,directAssetsDropDownList);
        directAssetsCategory.setAdapter(directAssetAdapter);

        ArrayAdapter<String> indirectAssetAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,indirectAssetsDropDownList);
        indirectAssetsCategory.setAdapter(indirectAssetAdapter);

        //get the user selection from the autocomplete text view for DB insertion
        assetsCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selectedTextAsset = (String) adapterView.getItemAtPosition(position);
            }
        });
        directAssetsCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedTextDirectAsset = (String) adapterView.getItemAtPosition(i);
            }
        });
        indirectAssetsCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedTextIndirectAsset = (String) adapterView.getItemAtPosition(i);
            }
        });

        ///////////////////////onClickListener set to send Assets data to the DB
        buttonSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postingSales();
            }
        });
        buttonAssets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postingAssetsData();
            }
        });
        buttonDirectAssets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postingDirectAssetsData();
            }
        });
        buttonIndirectAssets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postingIndirectAssets();
            }
        });
    }

    private void postingSales() {
        int quantity_int = 0;
        float unitCost_float = 0;
        float price_int = 0;

        //grab sales data from the fields
        String customer_name_input = customer_name.getText().toString();
        String quantity_input = quantity.getText().toString();
        String unit_input = unit_cost.getText().toString();
        String price_input = Price.getText().toString();
        String date_input =  date.getText().toString();

        //check if the input is not empty to avoid NumberFormatException
        if(!quantity_input.isEmpty() || !unit_input.isEmpty() || !price_input.isEmpty()){
            try {
                quantity_int = Integer.parseInt(quantity_input);
                unitCost_float = Float.parseFloat(unit_input);
                price_int = Float.parseFloat(price_input);
            }catch (NumberFormatException e){
                Toast.makeText(expenditure_activity.this,"invalid number entered",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(expenditure_activity.this,"Please enter Quantity/Unit cost/Price",Toast.LENGTH_SHORT).show();
        }
        myDAO = new MyDAO(expenditure_activity.this);
        myDAO.open();

        long result = myDAO.insertSales(customer_name_input,quantity_int,unitCost_float,price_int,date_input);
        if (result != -1) {
            Log.d("Database Insertion", "Insertion successful, ID: " + result);
        } else {
            Log.d("Database Insertion", "Insertion failed.");
        }

        myDAO.close();

        Toast.makeText(expenditure_activity.this,"data sent successfully",Toast.LENGTH_SHORT).show();
    }


    private void postingIndirectAssets() {

        String indirectAssetDescriptInput = indirectDescription.getText().toString();
        String indirectAssetAmountInput= amountOnIndirect_A.getText().toString();//parse to float
        String indirectAssetDateInput = dateCreated_indirectA.getText().toString();
        try {
            indirectAmountParse = Float.parseFloat(indirectAssetAmountInput);
        }catch (NumberFormatException e){
            Toast.makeText(expenditure_activity.this,"Invalid Input Type",Toast.LENGTH_SHORT).show();

        }
        myDAO = new MyDAO(expenditure_activity.this);
        myDAO.open();
        myDAO.insertIndirectAssetExpenditure(selectedTextIndirectAsset,indirectAssetDescriptInput,
                indirectAmountParse,indirectAssetDateInput);
        myDAO.close();
    }

    private void postingDirectAssetsData() {
        String directAssetDescriptInput = directAssetDescription.getText().toString();
        String directAssetAmountInput= amountOnDirectAsset.getText().toString();//parse to float
        String directAssetDateInput = dateCreated_directA.getText().toString();
        try {
            directAmountParse = Float.parseFloat(directAssetAmountInput);
        }catch (NumberFormatException e){
            Toast.makeText(expenditure_activity.this,"Invalid Input Type",Toast.LENGTH_SHORT).show();

        }
        myDAO = new MyDAO(expenditure_activity.this);
        myDAO.open();

        long resultIndirect = myDAO.insertDirectAssetExpenditure(selectedTextDirectAsset,directAssetDescriptInput,directAmountParse,directAssetDateInput);
        if (resultIndirect != -1) {
            Log.d("Database Insertion direct", "Insertion successful, ID: " + resultIndirect);
        } else {
            Log.d("Database Insertion", "Insertion failed.");
        }

        Toast.makeText(expenditure_activity.this,"data sent successfully",Toast.LENGTH_SHORT).show();
        myDAO.close();
    }

    private void postingAssetsData() {

        String assetDescriptionInput = assetDescription.getText().toString();
        String assetDepreciationInput = assetDepreciation.getText().toString();//parse to float
        String assetAmountInput= amountOnAsset.getText().toString();//parse to float
        String assetDateInput = dateCreated.getText().toString();
        if(!assetDepreciationInput.isEmpty() || !assetAmountInput.isEmpty()){
            try {
                depreciationParse = Float.parseFloat(assetDepreciationInput);
                assetAmountParse = Float.parseFloat(assetAmountInput);
            }catch (NumberFormatException e){
                Toast.makeText(expenditure_activity.this,"Invalid Input Type",Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(expenditure_activity.this,"Enter valid Input Types",Toast.LENGTH_SHORT).show();
        }
        myDAO = new MyDAO(expenditure_activity.this);
        myDAO.open();
        myDAO.insertAssetExpenditure(selectedTextAsset,assetDescriptionInput,depreciationParse,assetAmountParse,assetDateInput);
        myDAO.close();
        clearAssetsFields();
    }

    private void clearAssetsFields() {
        //Reminder to fill in the code to clear all input fields in all activities after user input
    }


}
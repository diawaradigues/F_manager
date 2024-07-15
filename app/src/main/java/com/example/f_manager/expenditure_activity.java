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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;

public class expenditure_activity extends AppCompatActivity {
    MyDAO myDAO;
    private Float depreciationParse;
    private Float indirectAmountParse;
    private Float assetAmountParse;
    private Float directAmountParse;
    private String selectedTextAsset;
    private String selectedTextIndirectAsset;
    private String selectedTextDirectAsset;
    private Button buttonAssets;
    private Button buttonDirectAssets;
    private Button buttonIndirectAssets;

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
        EditText assetDescription = findViewById(R.id.descriptionAssets);
        EditText assetDepreciation = findViewById(R.id.depreciationPercentageAssets);
        EditText amountOnAsset = findViewById(R.id.assetsAmount);
        EditText dateCreated = findViewById(R.id.assetsCreatedDate);

        //initialising edit text for Direct Assets
        EditText directAssetDescription = findViewById(R.id.descriptionDirectAssets);
        EditText amountOnDirectAsset = findViewById(R.id.directAssetAmount);
        EditText dateCreated_directA = findViewById(R.id.directAssetCreatedDate);

        //initialising edit text for indirect Assets
        EditText indirectDescription = findViewById(R.id.indirectDescriptionAssets);
        EditText amountOnIndirect_A = findViewById(R.id.indirectExpenseAmount);
        EditText dateCreated_indirectA = findViewById(R.id.indirectExpCreatedDate);

        //initialising the buttons
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
        buttonAssets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String assetDescriptionInput = assetDescription.getText().toString().trim();
                String assetDepreciationInput = assetDepreciation.getText().toString().trim();//parse to float
                String assetAmountInput= amountOnAsset.getText().toString().trim();//parse to float
                Date assetDateInput = (Date) dateCreated.getText();
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
        });
        //onClickListener set to send Direct Assets data to the DB
        buttonDirectAssets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String directAssetDescriptInput = directAssetDescription.getText().toString().trim();
                String directAssetAmountInput= amountOnDirectAsset.getText().toString().trim();//parse to float
                String directAssetDateInput = dateCreated_directA.getText().toString().trim();
                try {
                    directAmountParse = Float.parseFloat(directAssetAmountInput);
                }catch (NumberFormatException e){
                    Toast.makeText(expenditure_activity.this,"Invalid Input Type",Toast.LENGTH_SHORT).show();

                }
                myDAO = new MyDAO(expenditure_activity.this);
                myDAO.open();
                myDAO.insertDirectAssetExpenditure(selectedTextDirectAsset,directAssetDescriptInput,directAmountParse,directAssetDateInput);
                myDAO.close();
            }
        });
        ///////////////////////onClickListener set to send Indirect Assets data to the DB
        buttonIndirectAssets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String indirectAssetDescriptInput = indirectDescription.getText().toString().trim();
                String indirectAssetAmountInput= amountOnIndirect_A.getText().toString().trim();//parse to float
                String indirectAssetDateInput = dateCreated_indirectA.getText().toString().trim();
                try {
                    indirectAmountParse = Float.parseFloat(indirectAssetAmountInput);
                }catch (NumberFormatException e){
                    Toast.makeText(expenditure_activity.this,"Invalid Input Type",Toast.LENGTH_SHORT).show();

                }
                myDAO = new MyDAO(expenditure_activity.this);
                myDAO.open();
                myDAO.insertIndirectAssetExpenditure(selectedTextIndirectAsset,indirectAssetDescriptInput,indirectAmountParse,indirectAssetDateInput);
                myDAO.close();
            }
        });


    }
    private void clearAssetsFields() {
        //Reminder to fill in the code to clear all input fields in all activities after user input
    }


}
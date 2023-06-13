package com.mirea.kt.android2023.coursework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddProductsToListActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etProductName, etPrice, etAmount;
    private DBManager dbManager;
    private String dbName;

    private static final String TAG = "AddProductsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products_to_list);
        Log.d(TAG, "onCreate: Activity started");
        Bundle args = getIntent().getExtras();
        if (args != null) {
            ProductsList prList = (ProductsList) args.getSerializable("list_without_products");
            dbName = prList.getListName();
        } else {dbName = "my_database";}
        this.dbManager = new DBManager(new MyAppSQLiteHelper(this, dbName,
                null, 1));
        etProductName = findViewById(R.id.etProductName);
        etPrice = findViewById(R.id.etPrice);
        etAmount = findViewById(R.id.etAmount);
        Button btnAddProduct = findViewById(R.id.btnAddProduct);
        Button btnToList = findViewById(R.id.btnToList);
        btnAddProduct.setOnClickListener(this);
        btnToList.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAddProduct) {
            Log.d(TAG, "onClick: Button Add Product Pressed");
            if (this.dbManager != null) {
                String prName = etProductName.getText().toString();
                String price = etPrice.getText().toString();
                String amount = etAmount.getText().toString();
                if (!prName.isEmpty()&&price.isEmpty()&&amount.isEmpty()) {
                    boolean result = dbManager.saveProductToDatabase(new Product(prName));
                    if (result) {
                        Toast.makeText(this, R.string.insert_success, Toast.LENGTH_LONG).show();
                        Log.d(TAG, "onClickAdd: Product Without Price And Amount Added");
                    } else {
                        Toast.makeText(this, R.string.insert_error, Toast.LENGTH_LONG).show();
                        Log.d(TAG, "onClickAdd: Error While Addind Product Without Price And Amount");
                    }
                }else if (!prName.isEmpty() && !price.isEmpty() && !amount.isEmpty()) {
                    try {
                        dbManager.saveProductToDatabase(new Product(prName,
                                Integer.parseInt(price), Integer.parseInt(amount)));

                    }catch (Exception ex) {
                        Log.d(TAG, "OnClickAdd: " + ex.getMessage());
                        Toast.makeText(this, R.string.incorrect_value, Toast.LENGTH_LONG).show();
                    }
                }
            }
        }else if (v.getId() == R.id.btnToList) {
            Log.d(TAG, "OnCreate: Button Add To List Pressed");
            Bundle args = getIntent().getExtras();
            if (args != null) {
                ProductsList productsList = (ProductsList) args.getSerializable("list_without_products");
                productsList.setProducts(dbManager.loadAllProductsFromDatabase());
                Intent showProductsListsIntent = new Intent(getApplicationContext(), ShowProductsListsActivity.class);
                showProductsListsIntent.putExtra("products_list", productsList);
                startActivity(showProductsListsIntent);
            }
        }
    }
}
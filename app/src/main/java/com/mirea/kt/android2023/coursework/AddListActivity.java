package com.mirea.kt.android2023.coursework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;

public class AddListActivity extends AppCompatActivity {

    private EditText etListName;

    private static final String TAG = "AddListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        Log.d(TAG, "onCreate: Activity started");
        Button btnAddList = findViewById(R.id.btnAddNewList);
        etListName = findViewById(R.id.etListName);
        btnAddList.setOnClickListener(v -> {
            ProductsList productsList = new ProductsList(etListName.getText().toString());
            Intent addProductsToListIntent = new Intent(getApplicationContext(), AddProductsToListActivity.class);
            addProductsToListIntent.putExtra("list_without_products", productsList);
            startActivity(addProductsToListIntent);
        });

    }
}
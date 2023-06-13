package com.mirea.kt.android2023.coursework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Activity started");
        Button btnLogIn = findViewById(R.id.btnLogIn);

        btnLogIn.setOnClickListener( v -> {
            Log.d(TAG, "onCreate: Log In Button Pressed");
            EditText etLogin = findViewById(R.id.etLogin);
            EditText etPassword = findViewById(R.id.etPassword);
            TextView tvMessage = findViewById(R.id.tvMessage);

            HashMap<String, String> requestBody = new HashMap<>();
            requestBody.put("lgn", etLogin.getText().toString());
            requestBody.put("pwd", etPassword.getText().toString());
            requestBody.put("g", "RIBO-04-21");

            HTTPRunnable httpRunnable = new HTTPRunnable
                    ("https://android-for-students.ru/coursework/login.php", requestBody);
            Thread th = new Thread(httpRunnable);
            th.start();
            try {
                th.join();
                Log.d(TAG, "onCreate: " + httpRunnable.getResponseBody());

            }catch (Exception ex) {
                Log.d(TAG, "onCreate: " + ex.getMessage());
                tvMessage.setText("Ошибка подключения");
            }
            try {
                JSONObject jsonResponse = new JSONObject(httpRunnable.getResponseBody());
                int resultCode = jsonResponse.getInt("result_code");
                if(resultCode == 1) {
                    tvMessage.setText("OK");
                    Intent showProductsListsIntent = new Intent(getApplicationContext(), ShowProductsListsActivity.class);
                    startActivity(showProductsListsIntent);
                }else{
                    tvMessage.setText("Неверный логин или пароль");
                }

            }catch (Exception ex){
                tvMessage.setText("Ошибка подключения");
                Log.d(TAG, "onCreate: " + ex.getMessage());
            }

        });
    }
}
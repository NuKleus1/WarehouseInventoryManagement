package com.example.warehouseinventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button pickupButton, dropButton, addProductButton, inventoryButton;
//    Product productClass = new Product();
//    HashMap<Integer, String> product = productClass.getProduct();
//    HashMap<Integer, Integer> productQuantity = productClass.getProductQuantity();
//    ArrayList<Integer> productID = productClass.getProductID();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pickupButton = findViewById(R.id.pickup);
        dropButton = findViewById(R.id.drop);
        addProductButton = findViewById(R.id.addProduct);
        inventoryButton = findViewById(R.id.inventory);

        pickupButton.setOnClickListener(view -> {

            Intent intent = new Intent(MainActivity.this, fillDetails.class);
            intent.putExtra("key", "Pickup");
//            Bundle extras = new Bundle();
//            extras.putSerializable("product", product);
//            extras.putSerializable("productQuantity", productQuantity);
//            extras.putSerializable("productID", productID);
//            intent.putExtras(extras);
            startActivity(intent);
        });

        dropButton.setOnClickListener(view -> {

            Intent intent = new Intent(MainActivity.this, fillDetails.class);
            intent.putExtra("key", "Drop");
            startActivity(intent);
        });

        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, addProduct.class);
                startActivity(intent);
            }
        });

        inventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, inventoryList.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    public void saveHashMap(String key , Object obj) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        editor.putString(key,json);
        editor.apply();
    }

    public HashMap<Integer,Integer> getHashMap(String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        Gson gson = new Gson();
        String json = prefs.getString(key,"");
        java.lang.reflect.Type type = new TypeToken<HashMap<Integer,Integer>>(){}.getType();
        HashMap<Integer,Integer> obj = gson.fromJson(json, type);
        return obj;
    }
}
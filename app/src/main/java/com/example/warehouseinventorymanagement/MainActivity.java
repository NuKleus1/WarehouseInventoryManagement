package com.example.warehouseinventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
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

        getHashMap();
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

        saveHashMap();
    }

    public void saveHashMap() {
        try {
            SharedPreferences prefs = getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            Gson gson = new Gson();
            editor.putString("productID", gson.toJson(Product.productID));
            editor.putString("binID", gson.toJson(Product.binId));
            editor.putString("product", gson.toJson(Product.product));
            editor.putString("productQuantity", gson.toJson(Product.productQuantity));
            editor.putString("binQuantity", gson.toJson(Product.binQuantity));
            editor.putString("binproduct", gson.toJson(Product.binproduct));
            editor.apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getHashMap() {
        try {
            SharedPreferences prefs = getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE);
            Gson gson = new Gson();
            java.lang.reflect.Type type = new TypeToken<HashMap<Integer, Integer>>() {}.getType();

            String json = prefs.getString("productQuantity", "");
            if (json != ""){
                HashMap<Integer, Integer> obj = gson.fromJson(json, type);
                Product.productQuantity = obj;
            }

            json = prefs.getString("binQuantity", "");
            if (json != ""){
                HashMap<Integer, Integer> obj = gson.fromJson(json, type);
                Product.binQuantity = obj;
            }

            json = prefs.getString("binproduct", "");
            if (json != ""){
                HashMap<Integer, Integer> obj = gson.fromJson(json, type);
                Product.binproduct = obj;
            }

            java.lang.reflect.Type type1 = new TypeToken<HashMap<Integer, String>>() {}.getType();
            json = prefs.getString("product", "");
            if (json != ""){
                HashMap<Integer, String> obj = gson.fromJson(json, type1);
                Product.product = obj;
            }

            java.lang.reflect.Type type2 = new TypeToken<ArrayList<Integer>>() {}.getType();
            json = prefs.getString("productID", "");
            if (json != ""){
                ArrayList<Integer> obj = gson.fromJson(json, type2);
                Product.productID = obj;
            }

            java.lang.reflect.Type type3 = new TypeToken<ArrayList<Integer>>() {}.getType();
            json = prefs.getString("binId", "");
            if (json != ""){
                ArrayList<Integer> obj = gson.fromJson(json, type3);
                Product.binId = obj;
            }



        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveHashMap();
    }
}
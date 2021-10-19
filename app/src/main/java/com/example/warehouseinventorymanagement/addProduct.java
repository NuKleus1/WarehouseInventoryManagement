package com.example.warehouseinventorymanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Random;

public class addProduct extends AppCompatActivity {

    Button confirm;
    EditText quantityText, productnameText;
    TextView showview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproduct);

        confirm = findViewById(R.id.confirmButton4);
        quantityText = findViewById(R.id.quantity4);
        productnameText = findViewById(R.id.productname);
        showview = findViewById(R.id.show4);

        confirm.setOnClickListener(view -> {
            try {
                String productname = productnameText.getText().toString();
                if(productname.isEmpty()){
                    throw new ArithmeticException("Empty");
                }
                if(quantityText.getText().toString() == ""){
                    throw new ArrayStoreException("Empty");
                }
                int quantity = Integer.parseInt(quantityText.getText().toString());
                int i;
                i = setValues(productname, quantity);
                if(i == 0){
                    Toast.makeText(addProduct.this, "Product Added", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(addProduct.this, "Error Occured while adding the product!", Toast.LENGTH_LONG).show();
                }
            }catch (ArithmeticException e){
                Toast.makeText(addProduct.this, "Enter Product Name", Toast.LENGTH_LONG).show();
            }catch (ArrayStoreException e){
                Toast.makeText(addProduct.this, "Enter Quantity Name", Toast.LENGTH_LONG).show();
            }
            catch(Exception e){
                Toast.makeText(addProduct.this, "Error Occured !", Toast.LENGTH_LONG).show();
            }


        });

    }

    public int setValues(String productname, int quantity){
        int n = new Random().nextInt(100);
        if (Product.product.containsKey(n)){
            return 1;
        }else if(productname.isEmpty()){
            return 1;
        }
        else{
            Product.product.put(n, productname);
            Product.productID.add(n);
            Product.productQuantity.put(n, quantity);
            String message = "Product ID for "+productname+" is "+n;
            showview.setText(message);
            quantityText.setText("");
            productnameText.setText("");
            return 0;
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
    }
}
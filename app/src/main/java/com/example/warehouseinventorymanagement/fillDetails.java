package com.example.warehouseinventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class fillDetails extends AppCompatActivity {

    EditText productIDText, quantityText;
    TextView showText;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fill_details);

        confirm = findViewById(R.id.confirmButton);
        productIDText = findViewById(R.id.productID);
        quantityText = findViewById(R.id.quantity);
        showText = findViewById(R.id.show);

        Intent intent = getIntent();
        String buttonPressed = intent.getStringExtra("key");

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonPressed.equals("Pickup")){
                    try {
                        int productid = Integer.parseInt(productIDText.getText().toString());
                        int quantity = Integer.parseInt(quantityText.getText().toString());
                        pickup(productid, quantity);
                    }catch (Exception e){
                        Toast.makeText(fillDetails.this, "Pickup Update Error Occured!", Toast.LENGTH_LONG).show();
                    }

                }else if(buttonPressed.equals("Drop")){
                    try {
                        int productid = Integer.parseInt(productIDText.getText().toString());
                        int quantity = Integer.parseInt(quantityText.getText().toString());
                        drop(productid, quantity);
                        Toast.makeText(fillDetails.this, "Drop Update", Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        Toast.makeText(fillDetails.this, "Drop Update Error Occured!", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private int pickup(int productid, int quantity) {
        if(!Product.productID.contains(productid)){
            showText.setText("Product ID doesn't Exist. You have to add the Product First and generate an ID");
            return 0;
        }else if(!Product.binproduct.containsValue(productid)){
            String s = "Product ID: "+productid+"  is not in the Inventory. You need to Drop in the Bin First to Pickup";
            showText.setText(s);
            return 0;
        }else{

            // Get Bin Ids containing ProductID
            ArrayList<Integer> bins = new ArrayList<Integer>();
            for(Map.Entry<Integer, Integer> entry : Product.binproduct.entrySet()){
                if (entry.getValue() == productid){
                    bins.add(entry.getKey());
                }
            }
            int flag = 0;

            // Search for the Bin ID from where Pickup is Possible
            for(Integer i: bins){
                if(Product.binQuantity.get(i) >= quantity) {
                    Product.binQuantity.put(i, Product.binQuantity.get(i) - quantity);
                    Product.productQuantity.put(productid, Product.productQuantity.get(productid) - quantity);
                    String s = "From Bin ID:"+i+"\nQuantity Picked: "+quantity+"\nProduct ID: "+productid+"\nCurrent Quantity: "+Product.binQuantity.get(i);
                    showText.setText(s);
                    flag = 1;
                }
            }

            if (flag != 1){
                String s = "Quantity Not sufficient to Pickup";
                showText.setText(s);
                productIDText.setText("");
                quantityText.setText("");
                return 0;
            }
            return 0;
        }

    }

    int drop(int productid, int quantity){

        // Check if ProductID exist
        // Check if it was
        if(!Product.productID.contains(productid)){
            showText.setText("Product ID doesn't Exist. You have to add the Product First and generate an ID");
            return 0;
        }else if (!Product.binproduct.containsValue(productid)){
            createBin(productid, quantity);
            return 0;
        }else{
            // Get Bin Ids containing Product
            ArrayList<Integer> bins = new ArrayList<Integer>();
            for(Map.Entry<Integer, Integer> entry : Product.binproduct.entrySet()){
                if (entry.getValue() == productid){
                    bins.add(entry.getKey());
                }
            }
            int flag = 0;

            //Search for space to Drop ProductID in Bin
            for(Integer i : bins){
                if (i == null) {
                    Toast.makeText(fillDetails.this, "Error Occured while Updating Drop", Toast.LENGTH_SHORT).show();
                    break;
                }
                if(quantity <= (Product.MAX - Product.binQuantity.get(i))){
                    Product.binQuantity.put(i, Product.binQuantity.get(i)+quantity);
                    Product.productQuantity.put(productid, Product.productQuantity.get(productid)+quantity);
                    String s = "Dropped Product "+Product.product.get(productid)+"  at Bin ID: "+i+" with Total quantity to: "+Product.binQuantity.get(i);
                    showText.setText(s);
                    Toast.makeText(fillDetails.this, "Drop Location Updated", Toast.LENGTH_SHORT).show();
                    flag = 1;
                    break;
                }
            }
            if (flag != 1){
                createBin(productid, quantity);
            }
        }
        return 0;
    }

    private void createBin(int productid, int quantity) {
        Random i = new Random();
        int n = i.nextInt(1000);
        while(Product.binId.contains(n)){
            n = i.nextInt(1000);
        }
        Product.binId.add(n);
        Product.binQuantity.put(n, quantity);
        Product.binproduct.put(n, productid);
        String s = "Drop the "+Product.product.get(productid)+"at Bin ID: "+n+" with Total quantity to: "+Product.binQuantity.get(n);
        showText.setText(s);
        Toast.makeText(fillDetails.this, "Dropped into new Bin", Toast.LENGTH_LONG).show();
    }

}
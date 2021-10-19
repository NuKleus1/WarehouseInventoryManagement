package com.example.warehouseinventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class inventoryList extends AppCompatActivity {

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_list);

        listView = findViewById(R.id.inventoryList);

        ArrayList<HashMap<String, Object>> list = new ArrayList<>();

        for(Integer i: Product.product.keySet()){
            int quantity=0;
            String binIds = "";

            for(Map.Entry<Integer, Integer> entry : Product.binproduct.entrySet()){
                if (entry.getValue() == i){
                    binIds += entry.getKey().toString()+" ";
                    quantity += Product.binQuantity.get(entry.getKey());
                }
            }


            HashMap<String, Object> map = new HashMap<>();

            map.put("productName", Product.product.get(i));
            map.put("productBin", binIds);
            map.put("quantity", quantity);

            list.add(map);
        }

        String[] from = {"productName", "productBin", "quantity" };
        int to[] = {R.id.productname, R.id.binIDText, R.id.quantity};

        SimpleAdapter simpleAdapter = new SimpleAdapter(inventoryList.this, list, R.layout.cardview, from, to);
        listView.setAdapter(simpleAdapter);

    }
}
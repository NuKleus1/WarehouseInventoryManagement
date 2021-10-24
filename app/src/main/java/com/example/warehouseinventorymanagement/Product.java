package com.example.warehouseinventorymanagement;

import java.util.ArrayList;
import java.util.HashMap;

public class Product {

    // product[productID, productName]
    static HashMap<Integer, String> product = new HashMap<>();

    // productQuantity[productID, quantity]
    static HashMap<Integer, Integer> productQuantity = new HashMap<>();

    // binQuantity[binId, quantity]
    static HashMap<Integer, Integer> binQuantity = new HashMap<>();

    // binproduct[binId, productID]
    static HashMap<Integer, Integer> binproduct = new HashMap<>();

    static ArrayList<Integer> productID = new ArrayList<>();
    static ArrayList<Integer> binId = new ArrayList<>();

    // MAX BIN CAPACITY
    final static int MAX = 1000;

    public static HashMap<Integer, String> getProduct() {
        return product;
    }

    public static void setProduct(HashMap<Integer, String> product) {
        Product.product = product;
    }

    public static HashMap<Integer, Integer> getProductQuantity() {
        return productQuantity;
    }

    public static void setProductQuantity(HashMap<Integer, Integer> productQuantity) {
        Product.productQuantity = productQuantity;
    }


    public static ArrayList<Integer> getProductID() {
        return productID;
    }

    public static void setProductID(ArrayList<Integer> productID) {
        Product.productID = productID;
    }

    public static HashMap<Integer, Integer> getBinQuantity() {
        return binQuantity;
    }

    public static void setBinQuantity(HashMap<Integer, Integer> binQuantity) {
        Product.binQuantity = binQuantity;
    }

    public static HashMap<Integer, Integer> getBinproduct() {
        return binproduct;
    }

    public static void setBinproduct(HashMap<Integer, Integer> binproduct) {
        Product.binproduct = binproduct;
    }

    public static ArrayList<Integer> getBinId() {
        return binId;
    }

    public static void setBinId(ArrayList<Integer> binId) {
        Product.binId = binId;
    }
}

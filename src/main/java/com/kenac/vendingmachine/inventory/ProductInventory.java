/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenac.vendingmachine.inventory;

import com.kenac.vendingmachine.enums.Product;
import java.util.EnumMap;
import java.util.Map;

/**
 *
 * @author Kanaz
 */
public class ProductInventory {
     private Map<Product, Integer> products = new EnumMap<>(Product.class); 
     public int numberOfItems(Product product) {
        Integer value = products.get(product);
        return value == null ? 0 : value;
    }
      public void addProduct(Product product) {
        int count = products.get(product);
        products.put(product, count + 1);
    }

    public void deductProduct(Product product) {
        if (isProductAvailable(product)) {
            int count = products.get(product);
            products.put(product, count - 1);
        }
    }

    public boolean isProductAvailable(Product product) {
        return numberOfItems(product) > 0;
    }

    public void clear() {
        products.clear();
    }

    public void addProductAndQuantity(Product product, int quantity) {
        products.put(product, quantity);
    }
}

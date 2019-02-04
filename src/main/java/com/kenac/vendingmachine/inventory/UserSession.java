/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenac.vendingmachine.inventory;

import com.kenac.vendingmachine.enums.Coin;
import java.util.EnumMap;
import java.util.Map;

/**
 *
 * @author Kanaz
 */
public class UserSession {
 
     private Map<Coin, Integer> coins = new EnumMap<>(Coin.class); 
    public int numberOfCoins(Coin coin) {
        Integer value = coins.get(coin);
        return value == null ? 0 : value;
    }
      public void addCoin(Coin coin) {
        if (coins.containsKey(coin)) {
            int count = coins.get(coin);
            coins.put(coin, count + 1);
        }
        else{
           coins.put(coin, 1); 
        }

    }
       public void clear() {
        coins.clear();
    }
    
   
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenac.vendingmachine;

import com.kenac.vendingmachine.enums.Coin;
import com.kenac.vendingmachine.enums.Product;

/**
 *
 * @author Kanaz
 */
public interface VendingMachineService {
    public void punchCoin(Coin coin);
    public int sumUserCoins();   
    public int countCoins(Coin coin);
    public void cancelPurchase();
    public int purchaseProduct(Product product);
    public void reset();
    public int countProduct(Product product);
    public void clearValues();
}

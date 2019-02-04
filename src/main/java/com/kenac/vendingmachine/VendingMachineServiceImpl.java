/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenac.vendingmachine;

import com.kenac.vendingmachine.enums.Coin;
import com.kenac.vendingmachine.enums.Product;
import com.kenac.vendingmachine.exceptions.InSufficientChangeException;
import com.kenac.vendingmachine.exceptions.NotEnoughCoinsToPurchaseException;
import com.kenac.vendingmachine.exceptions.ProductNotAvailableException;
import com.kenac.vendingmachine.inventory.CoinInventory;
import com.kenac.vendingmachine.inventory.ProductInventory;
import com.kenac.vendingmachine.inventory.UserSession;
import java.util.EnumMap;
import java.util.Map;

/**
 *
 * @author Kanaz
 */
public class VendingMachineServiceImpl implements VendingMachineService {

    UserSession session = new UserSession();
    CoinInventory coinInventory = new CoinInventory();
    ProductInventory productInventory = new ProductInventory();

    public VendingMachineServiceImpl() {
       initVendingMachine();
    }

    @Override
    public void punchCoin(Coin coin) {
        session.addCoin(coin);
        coinInventory.addCoin(coin);
    }

    @Override
    public int sumUserCoins() {
        int sum = 0;
        for (Coin coin : Coin.values()) {
            int qty = session.numberOfCoins(coin);
            sum += (qty * coin.getDenomination());
        }
        return sum;
    }

    @Override
    public int countCoins(Coin coin) {
        return coinInventory.numberOfCoins(coin);
    }

    @Override
    public void cancelPurchase() {
        removeCoinsFromInventoryAndSession();
        //session.clear();
    }

    public void removeCoinsFromInventoryAndSession() {
        for (Coin coin : Coin.values()) {
            int availableCoins = session.numberOfCoins(coin);
            coinInventory.deductCoinsByQuantity(coin, availableCoins);
        }
    }

    @Override
    public int purchaseProduct(Product product) {
        return requestProduct(product);
    }

    public int requestProduct(Product product) throws NotEnoughCoinsToPurchaseException, InSufficientChangeException, ProductNotAvailableException {
        int coinsPunchedAmount = sumUserCoins();

        int change = coinsPunchedAmount - product.getPrice();
        if (coinsPunchedAmount >= product.getPrice()) {
            if (isProductAvailable(product)) {
                if (isChangeSufficient(change)) {
                    productInventory.deductProduct(product);
                    return change;
                } else {
                    throw new InSufficientChangeException("Insufficient Change");
                }
            } else {
                throw new ProductNotAvailableException(product.getName() + " is not available, Buy Another Product");
            }
        } else {
            int balance = product.getPrice() - coinsPunchedAmount;
            throw new NotEnoughCoinsToPurchaseException("Not Enough Coins to Buy, remaining balance", balance);
        }
    }

    public boolean isChangeSufficient(int change) throws InSufficientChangeException {

        boolean sufficient;
        Map<Coin, Integer> coins = new EnumMap<>(Coin.class);
        for (Coin coin : Coin.values()) {
            if (change > 0) {
                System.out.println(coin.name()+ " - Current Change :"+ change);
                int qty = coinInventory.numberOfCoins(coin);
                int amt = qty * coin.getDenomination();
                int divAnswer = amt/change;
                int remainder = amt%change;
                int coinBalance = amt - change;
                int coinQtyBal = coinBalance/coin.getDenomination();
                int tranferedToChange = qty - coinQtyBal;
                
                
                if(qty>0){ 
                    if(change==coin.getDenomination()){
                        System.out.println("change==deno");
                        coins.put(coin, 1);
                        change = 0;
                    }
                    else if(change<=amt && change%coin.getDenomination()==0){
                        change = change-(tranferedToChange * coin.getDenomination());
                        coins.put(coin, tranferedToChange);
                        System.out.println("Empty Statement - Change :"+ change); 
                    }
                    else if(divAnswer!=0 && remainder==0){
                        System.out.println("divAnswer :"+ divAnswer+"!=0  remainder: "+remainder+"=0");
                         change = (divAnswer*change)-change;
                         System.out.println("Change : divAnswer"+ change);
                         coins.put(coin, qty);
                     }
               
                }
            }
        }
        if (change == 0) {
            deductCoinInventory(coins);
            sufficient = true;
        } else {
            sufficient = false;
            throw new InSufficientChangeException("Insufficient Coins For Change");
        }

        return sufficient;
    }

    public void deductCoinInventory(Map<Coin, Integer> coins) {
        for (Coin coin : Coin.values()) {
            if (coins.containsKey(coin)) {
                System.out.println("Deducting From "+ coin.name());
                coinInventory.deductCoinsByQuantity(coin, coins.get(coin));
            }
        }
    }

    public boolean isProductAvailable(Product product) throws ProductNotAvailableException {
        return productInventory.isProductAvailable(product);
    }

    @Override
    public void reset() {
        coinInventory.clear();
        productInventory.clear();
        session.clear();
        initVendingMachine();
    }
    public void initVendingMachine(){
        for (Coin coin : Coin.values()) {
            coinInventory.addCoinAndQuantity(coin, 5);
        }
        for (Product product : Product.values()) {
            productInventory.addProductAndQuantity(product, 5);
        }
    }

    @Override
    public int countProduct(Product product) {
        return productInventory.numberOfItems(product);
    }

    @Override
    public void clearValues() {
        session.clear();
    }

}

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
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kanaz
 */
public class VendingMachineServiceImplTest {
    
    public VendingMachineServiceImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of punchCoin method, of class VendingMachineServiceImpl.
     */
    @Test
    public void testPunchCoin() {
        System.out.println("punchCoin");
      VendingMachineService  service = new VendingMachineServiceImpl();
        // punching a Penny in the vending machine will result the total pennies to be 6 
        // since there is already other 5
         service.punchCoin(Coin.PENNY);
         assertEquals(6, service.countCoins(Coin.PENNY));
    }

    /**
     * Test of sumUserCoins method, of class VendingMachineServiceImpl.
     */
    @Test
    public void testSumUserCoins() {
        System.out.println("sumUserCoins");
         VendingMachineService  service = new VendingMachineServiceImpl();
        // we have added Quarter and Penny for the customer which should amount to 25
        service.punchCoin(Coin.QUARTER);
        service.punchCoin(Coin.PENNY);
        assertEquals(26, service.sumUserCoins());
    }

    /**
     * Test of countCoins method, of class VendingMachineServiceImpl.
     */
    @Test
    public void testCountCoins() {
        System.out.println("countCoins");
         VendingMachineService  service = new VendingMachineServiceImpl();
        //we haven't add Dime Coins therefore the expected result should be 5 which is the initial value
         assertEquals(5, service.countCoins(Coin.DIME));
    }

    /**
     * Test of cancelPurchase method, of class VendingMachineServiceImpl.
     */
    @Test
    public void testCancelPurchase() {
        System.out.println("cancelPurchase");
         VendingMachineService  service = new VendingMachineServiceImpl();
        //Since we are cancelling a purchase, number of coins in the inventory should default to 5
        service.cancelPurchase();
        assertEquals(5, service.countCoins(Coin.DIME));
    }

    /**
     * Test of removeCoinsFromInventoryAndSession method, of class VendingMachineServiceImpl.
     */
    @Test
    public void testRemoveCoinsFromInventoryAndSession() {
        System.out.println("removeCoinsFromInventoryAndSession");
         VendingMachineService  service = new VendingMachineServiceImpl();
        service.punchCoin(Coin.NICKEL);
        service.cancelPurchase();
        assertEquals(5, service.countCoins(Coin.NICKEL));
    }

    /**
     * Test of purchaseProduct method, of class VendingMachineServiceImpl.
     */
    @Test(expected = NotEnoughCoinsToPurchaseException.class)
    public void testPurchaseProduct() {
       VendingMachineService  service = new VendingMachineServiceImpl();
        ProductInventory inventory = new ProductInventory();
        inventory.clear();
        service.purchaseProduct(Product.SODA);
        assertNull(service.countProduct(Product.SODA));//we expect null because we have cleared the inventory
    }

    /**
     * Test of requestProduct method, of class VendingMachineServiceImpl.
     */
    @Test()
    public void testRequestProduct() {
        System.out.println("requestProduct");
    }

    /**
     * Test of isChangeSufficient method, of class VendingMachineServiceImpl.
     */
    @Test
    public void testIsChangeSufficient() {
        System.out.println("isChangeSufficient");
      
    }

    /**
     * Test of deductCoinInventory method, of class VendingMachineServiceImpl.
     */
    @Test
    public void testDeductCoinInventory() {
        System.out.println("deductCoinInventory");
      
    }

    /**
     * Test of isProductAvailable method, of class VendingMachineServiceImpl.
     */
    @Test
    public void testIsProductAvailable() {
        System.out.println("isProductAvailable");
      
    }

    /**
     * Test of reset method, of class VendingMachineServiceImpl.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
       
    }

    /**
     * Test of initVendingMachine method, of class VendingMachineServiceImpl.
     */
    @Test
    public void testInitVendingMachine() {
        System.out.println("initVendingMachine");
      
    }

    /**
     * Test of countProduct method, of class VendingMachineServiceImpl.
     */
    @Test
    public void testCountProduct() {
        System.out.println("countProduct");
     
    }

    /**
     * Test of clearValues method, of class VendingMachineServiceImpl.
     */
    @Test
    public void testClearValues() {
        System.out.println("clearValues");
      
    }
    
}

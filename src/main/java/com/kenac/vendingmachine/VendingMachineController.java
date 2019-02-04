package com.kenac.vendingmachine;

import com.kenac.vendingmachine.enums.Coin;
import com.kenac.vendingmachine.enums.Product;
import com.kenac.vendingmachine.exceptions.InSufficientChangeException;
import com.kenac.vendingmachine.exceptions.NotEnoughCoinsToPurchaseException;
import com.kenac.vendingmachine.exceptions.ProductNotAvailableException;
import com.kenac.vendingmachine.inventory.CoinInventory;
import com.kenac.vendingmachine.inventory.ProductInventory;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class VendingMachineController implements Initializable {

    @FXML
    private TextField coinText;
    @FXML
    private Button btnPunchCoin;
    @FXML
    private Button purchaseBtn;
    @FXML
    private RadioButton coke;
    @FXML
    private RadioButton pepsi;
    @FXML
    private RadioButton soda;

   // @FXML
  //  private Label customerChange;
    @FXML
    private Label punchedCoins;
    @FXML
    private Label purchasedProduct;
    @FXML
    private Label purchaseInfo;
    
    @FXML
    private Label cokeAvailable;
     @FXML
    private Label pepsiAvailable;
     @FXML
    private Label sodaAvailable;
      @FXML
    private Label pennyAvailable;
     @FXML
    private Label nickelAvailable;
     @FXML
    private Label dimeAvailable;
      @FXML
    private Label quarterAvailable;

    int totalAmtOfPunchedCoins = 0;

    private Product selectedProduct;
    private VendingMachineService service;

    int amt = 0;
    List<Integer> punchedCoinsList = new ArrayList<>();

    @FXML
    private void punchCoin(ActionEvent event) {
        if (!coinText.getText().isEmpty()) {
            punchCoin(Integer.valueOf(coinText.getText()));
            punchedCoins.setText(String.valueOf(service.sumUserCoins()));
        } else {
            showNotification("Error", "Coin Invalid", "Coin Is Required in denomination of 1, 5, 10, 25", AlertType.ERROR);
        }
        coinText.setText("");
      //  resetValues();
    }
    @FXML
    private void reset(ActionEvent event) {
        service.reset();
        resetValues();           
        loadSupplierTab();
    }

    @FXML
    private void purchaseProduct(ActionEvent event) {
        if (selectedProduct != null) {
            try {
                int change = service.purchaseProduct(selectedProduct);
              //  customerChange.setText(String.valueOf(change));
              //  cancelPurchase(event);
                punchedCoins.setText("0");
                purchasedProduct.setText("Thank You For Buying " + selectedProduct.name());
                purchaseInfo.setText("Your Change is "+ change + " cents");
                showNotification("Success", "Purchase Complete", "Punch Coins To buy another Product", AlertType.INFORMATION);
                service.clearValues();
                selectedProduct = null;
                 coke.setSelected(false);
                pepsi.setSelected(false);
                soda.setSelected(false);
            } catch (ProductNotAvailableException | InSufficientChangeException | NotEnoughCoinsToPurchaseException ex) {
                showNotification("Error", "Purchase Failed", ex.getMessage(), AlertType.ERROR);
            }
        } else {
            showNotification("Error", "Purchase Failed", "Please Select Product", AlertType.ERROR);
        }
    }

    @FXML
    private void cancelPurchase(ActionEvent event) {
        service.cancelPurchase();
        selectedProduct = null;
        punchedCoins.setText(String.valueOf(service.sumUserCoins()));
        coke.setSelected(false);
        pepsi.setSelected(false);
        soda.setSelected(false);
        purchasedProduct.setText("Purchase Cancelled!!!");
        purchaseInfo.setText("Your Refund is "+ String.valueOf(service.sumUserCoins())+ " cents");
        service.clearValues();
    }

    @FXML
    private void cokeSelected(ActionEvent event) {
        selectedProduct = Product.COKE;
        purchaseBtn.setText("Purchase Coke");
    }

    @FXML
    private void pepsiSelected(ActionEvent event) {
        selectedProduct = Product.PEPSI;
        purchaseBtn.setText("Purchase Pepsi");
    }

    @FXML
    private void sodaSelected(ActionEvent event) {
        selectedProduct = Product.SODA;
        purchaseBtn.setText("Purchase Soda");
    }
    @FXML
    private void supplierTab(){
      loadSupplierTab();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        service = new VendingMachineServiceImpl();
        coinText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    long coin = Integer.parseInt(newValue);
                    purchaseInfo.setText("");
                    purchasedProduct.setText("");
                    coinText.setText(String.valueOf(coin));
                } catch (Exception ex) {
                    coinText.clear();
                }
            }
        });
    }

    public void punchCoin(int coin) {
        switch (coin) {
            case 1:
                service.punchCoin(Coin.PENNY);
                break;
            case 5:
                service.punchCoin(Coin.NICKEL);
                break;
            case 10:
                service.punchCoin(Coin.DIME);
                break;
            case 25:
                service.punchCoin(Coin.QUARTER);
                break;
            default:
                showNotification("Error", "Invalid Coin", "Please Enter Coins of Denomination(1, 5, 10, 25)", AlertType.ERROR);
                break;

        }
    }

    public void showNotification(String title, String headerText, String content, AlertType alert) {
        Alert a = new Alert(alert);
        a.setTitle(title);
        a.setHeaderText(headerText);
        a.setContentText(content);
        a.showAndWait();
    }
   public void resetValues(){
        coinText.setText("");
        purchasedProduct.setText("");
        purchaseInfo.setText("");
        selectedProduct = null;
        punchedCoins.setText(String.valueOf(service.sumUserCoins()));
        coke.setSelected(false);
        pepsi.setSelected(false);
        soda.setSelected(false);
        service.clearValues();
    }
   public void loadSupplierTab(){
       int cokes = service.countProduct(Product.COKE);
       int pepsis = service.countProduct(Product.PEPSI);
       int sodas = service.countProduct(Product.SODA);
       int pennies = service.countCoins(Coin.PENNY);
       int nickels = service.countCoins(Coin.NICKEL);
       int dimes = service.countCoins(Coin.DIME);
       int quarters = service.countCoins(Coin.QUARTER);
       cokeAvailable.setText("COKE\t\t"+ cokes);
       pepsiAvailable.setText("PEPSI\t\t"+ pepsis);
       sodaAvailable.setText("SODA\t\t"+ sodas);
       
       pennyAvailable.setText("PENNY\t\t"+pennies);
       nickelAvailable.setText("NICKEL\t\t"+nickels);
       dimeAvailable.setText("DIME\t\t"+dimes);
       quarterAvailable.setText("QUARTER\t\t"+quarters);
   }
}

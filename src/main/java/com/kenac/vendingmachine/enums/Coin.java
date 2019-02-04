/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenac.vendingmachine.enums;

/**
 *
 * @author Kanaz
 */
public enum Coin {
      PENNY(1), NICKEL(5), DIME(10), QUARTER(25);
       private final Integer denomination;


    Coin(Integer denomination) {
        this.denomination = denomination;
    }

    public static Coin get(Integer denomination) {
        for (Coin item : values()) {
            if (item.denomination.equals(denomination)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown parameter passed to method");
    }

    public Integer getDenomination() {
        return denomination;
    }

    
   
}

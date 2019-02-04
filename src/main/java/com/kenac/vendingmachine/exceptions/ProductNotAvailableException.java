/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenac.vendingmachine.exceptions;

/**
 *
 * @author Kanaz
 */
public class ProductNotAvailableException extends RuntimeException{
     private String message;

    public ProductNotAvailableException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

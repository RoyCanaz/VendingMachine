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
public class InSufficientChangeException extends RuntimeException{
   private String message;

    public InSufficientChangeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

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
public class NotEnoughCoinsToPurchaseException extends RuntimeException{
    private String message;
    private long remaining;

    public NotEnoughCoinsToPurchaseException(String message, long remaining) {
        this.message = message;
        this.remaining = remaining;
    }

    public long getRemaining() {
        return remaining;
    }

    @Override
    public String getMessage() {
        return message + remaining;
    }
}

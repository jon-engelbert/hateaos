package com.example.Exceptions;

public class CustomerNotFoundException  extends Exception {

    public CustomerNotFoundException(String message) {
        super(message);
    }

}
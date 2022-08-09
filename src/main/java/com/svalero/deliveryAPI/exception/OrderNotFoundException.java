package com.svalero.deliveryAPI.exception;

public class OrderNotFoundException extends Exception{

    private static final String DEFAULT_ERROR_MESSAGE = "Order not found";

    public OrderNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}

package com.svalero.deliveryAPI.exception;

public class RiderNotFoundException extends Exception{
    private static final String DEFAULT_ERROR_MESSAGE = "Rider not found";

    public RiderNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}

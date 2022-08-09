package com.svalero.deliveryAPI.exception;

public class RestaurantNotFoundException extends Exception {

    private static final String DEFAULT_ERROR_MESSAGE = "Restaurant not found";

    public RestaurantNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }

}

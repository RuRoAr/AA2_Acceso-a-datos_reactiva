package com.svalero.deliveryAPI.exception;

public class RestaurantNotFoundException extends Exception {

    private static String DEFAULT_ERROR_MESSAGE = "Restaurnat not found";

    public RestaurantNotFoundException(String message){
        super(message);
    }
    public RestaurantNotFoundException(){//por si no me aptece pasar mensaje
        super(DEFAULT_ERROR_MESSAGE);
    }

}

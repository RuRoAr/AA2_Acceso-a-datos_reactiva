package com.svalero.deliveryAPI.exception;

public class OrderNotFoundException extends Exception{

    private static String DEFAULT_ERROR_MESSAGE = "Orden not found";

    public OrderNotFoundException(String message){
        super(message);
    }
    public OrderNotFoundException(){//por si no me aptece pasar mensaje
        super(DEFAULT_ERROR_MESSAGE);
    }

}

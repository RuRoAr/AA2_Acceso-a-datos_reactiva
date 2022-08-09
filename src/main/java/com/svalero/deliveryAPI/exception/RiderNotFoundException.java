package com.svalero.deliveryAPI.exception;

public class RiderNotFoundException extends Exception{
    private static String DEFAULT_ERROR_MESSAGE = "Ciclista not found";

    public RiderNotFoundException(String message){
        super(message);
    }
    public RiderNotFoundException(){//por si no me aptece pasar mensaje
        super(DEFAULT_ERROR_MESSAGE);
    }
}

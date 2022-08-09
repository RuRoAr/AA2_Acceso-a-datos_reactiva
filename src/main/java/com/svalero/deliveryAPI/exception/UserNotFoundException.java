package com.svalero.deliveryAPI.exception;

public class UserNotFoundException extends Exception{
    private static String DEFAULT_ERROR_MESSAGE = "Usuario not found";

    public UserNotFoundException(String message){
        super(message);
    }
    public UserNotFoundException(){//por si no me aptece pasar mensaje
        super(DEFAULT_ERROR_MESSAGE);
    }
}

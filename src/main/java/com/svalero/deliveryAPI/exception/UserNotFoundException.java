package com.svalero.deliveryAPI.exception;

public class UserNotFoundException extends Exception{
    private static final String DEFAULT_ERROR_MESSAGE = "User not found";

    public UserNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}

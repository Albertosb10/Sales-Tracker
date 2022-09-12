package com.asolorzano.salesTracker.exceptions;

//para cuando salag el erro http 404 muestre el error

public class ModelNotFoundException extends Exception{


    //@ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelNotFoundException(String message) {
        super(message);
    }
}

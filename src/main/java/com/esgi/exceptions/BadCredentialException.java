package com.esgi.exceptions;

/**
 * Created by valentin on 25/11/2016.
 */
public class BadCredentialException extends RuntimeException {

    public BadCredentialException(){
        super("You have a non existing account ! you fool");
    }
}


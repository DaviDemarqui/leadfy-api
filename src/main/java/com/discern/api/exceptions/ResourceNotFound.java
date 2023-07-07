package com.discern.api.exceptions;

/**
 * @author Davi
 * @created 21/jun./2023
 */
public class ResourceNotFound extends RuntimeException {
    protected ResourceNotFound(String message){
        super(message);
    }

}

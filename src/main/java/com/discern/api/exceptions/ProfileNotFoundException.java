package com.discern.api.exceptions;

/**
 * @author Davi
 * @created 21/jun./2023
 */
public class ProfileNotFoundException extends ResourceNotFound {
    public ProfileNotFoundException() { super("User Profile Not Found!"); }
}

package com.discern.api.exceptions;

public class CompanyNotFoundException extends ResourceNotFound {
    public CompanyNotFoundException() { super("Company Not Found!"); }

}

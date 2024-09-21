package com.emazon.api_transaction.infraestructure.exceptionhandler;

import lombok.Getter;

@Getter
public class ConstantsException {

    public static final String NO_DATA_FOUND_EXCEPTION_MESSAGE = "No data found in the database";
    public static final String ACCESS_DENE = "Access denied";
    public static final String INCORRECT_DATA = "Incorrect login information";
    public static final String SERVICE_NOT_AVAILABLE = "The service is not available, please try again later.";
    public static final String STOCK_CONFLICT = "The article does not exist in the database.";
    public static final String ERROR_STOCK = "An error occurred while updating the article.";
    public static final String ACCESS_DENIED = "Access Denied";
    public static final String BAD_CREDENTIALS = "Bad Credentials";
    public static final String CLIENT_EXCEPTION = "Client Exception";
    public static final String FEIGN_EXCEPTION = "Feign Exception";

    private ConstantsException() {

    }
}

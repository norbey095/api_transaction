package com.emazon.api_transaction.infraestructure.exceptionhandler;

import lombok.Getter;

@Getter
public class ConstantsExcep {

    public static final String NO_DATA_FOUND_EXCEPTION_MESSAGE = "No data found in the database";
    public static final String ACCESS_DENE = "Access denied";
    public static final String INCORRECT_DATA = "Incorrect login information";
    public static final String SERVICE_NOT_AVAILABLE = "The service is not available, please try again later.";
    public static final String STOCK_CONFLICT = "The article does not exist in the database.";
    public static final String ERROR_STOCK = "An error occurred while updating the article.";
    public static final String PURCHASE_FAILURE = "The purchase could not be completed, please try again later.";

    private ConstantsExcep() {

    }
}

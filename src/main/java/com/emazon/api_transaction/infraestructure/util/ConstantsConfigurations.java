package com.emazon.api_transaction.infraestructure.util;

import lombok.Getter;

@Getter
public class ConstantsConfigurations {

    public static final String AUTHORIZATION = "Authorization";
    public static final String URL_STOCK_PORT = "http://localhost:9090";
    public static final String STOCK = "stock";
    public static final String URL_STOCK_UPDATE= "/stock/article/update";
    public static final String URL_STOCK_SUBTRACT= "/stock/article/subtract";
    public static final String URL_STOCK_ARTICLEID = "/stock/article/{articleId}";
    public static final String BEARER = "Bearer ";
    public static final Integer SEVEN_LETTERS = 7;
    public static final String APPLICATION_JSON = "application/json";
    public static final String TOKEN_INVALID = "Invalid token: signature does not match.";
    public static final String PASSWORD_EMPTY = "application/json";
    public static final String JWT_SECRET = "${jwt.secret}";
    public static final String AUTHORITIES = "authorities";
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
    public static final String PURCHASE_FAILURE = "The purchase could not be completed, please try again later.";

    private ConstantsConfigurations() {

    }
}

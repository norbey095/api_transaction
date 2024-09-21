package com.emazon.api_transaction.infraestructure.util;

import lombok.Getter;

@Getter
public class ConstantsConfigurations {

    public static final String AUTHORIZATION = "Authorization";
    public static final String URL_STOCK_PORT = "http://localhost:9090";
    public static final String STOCK = "stock";
    public static final String URL_STOCK_UPDATE= "/stock/article/update";
    public static final String URL_STOCK_ARTICLEID = "/stock/article/{articleId}";
    public static final String BEARER = "Bearer ";
    public static final Integer SEVEN_LETTERS = 7;
    public static final String APPLICATION_JSON = "application/json";
    public static final String TOKEN_INVALID = "Invalid token: signature does not match.";
    public static final String PASSWORD_EMPTY = "application/json";
    public static final String JWT_SECRET = "${jwt.secret}";
    public static final String AUTHORITIES = "authorities";

    private ConstantsConfigurations() {

    }
}

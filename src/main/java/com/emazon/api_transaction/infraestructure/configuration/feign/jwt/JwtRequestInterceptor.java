package com.emazon.api_transaction.infraestructure.configuration.feign.jwt;

import com.emazon.api_transaction.infraestructure.util.ConstantsConfigurations;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

public class JwtRequestInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = ConstantsConfigurations.AUTHORIZATION;

    @Override
    public void apply(RequestTemplate template) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder
                .getRequestAttributes())).getRequest();
        String jwt = request.getHeader(AUTHORIZATION_HEADER);
        template.header(AUTHORIZATION_HEADER,  jwt);
    }
}
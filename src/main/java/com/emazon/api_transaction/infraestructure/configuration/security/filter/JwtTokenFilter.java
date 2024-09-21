package com.emazon.api_transaction.infraestructure.configuration.security.filter;

import com.emazon.api_transaction.infraestructure.configuration.security.UserDetailService;
import com.emazon.api_transaction.infraestructure.exceptionhandler.ExceptionResponse;
import com.emazon.api_transaction.infraestructure.util.ConstantsConfigurations;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String authHeader = request.getHeader(ConstantsConfigurations.AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith(ConstantsConfigurations.BEARER)) {
                filterChain.doFilter(request, response);
                return;
            }

            String jwt = authHeader.substring(ConstantsConfigurations.SEVEN_LETTERS);
            UserDetails user = userDetailService.loadUserByUsername(jwt);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, jwt,
                    user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);

        }  catch (Exception ex) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(ConstantsConfigurations.APPLICATION_JSON);
            response.getWriter().write(new ObjectMapper().writeValueAsString(new ExceptionResponse(
                    ConstantsConfigurations.TOKEN_INVALID,
                    HttpStatus.UNAUTHORIZED.toString()
            )));
            return;
        }
        filterChain.doFilter(request, response);
    }
}

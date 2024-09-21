package com.emazon.api_transaction.infraestructure.output.adapter;

import com.emazon.api_transaction.infraestructure.util.ConstantsInfraestructure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

class AuthenticationAdapterTest {

    private AuthenticationAdapter authenticationAdapter;

    @Mock
    private UserDetails userDetails;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        authenticationAdapter = new AuthenticationAdapter();

        SecurityContext securityContext = org.mockito.Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                null);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
    }

    @Test
    void testGetUserName() {
        String expectedUsername = ConstantsInfraestructure.USER;
        Mockito.when(userDetails.getUsername()).thenReturn(expectedUsername);

        String actualUsername = authenticationAdapter.getUserName();

        Assertions.assertEquals(expectedUsername, actualUsername);
    }
}

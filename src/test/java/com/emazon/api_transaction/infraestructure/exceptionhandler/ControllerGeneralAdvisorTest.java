package com.emazon.api_transaction.infraestructure.exceptionhandler;

import com.emazon.api_transaction.application.handler.ISupplyHandler;
import com.emazon.api_transaction.domain.exception.CredentialsException;
import com.emazon.api_transaction.domain.exception.ErrorStockException;
import com.emazon.api_transaction.domain.exception.PurchaseFailureException;
import com.emazon.api_transaction.infraestructure.util.ConstantsConfigurations;
import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ControllerGeneralAdvisorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ISupplyHandler supplyHandler;

    @InjectMocks
    private ControllerGeneralAdvisor advisor;

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @Mock
    private FeignException.FeignClientException feignClientException;

    @Mock
    private FeignException feignException;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleNoDataFoundException() {
        ResponseEntity<ExceptionResponse> response = advisor.handleNoDataFoundException();

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ConstantsConfigurations.NO_DATA_FOUND_EXCEPTION_MESSAGE
                , response.getBody().getMessage());
    }

    @Test
    void testHandleAccessDeniedException() {
        ResponseEntity<ExceptionResponse> response = advisor.handleAccessDeniedException(
                new AccessDeniedException(ConstantsConfigurations.ACCESS_DENIED));

        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ConstantsConfigurations.ACCESS_DENE, response.getBody().getMessage());
    }

    @Test
    void testHandleBadCredentialsException() {
        ResponseEntity<ExceptionResponse> response = advisor.handleBadCredentialsException(
                new BadCredentialsException(ConstantsConfigurations.BAD_CREDENTIALS));

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ConstantsConfigurations.INCORRECT_DATA, response.getBody().getMessage());
    }

    @Test
    void testHandleCredentialsException() {
        ResponseEntity<ExceptionResponse> response = advisor.handleCredentialsException(
                new CredentialsException());

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ConstantsConfigurations.INCORRECT_DATA, response.getBody().getMessage());
    }

    @Test
    void testHandleErrorStockException() {
        ResponseEntity<ExceptionResponse> response = advisor.handleErrorStockException(new ErrorStockException());

        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ConstantsConfigurations.ERROR_STOCK, response.getBody().getMessage());
    }

    @Test
    void testHandleFeignClientException() {
        Mockito.when(feignClientException.getMessage()).thenReturn(ConstantsConfigurations.CLIENT_EXCEPTION);

        ResponseEntity<ExceptionResponse> response = advisor.handleFeignClientException(feignClientException);

        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ConstantsConfigurations.STOCK_CONFLICT, response.getBody().getMessage());
    }

    @Test
    void testHandleFeignException() {
        Mockito.when(feignException.getMessage()).thenReturn(ConstantsConfigurations.FEIGN_EXCEPTION);

        ResponseEntity<ExceptionResponse> response = advisor.handleFeignException(feignException);

        Assertions.assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ConstantsConfigurations.SERVICE_NOT_AVAILABLE, response.getBody().getMessage());
    }

    @Test
    void testHandlePurchaseFailureException() {
        ResponseEntity<ExceptionResponse> response = advisor.handlePurchaseFailureException(new PurchaseFailureException());

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ConstantsConfigurations.PURCHASE_FAILURE, response.getBody().getMessage());
    }
}
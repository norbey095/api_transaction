package com.emazon.api_transaction.infraestructure.exceptionhandler;

import com.emazon.api_transaction.domain.exception.CredentialsException;
import com.emazon.api_transaction.domain.exception.ErrorStockException;
import com.emazon.api_transaction.domain.exception.NoDataFoundException;
import com.emazon.api_transaction.domain.exception.PurchaseFailureException;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerGeneralAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException
            (MethodArgumentNotValidException ex) {
        StringBuilder messageBuilder = new StringBuilder();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            messageBuilder.append(errorMessage);
        });

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                messageBuilder.toString().trim(),
                HttpStatus.BAD_REQUEST.toString()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoDataFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                ConstantsExcep.NO_DATA_FOUND_EXCEPTION_MESSAGE
                , HttpStatus.NOT_FOUND.toString()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleAccessDeniedException (AccessDeniedException  exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionResponse(
                ConstantsExcep.ACCESS_DENE,
                HttpStatus.FORBIDDEN.toString()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleBadCredentialsException (BadCredentialsException  exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionResponse(
                ConstantsExcep.INCORRECT_DATA,
                HttpStatus.UNAUTHORIZED.toString()));
    }

    @ExceptionHandler(CredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleCredentialsException (CredentialsException  exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionResponse(
                ConstantsExcep.INCORRECT_DATA,
                HttpStatus.UNAUTHORIZED.toString()));
    }

    @ExceptionHandler(FeignException.FeignClientException.class)
    public ResponseEntity<ExceptionResponse> handleFeignClientException(FeignException.FeignClientException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).
                body(new ExceptionResponse(ConstantsExcep.STOCK_CONFLICT, HttpStatus.CONFLICT.toString()));
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ExceptionResponse> handleFeignException(FeignException exception) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).
                body(new ExceptionResponse(ConstantsExcep.SERVICE_NOT_AVAILABLE
                        , HttpStatus.SERVICE_UNAVAILABLE.toString()));

    }

    @ExceptionHandler(ErrorStockException.class)
    public ResponseEntity<ExceptionResponse> handleErrorStockException(ErrorStockException  exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(
                ConstantsExcep.ERROR_STOCK,
                HttpStatus.CONFLICT.toString()));
    }

    @ExceptionHandler(PurchaseFailureException.class)
    public ResponseEntity<ExceptionResponse> handlePurchaseFailureException(PurchaseFailureException  exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(
                ConstantsExcep.PURCHASE_FAILURE,
                HttpStatus.BAD_REQUEST.toString()));
    }



}

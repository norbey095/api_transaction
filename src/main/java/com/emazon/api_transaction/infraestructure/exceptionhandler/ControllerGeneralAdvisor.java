package com.emazon.api_transaction.infraestructure.exceptionhandler;

import com.emazon.api_transaction.domain.exception.CredentialsException;
import com.emazon.api_transaction.domain.exception.ErrorStockException;
import com.emazon.api_transaction.domain.exception.NoDataFoundException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
                ConstantsException.NO_DATA_FOUND_EXCEPTION_MESSAGE
                , HttpStatus.NOT_FOUND.toString()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleAccessDeniedException (AccessDeniedException  exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionResponse(
                ConstantsException.ACCESS_DENE,
                HttpStatus.FORBIDDEN.toString()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleBadCredentialsException (BadCredentialsException  exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionResponse(
                ConstantsException.INCORRECT_DATA,
                HttpStatus.UNAUTHORIZED.toString()));
    }

    @ExceptionHandler(CredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleCredentialsException (CredentialsException  exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionResponse(
                ConstantsException.INCORRECT_DATA,
                HttpStatus.UNAUTHORIZED.toString()));
    }

    @ExceptionHandler(FeignException.FeignClientException.class)
    public ResponseEntity<ExceptionResponse> handleFeignClientException(FeignException.FeignClientException exception) {
        try {
            return ResponseEntity.status(HttpStatus.CONFLICT).
                    body(new ExceptionResponse(ConstantsException.STOCK_CONFLICT, HttpStatus.CONFLICT.toString()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ExceptionResponse(ConstantsException.SERVICE_NOT_AVAILABLE,
                            HttpStatus.INTERNAL_SERVER_ERROR.toString()));
        }
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ExceptionResponse> handleFeignException(FeignException exception) {
        try {

            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).
                    body(new ExceptionResponse(ConstantsException.SERVICE_NOT_AVAILABLE
                            , HttpStatus.SERVICE_UNAVAILABLE.toString()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ExceptionResponse(ConstantsException.SERVICE_NOT_AVAILABLE,
                            HttpStatus.INTERNAL_SERVER_ERROR.toString()));
        }
    }

    @ExceptionHandler(ErrorStockException.class)
    public ResponseEntity<ExceptionResponse> handleErrorStockException(ErrorStockException  exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(
                ConstantsException.ERROR_STOCK,
                HttpStatus.CONFLICT.toString()));
    }

}

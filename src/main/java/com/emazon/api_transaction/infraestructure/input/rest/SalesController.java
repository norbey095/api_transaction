package com.emazon.api_transaction.infraestructure.input.rest;

import com.emazon.api_transaction.application.dto.stock.ResponseStockDto;
import com.emazon.api_transaction.application.dto.transaction.SalesRequestDto;
import com.emazon.api_transaction.application.handler.ISalesHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/transaction/sales")
@RequiredArgsConstructor
public class SalesController {

    private final ISalesHandler salesHandler;

    @Operation(summary = "Add Sales",
            description = "Add Sales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added", content = @Content),
            @ApiResponse(responseCode = "409", description = "Error with the request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "503", description = "Service not available", content = @Content),
    })
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping("/")
    public ResponseEntity<ResponseStockDto> addSales(
            @Valid @RequestBody List<SalesRequestDto> transactionRequest
    ) {
        return  ResponseEntity.ok(salesHandler.saveSales(transactionRequest));
    }
}

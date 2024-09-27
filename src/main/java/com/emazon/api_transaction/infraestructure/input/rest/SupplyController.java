package com.emazon.api_transaction.infraestructure.input.rest;

import com.emazon.api_transaction.application.dto.ArticleUpdateRequestDto;
import com.emazon.api_transaction.application.dto.ResponseStockDto;
import com.emazon.api_transaction.application.handler.ISupplyHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/supply")
@RequiredArgsConstructor
public class SupplyController {

    private final ISupplyHandler supplyHandler;

    @Operation(summary = "Add Supply",
            description = "Add Supply")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully added", content = @Content),
            @ApiResponse(responseCode = "409", description = "Error with the request", content = @Content),
            @ApiResponse(responseCode = "503", description = "Service not available", content = @Content),
    })
    @PreAuthorize("hasRole('ROLE_AUX_WAREHOUSE')")
    @PostMapping("/")
    public ResponseEntity<ResponseStockDto> addSupply(
            @Valid @RequestBody ArticleUpdateRequestDto request
    ) {
        return  ResponseEntity.ok(supplyHandler.addSupply(request));
    }
}

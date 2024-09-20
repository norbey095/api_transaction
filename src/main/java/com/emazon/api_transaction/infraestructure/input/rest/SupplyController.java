package com.emazon.api_transaction.infraestructure.input.rest;

import com.emazon.api_transaction.application.dto.ArticleUpdateRequestDto;
import com.emazon.api_transaction.application.dto.ResponseSuccessDto;
import com.emazon.api_transaction.application.handler.ISupplyHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/supply")
@RequiredArgsConstructor
public class SupplyController {

    private final ISupplyHandler supplyHandler;

    @Operation(summary = "Authenticacion User",
            description = "Authenticacion User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authenticated user", content = @Content),
            @ApiResponse(responseCode = "403", description = "Incorrect login information", content = @Content),
    })
    @PreAuthorize("hasRole('ROLE_AUX_WAREHOUSE')")
    @PostMapping("/")
    public ResponseEntity<ResponseSuccessDto> addSupply(
            @RequestBody ArticleUpdateRequestDto request
    ) {
        return  ResponseEntity.ok(supplyHandler.addSupply(request));
    }
}

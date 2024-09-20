package com.emazon.api_transaction.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseStockDto {

    private String messages;
    private String status;
}

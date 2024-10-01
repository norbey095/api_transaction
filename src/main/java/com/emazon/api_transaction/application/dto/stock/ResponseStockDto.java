package com.emazon.api_transaction.application.dto.stock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseStockDto {

    private String messages;
    private String status;
}

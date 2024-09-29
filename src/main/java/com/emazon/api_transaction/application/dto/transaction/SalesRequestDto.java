package com.emazon.api_transaction.application.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SalesRequestDto {

    private Integer id;
    private String  email;
    private LocalDateTime buyDate;
    private Integer idArticle;
    private Integer quantity;

}

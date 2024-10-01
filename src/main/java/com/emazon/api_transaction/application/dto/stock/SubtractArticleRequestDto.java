package com.emazon.api_transaction.application.dto.stock;

import lombok.Data;

@Data
public class SubtractArticleRequestDto {

    private Integer articleId;
    private Integer quantity;
}

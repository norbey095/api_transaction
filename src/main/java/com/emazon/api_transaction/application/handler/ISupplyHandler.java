package com.emazon.api_transaction.application.handler;


import com.emazon.api_transaction.application.dto.ArticleUpdateRequestDto;
import com.emazon.api_transaction.application.dto.ResponseStockDto;

public interface ISupplyHandler {

    ResponseStockDto addSupply(ArticleUpdateRequestDto articleUpdateRequestDto);

}

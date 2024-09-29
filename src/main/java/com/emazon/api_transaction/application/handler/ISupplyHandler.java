package com.emazon.api_transaction.application.handler;


import com.emazon.api_transaction.application.dto.stock.ResponseStockDto;
import com.emazon.api_transaction.application.dto.transaction.ArticleUpdateRequestDto;

public interface ISupplyHandler {

    ResponseStockDto addSupply(ArticleUpdateRequestDto articleUpdateRequestDto);

}

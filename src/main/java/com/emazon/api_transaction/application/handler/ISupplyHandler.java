package com.emazon.api_transaction.application.handler;


import com.emazon.api_transaction.application.dto.ArticleUpdateRequestDto;
import com.emazon.api_transaction.application.dto.ResponseSuccessDto;

public interface ISupplyHandler {

    ResponseSuccessDto addSupply(ArticleUpdateRequestDto articleUpdateRequestDto);

}

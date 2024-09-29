package com.emazon.api_transaction.application.handler;


import com.emazon.api_transaction.application.dto.stock.ResponseStockDto;
import com.emazon.api_transaction.application.dto.transaction.SalesRequestDto;

import java.util.List;

public interface ISalesHandler {

    ResponseStockDto saveSales(List<SalesRequestDto> transactionRequest);
}

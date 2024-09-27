package com.emazon.api_transaction.application.handler;


import com.emazon.api_transaction.application.dto.SalesRequestDto;

public interface ISalesHandler {

    void saveSales(SalesRequestDto salesRequestDto);
}

package com.emazon.api_transaction.application.handler;

import com.emazon.api_transaction.application.dto.SalesRequestDto;
import com.emazon.api_transaction.application.mapper.SalesMapper;
import com.emazon.api_transaction.domain.api.ISalesServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
public class SalesHandler implements ISalesHandler {

    private final ISalesServicePort salesServicePort;
    private final SalesMapper salesMapper;

    @Override
    public void saveSales(SalesRequestDto salesRequestDto) {
        salesServicePort.saveSales(salesMapper.salesRequestDtoToSalesRequest(salesRequestDto));
    }
}

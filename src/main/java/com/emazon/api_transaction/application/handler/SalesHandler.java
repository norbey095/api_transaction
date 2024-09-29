package com.emazon.api_transaction.application.handler;

import com.emazon.api_transaction.application.dto.stock.ResponseStockDto;
import com.emazon.api_transaction.application.dto.transaction.SalesRequestDto;
import com.emazon.api_transaction.application.mapper.SalesMapper;
import com.emazon.api_transaction.application.util.ConstantsDto;
import com.emazon.api_transaction.domain.api.ISalesServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class SalesHandler implements ISalesHandler {

    private final ISalesServicePort salesServicePort;
    private final SalesMapper salesMapper;

    @Override
    public ResponseStockDto saveSales(List<SalesRequestDto> salesRequestDto) {
        salesServicePort.saveSales(salesMapper.salesRequestDtoListToSalesRequestList(salesRequestDto));
        return new ResponseStockDto(ConstantsDto.SAVE_CORRECT, HttpStatus.OK.toString());
    }

    @Override
    public void deleteRegistry(String userName, LocalDateTime buyDate) {
        salesServicePort.deleteRegistry(userName, buyDate);
    }
}

package com.emazon.api_transaction.application.handler;

import com.emazon.api_transaction.application.dto.transaction.SalesRequestDto;
import com.emazon.api_transaction.application.mapper.SalesMapper;
import com.emazon.api_transaction.application.util.ConstantsApplication;
import com.emazon.api_transaction.domain.api.ISalesServicePort;
import com.emazon.api_transaction.domain.model.Sales;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class SalesHandlerTest {

    @InjectMocks
    private SalesHandler salesHandler;

    @Mock
    private SalesMapper salesMapper;

    @Mock
    private ISalesServicePort salesServicePort;

    private SalesRequestDto salesRequestDto;
    private Sales sales;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        salesRequestDto = new SalesRequestDto(ConstantsApplication.ARTICLE_ID
                , ConstantsApplication.EMAIL, LocalDateTime.now(),ConstantsApplication.ARTICLE_ID,
                ConstantsApplication.QUANTITY);

        sales = new Sales(ConstantsApplication.QUANTITY,ConstantsApplication.ARTICLE_ID
                ,LocalDateTime.now(), ConstantsApplication.EMAIL,ConstantsApplication.ARTICLE_ID);
    }

    @Test
    void shouldSaveSales() {
        List<SalesRequestDto> salesRequestDtoList = new ArrayList<>();
        salesRequestDtoList.add(salesRequestDto);

        List<Sales> salesList = new ArrayList<>();
        salesList.add(sales);

        when(salesMapper.salesRequestDtoListToSalesRequestList(salesRequestDtoList)).thenReturn(salesList);
        doNothing().when(salesServicePort).saveSales(salesList);


        salesHandler.saveSales(salesRequestDtoList);

        Mockito.verify(salesMapper, Mockito.times(ConstantsApplication.CALLED_1)).
                salesRequestDtoListToSalesRequestList(salesRequestDtoList);
    }

    @Test
    void shouldDeleteRegistry() {
        String userName = ConstantsApplication.EMAIL;
        LocalDateTime buyDate = LocalDateTime.now();

        doNothing().when(salesServicePort).deleteRegistry(userName,buyDate);


        salesHandler.deleteRegistry(userName,buyDate);
    }

}

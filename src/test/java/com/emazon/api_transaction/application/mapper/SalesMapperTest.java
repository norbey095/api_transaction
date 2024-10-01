package com.emazon.api_transaction.application.mapper;

import com.emazon.api_transaction.application.dto.transaction.SalesRequestDto;
import com.emazon.api_transaction.application.util.ConstantsApplication;
import com.emazon.api_transaction.domain.model.Sales;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class SalesMapperTest {

    private final SalesMapper salesMapper = Mappers.getMapper( SalesMapper.class);

    @Test
    void testSalesRequestDtoListToSalesRequestList() {
        SalesRequestDto salesRequestDto = new SalesRequestDto(ConstantsApplication.ARTICLE_ID
                , ConstantsApplication.EMAIL, LocalDateTime.now(), ConstantsApplication.ARTICLE_ID,
                ConstantsApplication.QUANTITY);
        List<SalesRequestDto> salesRequestDtoList = new ArrayList<>();
        salesRequestDtoList.add(salesRequestDto);

        List<Sales> salesList = salesMapper.salesRequestDtoListToSalesRequestList(salesRequestDtoList);

        Assertions.assertNotNull(salesList);
        Assertions.assertEquals(salesList.get(ConstantsApplication.NUMBER).getId(),
                salesRequestDtoList.get(ConstantsApplication.NUMBER).getId());
        Assertions.assertEquals(salesList.get(ConstantsApplication.NUMBER).getArticleId(),
                salesRequestDtoList.get(ConstantsApplication.NUMBER).getArticleId());
        Assertions.assertEquals(salesList.get(ConstantsApplication.NUMBER).getEmail(),
                salesRequestDtoList.get(ConstantsApplication.NUMBER).getEmail());
        Assertions.assertEquals(salesList.get(ConstantsApplication.NUMBER).getBuyDate(),
                salesRequestDtoList.get(ConstantsApplication.NUMBER).getBuyDate());
        Assertions.assertEquals(salesList.get(ConstantsApplication.NUMBER).getQuantity(),
                salesRequestDtoList.get(ConstantsApplication.NUMBER).getQuantity());
    }

    @Test
    void testArticleUpdateToSupplyEntityNull() {

        List<Sales> salesList = salesMapper.salesRequestDtoListToSalesRequestList(null);

        Assertions.assertNull(salesList);
    }
}
package com.emazon.api_transaction.infraestructure.output.entity;

import com.emazon.api_transaction.domain.model.Sales;
import com.emazon.api_transaction.infraestructure.output.mapper.ISalesEntityMapper;
import com.emazon.api_transaction.infraestructure.util.ConstantsInfraestructure;
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
class SalesEntityMapperTest {

    private final ISalesEntityMapper salesEntityMapper = Mappers.getMapper(ISalesEntityMapper.class);

    @Test
    void testSalesToSalesEntity() {
        Sales sales = new Sales(ConstantsInfraestructure.ID, ConstantsInfraestructure.ARTICLE_ID
                , LocalDateTime.now(), ConstantsInfraestructure.EMAIL,ConstantsInfraestructure.ARTICLE_ID);

        SalesEntity salesEntity = salesEntityMapper.salesToSalesEntity(sales);

        Assertions.assertNotNull(salesEntity);
        Assertions.assertEquals(salesEntity.getId(), sales.getId());
        Assertions.assertEquals(salesEntity.getArticleId(), sales.getArticleId());
        Assertions.assertEquals(salesEntity.getQuantity(), sales.getQuantity());
    }

    @Test
    void testSalesToSalesEntityNull() {
        SalesEntity salesEntity = salesEntityMapper.salesToSalesEntity(null);

        Assertions.assertNull(salesEntity);
    }

    @Test
    void testSalesEntityListToSalesList() {
        SalesEntity salesEntity = new SalesEntity(ConstantsInfraestructure.ID, ConstantsInfraestructure.EMAIL
                , LocalDateTime.now(),ConstantsInfraestructure.ARTICLE_ID ,ConstantsInfraestructure.ARTICLE_ID);
        List<SalesEntity> salesEntities = new ArrayList<>();
        salesEntities.add(salesEntity);

        List<Sales> salesList = salesEntityMapper.salesEntityListToSalesList(salesEntities);

        Assertions.assertNotNull(salesList);
        Assertions.assertEquals(salesList.get(ConstantsInfraestructure.NUMBER_0).getBuyDate(),
                salesEntity.getBuyDate());
        Assertions.assertEquals(salesList.get(ConstantsInfraestructure.NUMBER_0).getEmail(), salesEntity.getEmail());
        Assertions.assertEquals(salesList.get(ConstantsInfraestructure.NUMBER_0).getId(), salesEntity.getId());
        Assertions.assertEquals(salesList.get(ConstantsInfraestructure.NUMBER_0).getQuantity(),
                salesEntity.getQuantity());
        Assertions.assertEquals(salesList.get(ConstantsInfraestructure.NUMBER_0).getArticleId(),
                salesEntity.getArticleId());
    }

    @Test
    void testSalesEntityListToSalesListNull() {
        List<Sales> salesList = salesEntityMapper.salesEntityListToSalesList(null);

        Assertions.assertNull(salesList);
    }


}
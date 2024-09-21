package com.emazon.api_transaction.infraestructure.output.entity;

import com.emazon.api_transaction.application.dto.ResponseStockDto;
import com.emazon.api_transaction.domain.model.ArticleUpdate;
import com.emazon.api_transaction.domain.model.ResponseStock;
import com.emazon.api_transaction.infraestructure.output.mapper.ISupplyEntityMapper;
import com.emazon.api_transaction.infraestructure.util.ConstantsInfraestructure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class SupplyEntityMapperTest {

    private final ISupplyEntityMapper supplyEntityMapper = Mappers.getMapper( ISupplyEntityMapper.class);

    @Test
    void testArticleUpdateToSupplyEntity() {
        ArticleUpdate articleUpdate = new ArticleUpdate(ConstantsInfraestructure.ID, ConstantsInfraestructure.ARTICLE_ID
                , ConstantsInfraestructure.QUANTITY
                , LocalDate.now(), ConstantsInfraestructure.EMAIL);

        SupplyEntity supplyEntity = supplyEntityMapper.articleUpdateToSupplyEntity(articleUpdate);

        Assertions.assertNotNull(supplyEntity);
        Assertions.assertEquals(ConstantsInfraestructure.QUANTITY, supplyEntity.getQuantity());
        Assertions.assertEquals( LocalDate.now(), supplyEntity.getUpdateDate());
        Assertions.assertEquals( ConstantsInfraestructure.ARTICLE_ID, supplyEntity.getArticleId());
    }

    @Test
    void testArticleUpdateToSupplyEntityNull() {
        SupplyEntity supplyEntity = supplyEntityMapper.articleUpdateToSupplyEntity(null);

        Assertions.assertNull(supplyEntity);
    }

    @Test
    void testResponseStockDtoToResponseStock() {
        ResponseStockDto responseStockDto = new ResponseStockDto(ConstantsInfraestructure.MESSAGE,
                ConstantsInfraestructure.STATUS);

        ResponseStock responseStock = supplyEntityMapper.responseStockDtoToResponseStock(responseStockDto);

        Assertions.assertNotNull(responseStock);
        Assertions.assertEquals(ConstantsInfraestructure.MESSAGE, responseStock.getMessages());
        Assertions.assertEquals( ConstantsInfraestructure.STATUS, responseStock.getStatus());
    }

    @Test
    void testBrandToBrandEntity_NullInput() {
        ResponseStock responseStock = supplyEntityMapper.responseStockDtoToResponseStock(null);

        Assertions.assertNull(responseStock);
    }
}
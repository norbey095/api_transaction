package com.emazon.api_transaction.application.mapper;

import com.emazon.api_transaction.application.dto.ArticleUpdateRequestDto;
import com.emazon.api_transaction.application.util.ConstantsApplication;
import com.emazon.api_transaction.domain.model.ArticleUpdate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class SupplyMapperTest {

    private final SupplyMapper supplyMapper = Mappers.getMapper( SupplyMapper.class);

    @Test
    void testArticleUpdateRequestDtoToArticleUpdate() {
        ArticleUpdateRequestDto articleUpdateRequestDto = new ArticleUpdateRequestDto(ConstantsApplication.ARTICLE_ID
                , ConstantsApplication.QUANTITY);

        ArticleUpdate articleUpdate = supplyMapper.articleUpdateRequestDtoToArticleUpdate(articleUpdateRequestDto);

        Assertions.assertNotNull(articleUpdate);
        Assertions.assertEquals(articleUpdateRequestDto.getQuantity(), articleUpdate.getQuantity());
        Assertions.assertEquals(articleUpdateRequestDto.getArticleId(), articleUpdate.getArticleId());
    }

    @Test
    void testArticleUpdateToSupplyEntityNull() {
        ArticleUpdate articleUpdate = supplyMapper.articleUpdateRequestDtoToArticleUpdate(null);

        Assertions.assertNull(articleUpdate);
    }
}
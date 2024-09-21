package com.emazon.api_transaction.application.handler;

import com.emazon.api_transaction.application.dto.ArticleUpdateRequestDto;
import com.emazon.api_transaction.application.mapper.SupplyMapper;
import com.emazon.api_transaction.application.util.ConstantsApplication;
import com.emazon.api_transaction.domain.api.ISupplyServicePort;
import com.emazon.api_transaction.domain.model.ArticleUpdate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import static org.mockito.Mockito.when;

class SupplyHandlerTest {

    @InjectMocks
    private SupplyHandler supplyHandler;

    @Mock
    private SupplyMapper supplyMapper;

    @Mock
    private ISupplyServicePort supplyServicePort;

    private ArticleUpdateRequestDto articleUpdateRequestDto;

    private ArticleUpdate articleUpdate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        articleUpdateRequestDto = new ArticleUpdateRequestDto(ConstantsApplication.ARTICLE_ID,
                ConstantsApplication.QUANTITY);

        articleUpdate = new ArticleUpdate(ConstantsApplication.ID
                , ConstantsApplication.ARTICLE_ID
                , ConstantsApplication.QUANTITY
                , LocalDate.now(), ConstantsApplication.EMAIL);
    }

    @Test
    void shouldSaveBrand() {
        when(supplyMapper.articleUpdateRequestDtoToArticleUpdate(articleUpdateRequestDto)).thenReturn(articleUpdate);

        supplyHandler.addSupply(articleUpdateRequestDto);

        Mockito.verify(supplyMapper, Mockito.times(ConstantsApplication.CALLED_1)).
                articleUpdateRequestDtoToArticleUpdate(articleUpdateRequestDto);
    }


}

package com.emazon.api_transaction.infraestructure.output.adapter;

import com.emazon.api_transaction.application.dto.ArticleResponseDto;
import com.emazon.api_transaction.application.dto.ArticleUpdateRequestDto;
import com.emazon.api_transaction.application.dto.ResponseStockDto;
import com.emazon.api_transaction.domain.model.ResponseStock;
import com.emazon.api_transaction.infraestructure.configuration.feign.IFeignClientStock;
import com.emazon.api_transaction.infraestructure.output.mapper.ISupplyEntityMapper;
import com.emazon.api_transaction.infraestructure.util.ConstantsInfraestructure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class SupplyStockAdapterTest {

    @Mock
    private IFeignClientStock feignClientStock;

    @Mock
    private ISupplyEntityMapper supplyEntityMapper;

    @Spy
    @InjectMocks
    private SupplyStockAdapter supplyStockAdapter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExistArticleById() {
        Mockito.when(feignClientStock.existById(ConstantsInfraestructure.ARTICLE_ID))
                .thenReturn(new ArticleResponseDto());

        supplyStockAdapter.existArticleById(ConstantsInfraestructure.ARTICLE_ID);

        Mockito.verify(feignClientStock, Mockito.times(ConstantsInfraestructure.CALLED_1))
                .existById(ConstantsInfraestructure.ARTICLE_ID);
    }

    @Test
    void testUpdateQuantityArticle() {
        ResponseStockDto responseStockDto = new ResponseStockDto(ConstantsInfraestructure.MESSAGE
                ,ConstantsInfraestructure.STATUS);

        ResponseStock responseStockD = new ResponseStock(ConstantsInfraestructure.MESSAGE
                ,ConstantsInfraestructure.STATUS);

        Mockito.when(feignClientStock.updateArticle(new ArticleUpdateRequestDto(ConstantsInfraestructure.ARTICLE_ID,
                ConstantsInfraestructure.QUANTITY))).thenReturn(responseStockDto);
        Mockito.when(supplyEntityMapper.responseStockDtoToResponseStock(responseStockDto)).thenReturn(responseStockD);



        ResponseStock responseStock = supplyStockAdapter.updateQuantityArticle(ConstantsInfraestructure.ARTICLE_ID,
                ConstantsInfraestructure.QUANTITY);

        assertNotNull(responseStock);
        assertEquals(responseStock.getStatus(), responseStockDto.getStatus());
        assertEquals(responseStock.getMessages(), responseStockDto.getMessages());
    }
}

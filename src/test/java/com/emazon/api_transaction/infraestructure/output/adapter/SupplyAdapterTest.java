package com.emazon.api_transaction.infraestructure.output.adapter;

import com.emazon.api_transaction.domain.model.ArticleUpdate;
import com.emazon.api_transaction.infraestructure.output.entity.SupplyEntity;
import com.emazon.api_transaction.infraestructure.output.mapper.ISupplyEntityMapper;
import com.emazon.api_transaction.infraestructure.output.repository.ISupplyRepository;
import com.emazon.api_transaction.infraestructure.util.ConstantsInfraestructure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;

class SupplyAdapterTest {

    @Mock
    private ISupplyRepository supplyRepository;

    @Mock
    private ISupplyEntityMapper supplyEntityMapper;

    @Spy
    @InjectMocks
    private SupplyAdapter  supplyAdapter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testsaveSupply() {
        ArticleUpdate responseStockDto = new ArticleUpdate(ConstantsInfraestructure.ID
                , ConstantsInfraestructure.ARTICLE_ID
                , ConstantsInfraestructure.QUANTITY
                , LocalDate.now(), ConstantsInfraestructure.EMAIL);

        SupplyEntity supplyEntity = new SupplyEntity(ConstantsInfraestructure.ID
                , ConstantsInfraestructure.ARTICLE_ID
                , ConstantsInfraestructure.QUANTITY
                , LocalDate.now(), ConstantsInfraestructure.EMAIL);
        Mockito.when(supplyEntityMapper.articleUpdateToSupplyEntity(responseStockDto)).thenReturn(supplyEntity);

        supplyAdapter.saveSupply(responseStockDto);

        Mockito.verify(supplyEntityMapper, Mockito.times(ConstantsInfraestructure.CALLED_1))
                .articleUpdateToSupplyEntity(responseStockDto);
        Mockito.verify(supplyRepository, Mockito.times(ConstantsInfraestructure.CALLED_1))
                .save(supplyEntity);
    }
}

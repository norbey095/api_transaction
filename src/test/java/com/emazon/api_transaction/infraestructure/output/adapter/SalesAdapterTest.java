package com.emazon.api_transaction.infraestructure.output.adapter;


import com.emazon.api_transaction.domain.model.Sales;
import com.emazon.api_transaction.infraestructure.output.entity.SalesEntity;
import com.emazon.api_transaction.infraestructure.output.mapper.ISalesEntityMapper;
import com.emazon.api_transaction.infraestructure.output.repository.ISalesRepository;
import com.emazon.api_transaction.infraestructure.util.ConstantsInfraestructure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class SalesAdapterTest {

    @Mock
    private ISalesRepository salesRepository ;

    @Mock
    private ISalesEntityMapper salesEntityMapper;

    @Spy
    @InjectMocks
    private SalesAdapter salesAdapter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testSaveSales() {
        Sales sales = new Sales(ConstantsInfraestructure.QUANTITY,ConstantsInfraestructure.ARTICLE_ID
                , LocalDateTime.now(), ConstantsInfraestructure.EMAIL,ConstantsInfraestructure.ARTICLE_ID);
        SalesEntity salesEntity = new SalesEntity(ConstantsInfraestructure.ARTICLE_ID,ConstantsInfraestructure.EMAIL,
                LocalDateTime.now(),ConstantsInfraestructure.ARTICLE_ID,ConstantsInfraestructure.ARTICLE_ID);

        Mockito.when(salesEntityMapper.salesToSalesEntity(sales)).thenReturn(salesEntity);

        salesAdapter.saveSales(sales);

        Mockito.verify(salesEntityMapper, Mockito.times(ConstantsInfraestructure.CALLED_1))
                .salesToSalesEntity(sales);
    }

    @Test
    void testGetAllSales() {
        String userName = ConstantsInfraestructure.USER_NAME;
        LocalDateTime buyDate = LocalDateTime.now();

        SalesEntity salesEntity = new SalesEntity(ConstantsInfraestructure.ARTICLE_ID,ConstantsInfraestructure.EMAIL,
                LocalDateTime.now(),ConstantsInfraestructure.ARTICLE_ID,ConstantsInfraestructure.ARTICLE_ID);
        List<SalesEntity> salesEntityList = new ArrayList<>();
        salesEntityList.add(salesEntity);

        Mockito.when(salesRepository.findSalesByUserName(userName,buyDate)).thenReturn(salesEntityList);

        salesAdapter.getAllSales(userName,buyDate);

        Mockito.verify(salesRepository, Mockito.times(ConstantsInfraestructure.CALLED_1))
                .findSalesByUserName(userName,buyDate);
    }

    @Test
    void testDeleteRegistry() {
        String userName = ConstantsInfraestructure.EMAIL;
        LocalDateTime buyDate = LocalDateTime.now();

        Mockito.doNothing().when(salesRepository).deleteSalesByUserName(userName,buyDate);

        salesAdapter.deleteRegistry(userName,buyDate);

        Mockito.verify(salesRepository, Mockito.times(ConstantsInfraestructure.CALLED_1))
                .deleteSalesByUserName(userName,buyDate);
    }
}

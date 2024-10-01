package com.emazon.api_transaction.domain.usecase;


import com.emazon.api_transaction.domain.exception.ErrorStockException;
import com.emazon.api_transaction.domain.exception.NoDataFoundException;
import com.emazon.api_transaction.domain.exception.PurchaseFailureException;
import com.emazon.api_transaction.domain.model.ResponseStock;
import com.emazon.api_transaction.domain.model.Sales;
import com.emazon.api_transaction.domain.spi.IAthenticationPersistencePort;
import com.emazon.api_transaction.domain.spi.IClientStockPersistencePort;
import com.emazon.api_transaction.domain.spi.ISalesPersistencePort;
import com.emazon.api_transaction.domain.util.ConstantsDomain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


class SalesUseCaseTest {

    @Mock
    private ISalesPersistencePort salesPersistencePort;

    @Mock
    private IClientStockPersistencePort clientStockPersistencePort;

    @Mock
    private IAthenticationPersistencePort authenticationPersistencePort;

    @Mock
    private ResponseStock responseStock;

    @InjectMocks
    private SalesUseCase salesUseCase;

    private Sales sales;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        LocalDateTime localDateTime = LocalDateTime.now();
        sales = new Sales(ConstantsDomain.ID, ConstantsDomain.ARTICLE_ID
                , localDateTime, ConstantsDomain.NAME,ConstantsDomain.ARTICLE_ID);
    }

    @Test
    void shouldDeleteRegistryInPersistencePort() {
        String userName = ConstantsDomain.EMAIL;
        LocalDateTime buyDate = LocalDateTime.now();
        List<Sales> salesRequest = new ArrayList<>();
        salesRequest.add(sales);
        Mockito.when(salesPersistencePort.getAllSales(userName, buyDate))
                .thenReturn(salesRequest);

        salesUseCase.deleteRegistry(userName, buyDate);

        Mockito.verify(clientStockPersistencePort, Mockito.times(ConstantsDomain.CALLED_1)).
                updateQuantityArticle(ConstantsDomain.ARTICLE_ID,ConstantsDomain.QUANTITY);
        Mockito.verify(salesPersistencePort, Mockito.times(ConstantsDomain.CALLED_1)).
                deleteRegistry(userName,buyDate);
    }

    @Test
    void shouldSaveSales() {

        List<Sales> salesRequest = new ArrayList<>();
        salesRequest.add(sales);

        Mockito.when(clientStockPersistencePort.subtractQuantityArticle(salesRequest))
                .thenReturn(responseStock);
        Mockito.when(responseStock.getStatus()).thenReturn(HttpStatus.CREATED.toString());
        Mockito.when(clientStockPersistencePort.existArticleById(sales.getArticleId())).thenReturn(true);

        salesUseCase.saveSales(salesRequest);

        Mockito.verify(clientStockPersistencePort, Mockito.times(ConstantsDomain.CALLED_1))
                .subtractQuantityArticle(salesRequest);
    }

    @Test
    void shouldErrorStockException() {
        String userName = ConstantsDomain.EMAIL;
        LocalDateTime buyDate = LocalDateTime.now();

        List<Sales> salesRequest = new ArrayList<>();
        salesRequest.add(sales);

        Mockito.when(clientStockPersistencePort.subtractQuantityArticle(salesRequest))
                .thenReturn(responseStock);
        Mockito.when(responseStock.getStatus()).thenReturn(HttpStatus.CONFLICT.toString());
        Mockito.when(authenticationPersistencePort.getUserName())
                .thenReturn(userName);
        Mockito.when(salesPersistencePort.getAllSales(userName, buyDate))
                .thenReturn(salesRequest);

        Assertions.assertThrows(ErrorStockException.class, () -> {
            salesUseCase.saveSales(salesRequest);
        });

        Mockito.verify(clientStockPersistencePort, Mockito.times(ConstantsDomain.CALLED_1))
                .subtractQuantityArticle(salesRequest);
        Mockito.verify(authenticationPersistencePort, Mockito.times(ConstantsDomain.CALLED_0))
                .getUserName();
        Mockito.verify(salesPersistencePort, Mockito.times(ConstantsDomain.CALLED_0))
                .getAllSales(userName, buyDate);

    }

    @Test
    void shouldNoDataFoundException () {

        List<Sales> salesRequest = new ArrayList<>();
        salesRequest.add(sales);

        Mockito.when(clientStockPersistencePort.subtractQuantityArticle(salesRequest))
                .thenReturn(responseStock);
        Mockito.when(responseStock.getStatus()).thenReturn(HttpStatus.CREATED.toString());
        Mockito.when(clientStockPersistencePort.existArticleById(sales.getArticleId())).thenReturn(false);

        Assertions.assertThrows(NoDataFoundException.class, () -> {
            salesUseCase.saveSales(salesRequest);
        });

        Mockito.verify(clientStockPersistencePort, Mockito.times(ConstantsDomain.CALLED_1))
                .subtractQuantityArticle(salesRequest);
    }

    @Test
    void shouldErrorStockExceptionWithIsNull() {
        String userName = ConstantsDomain.EMAIL;
        LocalDateTime buyDate = LocalDateTime.now();

        List<Sales> salesRequest = new ArrayList<>();
        salesRequest.add(sales);

        Mockito.when(clientStockPersistencePort.subtractQuantityArticle(salesRequest))
                .thenReturn(null);
        Mockito.when(authenticationPersistencePort.getUserName())
                .thenReturn(userName);
        Mockito.when(salesPersistencePort.getAllSales(userName, buyDate))
                .thenReturn(salesRequest);

        Assertions.assertThrows(ErrorStockException.class, () -> {
            salesUseCase.saveSales(salesRequest);
        });

        Mockito.verify(clientStockPersistencePort, Mockito.times(ConstantsDomain.CALLED_1))
                .subtractQuantityArticle(salesRequest);
        Mockito.verify(authenticationPersistencePort, Mockito.times(ConstantsDomain.CALLED_0))
                .getUserName();
        Mockito.verify(salesPersistencePort, Mockito.times(ConstantsDomain.CALLED_0))
                .getAllSales(userName, buyDate);
    }

    @Test
    void shouldSaveSalesException() {
        String userName = ConstantsDomain.EMAIL;
        List<Sales> salesRequest = new ArrayList<>();
        salesRequest.add(sales);

        Mockito.doThrow(new IllegalArgumentException()).when(clientStockPersistencePort)
                .subtractQuantityArticle(salesRequest);
        Mockito.when(authenticationPersistencePort.getUserName())
                .thenReturn(userName);


        Mockito.when(responseStock.getStatus()).thenReturn(HttpStatus.CREATED.toString());
        Mockito.when(clientStockPersistencePort.existArticleById(sales.getArticleId())).thenReturn(true);

        Assertions.assertThrows(PurchaseFailureException.class, () -> {
            salesUseCase.saveSales(salesRequest);
        });

        Mockito.verify(clientStockPersistencePort, Mockito.times(ConstantsDomain.CALLED_1))
                .subtractQuantityArticle(salesRequest);
    }

}

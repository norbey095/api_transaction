package com.emazon.api_transaction.domain.usecase;


import com.emazon.api_transaction.domain.exception.ErrorStockException;
import com.emazon.api_transaction.domain.exception.NoDataFoundException;
import com.emazon.api_transaction.domain.model.ArticleUpdate;
import com.emazon.api_transaction.domain.model.ResponseStock;
import com.emazon.api_transaction.domain.spi.IAthenticationPersistencePort;
import com.emazon.api_transaction.domain.spi.ISupplyPersistencePort;
import com.emazon.api_transaction.domain.spi.IClientStockPersistencePort;
import com.emazon.api_transaction.domain.util.ConstantsDomain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

class SupplyUseCaseTest {

    @Mock
    private ISupplyPersistencePort supplyPersistencePort;

    @Mock
    private IClientStockPersistencePort supplyStockPersistencePort;

    @Mock
    private IAthenticationPersistencePort authenticationPersistencePort;

    @InjectMocks
    private SupplyUseCase supplyUseCase;

    private ArticleUpdate article;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        article = new ArticleUpdate(ConstantsDomain.ID
                , ConstantsDomain.ARTICLE_ID
                , ConstantsDomain.QUANTITY
                , LocalDate.now(), ConstantsDomain.EMAIL);
    }

    @Test
    void shouldSaveSupplyInPersistencePort() {
        Mockito.when(supplyStockPersistencePort.existArticleById(article.getArticleId()))
                .thenReturn(true);
        Mockito.when(authenticationPersistencePort.getUserName())
                .thenReturn(ConstantsDomain.EMAIL);
        Mockito.when(supplyStockPersistencePort.updateQuantityArticle(article.getArticleId(),article.getQuantity()))
                .thenReturn(new ResponseStock("", HttpStatus.CREATED.toString()));


        supplyUseCase.addSupply(article);

        Mockito.verify(supplyStockPersistencePort, Mockito.times(ConstantsDomain.CALLED_1)).
                existArticleById(article.getArticleId());
        Mockito.verify(authenticationPersistencePort, Mockito.times(ConstantsDomain.CALLED_1)).
                getUserName();
        Mockito.verify(supplyStockPersistencePort, Mockito.times(ConstantsDomain.CALLED_1)).
                updateQuantityArticle(article.getArticleId(),article.getQuantity());
    }

   @Test
    void shouldThrowsExceptionWhenArticleNotExist() {
       Mockito.when(supplyStockPersistencePort.existArticleById(article.getArticleId()))
               .thenReturn(false);

        Assertions.assertThrows(NoDataFoundException.class, () -> {
            supplyUseCase.addSupply(article);
        });

        Mockito.verify(supplyStockPersistencePort, Mockito.times(ConstantsDomain.CALLED_1))
                .existArticleById(article.getArticleId());
    }

    @Test
    void shouldThrowsExceptionWhenStockReturnConflict() {
        Mockito.when(supplyStockPersistencePort.existArticleById(article.getArticleId()))
                .thenReturn(true);
        Mockito.when(authenticationPersistencePort.getUserName())
                .thenReturn(ConstantsDomain.EMAIL);
        Mockito.when(supplyStockPersistencePort.updateQuantityArticle(article.getArticleId(),article.getQuantity()))
                .thenReturn(new ResponseStock("", HttpStatus.CONFLICT.toString()));

        Assertions.assertThrows(ErrorStockException.class, () -> {
            supplyUseCase.addSupply(article);
        });

        Mockito.verify(supplyStockPersistencePort, Mockito.times(ConstantsDomain.CALLED_1)).
                existArticleById(article.getArticleId());
        Mockito.verify(authenticationPersistencePort, Mockito.times(ConstantsDomain.CALLED_1)).
                getUserName();
        Mockito.verify(supplyStockPersistencePort, Mockito.times(ConstantsDomain.CALLED_1)).
                updateQuantityArticle(article.getArticleId(),article.getQuantity());
    }

    @Test
    void shouldThrowsExceptionWhenStockReturnNull() {
        Mockito.when(supplyStockPersistencePort.existArticleById(article.getArticleId()))
                .thenReturn(true);
        Mockito.when(authenticationPersistencePort.getUserName())
                .thenReturn(ConstantsDomain.EMAIL);
        Mockito.when(supplyStockPersistencePort.updateQuantityArticle(article.getArticleId(),article.getQuantity()))
                .thenReturn(null);

        Assertions.assertThrows(ErrorStockException.class, () -> {
            supplyUseCase.addSupply(article);
        });

        Mockito.verify(supplyStockPersistencePort, Mockito.times(ConstantsDomain.CALLED_1)).
                existArticleById(article.getArticleId());
        Mockito.verify(authenticationPersistencePort, Mockito.times(ConstantsDomain.CALLED_1)).
                getUserName();
        Mockito.verify(supplyStockPersistencePort, Mockito.times(ConstantsDomain.CALLED_1)).
                updateQuantityArticle(article.getArticleId(),article.getQuantity());
    }
}

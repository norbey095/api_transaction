package com.emazon.api_transaction.domain.usecase;


import com.emazon.api_transaction.domain.api.ISupplyServicePort;
import com.emazon.api_transaction.domain.exception.ErrorStockException;
import com.emazon.api_transaction.domain.exception.NoDataFoundException;
import com.emazon.api_transaction.domain.model.ArticleUpdate;
import com.emazon.api_transaction.domain.model.ResponseStock;
import com.emazon.api_transaction.domain.spi.IAthenticationPersistencePort;
import com.emazon.api_transaction.domain.spi.ISupplyPersistencePort;
import com.emazon.api_transaction.domain.spi.ISupplyStockPersistencePort;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;


public class SupplyUseCase implements ISupplyServicePort {

    private final ISupplyPersistencePort supplyPersistencePort;
    private final ISupplyStockPersistencePort supplyStockPersistencePort;
    private final IAthenticationPersistencePort authenticationPersistencePort;

    public SupplyUseCase(ISupplyPersistencePort supplyPersistencePort,
                         ISupplyStockPersistencePort supplyStockPersistencePort,
                         IAthenticationPersistencePort authenticationPersistencePort) {
        this.supplyPersistencePort = supplyPersistencePort;
        this.supplyStockPersistencePort = supplyStockPersistencePort;
        this.authenticationPersistencePort = authenticationPersistencePort;
    }

    @Override
    public void saveSupply(ArticleUpdate articleUpdate) {
        if (!supplyStockPersistencePort.existArticleById(articleUpdate.getArticleId())) {
            throw new NoDataFoundException();
        }
        articleUpdate.setUserEmail(authenticationPersistencePort.getUserName());
        articleUpdate.setUpdateDate(LocalDate.now());
        ResponseStock responseStock = supplyStockPersistencePort
                .updateQuantityArticle(articleUpdate.getArticleId(),articleUpdate.getQuantity());
        if(responseStock == null || !responseStock.getStatus().equals(HttpStatus.CREATED.toString())){
            throw new ErrorStockException();
        }
        supplyPersistencePort.saveSupply(articleUpdate);
    }
}

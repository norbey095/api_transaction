package com.emazon.api_transaction.domain.usecase;


import com.emazon.api_transaction.domain.api.ISupplyServicePort;
import com.emazon.api_transaction.domain.exception.ErrorStockException;
import com.emazon.api_transaction.domain.exception.NoDataFoundException;
import com.emazon.api_transaction.domain.model.ArticleUpdate;
import com.emazon.api_transaction.domain.model.ResponseStock;
import com.emazon.api_transaction.domain.spi.IAthenticationPersistencePort;
import com.emazon.api_transaction.domain.spi.ISupplyPersistencePort;
import com.emazon.api_transaction.domain.spi.IClientStockPersistencePort;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;


public class SupplyUseCase implements ISupplyServicePort {

    private final ISupplyPersistencePort supplyPersistencePort;
    private final IClientStockPersistencePort supplyStockPersistencePort;
    private final IAthenticationPersistencePort authenticationPersistencePort;

    public SupplyUseCase(ISupplyPersistencePort supplyPersistencePort,
                         IClientStockPersistencePort supplyStockPersistencePort,
                         IAthenticationPersistencePort authenticationPersistencePort) {
        this.supplyPersistencePort = supplyPersistencePort;
        this.supplyStockPersistencePort = supplyStockPersistencePort;
        this.authenticationPersistencePort = authenticationPersistencePort;
    }

    @Override
    public void addSupply(ArticleUpdate articleUpdate) {
        validateArticleID(articleUpdate.getArticleId());
        articleUpdate.setUserEmail(authenticationPersistencePort.getUserName());
        articleUpdate.setUpdateDate(LocalDate.now());
        updateQuantityInStock(articleUpdate.getArticleId(),articleUpdate.getQuantity());

        supplyPersistencePort.saveSupply(articleUpdate);
    }

    private void validateArticleID(Integer id){
        if (!supplyStockPersistencePort.existArticleById(id)) {
            throw new NoDataFoundException();
        }
    }

    private void updateQuantityInStock(Integer id,Integer quantity){
        ResponseStock responseStock = supplyStockPersistencePort
                .updateQuantityArticle(id,quantity);
        if(responseStock == null || !responseStock.getStatus().equals(HttpStatus.CREATED.toString())){
            throw new ErrorStockException();
        }
    }
}

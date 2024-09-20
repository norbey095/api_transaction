package com.emazon.api_transaction.domain.usecase;


import com.emazon.api_transaction.domain.api.ISupplyServicePort;
import com.emazon.api_transaction.domain.exception.NoDataFoundException;
import com.emazon.api_transaction.domain.model.ArticleUpdate;
import com.emazon.api_transaction.domain.spi.IAthenticationPersistencePort;
import com.emazon.api_transaction.domain.spi.ISupplyPersistencePort;
import com.emazon.api_transaction.domain.spi.ISupplyStockPersistencePort;

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
        supplyStockPersistencePort.updateQuantityArticle(articleUpdate.getArticleId(),articleUpdate.getQuantity());
        supplyPersistencePort.saveSupply(articleUpdate);
    }
}

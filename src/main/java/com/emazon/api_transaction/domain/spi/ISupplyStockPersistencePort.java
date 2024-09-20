package com.emazon.api_transaction.domain.spi;

import com.emazon.api_transaction.domain.model.ResponseStock;

public interface ISupplyStockPersistencePort {

      boolean existArticleById(Integer articleId);

      ResponseStock updateQuantityArticle(Integer articleId, Integer quantity);

}

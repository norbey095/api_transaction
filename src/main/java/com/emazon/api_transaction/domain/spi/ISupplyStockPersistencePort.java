package com.emazon.api_transaction.domain.spi;

public interface ISupplyStockPersistencePort {

      boolean existArticleById(Integer articleId);

      void updateQuantityArticle(Integer articleId, Integer quantity);

}

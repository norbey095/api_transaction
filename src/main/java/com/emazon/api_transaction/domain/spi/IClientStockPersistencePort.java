package com.emazon.api_transaction.domain.spi;

import com.emazon.api_transaction.domain.model.ResponseStock;
import com.emazon.api_transaction.domain.model.Sales;

import java.util.List;

public interface IClientStockPersistencePort {

      boolean existArticleById(Integer articleId);

      ResponseStock updateQuantityArticle(Integer articleId, Integer quantity);

      ResponseStock subtractQuantityArticle(List<Sales> salesRequest);

}

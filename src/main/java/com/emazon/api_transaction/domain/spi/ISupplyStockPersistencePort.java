package com.emazon.api_transaction.domain.spi;

import com.emazon.api_transaction.domain.model.ResponseStock;
import com.emazon.api_transaction.domain.model.SalesRequest;

import java.util.List;

public interface ISupplyStockPersistencePort {

      boolean existArticleById(Integer articleId);

      ResponseStock updateQuantityArticle(Integer articleId, Integer quantity);

      ResponseStock subtractQuantityArticle(List<SalesRequest> salesRequest);

}

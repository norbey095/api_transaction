package com.emazon.api_transaction.domain.spi;

import com.emazon.api_transaction.domain.model.ArticleUpdate;

public interface ISupplyPersistencePort {

      void saveSupply(ArticleUpdate articleUpdate);

}

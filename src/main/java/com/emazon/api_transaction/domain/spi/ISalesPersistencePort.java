package com.emazon.api_transaction.domain.spi;

import com.emazon.api_transaction.domain.model.SalesRequest;

public interface ISalesPersistencePort {

    void saveSales(SalesRequest salesRequest);

}

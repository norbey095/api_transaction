package com.emazon.api_transaction.domain.usecase;


import com.emazon.api_transaction.domain.api.ISalesServicePort;
import com.emazon.api_transaction.domain.model.SalesRequest;
import com.emazon.api_transaction.domain.spi.ISalesPersistencePort;

public class SalesUseCase implements ISalesServicePort {

    private final ISalesPersistencePort salesPersistencePort;

    public SalesUseCase(ISalesPersistencePort salesPersistencePort) {
        this.salesPersistencePort = salesPersistencePort;
    }

    @Override
    public void saveSales(SalesRequest salesRequest) {
        salesPersistencePort.saveSales(salesRequest);
    }
}

package com.emazon.api_transaction.domain.usecase;

import com.emazon.api_transaction.domain.api.ISalesServicePort;
import com.emazon.api_transaction.domain.exception.ErrorStockException;
import com.emazon.api_transaction.domain.exception.NoDataFoundException;
import com.emazon.api_transaction.domain.model.ResponseStock;
import com.emazon.api_transaction.domain.model.SalesRequest;
import com.emazon.api_transaction.domain.spi.ISalesPersistencePort;
import com.emazon.api_transaction.domain.spi.ISupplyStockPersistencePort;
import org.springframework.http.HttpStatus;

import java.util.List;

public class SalesUseCase implements ISalesServicePort {

    private final ISalesPersistencePort salesPersistencePort;
    private final ISupplyStockPersistencePort supplyStockPersistencePort;

    public SalesUseCase(ISalesPersistencePort salesPersistencePort,
                        ISupplyStockPersistencePort supplyStockPersistencePort) {
        this.salesPersistencePort = salesPersistencePort;
        this.supplyStockPersistencePort = supplyStockPersistencePort;
    }

    @Override
    public void saveSales(List<SalesRequest> salesRequest) {
        subtractQuantityArticle(salesRequest);

        for(SalesRequest sales: salesRequest){
            validateArticleID(sales.getIdArticle());
            salesPersistencePort.saveSales(sales);
        }
    }

    private void subtractQuantityArticle(List<SalesRequest> salesRequest){
        ResponseStock responseStock = supplyStockPersistencePort.subtractQuantityArticle(salesRequest);
        if(responseStock == null || !responseStock.getStatus().equals(HttpStatus.CREATED.toString())){
            throw new ErrorStockException();
        }
    }

    private void validateArticleID(Integer id){
        if (!supplyStockPersistencePort.existArticleById(id)) {
            throw new NoDataFoundException();
        }
    }
}

package com.emazon.api_transaction.domain.usecase;

import com.emazon.api_transaction.domain.api.ISalesServicePort;
import com.emazon.api_transaction.domain.exception.ErrorStockException;
import com.emazon.api_transaction.domain.exception.NoDataFoundException;
import com.emazon.api_transaction.domain.model.ResponseStock;
import com.emazon.api_transaction.domain.model.Sales;
import com.emazon.api_transaction.domain.spi.IAthenticationPersistencePort;
import com.emazon.api_transaction.domain.spi.ISalesPersistencePort;
import com.emazon.api_transaction.domain.spi.IClientStockPersistencePort;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class SalesUseCase implements ISalesServicePort {

    private static final Integer NUMBER_O = 0;

    private final ISalesPersistencePort salesPersistencePort;
    private final IClientStockPersistencePort supplyStockPersistencePort;
    private final IAthenticationPersistencePort athenticationPersistencePort;

    public SalesUseCase(ISalesPersistencePort salesPersistencePort,
                        IClientStockPersistencePort supplyStockPersistencePort,
                        IAthenticationPersistencePort athenticationPersistencePort) {
        this.salesPersistencePort = salesPersistencePort;
        this.supplyStockPersistencePort = supplyStockPersistencePort;
        this.athenticationPersistencePort = athenticationPersistencePort;
    }

    @Override
    public void saveSales(List<Sales> salesRequest) {
        try {
            subtractQuantityArticle(salesRequest);

            for(Sales sales: salesRequest){
                validateArticleID(sales.getArticleId());
                salesPersistencePort.saveSales(sales);
            }
        } catch (Exception e){
            String userName = athenticationPersistencePort.getUserName();
            this.deleteRegistry(userName, salesRequest.get(NUMBER_O).getBuyDate());
        }
    }

    @Override
    public void deleteRegistry(String userName, LocalDateTime buyDate) {
        List<Sales> salesList = salesPersistencePort.getAllSales(userName, buyDate);
        for(Sales sales : salesList){
            supplyStockPersistencePort.updateQuantityArticle(sales.getArticleId(), sales.getQuantity());
        }
        salesPersistencePort.deleteRegistry(userName, buyDate);
    }

    private void subtractQuantityArticle(List<Sales> salesRequest){
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

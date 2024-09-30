package com.emazon.api_transaction.domain.usecase;

import com.emazon.api_transaction.domain.api.ISalesServicePort;
import com.emazon.api_transaction.domain.exception.ErrorStockException;
import com.emazon.api_transaction.domain.exception.NoDataFoundException;
import com.emazon.api_transaction.domain.exception.PurchaseFailureException;
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
    private final IClientStockPersistencePort clientStockPersistencePort;
    private final IAthenticationPersistencePort athenticationPersistencePort;

    public SalesUseCase(ISalesPersistencePort salesPersistencePort,
                        IClientStockPersistencePort clientStockPersistencePort,
                        IAthenticationPersistencePort athenticationPersistencePort) {
        this.salesPersistencePort = salesPersistencePort;
        this.clientStockPersistencePort = clientStockPersistencePort;
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
        } catch (ErrorStockException e){
            throw new ErrorStockException();
        } catch (NoDataFoundException e){
            String userName = athenticationPersistencePort.getUserName();
            this.deleteRegistry(userName, salesRequest.get(NUMBER_O).getBuyDate());
            throw new NoDataFoundException();
        } catch (Exception e){
            String userName = athenticationPersistencePort.getUserName();
            this.deleteRegistry(userName, salesRequest.get(NUMBER_O).getBuyDate());
            throw new PurchaseFailureException();
        }
    }

    @Override
    public void deleteRegistry(String userName, LocalDateTime buyDate) {
        List<Sales> salesList = salesPersistencePort.getAllSales(userName, buyDate);
        for(Sales sales : salesList){
            clientStockPersistencePort.updateQuantityArticle(sales.getArticleId(), sales.getQuantity());
        }
        salesPersistencePort.deleteRegistry(userName, buyDate);
    }

    private void subtractQuantityArticle(List<Sales> salesRequest){
        ResponseStock responseStock = clientStockPersistencePort.subtractQuantityArticle(salesRequest);
        if(responseStock == null || !responseStock.getStatus().equals(HttpStatus.CREATED.toString())){
            throw new ErrorStockException();
        }
    }

    private void validateArticleID(Integer id){
        if (!clientStockPersistencePort.existArticleById(id)) {
            throw new NoDataFoundException();
        }
    }
}

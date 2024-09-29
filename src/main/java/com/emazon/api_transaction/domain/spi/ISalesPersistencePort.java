package com.emazon.api_transaction.domain.spi;

import com.emazon.api_transaction.domain.model.Sales;

import java.time.LocalDateTime;
import java.util.List;

public interface ISalesPersistencePort {

    void saveSales(Sales salesRequest);

    List<Sales> getAllSales(String userName, LocalDateTime buyDate);

    void deleteRegistry(String userName, LocalDateTime buyDate);
}

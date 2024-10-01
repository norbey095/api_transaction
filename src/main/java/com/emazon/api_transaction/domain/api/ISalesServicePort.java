package com.emazon.api_transaction.domain.api;

import com.emazon.api_transaction.domain.model.Sales;

import java.time.LocalDateTime;
import java.util.List;

public interface ISalesServicePort {

      void saveSales(List<Sales> salesRequest);

      void deleteRegistry(String userName, LocalDateTime buyDate);
}

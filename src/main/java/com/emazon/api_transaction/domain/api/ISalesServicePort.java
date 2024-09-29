package com.emazon.api_transaction.domain.api;

import com.emazon.api_transaction.domain.model.SalesRequest;

import java.util.List;

public interface ISalesServicePort {

      void saveSales(List<SalesRequest> salesRequest);
}

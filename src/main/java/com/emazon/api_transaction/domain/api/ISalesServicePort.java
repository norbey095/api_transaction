package com.emazon.api_transaction.domain.api;

import com.emazon.api_transaction.domain.model.SalesRequest;

public interface ISalesServicePort {

      void saveSales(SalesRequest salesRequest);
}

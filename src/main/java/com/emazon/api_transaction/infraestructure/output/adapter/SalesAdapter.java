package com.emazon.api_transaction.infraestructure.output.adapter;

import com.emazon.api_transaction.domain.model.Sales;
import com.emazon.api_transaction.domain.spi.ISalesPersistencePort;
import com.emazon.api_transaction.infraestructure.output.entity.SalesEntity;
import com.emazon.api_transaction.infraestructure.output.mapper.ISalesEntityMapper;
import com.emazon.api_transaction.infraestructure.output.repository.ISalesRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class SalesAdapter implements ISalesPersistencePort {

    private final ISalesRepository salesRepository;
    private final ISalesEntityMapper salesEntityMapper;

    @Override
    public void saveSales(Sales salesRequest) {

        SalesEntity salesEntity = salesEntityMapper.salesToSalesEntity(salesRequest);
        salesRepository.save(salesEntity);
    }

    @Override
    public List<Sales> getAllSales(String userName, LocalDateTime buyDate) {
        List<SalesEntity> salesEntities = salesRepository.findSalesByUserName(userName, buyDate);
        return salesEntityMapper.salesEntityListToSalesList(salesEntities);
    }

    @Override
    public void deleteRegistry(String userName, LocalDateTime buyDate) {
        salesRepository.deleteSalesByUserName(userName, buyDate);
    }
}

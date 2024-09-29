package com.emazon.api_transaction.infraestructure.output.mapper;

import com.emazon.api_transaction.domain.model.Sales;
import com.emazon.api_transaction.infraestructure.output.entity.SalesEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ISalesEntityMapper {

    SalesEntity salesToSalesEntity(Sales sales);

    List<Sales> salesEntityListToSalesList (List<SalesEntity> salesEntity);
}

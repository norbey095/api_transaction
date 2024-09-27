package com.emazon.api_transaction.application.mapper;

import com.emazon.api_transaction.application.dto.SalesRequestDto;
import com.emazon.api_transaction.domain.model.SalesRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SalesMapper {

    SalesRequest salesRequestDtoToSalesRequest(SalesRequestDto salesRequestDto);

}

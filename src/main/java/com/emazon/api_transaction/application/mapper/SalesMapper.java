package com.emazon.api_transaction.application.mapper;

import com.emazon.api_transaction.application.dto.transaction.SalesRequestDto;
import com.emazon.api_transaction.domain.model.SalesRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SalesMapper {

    List<SalesRequest> salesRequestDtoListToSalesRequestList(List<SalesRequestDto> salesRequestDtos);

}

package com.emazon.api_transaction.infraestructure.output.mapper;

import com.emazon.api_transaction.application.dto.stock.ResponseStockDto;
import com.emazon.api_transaction.application.dto.stock.SubtractArticleRequestDto;
import com.emazon.api_transaction.domain.model.ArticleUpdate;
import com.emazon.api_transaction.domain.model.ResponseStock;
import com.emazon.api_transaction.domain.model.SalesRequest;
import com.emazon.api_transaction.infraestructure.output.entity.SupplyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ISupplyEntityMapper {

    SupplyEntity articleUpdateToSupplyEntity(ArticleUpdate articleUpdate);

    ResponseStock responseStockDtoToResponseStock(ResponseStockDto responseStockDto);

    @Mapping(source = "salesRequests.idArticle", target = "id")
    @Mapping(source = "salesRequests.quantity", target = "quantity")
    List<SubtractArticleRequestDto> salesRequestsListToSubtractArticleRequestDtoList(List<SalesRequest> salesRequests);

}

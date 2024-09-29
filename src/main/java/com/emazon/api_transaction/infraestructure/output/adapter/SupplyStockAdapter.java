package com.emazon.api_transaction.infraestructure.output.adapter;

import com.emazon.api_transaction.application.dto.stock.ResponseStockDto;
import com.emazon.api_transaction.application.dto.stock.SubtractArticleRequestDto;
import com.emazon.api_transaction.application.dto.transaction.ArticleUpdateRequestDto;
import com.emazon.api_transaction.domain.model.ResponseStock;
import com.emazon.api_transaction.domain.model.SalesRequest;
import com.emazon.api_transaction.domain.spi.ISupplyStockPersistencePort;
import com.emazon.api_transaction.infraestructure.configuration.feign.IFeignClientStock;
import com.emazon.api_transaction.infraestructure.output.mapper.ISupplyEntityMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SupplyStockAdapter implements ISupplyStockPersistencePort {

    private final IFeignClientStock feignClientStock;
    private final ISupplyEntityMapper supplyEntityMapper;


    @Override
    public boolean existArticleById(Integer articleId) {
        return feignClientStock.existById(articleId) != null;
    }

    @Override
    public ResponseStock updateQuantityArticle(Integer articleId, Integer quantity) {
        ArticleUpdateRequestDto articleUpdateRequestDto = new ArticleUpdateRequestDto(articleId,quantity);

        return supplyEntityMapper.responseStockDtoToResponseStock(feignClientStock
                .updateArticle(articleUpdateRequestDto));
    }

    @Override
    public ResponseStock subtractQuantityArticle(List<SalesRequest> salesRequest) {
        List<SubtractArticleRequestDto> subtractArticleRequest = supplyEntityMapper.
                salesRequestsListToSubtractArticleRequestDtoList(salesRequest);

        ResponseStockDto responseStock = feignClientStock.subtractQuantityArticle(subtractArticleRequest);

        return supplyEntityMapper.responseStockDtoToResponseStock(responseStock);
    }
}

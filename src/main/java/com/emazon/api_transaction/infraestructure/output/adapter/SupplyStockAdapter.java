package com.emazon.api_transaction.infraestructure.output.adapter;


import com.emazon.api_transaction.application.dto.ArticleUpdateRequestDto;
import com.emazon.api_transaction.domain.spi.ISupplyStockPersistencePort;
import com.emazon.api_transaction.infraestructure.configuration.feign.IFeignClientStock;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SupplyStockAdapter implements ISupplyStockPersistencePort {

    private final IFeignClientStock feignClientStock;


    @Override
    public boolean existArticleById(Integer articleId) {
        return feignClientStock.existById(articleId);
    }

    @Override
    public void updateQuantityArticle(Integer articleId, Integer quantity) {
        ArticleUpdateRequestDto articleUpdateRequestDto = new ArticleUpdateRequestDto(articleId,quantity);
        feignClientStock.updateArticle(articleUpdateRequestDto);
    }
}

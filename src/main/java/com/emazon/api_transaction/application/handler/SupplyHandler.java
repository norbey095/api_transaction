package com.emazon.api_transaction.application.handler;

import com.emazon.api_transaction.application.dto.ArticleUpdateRequestDto;
import com.emazon.api_transaction.application.dto.ResponseStockDto;
import com.emazon.api_transaction.application.mapper.SupplyMapper;
import com.emazon.api_transaction.application.util.ConstantsDto;
import com.emazon.api_transaction.domain.api.ISupplyServicePort;
import com.emazon.api_transaction.domain.model.ArticleUpdate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
public class SupplyHandler implements ISupplyHandler {

    private final ISupplyServicePort supplyServicePort;
    private final SupplyMapper supplyMapper;

    @Override
    public ResponseStockDto addSupply(ArticleUpdateRequestDto articleUpdateRequestDto) {
        ArticleUpdate articleUpdate = supplyMapper.articleUpdateRequestDtoToArticleUpdate(articleUpdateRequestDto);
        supplyServicePort.saveSupply(articleUpdate);
        return new ResponseStockDto(ConstantsDto.UPDATE_CORRECT, HttpStatus.CREATED.toString());
    }
}

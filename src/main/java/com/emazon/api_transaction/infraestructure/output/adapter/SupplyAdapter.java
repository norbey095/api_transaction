package com.emazon.api_transaction.infraestructure.output.adapter;


import com.emazon.api_transaction.domain.model.ArticleUpdate;
import com.emazon.api_transaction.domain.spi.ISupplyPersistencePort;
import com.emazon.api_transaction.infraestructure.output.entity.SupplyEntity;
import com.emazon.api_transaction.infraestructure.output.mapper.ISupplyEntityMapper;
import com.emazon.api_transaction.infraestructure.output.repository.ISupplyRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SupplyAdapter implements ISupplyPersistencePort {

    private final ISupplyRepository supplyRepository;
    private final ISupplyEntityMapper supplyEntityMapper;


    @Override
    public void saveSupply(ArticleUpdate articleUpdate) {
        SupplyEntity supplyEntity = supplyEntityMapper.articleUpdateToSupplyEntity(articleUpdate);
        supplyRepository.save(supplyEntity);
    }
}

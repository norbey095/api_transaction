package com.emazon.api_transaction.infraestructure.output.mapper;

import com.emazon.api_transaction.domain.model.ArticleUpdate;
import com.emazon.api_transaction.infraestructure.output.entity.SupplyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISupplyEntityMapper {

    SupplyEntity articleUpdateToSupplyEntity(ArticleUpdate articleUpdate);

}

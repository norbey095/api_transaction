package com.emazon.api_transaction.application.mapper;

import com.emazon.api_transaction.application.dto.transaction.ArticleUpdateRequestDto;
import com.emazon.api_transaction.domain.model.ArticleUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SupplyMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "userEmail", ignore = true)
    ArticleUpdate articleUpdateRequestDtoToArticleUpdate(ArticleUpdateRequestDto articleUpdateRequestDto);

}

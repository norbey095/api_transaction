package com.emazon.api_transaction.infraestructure.configuration.feign;

import com.emazon.api_transaction.application.dto.ArticleResponseDto;
import com.emazon.api_transaction.application.dto.ArticleUpdateRequestDto;
import com.emazon.api_transaction.application.dto.ResponseStockDto;
import com.emazon.api_transaction.infraestructure.util.ConstantsConfigurations;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = ConstantsConfigurations.STOCK, url = ConstantsConfigurations.URL_STOCK_PORT
        , configuration = FeignConfig.class)
public interface IFeignClientStock {

    @GetMapping(ConstantsConfigurations.URL_STOCK_ARTICLEID)
    ArticleResponseDto existById(@PathVariable Integer articleId);


    @PostMapping(value = ConstantsConfigurations.URL_STOCK_UPDATE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseStockDto updateArticle(@RequestBody ArticleUpdateRequestDto articleUpdateRequestDto);

}

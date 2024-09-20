package com.emazon.api_transaction.infraestructure.configuration;

import com.emazon.api_transaction.domain.api.ISupplyServicePort;
import com.emazon.api_transaction.domain.spi.IAthenticationPersistencePort;
import com.emazon.api_transaction.domain.spi.ISupplyPersistencePort;
import com.emazon.api_transaction.domain.spi.ISupplyStockPersistencePort;
import com.emazon.api_transaction.domain.usecase.SupplyUseCase;
import com.emazon.api_transaction.infraestructure.configuration.feign.IFeignClientStock;
import com.emazon.api_transaction.infraestructure.output.adapter.AuthenticationAdapter;
import com.emazon.api_transaction.infraestructure.output.adapter.SupplyAdapter;
import com.emazon.api_transaction.infraestructure.output.adapter.SupplyStockAdapter;
import com.emazon.api_transaction.infraestructure.output.mapper.ISupplyEntityMapper;
import com.emazon.api_transaction.infraestructure.output.repository.ISupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ISupplyRepository supplyRepository;
    private final ISupplyEntityMapper supplyEntityMapper;


    @Bean
    public ISupplyPersistencePort supplyPersistencePort() {
        return new SupplyAdapter(supplyRepository, supplyEntityMapper);
    }

    @Bean
    public IAthenticationPersistencePort authenticationPersistencePort() {
        return new AuthenticationAdapter();
    }

    @Bean
    public ISupplyStockPersistencePort supplyStockPersistencePort(IFeignClientStock feignClientStock) {
        return new SupplyStockAdapter(feignClientStock);
    }

    @Bean
    public ISupplyServicePort supplyServicePort(ISupplyPersistencePort supplyPersistencePort,
                                                ISupplyStockPersistencePort supplyStockPersistencePort,
                                                IAthenticationPersistencePort authenticationPersistencePort) {
        return new SupplyUseCase(supplyPersistencePort, supplyStockPersistencePort,authenticationPersistencePort);
    }

}

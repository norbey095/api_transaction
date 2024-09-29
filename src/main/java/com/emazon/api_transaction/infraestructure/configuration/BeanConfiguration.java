package com.emazon.api_transaction.infraestructure.configuration;

import com.emazon.api_transaction.domain.api.ISalesServicePort;
import com.emazon.api_transaction.domain.api.ISupplyServicePort;
import com.emazon.api_transaction.domain.spi.IAthenticationPersistencePort;
import com.emazon.api_transaction.domain.spi.ISalesPersistencePort;
import com.emazon.api_transaction.domain.spi.ISupplyPersistencePort;
import com.emazon.api_transaction.domain.spi.IClientStockPersistencePort;
import com.emazon.api_transaction.domain.usecase.SalesUseCase;
import com.emazon.api_transaction.domain.usecase.SupplyUseCase;
import com.emazon.api_transaction.infraestructure.configuration.feign.IFeignClientStock;
import com.emazon.api_transaction.infraestructure.output.adapter.AuthenticationAdapter;
import com.emazon.api_transaction.infraestructure.output.adapter.SalesAdapter;
import com.emazon.api_transaction.infraestructure.output.adapter.SupplyAdapter;
import com.emazon.api_transaction.infraestructure.output.adapter.ClientStockAdapter;
import com.emazon.api_transaction.infraestructure.output.mapper.ISalesEntityMapper;
import com.emazon.api_transaction.infraestructure.output.mapper.ISupplyEntityMapper;
import com.emazon.api_transaction.infraestructure.output.repository.ISalesRepository;
import com.emazon.api_transaction.infraestructure.output.repository.ISupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ISupplyRepository supplyRepository;
    private final ISupplyEntityMapper supplyEntityMapper;
    private final ISalesRepository salesRepository;
    private final ISalesEntityMapper salesEntityMapper;


    @Bean
    public ISupplyPersistencePort supplyPersistencePort() {
        return new SupplyAdapter(supplyRepository, supplyEntityMapper);
    }

    @Bean
    public IAthenticationPersistencePort authenticationPersistencePort() {
        return new AuthenticationAdapter();
    }

    @Bean
    public IClientStockPersistencePort supplyStockPersistencePort(IFeignClientStock feignClientStock
            , ISupplyEntityMapper supplyEntityMapper) {
        return new ClientStockAdapter(feignClientStock,supplyEntityMapper);
    }

    @Bean
    public ISupplyServicePort supplyServicePort(ISupplyPersistencePort supplyPersistencePort,
                                                IClientStockPersistencePort supplyStockPersistencePort,
                                                IAthenticationPersistencePort authenticationPersistencePort) {
        return new SupplyUseCase(supplyPersistencePort, supplyStockPersistencePort,authenticationPersistencePort);
    }

    @Bean
    public ISalesServicePort salesServicePort(ISalesPersistencePort salesPersistencePort,
                                              IClientStockPersistencePort supplyStockPersistencePort,
                                              IAthenticationPersistencePort authenticationPersistencePort) {
        return new SalesUseCase(salesPersistencePort, supplyStockPersistencePort,authenticationPersistencePort);
    }

    @Bean
    public ISalesPersistencePort salesPersistencePort() {
        return new SalesAdapter(salesRepository, salesEntityMapper);
    }

}

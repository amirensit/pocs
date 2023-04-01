package org.poc2.example.infrastucture.configuration;

import org.poc2.example.Poc2Application;
import org.poc2.example.domain.repository.OrderRepository;
import org.poc2.example.domain.service.DomainOrderService;
import org.poc2.example.domain.service.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = Poc2Application.class)
public class BeanConfiguration {

    @Bean
    OrderService orderService(final OrderRepository orderRepository) {
        return new DomainOrderService(orderRepository);
    }
}
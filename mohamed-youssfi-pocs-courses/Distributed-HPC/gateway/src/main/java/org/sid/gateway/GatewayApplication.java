package org.sid.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    // @Bean there is an other solution better than this one
    RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
//                .route(r -> r.path("/customers/**").uri("http://localhost:8081/")) use this syntax when working without registry server
                .route(r -> r.path("/customers/**").uri("lb://CUSTOMER-SERVICE/")) // this syntax is used with registry server. It works either with lowerCase or uppercase
                .route(r -> r.path("/products/**").uri("lb://product-service/"))
                .build();
    }

    /*
    This method means just look at uri of request and you will find the name of microservice.
    http://localhost:8888/products will not work.
    The correct uri is: http://localhost:8888/PRODUCT-SERVICE/products  # only uppercase works here
     */
    @Bean
    DiscoveryClientRouteDefinitionLocator definitionLocator(ReactiveDiscoveryClient reactiveDiscoveryClient,
                                                            DiscoveryLocatorProperties discoveryLocatorProperties) {
        return new DiscoveryClientRouteDefinitionLocator(reactiveDiscoveryClient, discoveryLocatorProperties);
    }
}

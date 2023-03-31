package org.poc2.example.domain.service;

import org.poc2.example.domain.Product;

import java.util.UUID;

/**
 * In a hexagonal architecture, this service is an adapter that implements the port.
 * Additionally, we'll not register it as a Spring bean because, from a domain perspective,
 * this is in the inside part, and Spring configuration is on the outside.
 * We'll manually wire it with Spring in the infrastructure layer.
 */
public interface OrderService {
    UUID createOrder(Product product);

    void addProduct(UUID id, Product product);

    void completeOrder(UUID id);

    void deleteProduct(UUID id, UUID productId);
}

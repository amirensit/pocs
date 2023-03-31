package org.poc2.example.domain.repository;

import org.poc2.example.domain.Order;

import java.util.Optional;
import java.util.UUID;

/**
 * This repository package should be called port to respect hexagonal architecture
 */
public interface OrderRepository {
    Optional<Order> findById(UUID id);

    void save(Order order);
}

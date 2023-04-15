package org.poc2.example.infrastucture.repository.mongo;

import org.poc2.example.domain.Order;
import org.poc2.example.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * This implementation stores our Order in MongoDB.
 * In a hexagonal architecture, this implementation is an output adapter.
 */
@Component
@Primary
public class MongoDBOrderRepository implements OrderRepository {

    private final SpringDataMongoOrderRepository orderMongoRepository;

    @Autowired
    public MongoDBOrderRepository(final SpringDataMongoOrderRepository orderRepository) {
        this.orderMongoRepository = orderRepository;
    }

    @Override
    public Optional<Order> findById(final UUID id) {
        return orderMongoRepository.findById(id);
    }

    @Override
    public void save(final Order order) {
        orderMongoRepository.save(order);
    }
}
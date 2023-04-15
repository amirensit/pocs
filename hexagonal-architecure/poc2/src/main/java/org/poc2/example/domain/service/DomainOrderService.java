package org.poc2.example.domain.service;

import org.poc2.example.domain.Order;
import org.poc2.example.domain.Product;
import org.poc2.example.domain.repository.OrderRepository;

import java.util.UUID;


/**
 * In a hexagonal architecture, this service is a use case that implements the input port.
 * Additionally, we'll not register it as a Spring bean because, from a domain perspective,
 * this is in the inside part, and Spring configuration is on the outside.
 * We'll manually wire it with Spring in the infrastructure layer.
 */
public class DomainOrderService implements OrderService {

    private final OrderRepository orderRepository;

    public DomainOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public UUID createOrder(Product product) {
        Order order = new Order(UUID.randomUUID(), product);
        orderRepository.save(order);

        return order.getId();
    }

    @Override
    public void addProduct(UUID id, Product product) {
        Order order = getOrder(id);
        order.addOrder(product);

        orderRepository.save(order);
    }

    @Override
    public void completeOrder(UUID id) {
        Order order = getOrder(id);
        order.complete();

        orderRepository.save(order);
    }

    @Override
    public void deleteProduct(UUID id, UUID productId) {
        Order order = getOrder(id);
        order.removeOrder(productId);

        orderRepository.save(order);
    }

    private Order getOrder(UUID id) {
        return orderRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);
    }
}

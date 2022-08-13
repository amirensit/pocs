package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.*;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.order.service.domain.valueobject.OrderItemId;
import com.food.ordering.system.order.service.domain.valueobject.StreetAddress;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Order extends AggregateRoot<OrderId> {
    private final CustomerId customerId;
    private final RestaurantId restaurantId;
    private final StreetAddress streetAddress;
    private final Money price;
    private final List<OrderItem> items;


    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failuresMessages;

    public void initializeOrder() {
        setId(new OrderId(UUID.randomUUID()));
        trackingId = new TrackingId(UUID.randomUUID());
        orderStatus = OrderStatus.PENDING;
        initializeOrderItems();
    }

    private void initializeOrderItems() {
        long itemId = 1;
        for (OrderItem orderItem: items) {
            orderItem.initializeOrderItem(super.getId(), new OrderItemId(itemId++));
        }
    }

    public void validateOrder() {
        validateInitialOrder();
        validateTotalPrice();
        validateItemsPrice();
    }

    public void pay() {
        if (orderStatus != OrderStatus.PENDING) {
            throw new OrderDomainException("Order is not in correct state for pay");
        }
        orderStatus = OrderStatus.PAID;
    }

    public void approve() {
        if (orderStatus != OrderStatus.PAID) {
            throw new OrderDomainException("Order is not in correct state to approve");
        }
        orderStatus = OrderStatus.APPROUVED;
    }

    public void initCancel(List<String> failuresMessages) {
        if (orderStatus != OrderStatus.PAID) {
            throw new OrderDomainException("Order is not in correct state to initCancel");
        }
        orderStatus = OrderStatus.CANCELLING;
        updateFailureMessages(failuresMessages);
    }

    private void updateFailureMessages(List<String> failuresMessages) {
        if (this.failuresMessages != null && failuresMessages != null) {
            this.failuresMessages.addAll(failuresMessages.stream().filter(message -> !message.isEmpty()).collect(Collectors.toList()));
        }
        if (this.failuresMessages == null) {
            this.failuresMessages = failuresMessages;
        }
    }

    public void cancel(List<String> failuresMessages) {
       if ((!orderStatus.equals(OrderStatus.CANCELLING) || orderStatus.equals(OrderStatus.PENDING))) {
           throw new OrderDomainException("order is not in correct status for cancel");
       }
       orderStatus = OrderStatus.CANCELLED;
        updateFailureMessages(failuresMessages);
    }

    private void validateItemsPrice() {
        Money orderItemTotal = items.stream().map(orderItem -> {
            validateOrderItemPrice(orderItem);
            return orderItem.getSubTotal();
        }).reduce(Money.ZERO, Money::add);
        if (!orderItemTotal.equals(price)) {
            throw new OrderDomainException("price is not equal to total order items price");
        }
    }

    private void validateOrderItemPrice(OrderItem orderItem) {
        if (!orderItem.isPriceValid()) {
            throw new OrderDomainException("order item price " + orderItem.getPrice().getAmount()
                    + "is not valid for product " + orderItem.getProduct().getId().getValue());
        }
    }

    private void validateTotalPrice() {
        if (price != null && !price.isGreaterThanZero()) {
            throw new OrderDomainException("total price must be greater than zero");
        }
    }

    private void validateInitialOrder() {
        if (getId() != null || orderStatus != null) {
            throw new OrderDomainException("Order is not in correct state for initialization");
        }
    }

    private Order(Builder builder) {
        super.setId(builder.orderId);
        customerId = builder.customerId;
        restaurantId = builder.restaurantId;
        streetAddress = builder.streetAddress;
        price = builder.price;
        items = builder.items;
        trackingId = builder.trackingId;
        orderStatus = builder.orderStatus;
        failuresMessages = builder.failuresMessages;
    }

    public static Builder builder() {
        return new Builder();
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public StreetAddress getStreetAddress() {
        return streetAddress;
    }

    public Money getPrice() {
        return price;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public TrackingId getTrackingId() {
        return trackingId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public List<String> getFailuresMessages() {
        return failuresMessages;
    }

    public static final class Builder {
        private OrderId orderId;
        private CustomerId customerId;
        private RestaurantId restaurantId;
        private StreetAddress streetAddress;
        private Money price;
        private List<OrderItem> items;
        private TrackingId trackingId;
        private OrderStatus orderStatus;
        private List<String> failuresMessages;

        private Builder() {
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder restaurantId(RestaurantId val) {
            restaurantId = val;
            return this;
        }

        public Builder streetAddress(StreetAddress val) {
            streetAddress = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder items(List<OrderItem> val) {
            items = val;
            return this;
        }

        public Builder trackingId(TrackingId val) {
            trackingId = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder failuresMessages(List<String> val) {
            failuresMessages = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}

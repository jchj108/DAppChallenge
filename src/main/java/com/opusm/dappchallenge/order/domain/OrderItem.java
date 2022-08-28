package com.opusm.dappchallenge.order.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.opusm.dappchallenge.item.domain.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Builder
@Entity
@Getter
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;
    @Column
    private Long quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    @JsonBackReference
    @Setter
    private Order order;

    private Long orderPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId")
    @JsonManagedReference
    private Item item;

    public OrderItem(Long orderItemId, Long quantity, Order order, Long orderPrice, Item item) {
        this.orderItemId = orderItemId;
        this.quantity = quantity;
        this.order = order;
        this.orderPrice = orderPrice;
        this.item = item;
    }

    public static OrderItem createOrderItem(Item item, Long quantity, Long orderPrice) {
        OrderItem orderItem = OrderItem.builder()
                .item(item)
                .orderPrice(orderPrice)
                .quantity(quantity)
                .build();

        item.removeStock(quantity);
        return orderItem;
    }
}

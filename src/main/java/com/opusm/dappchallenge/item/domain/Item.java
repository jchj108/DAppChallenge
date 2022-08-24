package com.opusm.dappchallenge.item.domain;

import com.opusm.dappchallenge.order.domain.OrderItem;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@Getter
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    @Column
    private Long price;
    @Column
    private Double pointRate;
    @Column
    private Long amount;
    @Column
    @NotNull
    private String owner;
    @ManyToOne(fetch = FetchType.LAZY)
    private OrderItem orderItem;

    public Item(Long itemId, Long price, Double pointRate, Long amount, String owner, OrderItem orderItem) {
        this.itemId = itemId;
        this.price = price;
        this.pointRate = pointRate;
        this.amount = amount;
        this.owner = owner;
        this.orderItem = orderItem;
    }
}

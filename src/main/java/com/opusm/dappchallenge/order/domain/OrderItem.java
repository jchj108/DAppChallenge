package com.opusm.dappchallenge.order.domain;

import com.opusm.dappchallenge.item.domain.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column
    private Long quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private Order order;
    @OneToMany(mappedBy = "orderItem", fetch = FetchType.LAZY)
    private List<Item> itemList = new ArrayList<Item>();

    public OrderItem(Long id, Long quantity, Order order, List<Item> itemList) {
        Id = id;
        this.quantity = quantity;
        this.order = order;
        this.itemList = itemList;
    }
}

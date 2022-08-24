package com.opusm.dappchallenge.order.domain;

import com.opusm.dappchallenge.item.domain.Item;
import com.opusm.dappchallenge.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;
    @Column
    private LocalDateTime orderDate;
    @OneToMany(fetch = FetchType.LAZY)
    private List<OrderItem> itemList = new ArrayList<>();

    public Order(Long orderId, User user, LocalDateTime orderDate, List<OrderItem> itemList) {
        this.orderId = orderId;
        this.user = user;
        this.orderDate = orderDate;
        this.itemList = itemList;
    }
}

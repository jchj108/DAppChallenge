package com.opusm.dappchallenge.order.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.opusm.dappchallenge.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@NoArgsConstructor
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @JsonBackReference
    private User user;
    @Column
    private LocalDateTime orderDate;
    @Setter
    @JsonManagedReference
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList = new ArrayList<>();

    public Order(Long orderId, User user, LocalDateTime orderDate, List<OrderItem> orderItemList) {
        this.orderId = orderId;
        this.user = user;
        this.orderDate = orderDate;
        this.orderItemList = orderItemList;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItemList.add(orderItem);
        orderItem.setOrder(this);
    }

    public static Order createOrder(User user, OrderItem... orderItems) {
        Order order = Order.builder()
            .user(user)
            .orderDate(LocalDateTime.now())
            .orderItemList(new ArrayList<>())
            .build();
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }

        return order;
    }
}

package com.opusm.dappchallenge.order.dto;

import com.opusm.dappchallenge.order.domain.Order;
import com.opusm.dappchallenge.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class OrderRes {
    private Long orderId;
    private LocalDateTime orderDate;
    private String userId;
    private Long currentPoint;
    private Long currentAsserts;
    private long earnPoint;
    private Long orderItemId;
    private User user;

    public OrderRes(Order order, Long earnPoint) {
        this.orderId = order.getOrderId();
        this.orderDate = order.getOrderDate();
        this.user = order.getUser();
        this.earnPoint = earnPoint;
    }
}

package com.opusm.dappchallenge.order.dto;

import com.opusm.dappchallenge.order.domain.PayType;
import lombok.Getter;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class OrderReq {

    @NotNull
    private String userId;
    private LocalDateTime orderDate;
    @NotNull
    private Long orderPrice;
    @NotNull
    @Enumerated
    private PayType payType;
    private Long itemId;
    private Long quantity;

    public OrderReq(String userId, LocalDateTime orderDate, Long orderPrice, PayType payType, Long itemId, Long quantity) {
        this.userId = userId;
        this.orderDate = orderDate;
        this.orderPrice = orderPrice;
        this.payType = payType;
        this.itemId = itemId;
        this.quantity = quantity;
    }
}

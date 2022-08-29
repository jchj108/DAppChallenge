package com.opusm.dappchallenge.order.controller;

import com.opusm.dappchallenge.common.dto.DataResponseDto;
import com.opusm.dappchallenge.order.dto.OrderReq;
import com.opusm.dappchallenge.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping(path = "/order")
    public DataResponseDto<Object> order(@Validated OrderReq orderReq) throws Exception {

        return DataResponseDto.of(orderService.order(orderReq));
    }
}

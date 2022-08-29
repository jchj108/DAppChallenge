package com.opusm.dappchallenge.order.service;

import com.opusm.dappchallenge.common.constant.Code;
import com.opusm.dappchallenge.config.GeneralException;
import com.opusm.dappchallenge.item.domain.Item;
import com.opusm.dappchallenge.item.repository.ItemRepository;
import com.opusm.dappchallenge.order.domain.Order;
import com.opusm.dappchallenge.order.domain.OrderItem;
import com.opusm.dappchallenge.order.domain.PayType;
import com.opusm.dappchallenge.order.dto.OrderReq;
import com.opusm.dappchallenge.order.dto.OrderRes;
import com.opusm.dappchallenge.order.repository.OrderRepository;
import com.opusm.dappchallenge.user.domain.User;
import com.opusm.dappchallenge.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    @Transactional
    public OrderRes order(OrderReq orderReq) {
        Item item = itemRepository.findById(orderReq.getItemId())
                .orElseThrow(() -> new GeneralException(Code.NOT_FOUND, "아이템이 없습니다."));
        User user = userRepository.findById(orderReq.getUserId())
                .orElseThrow(() -> new GeneralException(Code.NOT_FOUND, "유저가 없습니다."));

        long earnPoint = 0;
        Long cost = item.getPrice() * orderReq.getQuantity();

        if(orderReq.getOrderPrice() < cost) {
            throw new GeneralException(Code.BAD_REQUEST, "상품 금액에 비해 지불 금액이 적습니다.");
        }
        if(orderReq.getPayType() == PayType.CASH) {
            if (user.getAsserts() < cost) {
                throw new GeneralException(Code.BAD_REQUEST, "돈이 모자랍니다");
            }
            Long prevPoint = user.getPoint();
            earnPoint = (long)(item.getPointRate() * cost / 100);
            user.setAsserts(user.getAsserts() - cost);
            user.setPoint(prevPoint + earnPoint);
        }
        if(orderReq.getPayType() == PayType.POINT) {
            if (user.getPoint() < cost) {
                throw new GeneralException(Code.BAD_REQUEST, "포인트가 모자랍니다");
            }
            user.setPoint(user.getAsserts() - cost);
        }

        OrderItem orderItem = OrderItem.createOrderItem(item, orderReq.getQuantity(), item.getPrice());
        Order order = Order.createOrder(user, orderItem);

        orderRepository.save(order);

        return new OrderRes(order, earnPoint);
    }
}

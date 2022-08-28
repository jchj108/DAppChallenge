package com.opusm.dappchallenge.order.repository;

import com.opusm.dappchallenge.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OrderRepository extends JpaRepository<Order, Long> {

}
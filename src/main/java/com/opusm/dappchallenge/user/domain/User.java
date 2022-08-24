package com.opusm.dappchallenge.user.domain;

import com.opusm.dappchallenge.order.domain.Order;
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
public class User {
    @Id
    private Long userId;
    @Column
    private String password;
    @Column
    private Long asserts;
    @Column
    private Long point;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orderList = new ArrayList<Order>();

    public User(Long userId, String password, Long asserts, Long point, List<Order> orderList) {
        this.userId = userId;
        this.password = password;
        this.asserts = asserts;
        this.point = point;
        this.orderList = orderList;
    }
}

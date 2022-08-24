package com.opusm.dappchallenge.user.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class User {
    @Id
    private Long userId;
    @Column
    private String password;
    @Column
    private Long asserts;
    @Column
    private Long point;
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<Order> orderList = new ArrayList<Order>();
//
//    public User(Long userId, String password, Long asserts, Long point, List<Order> orderList) {
//        this.userId = userId;
//        this.password = password;
//        this.asserts = asserts;
//        this.point = point;
//        this.orderList = orderList;
//    }
}

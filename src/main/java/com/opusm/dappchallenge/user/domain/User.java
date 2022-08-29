package com.opusm.dappchallenge.user.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.opusm.dappchallenge.order.domain.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@NoArgsConstructor
@Table(name = "Users")
public class User {
    @Id
    private String userId;
    @Column
    private String password;
    @Column
    @ColumnDefault("0")
    @Setter
    private Long asserts;

    @Column
    @ColumnDefault("0")
    @Setter
    private Long point;
    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orderList = new ArrayList<Order>();

    public User(String userId, String password, Long asserts, Long point, List<Order> orderList) {
        this.userId = userId;
        this.password = password;
        this.asserts = asserts;
        this.point = point;
        this.orderList = orderList;
    }

    @PrePersist
    public void prePersist() {
        this.asserts = this.asserts == null ? 0 : this.asserts;
        this.point = this.point == null ? 0 : this.point;
    }
}

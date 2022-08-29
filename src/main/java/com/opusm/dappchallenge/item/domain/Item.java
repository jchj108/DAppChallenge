package com.opusm.dappchallenge.item.domain;

import com.opusm.dappchallenge.common.constant.Code;
import com.opusm.dappchallenge.config.GeneralException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder
@Entity
@Getter
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    @NotNull
    private String name;
    @Column
    private Long price;
    @Column
    @ColumnDefault("0")
    private Double pointRate;
    @Column
    private Long amount;
    @Column
    @NotNull
    private String owner;

    public Item(Long itemId, String name, Long price, Double pointRate, Long amount, String owner) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.pointRate = pointRate;
        this.amount = amount;
        this.owner = owner;
    }

    public void removeStock(Long quantity) {
        Long restStock = this.amount - quantity;
        if (restStock < 0) {
            throw new GeneralException(Code.BAD_REQUEST, "재고가 부족합니다");
        }
        this.amount = restStock;
    }

    @PrePersist
    public void prePersist() {
        this.pointRate = this.pointRate == null ? 0 : this.pointRate;
    }
}

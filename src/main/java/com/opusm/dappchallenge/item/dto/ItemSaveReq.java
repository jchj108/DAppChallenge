package com.opusm.dappchallenge.item.dto;

import com.opusm.dappchallenge.item.domain.Item;
import lombok.Getter;

import javax.validation.constraints.NotNull;
@Getter
public class ItemSaveReq {

    @NotNull
    private String name;
    @NotNull
    private Long price;
    private Double pointRate;
    @NotNull
    private Long amount;
    @NotNull
    private String owner;

    public ItemSaveReq(String name, Long price, Double pointRate, Long amount, String owner) {
        this.name = name;
        this.price = price;
        this.pointRate = pointRate;
        this.amount = amount;
        this.owner = owner;
    }

    public Item toEntity() {
        return Item.builder()
                .name(this.name)
                .price(this.price)
                .pointRate(this.pointRate)
                .amount(this.amount)
                .owner(this.owner)
                .build();
    }
}

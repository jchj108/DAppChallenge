package com.opusm.dappchallenge.item.dto;

import com.opusm.dappchallenge.item.domain.Item;
import lombok.Getter;

@Getter
public class ItemRes {

    private Long itemId;
    private String name;
    private Long price;
    private Double pointRate;
    private Long amount;
    private String owner;

    public ItemRes(Item item) {
        this.itemId = item.getItemId();
        this.name = item.getName();
        this.amount = item.getAmount();
        this.owner = item.getOwner();
        this.price = item.getPrice();
        this.pointRate = item.getPointRate();
    }
}

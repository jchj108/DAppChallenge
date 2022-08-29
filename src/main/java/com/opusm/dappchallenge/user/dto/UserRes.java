package com.opusm.dappchallenge.user.dto;

import com.opusm.dappchallenge.user.domain.User;
import lombok.Getter;

@Getter
public class UserRes {

    private String userId;
    private Long asserts;
    private Long point;

    public UserRes(User user) {
        this.userId = user.getUserId();
        this.asserts = user.getAsserts();
        this.point = user.getPoint();
    }
}

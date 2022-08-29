package com.opusm.dappchallenge.user.dto;

import com.opusm.dappchallenge.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
public class UserSignUpReq {
    @NotEmpty
    private String userId;

    @NotEmpty
    private String password;

    private Long asserts;

    private Long point;

    public UserSignUpReq(String userId, String password, Long asserts, Long point) {
        this.userId = userId;
        this.password = password;
        this.asserts = asserts;
        this.point = point;
    }

    public User toEntity() {
        return User.builder()
                .userId(this.userId)
                .password(this.password)
                .asserts(this.asserts)
                .point(this.point)
                .build();
    }
}

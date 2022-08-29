package com.opusm.dappchallenge.user.controller;

import com.opusm.dappchallenge.common.constant.Code;
import com.opusm.dappchallenge.common.dto.DataResponseDto;
import com.opusm.dappchallenge.config.GeneralException;
import com.opusm.dappchallenge.user.domain.User;
import com.opusm.dappchallenge.user.dto.UserRes;
import com.opusm.dappchallenge.user.dto.UserSignUpReq;
import com.opusm.dappchallenge.user.repository.UserRepository;
import com.opusm.dappchallenge.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
public class UserController {
    private final UserService userService;

    private final UserRepository userRepository;

    @PostMapping(path = "/signUp")
    public DataResponseDto<Object> signUp(@Valid UserSignUpReq userSignUpReq) throws Exception {

        userRepository.findById(userSignUpReq.getUserId())
                .ifPresent(m -> {
                    throw new GeneralException(Code.VALIDATION_ERROR, "유저 아이디가 이미 존재합니다.");
                });;
        return DataResponseDto.of(new UserRes(userRepository.save(userSignUpReq.toEntity())));
    }

    @GetMapping(path = "/{userId}")
    public DataResponseDto<Object> findById(@PathVariable("userId") String userId) throws Exception {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(Code.NOT_FOUND, "유저가 없습니다."));

        return DataResponseDto.of(new UserRes(user));
    }

    @GetMapping(path = "")
    public DataResponseDto<Object> findAll() throws Exception {
        List<User> userList = userRepository.findAll();
        if (userList.size() == 0) {
            throw new GeneralException(Code.NOT_FOUND, "유저가 없습니다");
        }
        List<UserRes> resList = new ArrayList<>();
        for (User user : userList) {
            resList.add(new UserRes(user));
        }
        return DataResponseDto.of(resList);
    }
}

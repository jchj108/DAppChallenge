package com.opusm.dappchallenge.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opusm.dappchallenge.user.domain.User;
import com.opusm.dappchallenge.user.dto.UserSignUpReq;
import com.opusm.dappchallenge.user.repository.UserRepository;
import com.opusm.dappchallenge.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
class UserControllerTest {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mvc;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("회원가입 테스트")
    void signUpTest() {
        // given
        UserSignUpReq userSignUpReq = UserSignUpReq.builder().userId("test").password("1234").build();

        // when
        User user = userRepository.save(userSignUpReq.toEntity());

        // then
        assertThat(user.getUserId()).isEqualTo(userSignUpReq.getUserId());
    }
}
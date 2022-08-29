package com.opusm.dappchallenge.user.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("유저를 찾을 수 없습니다.");
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}

package utils;

import lombok.Getter;

@Getter
public enum StatusCode {
    OK(200),
    CREATED(201),
    NOT_FOUND(404),
    PARTIAL_RESPONSE(206),
    UNAUTHORIZED(401);


    private final int code;

    StatusCode(int code) {
        this.code = code;
    }

}


package ru.pycak.todolistapp.response;

import lombok.Data;

@Data
public final class BaseResponse<Body> {

    private final boolean success;
    private final Body body;
    private final String message;

    public static <Body> BaseResponse<Body> success(Body body, String message) {
        return new BaseResponse<>(true, body, message);
    }

    public static <Body> BaseResponse<Body> error(Body body, String message) {
        return new BaseResponse<>(false, body, message);
    }
}

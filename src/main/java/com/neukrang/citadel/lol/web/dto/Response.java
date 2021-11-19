package com.neukrang.citadel.lol.web.dto;

import lombok.Getter;

@Getter
public class Response<T> {

    private final boolean success;
    private final T data;
    private final String error;

    private Response(boolean isSuccess, T data, String error) {
        this.success = isSuccess;
        this.data = data;
        this.error = error;
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(true, data, "");
    }

    public static Response fail(String errorMessage) {
        return new Response(false, "", errorMessage);
    }
}

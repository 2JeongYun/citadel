package com.neukrang.citadel.lol.web.dto;

import lombok.Getter;

@Getter
public class ApiResponse<T> {

    private final boolean success;
    private final T data;
    private final String error;

    private ApiResponse(boolean isSuccess, T data, String error) {
        this.success = isSuccess;
        this.data = data;
        this.error = error;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, "");
    }

    public static ApiResponse fail(String errorMessage) {
        return new ApiResponse(false, "", errorMessage);
    }
}

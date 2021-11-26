package com.neukrang.citadel.aspect;

import com.neukrang.citadel.util.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    private ResponseEntity<ApiResponse<?>> makeErrorResponse(String message, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<ApiResponse<?>>(ApiResponse.fail(message), headers, status);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ApiResponse<?>> handleNotFoundError(Exception e) {
        log.error("Error - Not Found", e.getMessage(), e);

        return makeErrorResponse("Not Found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse<?>> handleError(Exception e) {
        log.error("Error - ", e.getMessage(), e);

        return makeErrorResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

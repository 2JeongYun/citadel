package com.neukrang.citadel.config.security.apikey;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class EmailVerificationCode {

    private String email;
    private String code;
    private LocalDateTime expiredDateTime;

    public boolean isExpired(LocalDateTime currentTime) {
        if (currentTime.isAfter(expiredDateTime))
            return true;
        return false;
    }
}

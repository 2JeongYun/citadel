package com.neukrang.citadel.config.security.apikey;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
public class ApiKey {

    public ApiKey() {};
    public ApiKey(String key, String email) {
        this.key = key;
        this.email = email;
    }

    @Id
    private String key;

    @Column(nullable = false)
    private String email;
}

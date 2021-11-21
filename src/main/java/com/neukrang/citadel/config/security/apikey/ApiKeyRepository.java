package com.neukrang.citadel.config.security.apikey;

import java.util.List;
import java.util.Optional;

public interface ApiKeyRepository {

    ApiKey save(ApiKey apiKey);
    List<ApiKey> findByEmail(String email);
    Optional<ApiKey> findByKey(String key);
    void remove(ApiKey apiKey);
}

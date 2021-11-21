package com.neukrang.citadel.config.security.apikey;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ApiKeyAuthManager implements AuthenticationManager {

    private final ApiKeyService apiKeyService;
    private final Set<String> keySet = ConcurrentHashMap.newKeySet();

    public ApiKeyAuthManager(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String key = (String) authentication.getPrincipal();

        if (!keySet.contains(key)) {
            Optional<ApiKey> apiKey = apiKeyService.findApiKeyByKey(key);
            if (!apiKey.isPresent())
                throw new BadCredentialsException("유효하지 않은 API KEY 입니다.");

            keySet.add(apiKey.get().getKey());
        }
        authentication.setAuthenticated(true);
        return authentication;
    }
}

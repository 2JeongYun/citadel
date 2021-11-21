package com.neukrang.citadel.config.security.apikey;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.concurrent.TimeUnit;

public class ApiKeyAuthManager implements AuthenticationManager {

    private final ApiKeyService apiKeyService;
    private final LoadingCache<String, ApiKey> apiKeyCache;

    public ApiKeyAuthManager(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
        apiKeyCache = Caffeine.newBuilder()
                .expireAfterAccess(5, TimeUnit.MINUTES)
                .build(new ApiKeyCacheLoader(apiKeyService));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String key = (String) authentication.getPrincipal();

        if (apiKeyCache.get(key) == null)
            throw new BadCredentialsException("유효하지 않은 API KEY 입니다.");

        authentication.setAuthenticated(true);
        return authentication;
    }

    @RequiredArgsConstructor
    private static class ApiKeyCacheLoader implements CacheLoader<String, ApiKey> {

        private final ApiKeyService apiKeyService;

        @Override
        public @Nullable ApiKey load(@NonNull String key) throws Exception {
            return apiKeyService.findApiKeyByKey(key).orElse(null);
        }
    }
}

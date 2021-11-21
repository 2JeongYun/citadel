package com.neukrang.citadel.config.security;

import com.neukrang.citadel.config.security.apikey.ApiKeyAuthFilter;
import com.neukrang.citadel.config.security.apikey.ApiKeyAuthManager;
import com.neukrang.citadel.config.security.apikey.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ApiKeyService apiKeyService;

    @Bean
    public ApiKeyAuthFilter apiKeyAuthFilter() {
        ApiKeyAuthFilter apiKeyAuthFilter =
                new ApiKeyAuthFilter("Authorization");
        apiKeyAuthFilter.setAuthenticationManager(apiKeyAuthManager());
        return apiKeyAuthFilter;
    }

    @Bean
    public ApiKeyAuthManager apiKeyAuthManager() {
        return new ApiKeyAuthManager(apiKeyService);
    }

    @Bean
    public CustomAuthenticationEntryPoint customAuthenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authorizeRequests()
                    .antMatchers("/api/v1/apikey/**").anonymous()
                    .antMatchers("/**").authenticated()
                    .and()
                .exceptionHandling()
                    .authenticationEntryPoint(customAuthenticationEntryPoint())
                    .and()
                .addFilter(apiKeyAuthFilter())
                .formLogin()
                    .disable();
    }
}

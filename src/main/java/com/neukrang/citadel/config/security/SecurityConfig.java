package com.neukrang.citadel.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public ApiKeyAuthFilter apiKeyAuthFilter() {
        ApiKeyAuthFilter apiKeyAuthFilter =
                new ApiKeyAuthFilter("Authorization");
        apiKeyAuthFilter.setAuthenticationManager(apiKeyAuthManager());
        return apiKeyAuthFilter;
    }

    @Bean
    public ApiKeyAuthManager apiKeyAuthManager() {
        return new ApiKeyAuthManager(dataSource);
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

package com.neukrang.citadel.config;

import com.neukrang.citadel.util.ApiCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@RequiredArgsConstructor
@Configuration
@PropertySource("classpath:/application-private.properties")
public class Config {

    private final Environment env;

    @Bean
    public ApiCaller krRiotApiCaller() {
        String apiKey = env.getProperty("app.riot.api.key");
        String baseUrl = "https://kr.api.riotgames.com";
        return new ApiCaller(apiKey, baseUrl);
    }

    @Bean
    public ApiCaller asiaRiotApiCaller() {
        String apiKey = env.getProperty("app.riot.api.key");
        String baseUrl = "https://asia.api.riotgames.com";
        return new ApiCaller(apiKey, baseUrl);
    }

    @Bean
    public ApiCaller ddRiotApiCaller() {
        String apiKey = env.getProperty("app.riot.api.key");
        String baseUrl = "https://ddragon.leagueoflegends.com";
        return new ApiCaller(apiKey, baseUrl);
    }
}

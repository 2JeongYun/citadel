package com.neukrang.citadel.lol.riotapi;

import com.neukrang.citadel.lol.domain.summoner.Summoner;
import com.neukrang.citadel.util.ApiCaller;
import com.neukrang.citadel.util.MethodType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class SummonerApiCaller {

    private final ApiCaller krRiotApiCaller;
    private final String baseUrl = "/lol/summoner/v4/summoners";

    public Optional<Summoner> getSummonerByName(String name) {
        String url = baseUrl + "/by-name/";

        name = URLEncoder.encode(name, StandardCharsets.UTF_8);

        try {
            Optional<Summoner> summoner =
                    Optional.ofNullable(krRiotApiCaller.call(url + name, MethodType.GET, Summoner.class));
            return summoner;
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Summoner> getSummonerByPuuid(String puuid) {
        String url = baseUrl + "/by-puuid/";

        try {
            Optional<Summoner> summoner =
                    Optional.ofNullable(krRiotApiCaller.call(url + puuid, MethodType.GET, Summoner.class));
            return summoner;
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}

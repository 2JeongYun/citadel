package com.neukrang.citadel.lol.riotapi;

import com.neukrang.citadel.lol.domain.summoner.Summoner;
import com.neukrang.citadel.util.ApiCaller;
import com.neukrang.citadel.util.MethodType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@Component
public class SummonerApiCaller {

    private final ApiCaller riotApiCaller;
    private final String baseUrl = "/lol/summoner/v4/summoners";

    public Summoner getSummonerByName(String name) {
        String url = baseUrl + "/by-name/";

        name = URLEncoder.encode(name, StandardCharsets.UTF_8);
        name.replaceAll("\\+", "%2B");
        
        Summoner summoner = riotApiCaller.call(url + name, MethodType.GET, Summoner.class);

        return summoner;
    }
}

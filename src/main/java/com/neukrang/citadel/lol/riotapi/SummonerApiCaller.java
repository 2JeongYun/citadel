package com.neukrang.citadel.lol.riotapi;

import com.neukrang.citadel.lol.domain.Summoner;
import com.neukrang.citadel.util.ApiCaller;
import com.neukrang.citadel.util.MethodType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Slf4j
@RequiredArgsConstructor
@Component
public class SummonerApiCaller {

    private final ApiCaller riotApiCaller;
    private final String baseUrl = "/lol/summoner/v4/summoners";

    public Summoner getSummonerByName(String name) {
        String url = baseUrl + "/by-name/";

        try {
            name = URLEncoder.encode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("%s을 URL encode 하는데 실패했습니다.", name);
        }
        
        Summoner summoner = riotApiCaller.call(url + name, MethodType.GET, Summoner.class);

        return summoner;
    }
}

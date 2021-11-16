package com.neukrang.citadel.lol.riotapi;

import com.neukrang.citadel.lol.domain.LeagueInfo;
import com.neukrang.citadel.util.ApiCaller;
import com.neukrang.citadel.util.MethodType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class LeagueApiCaller {

    private final ApiCaller riotApiCaller;
    private final String baseUrl = "/lol/league/v4";

    public List<LeagueInfo> getLeagueInfoList(String encryptedSummonerId) {
        String url = baseUrl + "/entries/by-summoner/" + encryptedSummonerId;

        LeagueInfo[] leagueInfoArr = riotApiCaller.call(url, MethodType.GET, LeagueInfo[].class);

        return Arrays.asList(leagueInfoArr);
    }
}

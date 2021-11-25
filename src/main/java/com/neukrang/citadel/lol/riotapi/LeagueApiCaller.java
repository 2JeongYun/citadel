package com.neukrang.citadel.lol.riotapi;

import com.neukrang.citadel.lol.domain.QueueType;
import com.neukrang.citadel.lol.domain.league.LeagueInfo;
import com.neukrang.citadel.lol.domain.summoner.Summoner;
import com.neukrang.citadel.util.ApiCaller;
import com.neukrang.citadel.util.MethodType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class LeagueApiCaller {

    private final ApiCaller krRiotApiCaller;
    private final String baseUrl = "/lol/league/v4";

    public List<LeagueInfo> getLeagueInfoList(Summoner summoner) {
        String url = baseUrl + "/entries/by-summoner/" + summoner.getId();

        LeagueInfo[] leagueInfoArr = krRiotApiCaller.call(url, MethodType.GET, LeagueInfo[].class);
        List<LeagueInfo> leagueInfoList = Arrays.stream(leagueInfoArr)
                .filter(leagueInfo -> leagueInfo.getQueueType() != QueueType.UNKNOWN)
                .collect(Collectors.toList());

        leagueInfoList.stream().forEach(leagueInfo -> leagueInfo.setSummoner(summoner));
        return leagueInfoList;
    }
}

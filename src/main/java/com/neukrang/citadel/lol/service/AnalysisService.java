package com.neukrang.citadel.lol.service;

import com.neukrang.citadel.lol.domain.league.LeagueInfo;
import com.neukrang.citadel.lol.domain.summoner.Summoner;
import com.neukrang.citadel.lol.riotapi.ChampionMasteryApiCaller;
import com.neukrang.citadel.lol.riotapi.dto.ChampionMasteryDto;
import com.neukrang.citadel.lol.web.dto.SimpleProfileResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AnalysisService {

    private final LeagueInfoService leagueInfoService;
    private final SummonerService summonerService;
    private final ChampionMasteryApiCaller championMasteryApiCaller;
    private final Analyzer analyzer;

    @Transactional
    public SimpleProfileResponseDto makeSimpleProfile(String name) {
        Summoner summoner = summonerService.findByName(name);
        List<LeagueInfo> leagueInfoList = leagueInfoService.findBySummoner(summoner);
        List<ChampionMasteryDto> championMasteryDtoList =
                championMasteryApiCaller.getChampionMasteryList(summoner);

        return analyzer.makeSimpleProfile(summoner, leagueInfoList, championMasteryDtoList);
    }
}
package com.neukrang.citadel.lol.service;

import com.neukrang.citadel.lol.domain.league.LeagueInfo;
import com.neukrang.citadel.lol.domain.league.LeagueInfoRepository;
import com.neukrang.citadel.lol.domain.summoner.Summoner;
import com.neukrang.citadel.lol.riotapi.LeagueApiCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LeagueInfoService {

    private final LeagueInfoRepository leagueInfoRepository;
    private final LeagueApiCaller leagueApiCaller;

    @Transactional
    public List<LeagueInfo> findBySummoner(Summoner summoner) {
        List<LeagueInfo> leagueInfoList = leagueInfoRepository.findBySummoner(summoner);
        if (leagueInfoList.size() > 0)
            return leagueInfoList;

        leagueInfoList = leagueApiCaller.getLeagueInfoList(summoner);
        leagueInfoList.stream()
                .forEach(leagueInfo -> leagueInfoRepository.save(leagueInfo));
        return leagueInfoList;
    }
}

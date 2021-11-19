package com.neukrang.citadel.lol.domain.league;

import com.neukrang.citadel.lol.domain.summoner.Summoner;

import java.util.List;

public interface LeagueInfoRepository {

    Long save(LeagueInfo leagueInfo);
    List<LeagueInfo> findBySummoner(Summoner summoner);
}

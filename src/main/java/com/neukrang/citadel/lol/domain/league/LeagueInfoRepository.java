package com.neukrang.citadel.lol.domain.league;

public interface LeagueInfoRepository {

    Long save(LeagueInfo leagueInfo);
    LeagueInfo find(Long id);
}

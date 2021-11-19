package com.neukrang.citadel.lol.domain.league;

import java.util.Optional;

public interface LeagueInfoRepository {

    Long save(LeagueInfo leagueInfo);
    Optional<LeagueInfo> find(Long id);
}

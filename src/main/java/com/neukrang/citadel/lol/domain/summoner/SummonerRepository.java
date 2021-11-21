package com.neukrang.citadel.lol.domain.summoner;

import java.util.Optional;

public interface SummonerRepository {

    Summoner save(Summoner summoner);
    Optional<Summoner> findByPuuid(String puuid);
    Optional<Summoner> findByName(String name);
    void remove(Summoner summoner);
}

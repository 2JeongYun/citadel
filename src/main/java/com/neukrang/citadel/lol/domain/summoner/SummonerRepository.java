package com.neukrang.citadel.lol.domain.summoner;

import java.util.Optional;

public interface SummonerRepository {

    String save(Summoner summoner);
    Optional<Summoner> find(String puuid);
}

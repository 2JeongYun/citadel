package com.neukrang.citadel.lol.domain.summoner;

public interface SummonerRepository {

    String save(Summoner summoner);
    Summoner find(String puuid);
}

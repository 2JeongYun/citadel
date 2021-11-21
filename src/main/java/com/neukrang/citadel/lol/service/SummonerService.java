package com.neukrang.citadel.lol.service;

import com.neukrang.citadel.lol.domain.summoner.Summoner;
import com.neukrang.citadel.lol.domain.summoner.SummonerRepository;
import com.neukrang.citadel.lol.riotapi.SummonerApiCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SummonerService {

    private final SummonerRepository summonerRepository;
    private final SummonerApiCaller summonerApiCaller;

    @Transactional
    public Summoner findByName(String name) {
        Optional<Summoner> summoner = summonerRepository.findByName(name);
        if (summoner.isPresent())
            return summoner.get();

        summoner = updateSummonerByRiot(name);
        if (summoner.isPresent())
            return summoner.get();

        throw new IllegalArgumentException(name + " 소환사를 찾을 수 없습니다.");
    }

    @Transactional
    public Optional<Summoner> updateSummonerByRiot(String name) {
        Optional<Summoner> summoner = summonerApiCaller.getSummonerByName(name);
        if (summoner.isPresent())
            summonerRepository.save(summoner.get());
        return summoner;
    }
}

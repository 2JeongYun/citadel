package com.neukrang.citadel.lol.service;

import com.neukrang.citadel.lol.domain.summoner.Summoner;
import com.neukrang.citadel.lol.domain.summoner.SummonerRepository;
import com.neukrang.citadel.lol.riotapi.SummonerApiCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SummonerService {

    private final SummonerRepository summonerRepository;
    private final SummonerApiCaller summonerApiCaller;

    @Value("${citadel.update-period.day}")
    private int updatePeriod;

    @Transactional
    public Summoner findByName(String name) {
        Optional<Summoner> summoner = summonerRepository.findByName(name);
        if (summoner.isPresent()) {
            if (summoner.get().needToUpdate(updatePeriod))
                summoner = updateSummonerByRiot(summoner.get());
        } else {
            summoner = updateSummonerByRiot(name);
        }

        return summoner
                .orElseThrow(() -> new IllegalArgumentException(name + " 소환사를 찾을 수 없습니다."));
    }

    @Transactional
    public Optional<Summoner> updateSummonerByRiot(String name) {
        Optional<Summoner> summoner = summonerApiCaller.getSummonerByName(name);
        if (summoner.isPresent())
            summonerRepository.save(summoner.get());
        return summoner;
    }

    @Transactional
    public Optional<Summoner> updateSummonerByRiot(Summoner prev) {
        Optional<Summoner> summoner = summonerApiCaller.getSummonerByName(prev.getName());
        if (summoner.isPresent())
            summonerRepository.save(summoner.get());
        else
            summonerRepository.remove(prev);
        return summoner;
    }
}

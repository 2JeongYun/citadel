package com.neukrang.citadel.lol.domain.summoner;

import com.neukrang.citadel.lol.riotapi.SummonerApiCaller;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class SummonerRepositoryTest {

    @Autowired
    SummonerApiCaller summonerApiCaller;

    @Autowired
    SummonerRepository summonerRepository;

    @Test
    @Transactional
    public void saveSummoner() {
        Summoner summoner = summonerApiCaller.getSummonerByName("고급 참치캔");

        String puuid = summonerRepository.save(summoner);
        Summoner foundedSummoner = summonerRepository.find(puuid);

        Assertions.assertThat(foundedSummoner)
                .usingRecursiveComparison()
                .isEqualTo(summoner);
    }
}
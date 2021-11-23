package com.neukrang.citadel.lol.domain.summoner;

import com.neukrang.citadel.util.lol.TestUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class SummonerRepositoryTest {

    @Autowired
    SummonerRepository summonerRepository;

    @Test
    @Transactional
    public void saveSummoner() {
        Summoner summoner = TestUtil.getTestSummoner();

        String puuid = summonerRepository.save(summoner).getPuuid();
        Summoner foundedSummoner = summonerRepository.findByPuuid(puuid).get();

        Assertions.assertThat(foundedSummoner)
                .usingRecursiveComparison()
                .isEqualTo(summoner);
    }
}
package com.neukrang.citadel.lol.domain.league;

import com.neukrang.citadel.lol.domain.summoner.Summoner;
import com.neukrang.citadel.lol.domain.summoner.SummonerRepository;
import com.neukrang.citadel.util.lol.TestUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class LeagueInfoRepositoryTest {

    @Autowired
    LeagueInfoRepository leagueInfoRepository;

    @Autowired
    SummonerRepository summonerRepository;

    @Test
    @Transactional
    public void saveLeagueInfo() {
        Summoner summoner = TestUtil.getTestSummoner();
        summonerRepository.save(summoner);

        List<LeagueInfo> leagueInfoList = TestUtil.getTestLeagueInfo(summoner);

        leagueInfoList.stream()
                .forEach(leagueInfo -> leagueInfoRepository.save(leagueInfo));

        List<LeagueInfo> foundedLeagueInfoList =
                leagueInfoRepository.findBySummoner(summoner);
        Assertions.assertThat(foundedLeagueInfoList)
                .containsAll(leagueInfoList);
    }
}
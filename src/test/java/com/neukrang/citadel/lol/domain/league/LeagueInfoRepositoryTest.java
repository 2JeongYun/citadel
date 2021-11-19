package com.neukrang.citadel.lol.domain.league;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neukrang.citadel.TestUtil;
import com.neukrang.citadel.lol.domain.summoner.Summoner;
import com.neukrang.citadel.lol.domain.summoner.SummonerRepository;
import com.neukrang.citadel.lol.riotapi.LeagueApiCaller;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class LeagueInfoRepositoryTest {

    @Autowired
    LeagueApiCaller leagueApiCaller;

    @Autowired
    LeagueInfoRepository leagueInfoRepository;

    @Autowired
    SummonerRepository summonerRepository;

    ObjectMapper om = new ObjectMapper();

    @Test
    @Transactional
    public void saveLeagueInfo() throws JsonProcessingException {
        String json = TestUtil.fileToString("./src/test/java/com/neukrang/citadel/lol/SummonerTestJson.txt");
        Summoner summoner = om.readValue(json, Summoner.class);
        summonerRepository.save(summoner);

        List<LeagueInfo> leagueInfoList = leagueApiCaller.getLeagueInfoList(summoner);

        leagueInfoList.stream()
                .forEach(leagueInfo -> leagueInfoRepository.save(leagueInfo));

        List<LeagueInfo> foundedLeagueInfoList =
                leagueInfoRepository.findBySummoner(summoner);
        Assertions.assertThat(foundedLeagueInfoList)
                .containsAll(leagueInfoList);
    }
}
package com.neukrang.citadel.lol.domain.league;

import com.neukrang.citadel.lol.domain.summoner.Summoner;
import com.neukrang.citadel.lol.domain.summoner.SummonerRepository;
import com.neukrang.citadel.lol.riotapi.LeagueApiCaller;
import com.neukrang.citadel.lol.riotapi.SummonerApiCaller;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class LeagueInfoRepositoryTest {

    @Autowired
    LeagueApiCaller leagueApiCaller;

    @Autowired
    LeagueInfoRepository leagueInfoRepository;

    @Autowired
    SummonerApiCaller summonerApiCaller;

    @Autowired
    SummonerRepository summonerRepository;

    @Test
    @Transactional
    public void saveLeagueInfo() {
        Summoner summoner = summonerApiCaller.getSummonerByName("헝 꾸");
        summonerRepository.save(summoner);

        List<LeagueInfo> leagueInfoList = leagueApiCaller.getLeagueInfoList(summoner);
        List<Long> infoIdList = new ArrayList<>();

        for (LeagueInfo leagueInfo : leagueInfoList) {
            Long id = leagueInfoRepository.save(leagueInfo);
            infoIdList.add(id);
        }

        for (int i = 0; i < infoIdList.size(); i++) {
            Long id = infoIdList.get(i);
            LeagueInfo leagueInfo = leagueInfoRepository.find(id).get();
            Assertions.assertThat(leagueInfo)
                    .usingRecursiveComparison()
                    .isEqualTo(leagueInfoList.get(i));
        }
    }
}
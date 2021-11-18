package com.neukrang.citadel.lol.riotapi;

import com.neukrang.citadel.lol.domain.QueueType;
import com.neukrang.citadel.lol.domain.Tier;
import com.neukrang.citadel.lol.domain.league.LeagueInfo;
import com.neukrang.citadel.lol.domain.summoner.Summoner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LeagueApiCallerTest {

    @Autowired
    SummonerApiCaller summonerApiCaller;

    @Autowired
    LeagueApiCaller leagueApiCaller;

    @Test
    void getLeagueInfoList() {
        Summoner summoner = summonerApiCaller.getSummonerByName("헝 꾸");
        List<LeagueInfo> leagueInfoList = leagueApiCaller.getLeagueInfoList(summoner);
        LeagueInfo flexInfo = null, soloInfo = null;
        if (leagueInfoList.get(0).getQueueType() == QueueType.FLEX) {
            flexInfo = leagueInfoList.get(0);
            soloInfo = leagueInfoList.get(1);
        } else {
            flexInfo = leagueInfoList.get(1);
            soloInfo = leagueInfoList.get(0);
        }

        assertThat(leagueInfoList).hasSize(2);
        assertThat(flexInfo.getTier()).isEqualTo(Tier.PLATINUM);
        assertThat(flexInfo.getRank()).isEqualTo(2);
        assertThat(soloInfo.getTier()).isEqualTo(Tier.PLATINUM);
        assertThat(soloInfo.getRank()).isEqualTo(3);
    }
}
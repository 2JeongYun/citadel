package com.neukrang.citadel.lol.riotapi;

import com.neukrang.citadel.config.Config;
import com.neukrang.citadel.lol.domain.league.LeagueInfo;
import com.neukrang.citadel.lol.domain.summoner.Summoner;
import com.neukrang.citadel.util.lol.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {Config.class, LeagueApiCaller.class})
class LeagueApiCallerTest {

    @Autowired
    LeagueApiCaller leagueApiCaller;

    @Test
    void getLeagueInfoList() {
        Summoner summoner = TestUtil.getTestSummoner();
        List<LeagueInfo> leagueInfoList = leagueApiCaller.getLeagueInfoList(summoner);

        assertThat(leagueInfoList).isNotNull();
    }
}
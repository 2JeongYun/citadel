package com.neukrang.citadel.lol.riotapi;

import com.neukrang.citadel.lol.domain.LeagueInfo;
import com.neukrang.citadel.lol.domain.QueueType;
import com.neukrang.citadel.lol.domain.Tier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LeagueApiCallerTest {

    @Autowired
    LeagueApiCaller leagueApiCaller;

    // 헝 꾸
    final String summonerId = "G4t3d-nL3amIPAVm8uqyAotV54g54U7NMdY6rQ8WLwZCnw";

    @Test
    void getLeagueInfoList() {
        List<LeagueInfo> leagueInfoList = leagueApiCaller.getLeagueInfoList(summonerId);
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
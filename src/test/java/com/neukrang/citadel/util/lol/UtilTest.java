package com.neukrang.citadel.util.lol;

import com.neukrang.citadel.lol.domain.QueueType;
import com.neukrang.citadel.lol.domain.Tier;
import com.neukrang.citadel.lol.domain.league.LeagueInfo;
import com.neukrang.citadel.lol.domain.summoner.Summoner;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UtilTest {

    static final String MODIFIED_DATE = "2021-11-22T19:26:58.264603600";

    @Test
    public void getTestSummoner() {
        Summoner testObj = TestUtil.getTestSummoner("SummonerHUNGGU.json");

        Assertions.assertThat(testObj.getId())
                .isEqualTo("G4t3d-nL3amIPAVm8uqyAotV54g54U7NMdY6rQ8WLwZCnw");
        Assertions.assertThat(testObj.getPuuid())
                .isEqualTo("QTP5OsXxt-gzyR6TMC1fTgYR5yptIIHDCCSbMxvTzY8uEN49FVtGPNkWnosf1ITICFFpsdIDXWoA0w");
        Assertions.assertThat(testObj.getName())
                .isEqualTo("헝 꾸");
        Assertions.assertThat(testObj.getSummonerLevel())
                .isEqualTo(320);
        Assertions.assertThat(testObj.getModifiedDate())
                .isEqualTo(MODIFIED_DATE);
    }

    @Test
    public void getTestLeagueInfoList() {
        Summoner summoner = TestUtil.getTestSummoner("SummonerHUNGGU.json");
        List<LeagueInfo> testObj = TestUtil.getTestLeagueInfo("LeagueInfoHUNGGU.json", summoner);
        testObj.stream()
                .forEach(leagueInfo -> leagueInfo.setSummoner(summoner));

        LeagueInfo leagueInfoSoloRank = testObj.stream()
                .filter(leagueInfo -> leagueInfo.getQueueType() == QueueType.SOLO)
                .findFirst().get();
        LeagueInfo leagueInfoFlexRank = testObj.stream()
                .filter(leagueInfo -> leagueInfo.getQueueType() == QueueType.FLEX)
                .findFirst().get();
        LeagueInfo leagueInfoTftRank = testObj.stream()
                .filter(leagueInfo -> leagueInfo.getQueueType() == QueueType.UNKNOWN)
                .findFirst().get();

        testObj.stream()
                .forEach(leagueInfo ->
                {
                    Assertions.assertThat(leagueInfo.getSummoner()).isEqualTo(summoner);
                    Assertions.assertThat(leagueInfo.getModifiedDate()).isEqualTo(MODIFIED_DATE);
                });

        Assertions.assertThat(leagueInfoSoloRank).isNotNull();
        Assertions.assertThat(leagueInfoSoloRank.getTier()).isEqualTo(Tier.PLATINUM);
        Assertions.assertThat(leagueInfoSoloRank.getRank()).isEqualTo(3);

        Assertions.assertThat(leagueInfoFlexRank).isNotNull();
        Assertions.assertThat(leagueInfoFlexRank.getTier()).isEqualTo(Tier.PLATINUM);
        Assertions.assertThat(leagueInfoFlexRank.getRank()).isEqualTo(2);

        Assertions.assertThat(leagueInfoTftRank).isNotNull();
        Assertions.assertThat(leagueInfoTftRank.getWins()).isEqualTo(0);
        Assertions.assertThat(leagueInfoTftRank.getLosses()).isEqualTo(8);
    }
}

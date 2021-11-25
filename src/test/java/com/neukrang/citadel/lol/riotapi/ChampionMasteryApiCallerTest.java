package com.neukrang.citadel.lol.riotapi;

import com.neukrang.citadel.config.Config;
import com.neukrang.citadel.lol.domain.summoner.Summoner;
import com.neukrang.citadel.lol.riotapi.dto.ChampionMasteryDto;
import com.neukrang.citadel.util.lol.TestUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = {Config.class, ChampionMasteryApiCaller.class})
class ChampionMasteryApiCallerTest {

    @Autowired
    ChampionMasteryApiCaller championMasteryApiCaller;

    @Test
    void getChampionMasteryList() {
        final int GANGPLANK = 41;
        Summoner summoner = TestUtil.getTestSummoner();
        List<ChampionMasteryDto> championMasteryList =
                championMasteryApiCaller.getChampionMasteryList(summoner);

        ChampionMasteryDto neverZero = championMasteryList.stream()
                        .filter(mastery -> mastery.getChampionId() == GANGPLANK)
                                .findFirst().get();

        Assertions.assertThat(championMasteryList).isNotNull();
        Assertions.assertThat(neverZero.getChampionLevel()).isGreaterThan(3);
    }
}
package com.neukrang.citadel.lol.riotapi;

import com.neukrang.citadel.config.Config;
import com.neukrang.citadel.lol.domain.summoner.Summoner;
import com.neukrang.citadel.util.lol.TestUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest(classes = {Config.class, SummonerApiCaller.class})
class SummonerApiCallerTest {

    @Autowired
    SummonerApiCaller summonerApiCaller;

    @Test
    @DisplayName("이름으로 소환사 정보 Riot에서 가져오기")
    void getSummonerByName() {
        String name = "고급 참치캔";

        Summoner result = summonerApiCaller.getSummonerByName(name).get();

        Assertions.assertThat(result.getName()).isEqualTo("고급 참치캔");
    }

    @Test
    @DisplayName("없는 소환사 이름 Riot에서 가져올 시 빈 Optional 반환")
    void getSummonerByNotExistName() {
        String name = "고오오오급참칰1";

        Optional<Summoner> summoner = summonerApiCaller.getSummonerByName(name);

        Assertions.assertThat(summoner.isEmpty()).isEqualTo(true);
    }

    @Test
    void getSummonerByPuuid() {
        String puuid = TestUtil.getTestSummoner().getPuuid();

        Optional<Summoner> summoner = summonerApiCaller.getSummonerByPuuid(puuid);

        Assertions.assertThat(summoner).isNotNull();
        Assertions.assertThat(summoner.get().getPuuid()).isEqualTo(puuid);
    }
}
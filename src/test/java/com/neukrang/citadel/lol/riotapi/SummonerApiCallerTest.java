package com.neukrang.citadel.lol.riotapi;

import com.neukrang.citadel.lol.domain.Summoner;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SummonerApiCallerTest {

    @Autowired
    SummonerApiCaller summonerApiCaller;

    @Test
    void getSummonerByName() {
        String name = "고급 참치캔";

        Summoner result = summonerApiCaller.getSummonerByName(name);

        Assertions.assertThat(result.getName()).isEqualTo("고급 참치캔");
    }
}
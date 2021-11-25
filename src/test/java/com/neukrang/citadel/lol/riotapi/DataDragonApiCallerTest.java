package com.neukrang.citadel.lol.riotapi;

import com.neukrang.citadel.config.Config;
import com.neukrang.citadel.lol.domain.ChampionInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest(classes = {Config.class, DataDragonApiCaller.class})
class DataDragonApiCallerTest {

    @Autowired
    DataDragonApiCaller dataDragonApiCaller;

    @Test
    void getLatestVersion() {
        String version = dataDragonApiCaller.getLatestVersion();
        String mainVersion = version.substring(0, version.indexOf('.'));

        Assertions.assertThat(Integer.valueOf(mainVersion)).isGreaterThanOrEqualTo(11);
    }

    @Test
    void getChampionInfo() {
        ChampionInfo championInfo = dataDragonApiCaller.getChampionInfo();

        Map<String, ChampionInfo.Champion> data = championInfo.getData();

        Assertions.assertThat(data.size()).isGreaterThan(100);
    }
}
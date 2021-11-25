package com.neukrang.citadel.lol.riotapi;

import com.neukrang.citadel.lol.domain.ChampionInfo;
import com.neukrang.citadel.util.ApiCaller;
import com.neukrang.citadel.util.MethodType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataDragonApiCaller {

    private final ApiCaller ddRiotApiCaller;
    private String dataDragonVersion;

    @PostConstruct
    public void setVersion() {
        dataDragonVersion = getLatestVersion();
    }

    public String getLatestVersion() {
        String url = "/api/versions.json";

        String[] versions = ddRiotApiCaller.call(url, MethodType.GET, String[].class);
        return versions[0];
    }

    public ChampionInfo getChampionInfo() {
        String url = "/cdn/" + dataDragonVersion + "/data/en_US/champion.json";

        ChampionInfo championInfo =
                ddRiotApiCaller.call(url, MethodType.GET, ChampionInfo.class);

        return championInfo;
    }

    public String getDataDragonVersion() {
        return dataDragonVersion;
    }
}

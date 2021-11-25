package com.neukrang.citadel.lol.domain;

import com.neukrang.citadel.lol.riotapi.DataDragonApiCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class Converter {

    private final DataDragonApiCaller apiCaller;
    private static Map<String, Integer> championNameMap = new HashMap<>();
    private static Map<Integer, String> championNumberMap = new HashMap<>();

    @PostConstruct
    public void setUp() {
        ChampionInfo championInfo = apiCaller.getChampionInfo();
        Map<String, ChampionInfo.Champion> data = championInfo.getData();

        data.values().forEach(
                champion -> {
                    championNameMap.put(champion.getId(), champion.getKey());
                    championNumberMap.put(champion.getKey(), champion.getId());
                }
        );
    }

    public static Integer getChampionNumber(String name) {
        return championNameMap.get(name);
    }

    public static String getChampionName(Integer number) {
        return championNumberMap.get(number);
    }
}

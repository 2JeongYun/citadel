package com.neukrang.citadel.lol.riotapi;

import com.neukrang.citadel.lol.domain.summoner.Summoner;
import com.neukrang.citadel.lol.riotapi.dto.ChampionMasteryDto;
import com.neukrang.citadel.util.ApiCaller;
import com.neukrang.citadel.util.MethodType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class ChampionMasteryApiCaller {

    private final ApiCaller krRiotApiCaller;
    private final String baseUrl = "/lol/champion-mastery/v4/champion-masteries/by-summoner/";

    public List<ChampionMasteryDto> getChampionMasteryList(Summoner summoner) {
        String url = baseUrl + summoner.getId();

        ChampionMasteryDto[] championMasteryDtoArr =
                krRiotApiCaller.call(url, MethodType.GET, ChampionMasteryDto[].class);

        return Arrays.asList(championMasteryDtoArr);
    }

}

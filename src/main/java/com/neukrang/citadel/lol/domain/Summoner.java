package com.neukrang.citadel.lol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Summoner {

    private String puuid;
    private String name;
    private Long summonerLevel;
}

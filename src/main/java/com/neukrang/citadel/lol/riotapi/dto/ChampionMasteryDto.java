package com.neukrang.citadel.lol.riotapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChampionMasteryDto {

    private Long championId;
    private Long lastPlayTime;
    private Integer championLevel;
    private Integer championPoints;
}
